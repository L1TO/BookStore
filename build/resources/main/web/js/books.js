import {getData} from "./get-post.js";
const bookList = document.getElementById("bookList")
const searchBtn = document.getElementById("submit")
const searchInput = document.getElementById("search")

searchBtn.onclick = () => {
    console.log(window.location.href="book-search.html?search=" + searchInput.value)
}

getData("/books/fetchAll").then(
    results => {
        results.forEach((element, index) => {
            if (element.active == "yes"){
                const data =
                    `<div class="book-menu-box">
                <div class="book-menu-img">
                    <img src="${element.image_name}" class="img-responsive img-curve">
                </div>

                <div class="book-menu-desc">
                    <h4>${element.title}</h4>
                    <p class="book-price">${'$' + element.price}</p>
                    <p class="book-detail">
                        ${element.description}
                    </p>
                    <br>

                    <a href="order.html?id=${element.id}" class="btn btn-primary">Order Now</a>
                </div>
            </div>`
                bookList.insertAdjacentHTML('afterend', data)
            }

        })
    }
)