
initializePage()

function initializePage(){
    const screeningsJson = sessionStorage.getItem("movieScreenings");

    const screenings = JSON.parse(screeningsJson);

    displayScreenings(screenings);
}

function displayScreenings(screenings) {

    const title = document.getElementById("movieTitle");
    title.innerText = screenings[0].movie.movieTitle;

    const description = document.getElementById("movieDescription");
    description.innerText = screenings[0].movie.description;

    const container = document.getElementById("screenings");

    container.innerHTML = "";

    screenings.forEach(screening => {

        const screeningCard = document.createElement("div");
        screeningCard.classList.add("screening-card");

        const date = document.createElement("div");
        date.classList.add("screening-date");

        const time = document.createElement("div");
        time.classList.add("screening-time");

        const theater = document.createElement("div");
        theater.classList.add("screening-theater");

        const startTime = new Date(screening.startTime);

        //formats Date
        date.textContent = startTime.toLocaleDateString("en-GB", {
            weekday: "long",
            day: "numeric",
            month: "long",
            year: "numeric"
        });

        //formats Time
        time.textContent = startTime.toLocaleTimeString("en-GB", {
            hour: "2-digit",
            minute: "2-digit"
        });

        theater.textContent = screening.theater.name;

        screeningCard.appendChild(date);
        screeningCard.appendChild(time);
        screeningCard.appendChild(theater);

        container.appendChild(screeningCard);

    });
}

//gave up, found a different solution
function displayWeek(){

    const week = document.createElement("div");
    week.classList.add("week");
}