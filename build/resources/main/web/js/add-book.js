const id = document.getElementById("id")
const title = document.getElementById("title")
const price = document.getElementById("price")
const image_name = document.getElementById("image_name")
const description = document.getElementById("description")
const categoryList = document.getElementById("category")
const featured = document.getElementsByName('featured');
const active = document.getElementsByName('active');
import {sendData, getData} from "./get-post.js";
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

const sendData1 = async (url, data) => {
    const response = (await fetch(url, {
        method: 'POST',
        headers: new Headers({'content-type': 'application/json'}),
        body: data,
    }))
    // .then(response => {return response.text()})

    if ( response.status == 409 ) {
        alert( "Book  already exists" );
    }

    return response;
}



sendBtn.onclick = () => {
    const checkbox = getCheckedCheckBoxes()
    let data = {
        id: id.value,
        title: title.value,
        description: description.value,
        price: price.value,
        image_name: image_name.value,
        category_id: categoryList.value,
        featured: checkbox[0],
        active: checkbox[1]
    }
    console.log(data)
    sendData1("/addBook", JSON.stringify(data))
        .then(results => {
            if (results.status == 200){
                window.location.href='books-admin.html'
            } else {
                alert("Book already exists")
            }})
}