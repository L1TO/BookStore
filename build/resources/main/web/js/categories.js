import {sendData, getData} from "./get-post.js";
const categoriesList = document.getElementById("categoriesList")
const container = document.getElementById("container")

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});

getData("/categories/fetchAll")
    .then(results => {
            results.forEach((element, index) => {
                if (element.active == "yes") {
                const data =
                    `<a href="category-books.html?id=${element.id}&title=${element.title}">
                        <div class="box-3 float-container">
                        <img src="${element.image_name}" class="img-responsive img-curve">

                        <h3 >${element.title}</h3>
                        </div>
                    </a>`
                categoriesList.insertAdjacentHTML('afterend', data)
            }})
        })