


loadGenres();
loadAgeLimit();

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
        movieDescription: movieDescription,
        genreSelect: genreSelect,
        ageLimitSelect: ageLimitSelect,
        duration: duration
    };

    console.log(newMovie)

    try {

        const response = await fetch("/api/screenings/batch", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(newMovie)
        });

        if (!response.ok) {
            throw new Error("Failed to save screenings");
        }

        alert("Screenings created successfully");

    } catch (error) {
        console.error(error);
    }
}

async function loadGenres() {

    const response = await fetch("/api/genres");

    if (!response.ok) {
        throw new Error("Failed to load genres");
    }

    const genres = await response.json();

    const genreSelect = document.getElementById("genreSelect");

    genres.forEach(genre => {

        const option = document.createElement("option");

        option.value = genre;
        option.textContent = genre;

        genreSelect.appendChild(option);
    });
}

async function loadAgeLimit() {

    const response = await fetch("/api/agelimit");

    if (!response.ok) {
        throw new Error("Failed to load genres");
    }

    const ageLimits = await response.json();

    const ageLimitSelect = document.getElementById("ageLimitSelect");

    ageLimits.forEach(ageLimit => {

        const option = document.createElement("option");

        option.value = ageLimit;
        option.textContent = ageLimit;

        ageLimitSelect.appendChild(option);
    });
}