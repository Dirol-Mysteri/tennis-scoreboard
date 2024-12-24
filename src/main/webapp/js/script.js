// Script for validating player names in the new match form

const formElem = document.querySelector("#new-match-form");
const errorMessageElem = document.querySelector("#new-match-form-error");

function checkFields(event) {

    const playerOneName = document.getElementById("playerOne").value;
    const playerTwoName = document.getElementById("playerTwo").value;

    if (!playerOneName || !playerTwoName) {
        errorMessageElem.style.display = "block";
        errorMessageElem.textContent = "Player names cannot be empty! Fill them out.";
        setTimeout(() => {
            errorMessageElem.style.display = "none";
        }, 5000);
        event.preventDefault();
        return;
    }

    if (playerOneName === playerTwoName) {
        errorMessageElem.style.display = "block";
        errorMessageElem.textContent = "Player names are equal! They should be different "
        setTimeout(() => {
            errorMessageElem.style.display = "none";
        }, 5000)
        event.preventDefault();
    }
}

formElem.addEventListener("submit", checkFields);
