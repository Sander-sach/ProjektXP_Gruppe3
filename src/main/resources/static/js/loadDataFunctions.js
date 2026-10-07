let allMovies = [];
let allScreenings = [];

export async function loadMovies() {

    const response = await fetch("/api/movies");

    if (!response.ok) {
        throw new Error("Failed to load movies");
    }

    return allMovies = await response.json();

}

export async function loadScreenings() {

    const response = await fetch("/api/screenings");

    if (!response.ok) {
        throw new Error("Failed to load Screenings");
    }

    return allScreenings = await response.json();
}

export async function loadGenres() {

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
    return genres;
}

export async function loadAgeLimit() {

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
    return ageLimits;
}