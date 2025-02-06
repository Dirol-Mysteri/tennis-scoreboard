document.addEventListener("DOMContentLoaded", function() {
    const matchesPerPage = 3;
    let currentPage = 0;
    const filterInput = document.querySelector('.input-filter');
    const filterBtn = document.querySelector(".btn-filter");

    const matches = document.querySelectorAll('.match');

    function showPage(page) {
        const startIndex = page * matchesPerPage;
        const endIndex = startIndex + matchesPerPage;

        matches.forEach((match, index) => {
            match.style.display = (index >= startIndex && index < endIndex) ? 'table-row' : 'none';
        });

        updatePaginationButtons(page);
    }

    function updatePaginationButtons(currentPage) {
        const paginationDiv = document.querySelector('.pagination');
        paginationDiv.innerHTML = ''; // Очищаем предыдущие кнопки

        const totalPages = Math.ceil(matches.length / matchesPerPage);
        const startPage = Math.max(0, currentPage - 1);
        const endPage = Math.min(totalPages - 1, currentPage + 1);

        for (let i = startPage; i <= endPage; i++) {
            const button = document.createElement('button');
            button.textContent = i + 1;
            button.className = (i === currentPage) ? 'active' : '';
            button.addEventListener('click', () => {
                currentPage = i;
                showPage(currentPage);
            });
            paginationDiv.appendChild(button);
        }

        // Добавляем кнопки "Предыдущая" и "Следующая"
        if (currentPage > 0) {
            const prevButton = document.createElement('button');
            prevButton.textContent = 'Предыдущая';
            prevButton.addEventListener('click', () => {
                currentPage--;
                showPage(currentPage);
            });
            paginationDiv.prepend(prevButton);
        }

        if (currentPage < totalPages - 1) {
            const nextButton = document.createElement('button');
            nextButton.textContent = 'Следующая';
            nextButton.addEventListener('click', () => {
                currentPage++;
                showPage(currentPage);
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
        }
    }

    function redirectToNewPage(url) {
        window.location.assign(url);
    }

    filterBtn.addEventListener("click", handleFilterClick);

    showPage(currentPage);
});


