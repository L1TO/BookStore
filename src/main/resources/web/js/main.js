import {getData} from "./get-post.js";
const categorieslist = document.getElementById("beforeCategories")
const bookList = document.getElementById("bookList")
const searchBtn = document.getElementById("submit")
const searchInput = document.getElementById("search")

getData("/categories/fetchAll").then(
    results => {
        results.forEach((element, index) => {
            let i = 0
            if (element.active == "yes"  && element.featured == "yes"){
                i++
                const data =
                    `<a href="category-books.html?id=${element.id}&title=${element.title}">
            <div style="float: left; margin-right: 10px; margin-top: 15px" className="box-3 float-container">
                <img class="categories-preview-image"  src="${element.image_name}" onerror="${console.log(this)}" alt="${element.id}" className="img-responsive img-curve">
                <h3 style="text-align: center" className="float-text text-white">${element.title}</h3>
                    </div>
                    </a>`
                categorieslist.insertAdjacentHTML('afterend', data)
            }
            if (i == 3){
                return
            }

        })
    }
)

getData("/books/fetchAll").then(
    results => {
        results.forEach((element, index) => {
            if (element.active == "yes"  && element.featured == "yes"){
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
searchBtn.onclick = () => {
    console.log(window.location.href="book-search.html?search=" + searchInput.value)
}