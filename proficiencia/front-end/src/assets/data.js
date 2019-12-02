export let basicData = {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Exame/Procedimentos dos funcionarios'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: false
            },
            showInLegend: true
        }
    },
    series: [{
        name: 'Brands',
        colorByPoint: true,
        data: [{
            name: 'Acidente de Trabalho',
            y: 61.41,
            sliced: true,
            selected: true
        }, {
            name: 'Exame Periodico',
            y: 11.84
        }, {
            name: 'Exame Admissional',
            y: 10.85
        }, {
            name: 'Exame Demissional',
            y: 4.67
        }]
    }]
};
