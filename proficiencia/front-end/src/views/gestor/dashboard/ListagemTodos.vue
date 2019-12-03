<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container grid-list-xl fluid>
    <v-layout row>
      <v-flex grow pa-1>
        <v-card>
          <v-toolbar card>
            <h3>Painel de Bordo</h3>
            <v-spacer></v-spacer>
          </v-toolbar>
        </v-card>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex grow pa-1>
        <v-card>
          <v-toolbar card color="white">
            <h4>
              <b>Visão Geral das Ocorrências</b>
            </h4>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-card-text>
            <div>
              <section class="charts">
                <div id="pizzaChart"></div>
              </section>
            </div>
            <v-flex>
              <H1></H1>
            </v-flex>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
/*import moment from "moment";
 */

//import VueHighcharts from "vue2-highcharts";
import Highcharts, { Pointer } from "highcharts";

//import * as data from "../../../assets/data.js";

export default {
  //components: { VueHighcharts },
  data() {
    return {
      exameSelecionado: '',
      detalheDialogo: false,
      examesQtd: 0,
      examesAcidente: [],
      examesAdmissional: [],
      examesDemissional: [],
      examePeriodico: [],
      unidadeR: [],
      unidadeRisco: "",
      atraso: 0,
      emDia: 0,
      proximoAvencer: 0,
      cardsAvaliacao: [],
      dialog: false,
      cards: [],
      totalNiveis: 0,
      impactos: [],
      probabilidades: [],
      niveisRisco: [],
      avaliacoesRisco: [],
      testeAvaliacoesRisco: [],
      riscos: {},
      ocorrencias: [],
      avaliacoes: [],
      datas: [],
      meses: [],
      qtdRisco: [
        { jan: 0 },
        { fev: 0 },
        { mar: 0 },
        { abr: 0 },
        { mai: 0 },
        { jun: 0 },
        { jul: 0 },
        { ago: 0 },
        { set: 0 },
        { out: 0 },
        { nov: 0 },
        { dez: 0 }
      ],
      mapa: {},
      mapaLoaded: false,
      //options: data.basicData,
      //Highcharts: Highcharts,
      percentualAtraso: 0,
      percentualEmDia: 0,
      percentualProximo: 0,
      riscoAmeaca: 0
    };
  },
  mounted() {
    this.listarExames();
  },
  methods: {
    perExames(){
      let perExam = []

        perExam.push({
          name: 'ACIDENTE DE TRABALHO',
          y: (this.examesAcidente.length / this.examesQtd) * 100
        })

        perExam.push({
          name: 'ADMISSIONAL',
          y: (this.examesAdmissional.length / this.examesQtd) * 100
        })

        perExam.push({
          name: 'DEMISSIONAL',
          y: (this.examesDemissional.length / this.examesQtd) * 100
        })

        perExam.push({
          name: 'PERIODICO',
          y: (this.examePeriodico.length / this.examesQtd) * 100
        })

      return perExam
    },


    async listarExames() {
      await axios.get("/exame/").then(res => {
        var todosExames;
        todosExames = res.data;
        this.examesQtd = todosExames.length;

        console.log(todosExames)

        for (let i = 0; i < todosExames.length; i++) {
          if (todosExames[i].nomeExame === "ACIDENTE DE TRABALHO") {
            this.examesAcidente.push(todosExames[i]);
          } else if (todosExames[i].nomeExame === "ADMISSIONAL") {
            this.examesAdmissional.push(todosExames[i]);
          } else if (todosExames[i].nomeExame === "DEMISSIONAL") {
            this.examesDemissional.push(todosExames[i]);
          } else {
            this.examePeriodico.push(todosExames[i]);
          }
        }
        console.log(todosExames[0])

      });
      
    

      Highcharts.chart("pizzaChart", {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false,
          type: "pie"
        },
        title: {
          text: "Exame/Procedimentos dos funcionarios"
        },
        tooltip: {
          pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
             point:{
                  events:{
                      click: function(e){
                          this.exameSelecionado = e.point.name
                          this.detalheDialogo = true
                          console.log(this.exameSelecionado)
                      } 
                  }
              },
            dataLabels: {
              enabled: false
            },
            
            showInLegend: true
          }
        },
        series: [{
          name:'exames',
          colorByPoint: true,
          data: this.perExames()
        }]
      });
    }
  },
};
</script>

<style scoped>
p {
  margin-top: 150px;
  margin-bottom: 100px;
  margin-right: 150px;
  margin-left: 30px;
}
table.matriz {
  margin-top: -350px;
  margin-bottom: 100px;
  margin-right: 150px;
  margin-left: 100px;
}
/*.cardNome{
      margin-top: 50px;
      margin-bottom: 0px;
      margin-right: 100px;
      margin-left: 100px;
    }
    .cardValor{
      margin-top: 0px;
      margin-bottom: 0px;
      margin-right: 100px;
      margin-left: 100px;
    }*/
</style>
