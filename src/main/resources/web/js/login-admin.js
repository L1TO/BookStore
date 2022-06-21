import {sendData} from "./get-post.js";
const loginRegister = document.getElementById("username")
const passwordRegister = document.getElementById("password")
const error = document.getElementById("error")

const sendBtn = document.getElementById("sendBtn")
sendBtn.onclick = () => {
    let data = {
        login: loginRegister.value,
        password: passwordRegister.value
    }
    sendData("/login", JSON.stringify(data)).then(results => {
        if (results == "User not found"){
            error.innerText = results
        }
        else if (results == "Invalid password"){
            error.innerText = results
        } else {
            localStorage.setItem('token', results.token);
            window.location.href='main-admin.html'
        }

    })
}