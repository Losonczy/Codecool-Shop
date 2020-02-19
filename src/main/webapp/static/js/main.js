function post(url, data) {

    fetch(url, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(data)
    })
}

function filter() {

    const filterButtons = document.querySelectorAll('.category');
    for (let i = 0; i < filterButtons.length; i++) {
        filterButtons[i].addEventListener('click', function () {
            let focusSwitch = filterButtons[i].getAttribute("data-switch");
            setFocus(this);
            if(focusSwitch == "1"){
                filterButtons[i].setAttribute('data-switch', "2");
            }else{
                filterButtons[i].setAttribute('data-switch', "1");
            }
            if (filterButtons[i].textContent === "Animal") {
                hide("Animal",focusSwitch)
            } else if (filterButtons[i].textContent === "Holiday") {
                hide("Holiday",focusSwitch)
            } else if (filterButtons[i].textContent === "Cartoon") {
                hide("Cartoon",focusSwitch)
            } else if (filterButtons[i].textContent === "Other") {
                hide("Other",focusSwitch)
            } else {
                hide("All",focusSwitch)
            }

        });
    }

}

function hide(category,focusSwitch) {
    const cards = document.querySelectorAll("[data-label]");

    for (let j = 0; j < cards.length; j++) {

        show(cards[j]);

        if (cards[j].getAttribute("data-label").toString() !== category) {
            cards[j].setAttribute('class', 'card card-hidden');
            cards[j].parentElement.setAttribute('class', 'card-hidden');
        }
        if (category === "All") {
            show(cards[j]);

        }
        if(focusSwitch == "2"){
            show(cards[j]);

        }
    }
}

function show(card) {
    card.setAttribute('class', 'card');
    card.parentElement.setAttribute('class', 'col col-sm-12 col-md-6 col-lg-4');
}

function setFocus(button){
    let current = document.getElementsByClassName('focus');
    current[0].className = current[0].className.replace(" focus", "");
    button.className += " focus";
}

filter();








