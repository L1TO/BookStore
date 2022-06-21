const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidEmail = email => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

const isValidLogin = login => {
    const re = /^\S+$/;
    return re.test(String(login).toLowerCase());
}

const validateInputs = () => {
    let i = 0;

    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();


    if(emailValue === '') {
        i++
        setError(email, 'Email is required');
    } else if (!isValidEmail(emailValue)) {
        i++
        setError(email, 'Provide a valid email address');
    } else {
        setSuccess(email);
    }

    if(passwordValue === '') {
        i++
        setError(password, 'Password is required');
    } else if (passwordValue.length < 8 ) {
        i++
        setError(password, 'Password must be at least 8 character.')
    } else {
        setSuccess(password);
    }

    // if (isValidLogin(loginValue)){
    //     setSuccess(login)
    // } else {
    //     i++
    //     setError(login, "Login must be without spaces")
    // }

    if(password2Value === '') {
        i++
        setError(password2, 'Please confirm your password');
    } else if (password2Value !== passwordValue) {
        i++
        setError(password2, "Passwords doesn't match");
    } else {
        setSuccess(password2);
    }

    return i
};

export {validateInputs};
