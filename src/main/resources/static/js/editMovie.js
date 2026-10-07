import {loadAndPopulateAgeLimit, loadAndPopulateGenres} from "./loadDataFunctions.js";

let movie;

document.getElementById("updateMovieConfirmation")
    .addEventListener("click", updateMovie);

initializePage();

async function initializePage() {

    await loadAndPopulateGenres();
    await loadAndPopulateAgeLimit();

    const movieJson = sessionStorage.getItem("movie");
    movie = JSON.parse(movieJson);

    //inserts the data the movie already have
    //a movie can only have one genre, sry not sorry
    document.getElementById("movieTitle").value = movie.movieTitle;
    document.getElementById("movieDescription").value = movie.description;
    document.getElementById("genreSelect").value = movie.genre;
    document.getElementById("ageLimitSelect").value = movie.ageLimit;
    document.getElementById("duration").value = movie.duration;
}

async function updateMovie(){

    const movieId = movie.id
    const movieTitle = document.getElementById("movieTitle").value;
    const movieDescription = document.getElementById("movieDescription").value;
    const genreSelect = document.getElementById("genreSelect").value;
    const ageLimitSelect = document.getElementById("ageLimitSelect").value;
    const duration = document.getElementById("duration").value;

    const updatedMovie = {
        movieTitle: movieTitle,
        description: movieDescription,
        genre: genreSelect,
        ageLimit: ageLimitSelect,
        duration: duration
    };

    try {
        const response = await fetch(`/api/movies/update/${movieId}`, {

            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(updatedMovie)

        });

        if (!response.ok) {throw new Error("Failed to update movie");}

        alert("Movie updated successfully");

    } catch (error) {
        console.error(error);
    }
}