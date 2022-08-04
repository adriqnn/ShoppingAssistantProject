package com.example.shoppingassistant.service;

import com.example.shoppingassistant.event.AddedProductByUserEvent;
import com.example.shoppingassistant.exceptions.ProductNotFoundException;
import com.example.shoppingassistant.model.entity.Product;
import com.example.shoppingassistant.model.entity.ProductCategory;
import com.example.shoppingassistant.model.entity.ProductPicture;
import com.example.shoppingassistant.model.entity.User;
import com.example.shoppingassistant.model.service.ProductServiceModel;
import com.example.shoppingassistant.model.view.ProductViewModel;
import com.example.shoppingassistant.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductCategoryService productCategoryService;
    private final UserService userService;
    private final ProductPictureService productPictureService;
    private final ApplicationEventPublisher applicationEventPublisher;


    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, ProductCategoryService productCategoryService, UserService userService, ProductPictureService productPictureService, ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.productCategoryService = productCategoryService;
        this.userService = userService;
        this.productPictureService = productPictureService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void addProduct(ProductServiceModel productServiceModel, String username){

        Product product = modelMapper.map(productServiceModel, Product.class);
        ProductCategory productCategory = this.productCategoryService.findByProductCategory(productServiceModel.getProductCategory());
        ProductPicture productPicture = this.productPictureService.getPictureByTagName(productCategory.getProductCategory().name());

        product.setProductPicture(productPicture.getLocation());
        product.setCategory(productCategory);
        product.setProductPictureClass(productPicture);
        this.productRepository.save(product);

        User user = this.userService.findByName(username);
        user.getProducts().add(product);
        this.userService.saveChanges(user);

        AddedProductByUserEvent addedProductByUserEvent = new AddedProductByUserEvent(ProductService.class.getSimpleName(), user.getId());
        this.applicationEventPublisher.publishEvent(addedProductByUserEvent);
    }

    public List<ProductViewModel> getAllProductsInUsersShoppingList(String username) {

        User user = this.userService.findByName(username);

        return user.getProducts().stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalSum(String username) {
        User user = this.userService.findByName(username);
        return user.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

    public void removeAllItemsFromTheList(String username) {
        User user = this.userService.findByName(username);
        List<Product> products = user.getProducts();
        user.getProducts().clear();
        this.productRepository.deleteAll(products);
    }

    public boolean removeOneItemFromTheList(String username, Long itemId) {
        User user = this.userService.findByName(username);
        Product product = this.productRepository.findById(itemId).orElseThrow(() -> new ProductNotFoundException("Product with " + itemId + " was not found."));

        if(user.getProducts().contains(product)){
            user.getProducts().remove(product);
            this.productRepository.deleteById(product.getId());
            return true;
        }
        return false;
    }
}
