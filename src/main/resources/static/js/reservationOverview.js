import {loadReservations} from "./loadDataFunctions.js";

let allReservations = [];

// EventListeners - Automatically update when filters change

document.getElementById("search-reservation-bar")
    .addEventListener("input", filterReservations);

document.getElementById("search-input-date")
    .addEventListener("change", filterReservations);

initializePage();

//Filters Reservations - Runs when text is written in the search bar or a date is picked
function filterReservations() {

    // the text the user inputs into the search bar in lower case, so it's easier to compare
    const textInput = document.getElementById("search-reservation-bar").value.toLowerCase();
    //The date the user chooses in the calendar thing
    const selectedDate = document.getElementById("search-input-date").value;


    // if the input text matches ANY of the three things we wanna check, Name, Phone, Movie (and the date)
    // this lambda function returns "yes! Add this to the filtered list"
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

        //creates a new row, so we can insert info into the Table
        const row = document.createElement("tr");

        // creates relevant "cells" in the row - Look at the HTML same cells as the "header"
        const name = document.createElement("td");
        const tickets = document.createElement("td");
        const phone = document.createElement("td");
        const movie = document.createElement("td");
        const date = document.createElement("td");
        const time = document.createElement("td");
        const theater = document.createElement("td");

        //Grabs all needed info from the current reservation and inserts it into the elements we just created
        name.textContent = reservation.customerName;
        tickets.textContent = reservation.numberOfPeople;
        phone.textContent = reservation.customerMobile;
        movie.textContent = reservation.screening.movie.movieTitle;
        theater.textContent = reservation.screening.theater.name;

        //changes startTime to Date, so we can do JS stuff to make it readable/ pretty
        const timeDate = new Date(reservation.screening.startTime);

        //formats Date and then inserts it into the correct element
        date.textContent = timeDate.toLocaleDateString("en-GB", {
            day: "2-digit",
            month: "short"
        });

        //formats Time and the same as before
        time.textContent = timeDate.toLocaleTimeString("en-GB", {
            hour: "2-digit",
            minute: "2-digit"
        });

        //adds all the elements to the row
        row.appendChild(name);
        row.appendChild(tickets);
        row.appendChild(phone);
        row.appendChild(movie);
        row.appendChild(date);
        row.appendChild(time);
        row.appendChild(theater);

        // adds the row to the table body, so the data stays
        reservationBody.appendChild(row);
    })
}

// Load initial data
async function initializePage() {

    try {
        //Promise fails if any of the functions fail, then initialization doesnt work
        [allReservations] = await Promise.all([
            //imported function from loadDataFunctions.js
            loadReservations()
        ]);

        filterReservations()

    } catch (error) {
        console.error("Failed to initialize page", error);
    }
}