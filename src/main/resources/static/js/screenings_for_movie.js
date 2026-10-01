const params = new URLSearchParams(window.location.search);

const movieId = params.get("id");

const screeningsJson = sessionStorage.getItem("movieScreenings");

const screenings = JSON.parse(screeningsJson);

console.log(screenings);

displayScreenings(screenings);

function displayScreenings(screenings) {

    const container = document.getElementById("screenings");

    container.innerHTML = "";

    screenings.forEach(screening => {

        console.log(screening);

        const screeningElement = document.createElement("div");

        screeningElement.textContent =
            screening.startTime;

        container.appendChild(screeningElement);
    });
}

function displayWeek(){

    const week = document.createElement("div");
    week.classList.add("week");
}