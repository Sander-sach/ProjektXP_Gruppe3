import {loadAndPopulateAgeLimit, loadAndPopulateGenres} from "./loadDataFunctions.js";


loadAndPopulateGenres();
loadAndPopulateAgeLimit();

document.getElementById("createMovieConfirmation")
    .addEventListener("click", createNewMovie);

async function createNewMovie(){

    const movieTitle =
        document.getElementById("movieTitle").value;
    const movieDescription =
        document.getElementById("movieDescription").value;
    const genreSelect =
        document.getElementById("genreSelect").value;
    const ageLimitSelect =
        document.getElementById("ageLimitSelect").value;
    const duration =
        document.getElementById("duration").value;

    const newMovie = {
        movieTitle: movieTitle,
        description: movieDescription,
        genre: genreSelect,
        ageLimit: ageLimitSelect,
        duration: duration
    };

    try {

        const response = await fetch("/api/movies/create", {

            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newMovie)

        });

        if (!response.ok) {throw new Error("Failed to save movie");}

        alert("Movie created successfully");

    } catch (error) {
        console.error(error);
    }
}
