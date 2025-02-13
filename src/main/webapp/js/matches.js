document.addEventListener("DOMContentLoaded", function () {
    const matchesPerPage = 5;
    const filterInput = document.querySelector('.input-filter');
    const filterBtn = document.querySelector(".btn-filter");

    const dataElement = document.getElementById('data');
    if (dataElement) {
        const jsonData = JSON.parse(dataElement.textContent);

        window.totalMatchesCount = jsonData.totalMatchesCount;
        window.page = jsonData.page;
        window.filteredPlayerName = jsonData.filteredPlayerName;
    }

    const page = window.page;
    const totalMatchesCount = window.totalMatchesCount;
    const filteredPlayerName = window.filteredPlayerName;

    function showPage() {
        updatePaginationButtons(page);
    }

    function updatePaginationButtons(currentPage) {
        // Number buttons
        const paginationDiv = document.querySelector('.pagination');
        paginationDiv.innerHTML = ''; // Clearing previous buttons

        const totalPages = Math.ceil(totalMatchesCount / matchesPerPage);
        const startPage = Math.max(1, currentPage - 1);
        const endPage = currentPage === 1 ? Math.min(totalPages, currentPage + 2) : Math.min(totalPages, currentPage + 1);
        for (let i = startPage; i <= endPage; i++) {
            const button = document.createElement('button');
            button.textContent = i;
            button.className = (i === currentPage) ? 'active' : '';
            button.addEventListener('click', () => {
                currentPage = i;
                const url = `/matches?page=${currentPage}&filter_by_player_name=${encodeURIComponent(filteredPlayerName)}`;
                redirectToNewPage(url);
            });
            paginationDiv.appendChild(button);
        }

        // Next and Previous Buttons
        if (currentPage > 1) {
            const prevButton = document.createElement('button');
            prevButton.textContent = 'Previous';
            prevButton.addEventListener('click', () => {
                currentPage--;
                const url = `/matches?page=${currentPage}&filter_by_player_name=${encodeURIComponent(filteredPlayerName)}`;
                redirectToNewPage(url);
            });
            paginationDiv.prepend(prevButton);
        }

        if (currentPage < totalPages) {
            const nextButton = document.createElement('button');
            nextButton.textContent = 'Next';
            nextButton.addEventListener('click', () => {
                currentPage++;
                const url = `/matches?page=${currentPage}&filter_by_player_name=${encodeURIComponent(filteredPlayerName)}`;
                redirectToNewPage(url);
            });
            paginationDiv.appendChild(nextButton);
        }
    }

    function handleFilterClick(event) {
        event.preventDefault();

        const inputValue = filterInput.value.trim();

        if (inputValue) {
            url = `/matches?filter_by_player_name=${encodeURIComponent(inputValue)}`;
            redirectToNewPage(url);
        } else {
            url = `/matches?filter_by_player_name=`;
            redirectToNewPage(url);
        }
    }

    function redirectToNewPage(url) {
        window.location.assign(url);
    }

    filterBtn.addEventListener("click", handleFilterClick);

    showPage(page);
});


