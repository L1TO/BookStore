import {getData} from "./get-post.js";

const countCategory = document.getElementById("clearfix")
getData("/categories/fetchAll").then(results => {
    let count = 0
    results.forEach((element, index)=> {
        count++
    })
    let data = `<div class="col-4 text-center">
                <h1>${count}</h1>
                <br>
                Categories
                </div>`
    countCategory.insertAdjacentHTML('beforebegin', data)
})

getData("/books/fetchAll").then(results => {
    let count = 0
    results.forEach((element, index)=> {
        count++
    })
    let data = `<div class="col-4 text-center">
                <h1>${count}</h1>
                <br>
                Books
                </div>`
    countCategory.insertAdjacentHTML('beforebegin', data)
})

getData("/orders/fetchAll").then(results => {
    let count = 0
    let priceCount = 0
    results.forEach((element, index)=> {
        count++
        priceCount += element.total
    })
    let data = `<div class="col-4 text-center">
                <h1>${count}</h1>
                <br>
                Open Orders
                </div>

                <div class="col-4 text-center">
                <h1>${"$" + priceCount}</h1>
                <br>
                Our Profit
                </div>`
    countCategory.insertAdjacentHTML('beforebegin', data)
})