import {validateInputs} from "./validate-form.js";
import {sendData} from "./get-post.js";

const form = document.getElementById('form');
const username = document.getElementById('full-name');
const email = document.getElementById('email');
const login = document.getElementById('username');
const sendBtn = document.getElementById("sendBtn")
const password = document.getElementById("password")
const password2 = document.getElementById("password2")

var search = location.search.substr(1)
    .split('&')
    .reduce(function (res, a) {
        var t = a.split('=');
        res[decodeURIComponent(t[0])] = t.length == 1 ? null : decodeURIComponent(t[1]);
        return res;
    }, {});


sendData( "http://0.0.0.0:8080/users/fetch", JSON.stringify({'login': search.login}))
    .then(result => {username.value = result.username,
                     login.value = result.login,
                     email.value = result.email})

sendBtn.onclick = () => {
    validateInputs()
    if (validateInputs() == 0){
        let data = {
            email: email.value,
            login: login.value,
            username: username.value,

        }
        sendData("/users/update", JSON.stringify(data))
        window.location = "/admin/manage-admin.html";
    }};