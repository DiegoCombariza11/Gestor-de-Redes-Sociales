import { Interactions } from './Interactions.js';
import { TimePost } from './TimePost.js';

class Statistics {
    constructor() {
        this.interactions = new Interactions();
        this.timePost = new TimePost();
    }

    async generateCharts() {
        const ctx1Element = document.getElementById('interactionChart1');
        const ctx2Element = document.getElementById('interactionChart2');

        if (ctx1Element && ctx2Element) {
            const ctx1 = ctx1Element.getContext('2d');
            const ctx2 = ctx2Element.getContext('2d');

            await this.interactions.renderChart(ctx1);
            await this.timePost.renderChart(ctx2);
        } else {
            console.error('Chart elements not found');
        }
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const stats = new Statistics();
    stats.generateCharts();
});
