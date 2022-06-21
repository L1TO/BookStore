import {getData, sendData} from "./get-post.js";
const userslist = document.getElementById("categories-list")

getData("/books/fetchAll").then(results => {
    window.onresize = function(){ location.reload(); }
    results.forEach((element, index)=> {
        let id = {
            id: element.category_id,
        }
        sendData("/categories/fetch", JSON.stringify(id)).then(resultsSecond => {
            const data = `
          <tr>
            <td>${element.id}</td>
            <td>${element.title}</td>
            <td>${element.description}</td>
            <td> ${"$" + element.price}</td>
            <td><img class="category-image" src="${element.image_name}" ></td>
            <td value="${resultsSecond.id}">${resultsSecond.title}</td>
            <td>${element.featured.toUpperCase()}</td>
            <td>${element.active.toUpperCase()}</td>
            <td>
              <a href="/admin/update-book.html?id=${element.id}" class="btn-secondary">Update book</a> 
              <a href="/admin/delete-book.html?id=${element.id}" class="btn-primary">Remove book</a>
            </td>
          </tr>`
            userslist.insertAdjacentHTML('beforeend', data)
        })


    })})