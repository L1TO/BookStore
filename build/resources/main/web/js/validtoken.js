import {sendData} from "./get-post.js";

let data = {
    token: localStorage.getItem('token'),
}

if (localStorage.getItem('token') != null){
    sendData("/token", JSON.stringify( data ))
        .then(results => {
            if(results == "Token does not exist"){
                window.location = "/admin/login-admin.html";
            }
        })
} else {
    window.location = "/admin/login-admin.html";
}


