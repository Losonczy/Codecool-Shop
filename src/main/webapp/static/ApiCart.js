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
        let x = document.getElementById("snackbar");

        // Add the "show" class to DIV
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    })
}

function snackBaring() {
    // Get the snackbar DIV
    let x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}


        });
    }
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


    for(let i=cartCounter; i<cartItems.length; i++){
        cartCounter ++;
        console.log(cartCounter);
        displayCartData(cartItems[i])
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

    emptyP.textContent = "";
    header.style.display = "block";
    name.textContent = item["name"];
    counter.setAttribute('value',`${item['amount']}`);
    price.textContent = item["defaultPrice"] + " USD";
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


