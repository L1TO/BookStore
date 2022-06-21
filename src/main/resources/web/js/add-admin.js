import {validateInputs} from "./validate-form.js";
const form = document.getElementById('form');
const username = document.getElementById('full-name');
const email = document.getElementById('email');
const login = document.getElementById('username');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');

const getData = async (url) => {

  const response = await fetch(url);

  if (!response.ok) {
    throw new Error(`Error ${url}, status ${response.status}`);
  }

  return await response.json();

};


const sendData1 = async (url, data) => {
const response = (await fetch(url, {
    method: 'POST',
    headers: new Headers({'content-type': 'application/json'}),
    body: data,
}))
    // .then(response => {return response.text()})

if ( response.status == 409 ) {
  alert( "User already exists" );
}
else if (response.ok)
    window.location.href='manage-admin.html'

return response;
}
//
// const signUpBtn = document.getElementById("sendBtn")
// signUpBtn.onclick = () => {
//
// }



sendBtn.onclick = () => {
    validateInputs();
    if (validateInputs() == 0){
        let data = {
            login: login.value,
            password: password.value,
            username: username.value,
            email: email.value
        }
    sendData1("/registration", JSON.stringify(data))
}};



