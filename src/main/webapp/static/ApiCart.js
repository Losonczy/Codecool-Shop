let cartCounter = 0;

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
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function getCartItems() {
    let cartButton = document.querySelector("#cart-button");

    cartButton.addEventListener('click', function () {
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

    for(let i=0; i<cartItems.length; i++){
        displayCartData(cartItems[i]);
    }
}

function displayCartData(item) {
    const emptyP = document.querySelector('#empty');
    const body = document.querySelector('#container');
    const header = document.querySelector('.row-header');

    const template = document.querySelector('#cart-template');
    const clone = document.importNode(template.content, true);

    const name = clone.querySelector('#name');
    const price = clone.querySelector('#price');
    const counter = clone.querySelector('.count');
    counter.setAttribute('id', `counter_${item['id']}`);
    const plus = clone.querySelector('.plus');
    plus.setAttribute('id', `plus_${item['id']}`);
    const minus = clone.querySelector('.minus');
    minus.setAttribute('id', `minus_${item['id']}`);


    emptyP.textContent = "";
    header.style.display = "block";
    name.textContent = item["name"];
    counter.setAttribute('value',`${item['quantity']}`);
    price.textContent = item["defaultPrice"] + " USD";
    body.appendChild(clone);
    quantityCounter(item);

}
function quantityCounter(item){
    const plus = document.getElementById(`plus_${item['id']}`);
    const minus = document.getElementById(`minus_${item['id']}`);
    const counter = document.getElementById(`counter_${item['id']}`);

    plus.addEventListener('click',function () {
        item['quantity'] +=1;
        counter.setAttribute('value',`${item['quantity']}`);
        postData('/cartQuantity', {"id": item.id,"quantity": item['quantity'] })
            .then((data) => {
                console.log(data); // JSON data parsed by `response.json()` call
            });

    });
    minus.addEventListener('click',function () {
        item['quantity'] -=1;
        counter.setAttribute('value',`${item['quantity']}`);
        postData('/cartQuantity', {"id": item.id,"quantity": item['quantity']})
            .then((data) => {
                console.log(data); // JSON data parsed by `response.json()` call
            });

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



