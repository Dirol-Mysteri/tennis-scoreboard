// Script for validating player names in the new match form

// Constants for error messages
const ERROR_EMPTY_NAMES = "Player names cannot be empty! Fill them out.";
const ERROR_SAME_NAMES = "Player names are equal! They should be different.";

// Selectors
const formElem = document.querySelector("#new-match-form");
const errorMessageElem = document.querySelector("#new-match-form-error");

function checkFields() {
    const playerOneName = document.getElementById("playerOne").value;
    const playerTwoName = document.getElementById("playerTwo").value;

    if (!playerOneName || !playerTwoName) {
        showError(ERROR_EMPTY_NAMES);
        return false;
    }

    if (playerOneName === playerTwoName) {
        showError(ERROR_SAME_NAMES);
        return false;
    }
    return true;
}

async function handleSubmit() {
    const formData = new FormData(formElem);
    const data = Object.fromEntries(formData);
    // Dynamic url
    const url = `${window.location.origin}/new-match`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        const jsonResponse = await response.json();

        // if (response.status === 409) {
        //     showError(jsonResponse.message);
        // }

        if (response.status === 200) {
            let url = "/match-score?uuid=" + jsonResponse.matchId;
            redirectToNewPage(url);
        }

    } catch (error) {
        console.error('Ошибка:', error);
        errorMessageElem.textContent = 'Произошла ошибка при отправке данных.';
    }
}

formElem.addEventListener("submit", async (event) => {
    event.preventDefault();
    if (checkFields()) {
        await handleSubmit();
    }
});

function redirectToNewPage(url) {
    window.location.assign(url);
}

function showError(message) {
    errorMessageElem.style.display = "block";
    errorMessageElem.textContent = message;
    setTimeout(() => {
        errorMessageElem.style.display = "none";
    }, 5000);
}
