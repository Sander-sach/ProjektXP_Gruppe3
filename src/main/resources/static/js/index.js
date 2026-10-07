document.addEventListener('DOMContentLoaded', () => {
    const registerDialog = document.getElementById('registerDialog');
    const loginDialog = document.getElementById('loginDialog');

    document.getElementById('registerBtn')
        .addEventListener('click', () => registerDialog.showModal());
    document.getElementById('loginBtn')
        .addEventListener('click', () => loginDialog.showModal());
    registerDialog.querySelector(".cancel-btn")
        .addEventListener('click', () => registerDialog.close());
    loginDialog.querySelector(".cancel-btn")
        .addEventListener('click', () => loginDialog.close());
});

const registerForm = document.getElementById('register');


registerForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const response = await fetch('/api/create-employee', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            userName: registerForm.registerUserName.value,
            password: registerForm.registerPassword.value,
            employeeRole: registerForm.role.value
        })
    });

    if (response.ok) {
        registerDialog.close();
        registerForm.reset();
    } else {
        console.error('Registration failed:', response.status);
    }
});

const loginForm = document.getElementById("login")

loginForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const response = await fetch('/api/login-request', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            userName: loginForm.loginUserName.value,
            password: loginForm.loginPassword.value,
        })
    });

    if (response.ok) {
        const loginResponse = await response.json();
        sessionStorage.setItem('user', JSON.stringify(loginResponse));
        registerDialog.close();
        location.replace('/create_screening_form.html');
    } else {
        console.error('Login failed:', response.status);
    }
});


