import ResumeGraph from './modules/ResumeGraph.js';

fetch('/user/evento/resumen/json')
    .then(res => res.json())
    .then(data =>{
        let canva = document.getElementById('balance');

        let graph = new ResumeGraph(data, canva);
    }
);