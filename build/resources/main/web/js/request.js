import {sendData} from "./get-post.js";
    const emailLogin = document.getElementById("emailLogin")
    const passwordLogin = document.getElementById("passwordLogin")
    const loginRegister = document.getElementById("loginRegister")
    const emailRegister = document.getElementById("emailRegister")
    const passwordRegister = document.getElementById("passwordRegister")

    const signInBtn = document.getElementById("signIn")
    signInBtn.onclick = () => {
        sendData("/login", JSON.stringify(data = {
            login: emailLogin.value,
            password: passwordLogin.value
        }))
    }

    const signUpBtn = document.getElementById("signUp")
    signUpBtn.onclick = () => {
        sendData("/registration", JSON.stringify(data = {
            login: loginRegister.value,
            email: emailRegister.value,
            password: passwordRegister.value
        })).then(results => {
                // window.location.href='../index.html'
                console.log(results)
        })
    }