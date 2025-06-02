document.addEventListener('DOMContentLoaded', () => {
    const loadButton = document.getElementById('loadItemsBtn');
    const dateInput = document.getElementById('filterDate');

    loadButton.addEventListener('click', () => {
        const selectedDate = dateInput.value;

        if (!selectedDate) {
            alert("Please select a date first.");
            return;
        }

        const url = `/api/itemsAsOf?date=${encodeURIComponent(selectedDate)}`;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to fetch items: " + response.statusText);
                }
                return response.json();
            })
            .then(items => {
                const tableBody = document.getElementById('itemTableBody');
                tableBody.innerHTML = "";

                if (items.length === 0) {
                    tableBody.innerHTML = "<tr><td colspan='4'>No items found for selected date.</td></tr>";
                    return;
                }

                items.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.name}</td>
                        <td>${item.sellIn}</td>
                        <td>${item.quality}</td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error(error);
                document.getElementById('itemTableBody').innerHTML = `
                    <tr><td colspan='4'>Error loading items</td></tr>
                `;
            });
    });
});
