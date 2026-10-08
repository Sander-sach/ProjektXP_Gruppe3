let movie

document.getElementById("deleteMovieConfirmation")
    .addEventListener("click", deleteMovie);

initializePage();

async function deleteMovie() {

    try {
        const response = await fetch(`/api/movies/setInactive/${movie.id}`, {

            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: ""

        });

        if (!response.ok) {
            throw new Error("Failed to delete movie");
        }

        alert("Movie deleted successfully");
        window.location.href = "choose_movie_to_delete.html";

    } catch (error) {
        console.error(error);
    }
}

function initializePage() {
    const movieJson = sessionStorage.getItem("movie");
    movie = JSON.parse(movieJson);
    document.getElementById("movieTitle").value = movie.movieTitle;
    document.getElementById("areYouSureText").innerText =
        "Are you sure you want to delete " + movie.movieTitle;
}