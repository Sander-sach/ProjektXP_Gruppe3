export async function checkLogin(){
    const response = await fetch("/api/user");
    if(!response.ok){
        location.replace("/index.html");
        return null;
    }
    return response.json();
}
export function checkAdmin(user){
    return user?.role === "FILMOPERATOER";
}