function addItemToCart() {
    let itemToCart = document.querySelectorAll(".toggle-button");
    for (let item of itemToCart) {
        item.addEventListener('click', (event) => {
            postData('/apiCart', {"id": item.id})
                .then((data) => {
                    console.log(data); // JSON data parsed by `response.json()` call
                });

        });
    }
}

function quantityCounter() {
    $(document).ready(function () {
        $('.count').prop('disabled', false);
        $(document).on('click', '.plus', function () {
            $('.count').val(parseInt($('.count').val()) + 1);
            if ($('.count').val() == 11) {
                $('.count').val(10);
            }
        });
        $(document).on('click', '.minus', function () {
            $('.count').val(parseInt($('.count').val()) - 1);
            if ($('.count').val() == 0) {
                $('.count').val(1);
            }
        });
    })
}

function getCartItems() {
    let cartButton = document.querySelector("#cart-button");
    cartButton.addEventListener('click', function () {

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

    for (let item of cartItems) {
        displayCartData(item)
    }
}

function displayCartData(item) {
    const body = document.querySelector('#container');
    const emptyP = document.querySelector('#empty');
    const template = document.querySelector('#cart-template');
    const clone = document.importNode(template.content, true);

    const name = clone.querySelector('#name');
    const existingNames = document.querySelectorAll('#name');
    const quantity = clone.querySelector('#quantity');
    const existingQuantity = document.querySelector('#quantity');
    const price = clone.querySelector('#price');
    const existingPrice = document.querySelector('#price');


    name.textContent = item["name"];
    quantity.setAttribute('value', `${item["amount"]}`);
    price.textContent = item["defaultPrice"];
    emptyP.textContent = "";


    body.appendChild(clone);
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
quantityCounter();

