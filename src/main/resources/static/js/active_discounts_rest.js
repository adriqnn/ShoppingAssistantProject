const route = 'http://localhost:8080/api/discounts';
discountContainer = document.getElementById('containerJS');

let allActiveDiscounts = [];
let discountNumberForHTMLDivId = 1;

fetch(`${route}/get-active-daily-discounts`,{
    headers:{
        "Accepts": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for(let discount of data){
            allActiveDiscounts.push(discount);
            displayDiscount(discount);
        }
    });

function displayDiscount(discount){
    let div = document.createElement('div');
    div.id = `discount${discountNumberForHTMLDivId}`;
    div.classList.add("container", "bg-blur", "rounded", "mt-5", "mb-4");

    div.innerHTML = `
    <h1><img class=" img-profile2" src="/images/discountHi.png"> Discount Ticket #${discountNumberForHTMLDivId}</h1>
    <div class="col-12 bg-danger rounded m-auto" style="height: 9vh; overflow: auto">
    <h3 class="mt-3 rounded badge-light text-center font-weight-bold font-italic">In all the Stores that we work with!</h3>
    <h3 class="mt-3 rounded badge-light text-center font-weight-bold font-italic">${discount.description}</h3>
    </div>`;

    discountContainer.appendChild(div);
    discountNumberForHTMLDivId++;
}




