import {sendData} from "./get-post.js";

const bookTitle = document.getElementById('book-title');
const bookPrice = document.getElementById('book-price');
const bookImage = document.getElementById('book-image');
const btnSubmit = document.getElementById('btnSubmit');
const address = document.getElementById('address');
const email = document.getElementById('email');
const contact = document.getElementById('contact');
const fullName = document.getElementById('full-name');
const qty = document.getElementById('qty');

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});
sendData("/books/fetch", JSON.stringify({'id': search.id}))
    .then(results => {
        bookTitle.innerText = results.title,
        bookImage.src = results.image_name,
        bookPrice.innerText =results.price
    })


btnSubmit.onclick = () => {
    let totalPrice = Math.round( qty.value*bookPrice.innerText )
    let data = {
        id: Math.ceil(Math.random()*10000),
        book_name: bookTitle.innerText,
        price: bookPrice.innerText,
        quantity: qty.value,
        order_date: new Date().toDateString(),
        total: totalPrice,
        customer_name: fullName.value,
        customer_address: address.value,
        customer_email: email.value
    }
    sendData("/addOrder", JSON.stringify(data))
    alert("Order accepted")
    window.location = "/index.html";
}