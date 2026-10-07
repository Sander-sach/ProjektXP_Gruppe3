import {loadMovies, loadScreenings, loadReservations, loadSeats} from "./loadDataFunctions.js";

//These lists always contain all data. The statistics are calculated from them
let allMovies = [];
let allScreenings = [];
let allReservations = [];
let allSeats = [];

//Occupancy under LOW_OCCUPANCY = the movie does not attract enough viewers
//Occupancy over HIGH_OCCUPANCY = plan extra screenings
const LOW_OCCUPANCY = 30;
const HIGH_OCCUPANCY = 70;



// Calculate tickets sold and occupancy for every active movie
// Occupancy = tickets sold / seats in all the movie's screenings
function calculateStatistics() {

    const activeMovies = allMovies.filter(movie => movie.active);

    const statistics = activeMovies.map(movie => {

        const movieScreenings = allScreenings.filter(screening => screening.movie.id === movie.id);

        let ticketsSold = 0;
        let totalSeats = 0;

        movieScreenings.forEach(screening => {

            //Seats in the theater the screening is shown in
            totalSeats += allSeats.filter(seat => seat.theater.id === screening.theater.id).length;

            //Tickets sold to the screening
            allReservations
                .filter(reservation => reservation.screening.id === screening.id)
                .forEach(reservation => ticketsSold += reservation.numberOfPeople);
        });

        let occupancy = 0;
        if (totalSeats > 0) {
            occupancy = ticketsSold * 100 / totalSeats;
        }

        return {
            movie: movie,
            screenings: movieScreenings.length,
            ticketsSold: ticketsSold,
            totalSeats: totalSeats,
            occupancy: occupancy
        };
    });

    //Lowest occupancy first
    statistics.sort((a, b) => a.occupancy - b.occupancy);

    return statistics;
}

function getStatus(occupancy) {

    if (occupancy < LOW_OCCUPANCY) {
        return "Low";
    }

    if (occupancy >= HIGH_OCCUPANCY) {
        return "High";
    }

    return "Normal";
}

// Display statistics in the table
function displayStatistics(statistics) {

    const statisticsBody = document.getElementById("statisticsBody");

    statisticsBody.innerHTML = "";

    statistics.forEach(statistic => {

        const row = document.createElement("tr");

        const title = document.createElement("td");
        title.textContent = statistic.movie.movieTitle;

        const screenings = document.createElement("td");
        screenings.textContent = statistic.screenings;

        const tickets = document.createElement("td");
        tickets.textContent = statistic.ticketsSold + " / " + statistic.totalSeats;

        const occupancy = document.createElement("td");
        occupancy.textContent = statistic.occupancy.toFixed(1) + " %";

        const status = document.createElement("td");
        status.textContent = getStatus(statistic.occupancy);
        status.classList.add("status-" + getStatus(statistic.occupancy).toLowerCase());

        const removeCell = document.createElement("td");
        const removeButton = document.createElement("button");
        removeButton.textContent = "Remove";
        removeButton.addEventListener("click", () => removeMovie(statistic.movie));
        removeCell.appendChild(removeButton);

        row.appendChild(title);
        row.appendChild(screenings);
        row.appendChild(tickets);
        row.appendChild(occupancy);
        row.appendChild(status);
        row.appendChild(removeCell);

        statisticsBody.appendChild(row);
    });
}

// Remove a movie from the program and reload the table
async function removeMovie(movie) {

    if (!confirm("Remove " + movie.movieTitle + " from the program?")) {
        return;
    }

    const response = await fetch("/api/movies/" + movie.id, {
        method: "DELETE"
    });

    if (!response.ok) {
        console.error("Failed to remove movie");
        return;
    }

    await initializePage();
}



// Load initial data
async function initializePage() {

    try {
        //Promise fails if any of the functions fail, then initialization doesnt work
        [allMovies, allScreenings, allReservations, allSeats] = await Promise.all([
            loadMovies(),
            loadScreenings(),
            loadReservations(),
            loadSeats()
        ]);


        displayStatistics(calculateStatistics());

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}

initializePage();