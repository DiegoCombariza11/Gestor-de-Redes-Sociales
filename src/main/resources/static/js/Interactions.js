document.addEventListener('DOMContentLoaded', async function () {
    const ctx = document.getElementById('interactionChart').getContext('2d');

    async function getInteractions() {
        const request = await fetch('/interactions', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        const interactions = await request.json();
        const interactionData = [];

        interactions.forEach(interaction => {
            const interactionDate = new Date(interaction.date);
            const existingInteraction = interactionData.find(entry => entry.date.getTime() === interactionDate.getTime());

            if (existingInteraction) {
                existingInteraction.interactions += 1;
            } else {
                interactionData.push({ date: interactionDate, interactions: 1 });
            }
        });

        interactionData.sort((a, b) => a.date - b.date);

        return interactionData;
    }

    async function renderChart() {
        const interactionData = await getInteractions();

        const labels = interactionData.map(entry => entry.date.toISOString().split('T')[0]);
        const dataPoints = interactionData.map(entry => entry.interactions);

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Interacciones por día',
                    data: dataPoints,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    fill: false,
                    tension: 0.1
                }]
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'day',
                            tooltipFormat: 'MMM dd, yyyy'
                        },
                        title: {
                            display: true,
                            text: 'Fecha'
                        }
                    },
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Interacciones'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                },
                responsive: true,
                maintainAspectRatio: false
            }
        });
    }

    renderChart();
});