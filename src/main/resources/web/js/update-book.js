import {getData, sendData} from "./get-post.js";
const title = document.getElementById('title');
const image_name = document.getElementById('image_name');
const id = document.getElementById('id');
const description = document.getElementById('description');
const price = document.getElementById('price');
const categoryList = document.getElementById("category")
const featured = document.getElementsByName('featured');
const active = document.getElementsByName('active');

getData("/categories/fetchAll")
    .then(results =>{
        let i = 0
        results.forEach((element, index)=> {
            if (element.active == "yes") {
                i++
                const data = `<option value="${element.id}">${element.title}</option>`
                categoryList.insertAdjacentHTML('beforeend', data)
            }
        })
        if (i == 0){
            const data = `<option value="0">No active categories</option>`
            categoryList.insertAdjacentHTML('beforeend', data)
        }
    })


function getCheckedCheckBoxes() {
    var selectedCheckBoxes = document.querySelectorAll('input.checkbox:checked');

    var checkedValues = Array.from(selectedCheckBoxes).map(cb => cb.value);

    console.log(checkedValues);

    return checkedValues;
}

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});


sendData( "/books/fetch", JSON.stringify({'id': search.id}))
    .then(result => {id.value = result.id,
        title.value = result.title,
        image_name.value = result.image_name,
        description.value = result.description,
        price.value = result.price,
        categoryList.value = result.category_id
    })

sendBtn.onclick = () => {
    const checkbox = getCheckedCheckBoxes()
    let data = {
        id: id.value,
        title: title.value,
        description: description.value,
        price: price.value,
        category_id: categoryList.value,
        image_name: image_name.value,
        featured: checkbox[0],
        active: checkbox[1]

    }
    sendData("/books/update", JSON.stringify(data))
    window.location = "/admin/books-admin.html";
};
