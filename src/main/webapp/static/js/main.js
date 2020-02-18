function post(url, data) {

    fetch(url, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'},

        body: JSON.stringify(data)
    })
}

function filter() {
    const filterButtons = document.querySelectorAll('.category');
    for (let i = 0; i < filterButtons.length; i++) {
        filterButtons[i].addEventListener('click', function () {
            if (filterButtons[i].textContent === "Blue") {
                hide("Blue")
            } else if (filterButtons[i].textContent === "Green") {
                hide("Green")
            } else if (filterButtons[i].textContent === "Pink") {
                hide("Pink")
            }else{
                hide("All")
            }
    });
    }

}

function hide(category){
    const cards = document.querySelectorAll("[data-label]");

    for(let j=0;j<cards.length;j++){
        cards[j].setAttribute('class','card');
        cards[j].parentElement.setAttribute('class','col col-sm-12 col-md-6 col-lg-4');
        if(cards[j].getAttribute("data-label").toString() !== category){
            cards[j].setAttribute('class', 'card card-hidden');
            cards[j].parentElement.setAttribute('class','card-hidden');
        }if(category === "All"){
            cards[j].setAttribute('class','card');
            cards[j].parentElement.setAttribute('class','col col-sm-12 col-md-6 col-lg-4');

        }
    }
}

filter();








