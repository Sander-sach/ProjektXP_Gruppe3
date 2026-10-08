let allMovies = [];
let allTheaters = [];
let allScreenings = [];

async function loadData() {

    const [movieResponse, theaterResponse, screeningResponse] =
        await Promise.all([
            fetch("/api/movies"),
            fetch("/api/theaters"),
            fetch("/api/screenings")
        ]);


    if (!movieResponse.ok || !theaterResponse.ok || !screeningResponse.ok) {
        throw new Error("Failed to load screening data");
    }

    allMovies = await movieResponse.json();
    allTheaters = await theaterResponse.json();
    allScreenings = await screeningResponse.json();
}

function populateMovies() {
    const movieSelect =
        document.getElementById("movieSelection");

    allMovies.forEach(movie =>  {
        if (movie.active===true) {
            const option = document.createElement("option");

            option.value = movie.id;

            option.textContent = `${movie.movieTitle} (${movie.duration} min)`;

            movieSelect.appendChild(option);
        }
    });
}


function addScreeningSection() {

    const container = document.getElementById("screeningsContainer");

    const section = document.createElement("div");

    section.classList.add("screening-section");

    const screeningNumber = container.children.length +1;

    section.innerHTML = `
        <h3>Screening ${screeningNumber}</h3>
        
        <input
            type="datetime-local"
            class="screening-date"
        >
        
        <select class="screening-theater" disabled>
            <option value="">Select date and time first</option>
        </select>
        
        <button type="button" class="remove-screening">
            Remove
        </button>
    `;

    container.appendChild(section);

    //querySelector(".") ensure that we get the data belonging to
    //the specific screening, where getElementById looks for a specific unique ID
    const dateInput = section.querySelector(".screening-date");

    const theaterSelect = section.querySelector(".screening-theater")

    dateInput.addEventListener("change", () => {
        updateAvailableTheaters(section);
    });

    theaterSelect.addEventListener("change", () => {
        handleScreeningCompleted(section);
    });

    section.querySelector(".remove-screening")
        .addEventListener("click", () => {
            section.remove();
            renumberScreenings();
        });
}



function handleScreeningCompleted(section) {

    const date = section.querySelector(".screening-date").value;

    const theater = section.querySelector(".screening-theater").value;

    if (date === "" || theater === "") {
        return;
    }

    const container = document.getElementById("screeningsContainer");

    const lastSection = container.lastElementChild;

    //This checks to see if the last section is the current section being worked on.
    //To prevent a new section form appearing everytime a previous section gets edited
    if (section === lastSection) {
        addScreeningSection();
    }

    refreshTheaterOptions();
}



function renumberScreenings() {

    const section =
        document.querySelectorAll(".screening-section");

    section.forEach((section, index) => {
        section.querySelector("h3").textContent =
            `Screening ${index + 1}`;
    });
}


//Calculates the end time of a screening
function calculateEndTime(startTime, duration) {

    const endTime = new Date(startTime);

    endTime.setMinutes(endTime.getMinutes() + duration);

    return endTime;
}



//Check whether a theater is available
function isTheaterAvailable(theaterId, startTime, currentSection) {

    const movieId =
        document.getElementById("movieSelection").value;

    const selectedMovie =
        allMovies.find(movie => movie.id === Number(movieId));

    if (!selectedMovie || !startTime) {
        return false;
    }

    const newStart = new Date(startTime);

    const newEnd = calculateEndTime(startTime, selectedMovie.duration);

    const databaseConflict = allScreenings.some(screening => {

        if (screening.theater.id !== theaterId) {
            return false;
        }

        const existingMovie =
            allMovies.find(movie => movie.id === screening.movie.id);

        if (!existingMovie) {
            return true;
        }

        const existingStart =
            new Date(screening.startTime);

        const existingEnd =
            calculateEndTime(screening.startTime, existingMovie.duration);

        return newStart < existingEnd &&
            newEnd > existingStart;
    });

    //Checking conflicts with screenings currently being created
    const pendingConflict =
        [...document.querySelectorAll(".screening-section")]
            .some(section => {

                //To not compare a section with itself
                if (section === currentSection) {
                    return false;
                }

                const date =
                    section.querySelector(".screening-date").value;

                const theater =
                    section.querySelector(".screening-theater").value;

                if (!date || Number(theater) !== theaterId) {
                    return false;
                }

                const pendingStart = new Date(date);

                const pendingEnd = calculateEndTime(date, selectedMovie.duration);

                return newStart < pendingEnd &&
                    newEnd > pendingStart;
            });

    return !databaseConflict && !pendingConflict; //Returns true if both are false
}


//Only use available theaters
function updateAvailableTheaters(section) {

    const date = section.querySelector(".screening-date").value;

    const theaterSelect = section.querySelector(".screening-theater");

    const previousSelection = theaterSelect.value;

    theaterSelect.innerHTML =
        `<option value="">Select Theater</option>`;

    const movieId =
        document.getElementById("movieSelection").value;

    if (!date || !movieId) {
        theaterSelect.disabled = true;
        return;
    }

    theaterSelect.disabled = false;

    allTheaters.forEach(theater => {

        if (isTheaterAvailable(theater.id, date, section)) {

            const option = document.createElement("option");

            option.value = theater.id;
            option.textContent = theater.name;

            theaterSelect.appendChild(option);
        }
    });

    //Preserve the selection if theater is still available
    if ([...theaterSelect.options].some(
        option => option.value === previousSelection
    )) {
        theaterSelect.value = previousSelection;
    }
}


//To keep updating theaters in case a previous screening gets changed
function refreshTheaterOptions() {

    document.querySelectorAll(".screnning-section")
        .forEach(section => {
            updateAvailableTheaters(section);
        });
}


async function saveScreenings() {

    const movieId =
        Number(document.getElementById("movieSelection").value);

    const sections =
        document.querySelectorAll(".screening-section");

    const screenings = [];

    sections.forEach(section => {

        const startTime =
            section.querySelector(".screening-date").value;

        const theaterId =
            section.querySelector(".screening-theater").value;

        if (startTime && theaterId) {

            //.push adds an object directly to the end of an array, without creating a new one
            screenings.push({
                movieId: movieId,
                theaterId: Number(theaterId),
                startTime: startTime
            });
        }
    });

    if (!movieId || screenings.length === 0) {
        alert("Please select a movie and at least one screening");
        return;
    }

    try {

        const response = await fetch("/api/screenings/batch", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(screenings)
        });

        if (!response.ok) {
            throw new Error("Failed to save screenings");
        }

        alert("Screenings created successfully");

    } catch (error) {
        console.error(error);
    }
}


async function initializePage() {

    try {
        await loadData();

        populateMovies();

        addScreeningSection();

    } catch (error) {
        console.error("Failed to initialize page: ", error);
    }
}


document.getElementById("movieSelection")
    .addEventListener("change", () => {
        refreshTheaterOptions();
    });

document.getElementById("resetBtn")
    .addEventListener("click", () => {
        document.getElementById("movieSelection").value = "";
        document.getElementById("screeningsContainer").innerHTML = "";
        addScreeningSection();
    });

document.getElementById("addBtn")
    .addEventListener("click", saveScreenings);

initializePage();
