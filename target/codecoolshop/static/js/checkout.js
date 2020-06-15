const payment_btn = document.querySelector('.payment-btn');
payment_btn.addEventListener('click', function () {
    document.querySelector('#confirm').removeAttribute('disabled');
});