import {getData} from "./get-post.js";
const userslist = document.getElementById("categories-list")

getData("/categories/fetchAll").then(results => {
    window.onresize = function(){ location.reload(); }
    results.forEach((element, index)=> {
        const data = `
          <tr>
            <td>${element.id}</td>
            <td>${element.title}</td>
            <td><img class="category-image" src="${element.image_name}" ></td>
            <td>${element.featured.toUpperCase()}</td>
            <td>${element.active.toUpperCase()}</td>
            <td>
              <a href="/admin/update-category.html?id=${element.id}" class="btn-secondary">Update category</a> 
              <a href="/admin/delete-category.html?id=${element.id}" class="btn-primary">Remove category</a>
            </td>
          </tr>`
        userslist.insertAdjacentHTML('beforeend', data)

    })})