import {sendData} from "./get-post.js";
const title = document.getElementById('title');
const image_name = document.getElementById('image');
const id = document.getElementById('id');
const featured = document.getElementsByName('featured');
const active = document.getElementsByName('active');

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


sendData( "http://0.0.0.0:8080/categories/fetch", JSON.stringify({'id': search.id}))
    .then(result => {id.value = result.id,
        title.value = result.title,
        image_name.value = result.image_name
        })

sendBtn.onclick = () => {
    const checkbox = getCheckedCheckBoxes()
        let data = {
            id: id.value,
            title: title.value,
            image_name: image_name.value,
            featured: checkbox[0],
            active: checkbox[1]

        }
        sendData("/categories/update", JSON.stringify(data))
        window.location = "/admin/category-admin.html";
    };