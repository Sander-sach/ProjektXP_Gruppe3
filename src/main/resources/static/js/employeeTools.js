document.addEventListener('DOMContentLoaded', () => {
    const registerDialog = document.getElementById('registerDialog');


    document.getElementById('registerBtn')
        .addEventListener('click', () => registerDialog.showModal());
    registerDialog.querySelector(".cancel-btn")
        .addEventListener('click', () => registerDialog.close());
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
