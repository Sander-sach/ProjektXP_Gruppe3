import {loadAndPopulateGenres, loadMovies, loadScreenings} from "./loadDataFunctions.js";

//This list always contains all movies. Instead we make temporary lists for filtering
let allMovies = [];
let allScreenings = [];

// EventListeners - Automatically update when filters change
document.getElementById("genreSelect")
    .addEventListener("change", filterMovies);

document.getElementById("castSearch")
    .addEventListener("input", filterMovies);

document.getElementById("date")
    .addEventListener("change", filterMovies);

initializePage();


//Filtering by date becomes different to the movie filtering,
// because it needs to filter the screenings and not the movies,
// and if there are multiple criteria, then filter screenings in relation to the movies

// Filter movies based on selected filters
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
        return matchesGenre && matchesSearch && matchesDate;
    });

    displayMovies(filteredMovies);
}

// Display movies in the grid
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

            const movieScreening = allScreenings.filter(screening => screening.movie.id === movie.id);

            sessionStorage.setItem("movieScreenings", JSON.stringify(movieScreening));

            window.location.href = "screenings_for_movie.html?id=" + movie.id;

        });

        movieGrid.appendChild(card);
    });
}


// Load initial data
async function initializePage() {

    try {
        //Promise fails if any of the functions fail, then initialization doesnt work
        //Error will be displayed in web console if fails
        [allMovies, allScreenings] = await Promise.all([
            loadMovies(),
            loadScreenings(),
            loadAndPopulateGenres()
        ]);

        filterMovies();

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}