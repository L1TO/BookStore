import {sendData, getData} from "./get-post.js";
const booksList = document.getElementById("booksList")
const container = document.getElementById("container")

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});

    const data =
        `<h2 style="color: #de445b">Books on <a href="#" class="text-white">"${search.title}"</a></h2>`
    container.insertAdjacentHTML('afterend', data)



getData("/books/fetchAll")
    .then(results => {
        results.forEach((element, index) => {
            if (element.category_id == search.id && element.active == "yes"){
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
                booksList.insertAdjacentHTML('afterend', data)
            }

        })
    })