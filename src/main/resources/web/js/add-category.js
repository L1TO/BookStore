const id = document.getElementById('id');
const title = document.getElementById('title');
const image = document.getElementById('image');
const featured = document.getElementsByName('featured');
const active = document.getElementsByName('active');

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
        alert( "Category already exists" );
    }

    return response;
}



sendBtn.onclick = () => {
    const checkbox = getCheckedCheckBoxes()
    let data = {
        id: id.value,
        title: title.value,
        image_name: image.value,
        featured: checkbox[0],
        active: checkbox[1]
    }
    console.log(data)
        sendData1("/addCategory", JSON.stringify(data))
            .then(results => {
                if (results.status == 200){
                    window.location.href='category-admin.html'
                } else {
                    alert("Category already exists")
            }})
    }



