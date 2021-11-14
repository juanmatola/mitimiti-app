function getNames(data){
    let res = new Array();

    for (const key in data) {
        if (Object.hasOwnProperty.call(data, key)) {
            res.push(key);
        }
    }

    return res;

}

function getAmount(data){
    let res = new Array();

    for (const key in data) {
        if (Object.hasOwnProperty.call(data, key)) {
            let amount = data[key];
            res.push(amount);
        }
    }

    return res;

}

function generateColors(amounts) {

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

function renderData(data) {
    const ctx = document.getElementById('balance').getContext('2d');
    
    let labels = getNames(data);
    let amounts = getAmount(data);
    let colors = generateColors(amounts);

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

fetch('/user/evento/resumen/resume')
.then(res => res.json())
.then(data =>{
    console.log(data);
    renderData(data);
});