package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.Store;
import com.example.shoppingassistant.model.entity.StoreProductCategoryPricing;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.model.view.StoreViewModel;
import com.example.shoppingassistant.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreProductCategoryPricingService storeProductCategoryPricingService;

    private ModelMapper modelMapper = new ModelMapper();

    private StoreService storeService;

    @BeforeEach
    void setUp(){
        this.storeService = new StoreService(storeRepository, modelMapper, storeProductCategoryPricingService);
    }

    @Test
    void testFindByName_returnStore(){

        StoreProductCategoryPricing storeProductCategoryPricing1 = new StoreProductCategoryPricing(ProductCategoryEnum.MEAT, 1, "test1").setId(1L);
        StoreProductCategoryPricing storeProductCategoryPricing2 = new StoreProductCategoryPricing().setId(2L).setPricingCategory(ProductCategoryEnum.FISH).setDiscountPercentage(2).setDescription("test2");

        Store store1 = new Store(1L,"test1","test1","test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));
        Store store2 = new Store().setId(2L).setName("test2").setDescription("test2").setStoreLogo("test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));

        when(this.storeRepository.findByName("test1")).thenReturn(Optional.of(store1));
        when(this.storeRepository.findByName("test2")).thenReturn(Optional.of(store2));

        Assertions.assertEquals(1L, storeService.findByName("test1").get().getId());
        Assertions.assertEquals(2L, storeService.findByName("test2").get().getId());


    }

    @Test
    void testGetAllStoresNames_returnListOfStringStoreNames(){

        List<String> storeNames = new ArrayList<>();
        storeNames.add("1");
        storeNames.add("2");
        storeNames.add("3");

        when(storeRepository.getAllStoresNames()).thenReturn(storeNames);

        Assertions.assertEquals("1",this.storeService.getAllStoresNames().get(0));
        Assertions.assertEquals("2",this.storeService.getAllStoresNames().get(1));
        Assertions.assertEquals("3",this.storeService.getAllStoresNames().get(2));
    }

    @Test
    void testGetCountOfAllStores(){

        when(storeRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10L, this.storeService.getCountOfAllStores());
    }

    @Test
    void testGetAllStores_returnsAllStoresViewModelList(){
        StoreProductCategoryPricing storeProductCategoryPricing1 = new StoreProductCategoryPricing(ProductCategoryEnum.MEAT, 1, "test1").setId(1L);
        StoreProductCategoryPricing storeProductCategoryPricing2 = new StoreProductCategoryPricing().setId(2L).setPricingCategory(ProductCategoryEnum.FISH).setDiscountPercentage(2).setDescription("test2");

        Store store1 = new Store(1L,"test1","test1","test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));
        Store store2 = new Store().setId(2L).setName("test2").setDescription("test2").setStoreLogo("test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));

        StoreViewModel storeViewModel1 = new StoreViewModel("test1","test1",1);
        StoreViewModel storeViewModel2 = new StoreViewModel().setName("test2").setPricingCategory("test2").setDiscountPercentage(2);

        List<Store> stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);

        when(storeRepository.findAll()).thenReturn(stores);

        Assertions.assertEquals(4, storeService.getAllStores().size());
        Assertions.assertEquals(storeViewModel1.getName(), storeService.getAllStores().get(0).getName());
        Assertions.assertEquals(storeViewModel1.getName(), storeService.getAllStores().get(1).getName());
        Assertions.assertEquals(storeViewModel2.getName(), storeService.getAllStores().get(2).getName());
        Assertions.assertEquals(storeViewModel2.getName(), storeService.getAllStores().get(3).getName());

    }

    @Test
    void testGetAllStoresAndFilterOnlyDiscountedCategories_returnListOfStoreViewModel(){
        StoreProductCategoryPricing storeProductCategoryPricing1 = new StoreProductCategoryPricing(ProductCategoryEnum.MEAT, 1, "test1").setId(1L);
        StoreProductCategoryPricing storeProductCategoryPricing2 = new StoreProductCategoryPricing().setId(2L).setPricingCategory(ProductCategoryEnum.FISH).setDiscountPercentage(0).setDescription("test2");

        Store store1 = new Store(1L,"test1","test1","test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));
        Store store2 = new Store().setId(2L).setName("test2").setDescription("test2").setStoreLogo("test.png").setPricingList(List.of(storeProductCategoryPricing1, storeProductCategoryPricing2));

        StoreViewModel storeViewModel1 = new StoreViewModel("test1","test1",1);
        StoreViewModel storeViewModel2 = new StoreViewModel().setName("test2").setPricingCategory("test2").setDiscountPercentage(2);

        List<Store> stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);

        when(storeRepository.findAll()).thenReturn(stores);

        Assertions.assertEquals(2, storeService.getAllStoresAndFilterOnlyDiscountedCategories().size());
        Assertions.assertEquals(storeViewModel1.getName(), storeService.getAllStoresAndFilterOnlyDiscountedCategories().get(0).getName());
        Assertions.assertEquals(storeViewModel2.getName(), storeService.getAllStoresAndFilterOnlyDiscountedCategories().get(1).getName());

    }


}
