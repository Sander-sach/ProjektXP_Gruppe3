import { checkLogin, checkAdmin} from './auth.js';

const registerForm = document.getElementById('register');
const errorMessage = document.querySelector(".error-message");
const registerDialog = document.getElementById('registerDialog');
const user = await checkLogin();
if(!user) throw new Error("Not Authenticated");

document.getElementById("registerBtn").hidden = !checkAdmin(user);
document.getElementById('registerBtn')
    .addEventListener('click', () => registerDialog.showModal());
    registerDialog.querySelector(".cancel-btn")
        .addEventListener('click', () => registerDialog.close());



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
        errorMessage.textContent = "You dont have access to use this tool";
        errorMessage.hidden = false;
    }
});
