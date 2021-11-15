export default class ResumeGraph {

    constructor(data, canvaElement){

        this.renderData(data, canvaElement);

    }

    renderData(data, canvaElement) {
        const ctx = canvaElement.getContext('2d');
        let labels = this.getNames(data);
        let amounts = this.getAmounts(data);
        let colors = this.generateColors(amounts);
    
        const config = {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Resumen de saldos (Pesos Argentinos)',
                    data: amounts,
                    backgroundColor: colors,
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        };
    
        const myChart = new Chart(ctx, config);
    }
    
    getNames(data){
        let names = new Array();
    
        for (const key in data) {
            if (Object.hasOwnProperty.call(data, key)) {
                names.push(key);
            }
        }
    
        return names;
    
    }
    
    getAmounts(data){
        let amounts = new Array();
    
        for (const key in data) {
            if (Object.hasOwnProperty.call(data, key)) {
                let amount = data[key];
                amounts.push(amount);
            }
        }
    
        return amounts;
    
    }
    
    generateColors(amounts) {
    
        let colors = new Array();
    
        for (let index = 0; index < amounts.length; index++) {
            const element = amounts[index];
    
            if (element < 0) {
                colors.push('#c21616af');        
            }else{
                colors.push('#19a700af');
            }
    
        }
    
        return colors;
    
    }

}