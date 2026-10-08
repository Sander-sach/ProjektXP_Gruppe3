import {loadAndPopulateGenres, loadMovies, loadScreenings} from "./loadDataFunctions.js";

let allMovies = [];
let allScreenings = [];
let allGenres = [];


// EventListeners - Automatically update when filters change
document.getElementById("genreSelect")
    .addEventListener("change", filterMovies);

document.getElementById("castSearch")
    .addEventListener("input", filterMovies);

document.getElementById("date")
    .addEventListener("change", filterMovies)


initializePage();

// Load initial data
async function initializePage() {

    try {
        //Promise fails if any of the functions fail, then initialization doesnt work
        //Error will be displayed in web console if fails
        const [movies, screenings, genres] = await Promise.all([
            loadMovies(),
            loadScreenings(),
            loadAndPopulateGenres()
        ]);

        allMovies = movies;
        allScreenings = screenings;
        allGenres = genres;

        filterMovies();

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}

function filterMovies() {

    const selectedGenre =
        document.getElementById("genreSelect").value;

    const searchText =
        document.getElementById("castSearch").value.toLowerCase();

    const selectedDate =
        document.getElementById("date").value;

    const filteredMovies = allMovies.filter(movie => {

        //Genre filter
        const matchesGenre =
            selectedGenre === "" ||
            movie.genre === selectedGenre;

        //Search filter
        //Text search currently searches by movie title, later to change to cast members.
        const matchesSearch =
            movie.movieTitle.toLowerCase().includes(searchText);

        //Date filtering
        const matchesDate =
            selectedDate === "" ||
            allScreenings.some(screening => {

                return screening.movie.id === movie.id &&
                    screening.startTime.startsWith(selectedDate);
            })

        //Only returns movies where matchesGenre, matchesSearch and matchesDate are all true
        return matchesGenre && matchesSearch && matchesDate && movie.active===true;
    });

    displayMovies(filteredMovies);
}

function displayMovies(movies) {

    const movieGrid = document.getElementById("movieGrid");

    movieGrid.innerHTML = "";

    movies.forEach(movie => {

        const card = document.createElement("div");
        card.classList.add("movie-card");

        const image = document.createElement("div");
        image.classList.add("movie-image");

        const title = document.createElement("h3");
        title.textContent = movie.movieTitle;

        card.appendChild(image);
        card.appendChild(title);

        card.addEventListener("click", () => {

            sessionStorage.setItem("movie", JSON.stringify(movie));

            window.location.href = "edit_movie.html?id=" + movie.id;

        });

        movieGrid.appendChild(card);
    });
}