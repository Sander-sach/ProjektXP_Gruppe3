document.addEventListener("DOMContentLoaded", () => {

    // Load screening from sessionStorage
    const screeningJson = sessionStorage.getItem("selectedScreening");
    const screeningInfoDiv = document.getElementById("screeningInfo");

    if (screeningJson) {
        const screening = JSON.parse(screeningJson);
        const startTime = new Date(screening.startTime);

        screeningInfoDiv.textContent =
            screening.movie.movieTitle + " – " + startTime.toLocaleString("en-GB");
    } else {
        screeningInfoDiv.textContent = "No screening selected.";
    }

    // Handle form submission
    const form = document.getElementById("reservationForm");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const screening = JSON.parse(sessionStorage.getItem("selectedScreening"));

        const reservation = {
            screeningId: screening.id,
            customerName: document.getElementById("name").value,
            customerMobile: document.getElementById("mobile").value,
            numberOfPeople: parseInt(document.getElementById("people").value)
        };


        try {
            const response = await fetch("api/reservation", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(reservation)
            });

            console.log("Response:", response);

        } catch (err) {
            console.error("Error:", err);
        }
    });
});