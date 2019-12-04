<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container grid-list-xl fluid>
    <v-layout row>
      <v-flex grow pa-1>
        <v-card>
          <v-toolbar card>
            <h3>Visão Geral das Ocorrências</h3>
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
              <b>Total de Funcionários: {{this.usuarios.length}}</b>
            </h4>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-card-text>
            <div>
              <section class="charts">
                <div id="pizzaChart"></div>
                <v-row align="center">
                <v-flex xs12 class="text-xs-center">
                  <v-btn text small @click="showDialog('ACIDENTE DE TRABALHO')" color="primary">ACIDENTE DE TRABALHO</v-btn>
                  <v-btn text small @click="showDialog('ADMISSIONAL')" color="primary">ADMISSIONAL</v-btn>
                  <v-btn text small @click="showDialog('DEMISSIONAL')" color="primary">DEMISSIONAL</v-btn>
                  <v-btn text small @click="showDialog('PERIODICO')" color="primary">PERIÓDICO</v-btn>
                  <v-btn text small @click="showDialog('TODOSOSEXAMES')" color="primary">TODOS OS EXAMES</v-btn>
                </v-flex>
                </v-row>
              
              </section>
            </div>
            <v-flex>
              <H1></H1>
            </v-flex>
          </v-card-text>
        </v-card>
      </v-flex>
      <v-dialog v-model="detalheDialogo" fullscreen hide-overlay transition="dialog-bottom-transition">
        <v-card>
          <v-toolbar dark color="primary" class="noneImprime">
            <v-btn icon dark @click="detalheDialogo = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>Exames</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn dark text v-on:click="dialog = false" onClick="window.print()">Gerar Relatório</v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-data-table
            :headers="headers"
            :items="exames"
            :items-per-page="5"
            class="elevation-1"
            no-data-text="Nenhum resultado encontrado"
            no-results-text="Nenhum resultado encontrado"
          >
           <template v-slot:items="props">
                <tr @click="props.expanded = !props.expanded">
                  <td class="text-xs-left">{{ props.item.nomeExame }}</td>
                  <td class="text-xs-left">{{ props.item.descricao }}</td>
                  <td class="text-xs-left">{{ props.item.crmMedico }}</td>
                  <td class="text-xs-left">{{ props.item.avaliacaoMedica }}</td>
                  <td class="text-xs-left">{{ props.item.dataExame | data}}</td>
                  <td class="text-xs-left">{{ props.item.diaProximoExame | data}}</td>
<!--
                  <td class="text-xs-left">{{ props.item.diasAfastamento }}</td>
-->
                   <td class="text-xs-left" v-if="props.item.diaProximoExame
                    == props.item.dataExame "><i class="material-icons">done_outline</i></td>

                  <td class="text-xs-left" v-if="dataAtual > props.item.diaProximoExame &&
                    props.item.diaProximoExame != props.item.dataExame "><i class="material-icons">
                    report_problem
                  </i></td>

                  <td class="text-xs-left" v-if="dataAtual <= props.item.diaProximoExame"><i class="material-icons">
                    schedule
                  </i></td>


                </tr>
              </template>           
          </v-data-table>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";
//import VueHighcharts from "vue2-highcharts";
import Highcharts, { Pointer } from "highcharts";

//import * as data from "../../../assets/data.js";

