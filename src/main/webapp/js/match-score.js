const appContext = window.location.pathname.split('/')[1];
const baseUrl = `${window.location.origin}/${appContext}`;

async function handleScoreAdd(event) {
    const element = event.target;
    const matchUUID = element.getAttribute("data-uuid");
    const player = element.getAttribute("data-winner");

    const data = {
        "winner": player
    };
    // Dynamic url
    const url = `${baseUrl}/match-score?uuid=` + matchUUID;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        if (response.status == 200) {
            let url = `${baseUrl}/match-score?uuid=` + matchUUID;
            redirectToNewPage(url);
        }
    } catch (error) {
        console.error('Ошибка:', error);
        errorMessageElem.textContent = 'Произошла ошибка при отправке данных.';
    }
}

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
