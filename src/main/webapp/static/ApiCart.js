let totalCost = 0;

function addItemToCart() {
    let itemToCart = document.querySelectorAll(".toggle-button");

    for (let item of itemToCart) {
        item.addEventListener('click', (event) => {
            postData('/apiCart', {"id": item.id})
                .then((data) => {
                    console.log(data); // JSON data parsed by `response.json()` call
                });
        });
        item.addEventListener('click', (event) => {
            snackBaring();
        })
    }

}

function snackBaring() {
    // Get the snackbar DIV
    let x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function () {
        x.className = x.className.replace("show", "");
    }, 3000);
}

function getCartItems() {
    let cartButton = document.querySelector("#cart-button");

    cartButton.addEventListener('click', function () {
        totalCost = 0;
        const body = document.querySelector('#container');
        body.innerHTML = "";
        getData("/apiGetCartData", loadCartData);

    });
}

function getData(url, callback) {

    fetch(url, {  // set the path; the method is GET by default, but can be modified with a second parameter
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(response => response.json())  // parse JSON format into JS object
        .then(data => callback(data))
}

function loadCartData(cartItems) {
    const checkOut = document.getElementById('checkout');
    checkOut.disabled = cartItems.length == 0;


    for (let i = 0; i < cartItems.length; i++) {
        totalCost += cartItems[i]['defaultPrice'] * cartItems[i]['quantity'];
        displayCartData(cartItems[i]);
    }
}

function displayCartData(item) {
    const emptyP = document.querySelector('#empty');
    const body = document.querySelector('#container');
    const header = document.querySelector('.row-header');
    const totalCostContainer = document.querySelector('.total-cost');
    const displayTotalCost = totalCostContainer.querySelector('.total-cost-number');

    const template = document.querySelector('#cart-template');
    const clone = document.importNode(template.content, true);
    const row  = clone.querySelector('.cart-row');
    row.setAttribute('id', `body_${item['id']}`);
    const name = clone.querySelector('#name');
    const price = clone.querySelector('#price');
    price.setAttribute('id', `price_${item['id']}`);
    const counter = clone.querySelector('.count');
    counter.setAttribute('id', `counter_${item['id']}`);
    const plus = clone.querySelector('.plus');
    plus.setAttribute('id', `plus_${item['id']}`);
    const minus = clone.querySelector('.minus');
    minus.setAttribute('id', `minus_${item['id']}`);
    const delete_button = clone.querySelector('#delete-img');
    delete_button.setAttribute('id', `delete_${item['id']}`);

    totalCostContainer.style.display = "block";
    displayTotalCost.innerText = totalCost;
    emptyP.textContent = "";
    header.style.display = "block";
    name.textContent = item["name"];
    counter.setAttribute('value', `${item['quantity']}`);

    price.textContent = item["defaultPrice"] * item['quantity'] + " USD";


    body.appendChild(clone);
    quantityCounter(item);
    deleteItem(item);

}
function deleteItem(item){

    const body = document.querySelector('#container');
    const totalCostCounter = document.querySelector('.total-cost-number');
    const emptyP = document.querySelector('#empty');
    const header = document.querySelector('.row-header');
    const delete_button= document.getElementById(`delete_${item['id']}`);
    const itemContainer = document.getElementById(`body_${item['id']}`);
    delete_button.addEventListener('click',function () {
        let result = confirm("Are you sure you want to delete?");
        if (result) {
            itemContainer.remove();
            totalCost -= item["defaultPrice"] * item["quantity"];
            totalCostCounter.innerText = totalCost;
            postData('/deleteCartItem', {
                "id": item.id,
            })
                .then((data) => {
                    console.log(data);
                });
        }

    });

}

function quantityCounter(item) {
    const totalCostCounter = document.querySelector('.total-cost-number');
    const plus = document.getElementById(`plus_${item['id']}`);
    const minus = document.getElementById(`minus_${item['id']}`);
    const counter = document.getElementById(`counter_${item['id']}`);
    const price = document.getElementById(`price_${item['id']}`);

    plus.addEventListener('click', function () {
        if (item['quantity'] <= item['amount'] && item["quantity"] >= 1) {
            item['quantity'] += 1;
            price.textContent = item["defaultPrice"]*item['quantity'] + " USD";
            totalCost += item["defaultPrice"]
            totalCostCounter.innerText = totalCost;


            counter.setAttribute('value', `${item['quantity']}`);
            postData('/cartQuantity', {
                "id": item.id,
                "quantity": item['quantity'],
                "defaultPrice": item["defaultPrice"]
            })
                .then((data) => {
                    console.log(data); // JSON data parsed by `response.json()` call
                });
            console.log(item["defaultPrice"]);
        }

    });
    minus.addEventListener('click', function () {
        if (item["quantity"] > 1 && item['quantity'] <= item['amount'] + 1) {
            item['quantity'] -= 1;
            if (item["quantity"] >= 1) {
                price.textContent = item["defaultPrice"]*item['quantity'] + " USD";
                totalCost -= item["defaultPrice"];
                totalCostCounter.innerText = totalCost;
            }

            counter.setAttribute('value', `${item['quantity']}`);
            postData('/cartQuantity', {
                "id": item.id,
                "quantity": item['quantity'],
                "defaultPrice": item["defaultPrice"]
            })
                .then((data) => {
                    console.log(data); // JSON data parsed by `response.json()` call
                });
            console.log(item["defaultPrice"]);
        }

    });
}


async function postData(url, data) {

    const response = fetch(url, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'

        },
        redirect: 'follow', //
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });
    return response;
}

addItemToCart();
getCartItems();



