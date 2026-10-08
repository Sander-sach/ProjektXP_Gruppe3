document.addEventListener('DOMContentLoaded', () => {

});

const loginForm = document.getElementById("login")

loginForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const response = await fetch('/api/login-request', {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            userName: loginForm.loginUserName.value,
            password: loginForm.loginPassword.value,
        })
    });

    if (response.ok) {
        const loginResponse = await response.json();
        sessionStorage.setItem("user", JSON.stringify(loginResponse));
        location.replace("/index.html");
    } else {
        console.error("Login failed:", response.status);
    }
});
