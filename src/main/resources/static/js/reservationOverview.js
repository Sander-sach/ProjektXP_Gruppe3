import {loadReservations} from "./loadDataFunctions.js";

let allReservations = [];

// EventListeners - Automatically update when filters change

document.getElementById("search-reservation-bar")
    .addEventListener("input", filterReservations);

document.getElementById("search-input-date")
    .addEventListener("change", filterReservations);

initializePage();

//Filters Reservations - Runs when text is written or date is picked
function filterReservations() {
    const textInput = document.getElementById("search-reservation-bar").value.toLowerCase();

    const selectedDate = document.getElementById("search-input-date").value;
    console.log(selectedDate);


    const filteredReservations = allReservations.filter(reservation => {

        //Check if input matches any of the values we wanna check - Name, Phone, Movie Title
        const matchesName =
            reservation.customerName.toLowerCase().includes(textInput)
            || textInput==="";

        const matchesPhone =
            reservation.customerMobile.toLowerCase().includes(textInput)
            || textInput==="";

        const matchesMovie =
            reservation.screening.movie.movieTitle.toLowerCase().includes(textInput)
            || textInput==="";

        // Fixes timedate thing

        const matchesDate =
            reservation.screening.startTime.startsWith(selectedDate)
            || selectedDate === "";

        return (matchesName && matchesDate) || (matchesPhone && matchesDate) || (matchesMovie && matchesDate);
    })

    displayReservations(filteredReservations);

}

//Populates the table with filtered info
function displayReservations(filteredReservations) {
    const reservationBody = document.getElementById("reservation-body");
    reservationBody.innerHTML = "";

    filteredReservations.forEach(reservation => {

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
            month: "short"
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

        filterReservations()

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}