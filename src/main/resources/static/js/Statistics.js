
import { Interactions } from './Interactions.js';
import { TimePost } from './TimePost.js';

class Statistics {
    constructor(chart) {
        this.interactions = new Interactions(chart);
        this.timePost = new TimePost(chart);
    }

    async generateCharts() {
        const ctx1 = document.getElementById('interactionChart1').getContext('2d');
        const ctx2 = document.getElementById('interactionChart2').getContext('2d');

        await this.interactions.renderChart(ctx1);
        await this.timePost.renderChart(ctx2);
    }
}
const graphElement1 = document.getElementById('interactionChart1');
const graphElement2 = document.getElementById('interactionChart2');

this.generateCharts();