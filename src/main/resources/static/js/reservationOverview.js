let allMovies = [];
let allScreenings = [];
let allReservations = [];
let allSeats = [];

initializePage();




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