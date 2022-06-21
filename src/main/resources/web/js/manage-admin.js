import {getData} from "./get-post.js";
const userslist = document.getElementById("users-list")

getData("/users/fetchAll").then(results => {
    results.forEach((element, index)=> {
        const data = `
          <tr>
            <td>${index+1}</td>
            <td>${element.username}</td>
            <td>${element.login}</td>
            <td>${element.email}</td>
            <td>
              <a href="/admin/update-admin.html?login=${element.login}" class="btn-secondary">Update admin</a> 
              <a href="/admin/delete-admin.html?login=${element.login}" class="btn-primary">Remove admin</a>
            </td>
          </tr>`
        userslist.insertAdjacentHTML('beforeend', data)

    })})