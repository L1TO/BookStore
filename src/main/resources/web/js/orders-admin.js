import {getData} from "./get-post.js";
const orderslist = document.getElementById("orders-list")

getData("/orders/fetchAll").then(results => {
    window.onresize = function(){ location.reload(); }
    results.forEach((element, index)=> {
        const data = `
          <tr>
            <td>${element.id}</td>
            <td>${element.book_name}</td>
            <td>${"$" + element.price}</td>
            <td>${element.quantity}</td>
            <td>${"$" + element.total}</td>
            <td>${element.order_date}</td>
            <td>${element.customer_name}</td>
            <td>${element.customer_address}</td>
            <td>${element.customer_email}</td>
            <td> 
              <a href="/admin/delete-order.html?id=${element.id}" class="btn-primary">Remove category</a>
            </td>
          </tr>`
        orderslist.insertAdjacentHTML('beforeend', data)

    })})