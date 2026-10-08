// All the load functions in one place.
// Just import the function in your js and change the script to type=module in the HTML


//ElementId="genreSelect"
//In your HTML you have to call the dropDown Id "genreSelect"
//then the funvtion populates the dropdown with the correct data
export async function loadAndPopulateGenres() {

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

//ElementId="ageLimitSelect"
//same as Genres but with agelimits
export async function loadAndPopulateAgeLimit() {

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

export async function loadMovies() {

    const response = await fetch("/api/movies");

    if (!response.ok) {
        throw new Error("Failed to load movies");
    }
    const allMovies= await response.json();
    return allMovies;

}

export async function loadScreenings() {

    const response = await fetch("/api/screenings");

    if (!response.ok) {
        throw new Error("Failed to load Screenings");
    }
    const allScreenings = await response.json();
    return allScreenings;
}

export async function loadReservations() {

    const response = await fetch("/api/reservation");

    if (!response.ok) {
        throw new Error("Failed to load reservations");
    }

    const allReservations = await response.json();
    return allReservations;
}

export async function loadSeats() {

    const response = await fetch("/api/seats");

    if (!response.ok) {
        throw new Error("Failed to load seats");
    }

    const allSeats = await response.json();
    return allSeats;
}