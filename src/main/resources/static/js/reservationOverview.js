import {loadMovies, loadScreenings, loadReservations, loadSeats} from "./loadDataFunctions.js";

let allMovies = [];
let allScreenings = [];
let allReservations = [];
let allSeats = [];

initializePage();

function displayReservations() {
    const reservationBody = document.getElementById("reservation-body");
    reservationBody.innerHTML = "";

    allReservations.forEach(reservation => {

        const row = document.createElement("tr");

        const name = document.createElement("td");
        const tickets = document.createElement("td");
        const phone = document.createElement("td");
        const movie = document.createElement("td");
        const date = document.createElement("td");
        const time = document.createElement("td");
        const theater = document.createElement("td");

        name.textContent = reservation.customerName;
        tickets.textContent = reservation.numberOfPeople;
        phone.textContent = reservation.customerMobile;
        movie.textContent = reservation.screening.movie.movieTitle;
        theater.textContent = reservation.screening.theater.name;

        const timeDate = new Date(reservation.screening.startTime);

        //formats Date
        date.textContent = timeDate.toLocaleDateString("en-GB", {
            day: "2-digit",
            month: "2-digit",
            year: "2-digit"
        });

        //formats Time
        time.textContent = timeDate.toLocaleTimeString("en-GB", {
            hour: "2-digit",
            minute: "2-digit"
        });

        row.appendChild(name);
        row.appendChild(tickets);
        row.appendChild(phone);
        row.appendChild(movie);
        row.appendChild(date);
        row.appendChild(time);
        row.appendChild(theater);

        reservationBody.appendChild(row);
    })
}

// Load initial data
async function initializePage() {

    try {
        //Promise fails if any of the functions fail, then initialization doesnt work
        [allReservations] = await Promise.all([
            loadReservations()
        ]);

        console.log("reservations")
        console.log(allReservations[0])

        displayReservations()

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}