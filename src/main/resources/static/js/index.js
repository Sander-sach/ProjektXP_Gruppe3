document.addEventListener('DOMContentLoaded', () => {
    const registerDialog = document.getElementById('registerDialog');
    const loginDialog = document.getElementById('loginDialog');

    document.getElementById('registerBtn')
        .addEventListener('click', () => registerDialog.showModal());
    document.getElementById('loginBtn')
        .addEventListener('click', () => loginDialog.showModal());
});
const registerForm = document.getElementById('register');


registerForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const response = await fetch('/api/v1/create-employee', {
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



async function registerEmployee(userName, password, employeeRole){
}
async function index(userName, password){
}

function openRegisterDialog(){
}
function closeRegisterDialog(){
}