export default {
  //components: { VueHighcharts },
  data() {
    return {
      headers: [
          {
            text: 'Tipo exame',
            align: 'left',
            sortable: false,
            value: 'nomeExame',
          },
          //{ text: 'Data exame', value: 'dataExame' },
          { text: 'Descrição', value: 'descricao' },
          { text: 'crmMedico', value: 'crmMedico' },
          { text: 'Avaliação Medica', value: 'avaliacaoMedica' },
          { text: 'Data Exame', value: 'dataExame' },
          { text: 'Data do Proximo Exame', value: 'diaProximoExame' },
/*
          { text: 'Dias de Afastamento', value: 'diasAfastamento' },
*/
          { text: 'Status do Exame', value: 'statusExame' },
        ],
      exameSelecionado: "",
      dataAtual: new Date().toISOString().substr(0, 10),
      exames: [],
      detalheDialogo: false,
      examesQtd: 0,
      examesTodos:[],
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
      riscoAmeaca: 0,
      usuarios:[]
    };
  },
  mounted() {
    this.listarExames();

  },
  created(){
    axios.get("usuario/listarUsuarios").then(res => {
      this.usuarios = res.data;
    });
  },
  filters: {
    data: function (data) {

      return data ? moment(data).format("LL") : "";


    },
  },
  methods: {
    showDialog(tipo){
      this.exames=[];
      this.detalheDialogo = true
      if(tipo === "ACIDENTE DE TRABALHO"){
        this.examesAcidente.forEach(item => {
          this.exames.push({
            'nomeExame'      : item.nomeExame,
            'descricao'      : item.descricao,
            'crmMedico'      : item.crmMedico,
            'cid'            : item.cid,
            'avaliacaoMedica': item.avaliacaoMedica,
            'dataExame': item.dataExame,
            'diaProximoExame': item.diaProximoExame,
            // 'diasAfastamento': item.diasAfastamento,
            'statusExame'    : item.statusExame
          })
        })
      }
      else if(tipo === "ADMISSIONAL"){
          this.examesAdmissional.forEach(item => {
          this.exames.push({
            'nomeExame'      : item.nomeExame,
            'descricao'      : item.descricao,
            'crmMedico'      : item.crmMedico,
            'cid'            : item.cid,
            'avaliacaoMedica': item.avaliacaoMedica,
            'dataExame': item.dataExame,
            'diaProximoExame': item.diaProximoExame,
            // 'diasAfastamento': item.diasAfastamento,
            'statusExame'    : item.statusExame
          })
        })
      }
      else if(tipo === "DEMISSIONAL"){
          this.examesDemissional.forEach(item => {
          this.exames.push({
            'nomeExame'      : item.nomeExame,
            'descricao'      : item.descricao,
            'crmMedico'      : item.crmMedico,
            'cid'            : item.cid,
            'avaliacaoMedica': item.avaliacaoMedica,
            'dataExame': item.dataExame,
            'diaProximoExame': item.diaProximoExame,
            // 'diasAfastamento': item.diasAfastamento,
            'statusExame'    : item.statusExame
          })
        })
      }
      else if(tipo === "PERIODICO"){
          this.examePeriodico.forEach(item => {
          this.exames.push({
            'nomeExame'      : item.nomeExame,
            'descricao'      : item.descricao,
            'crmMedico'      : item.crmMedico,
            'cid'            : item.cid,
            'avaliacaoMedica': item.avaliacaoMedica,
            'dataExame': item.dataExame,
            'diaProximoExame': item.diaProximoExame,
            // 'diasAfastamento': item.diasAfastamento,
            'statusExame'    : item.statusExame
          })
        })
      }
      else {
          this.examesTodos.forEach(item => {
          this.exames.push({
            'nomeExame'      : item.nomeExame,
            'descricao'      : item.descricao,
            'crmMedico'      : item.crmMedico,
            'cid'            : item.cid,
            'avaliacaoMedica': item.avaliacaoMedica,
            'dataExame': item.dataExame,
            'diaProximoExame': item.diaProximoExame,
            'diasAfastamento': item.diasAfastamento,
            'statusExame'    : item.statusExame
          })
        })
      }
    },
    perExames() {
      let perExam = [];

      perExam.push({
        name: "ACIDENTE DE TRABALHO",
        y: (this.examesAcidente.length / this.examesQtd) * 100
      });

      perExam.push({
        name: "ADMISSIONAL",
        y: (this.examesAdmissional.length / this.examesQtd) * 100
      });

      perExam.push({
        name: "DEMISSIONAL",
        y: (this.examesDemissional.length / this.examesQtd) * 100
      });

      perExam.push({
        name: "PERIODICO",
        y: (this.examePeriodico.length / this.examesQtd) * 100
      });

      // perExam.push({
      //   name: "TODOSOSEXAMES",
      //   y: (this.examesTodos.length / this.examesQtd) * 100
      // });

      return perExam;
    },

    async listarExames() {
      await axios.get("/exame/").then(res => {
        var todosExames;
        todosExames = res.data;
        this.examesQtd = todosExames.length;

        console.log(todosExames);

        for (let i = 0; i < todosExames.length; i++) {
          if (todosExames[i].nomeExame === "ACIDENTE DE TRABALHO") {
            this.examesAcidente.push(todosExames[i]);
            this.examesTodos.push(todosExames[i]);
          } else if (todosExames[i].nomeExame === "ADMISSIONAL") {
            this.examesAdmissional.push(todosExames[i]);
            this.examesTodos.push(todosExames[i]);
          } else if (todosExames[i].nomeExame === "DEMISSIONAL") {
            this.examesDemissional.push(todosExames[i]);
            this.examesTodos.push(todosExames[i]);
          } else {
            this.examePeriodico.push(todosExames[i]);
            this.examesTodos.push(todosExames[i]);
          } 
        
        }
        console.log(todosExames[0]);
      });

      Highcharts.chart("pizzaChart", {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false,
          type: "pie"
        },
        title: {
          text: "Exame/Procedimentos dos Funcionários"
        },
        tooltip: {
          pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: "pointer",
            point: {
              events: {
                click: function(e) {
                  this.exameSelecionado = e.point.name;
                  this.detalheDialogo = true;
                  console.log(this.exameSelecionado);
                }
              }
            },
            dataLabels: {
              enabled: false
            },

            showInLegend: true
          }
        },
        series: [
          {
            name: "Exames",
            colorByPoint: true,
            data: this.perExames()
          }
        ]
      });
    }
  }
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


@media print {
 .noneImprime {
   display: none;
 } 
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
