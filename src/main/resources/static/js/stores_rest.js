const route = 'http://localhost:8080/api/stores';
discountContainer = document.getElementById('containerJS');

let allStoresDiscountCategories = [];
let discountNumberForHTMLDivId = 1;

fetch(`${route}/get-all-discounted-categories-for-each-store`,{
    headers:{
        "Accepts": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for(let storeDiscountCategory of data){
            allStoresDiscountCategories.push(storeDiscountCategory);
            displayStoreDiscount(storeDiscountCategory);
        }
    }).catch(error => {
        ifTheResponseis404ErrorDisplayNoDiscounts();
});


function ifTheResponseis404ErrorDisplayNoDiscounts(){
    let div = document.createElement('div');
    div.id = `discount${discountNumberForHTMLDivId}`;
    div.classList.add("container", "bg-blur", "rounded", "mt-5", "mb-4");

    div.innerHTML = `
        <h1>No discounts currently in any of the stores.</h1>
        </div>`;
    discountContainer.appendChild(div);
}

function displayStoreDiscount(storeDiscountCategory){
    let div = document.createElement('div');
    div.id = `discount${discountNumberForHTMLDivId}`;
    div.classList.add("container", "bg-blur", "rounded", "mt-5", "mb-4");

    let category = storeDiscountCategory.pricingCategory.toLowerCase();

    div.innerHTML = `
    <h1><img class=" img-profile2" src="/images/${storeDiscountCategory.pricingCategory}.png">${storeDiscountCategory.discountPercentage}% discount for ${category} in ${storeDiscountCategory.name}.</h1>
    </div>`;

    discountContainer.appendChild(div);
    discountNumberForHTMLDivId++;
}



