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
                                <vue-highcharts :options="options" ></vue-highcharts>
                            </section>
                        </div>
                    </v-card-text>

                </v-card>
            </v-flex>
        </v-layout>

    </v-container>

</template>

<script>
    /*import axios from "axios";
    import moment from "moment";
*/
    import VueHighcharts from "vue2-highcharts";
    import Highcharts from "highcharts";

    import * as data from "../../../assets/data.js";

    export default {
        components: { VueHighcharts },
        data() {
            return {
                unidadeR:[],
                unidadeRisco:'',
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
                options: data.basicData,
                Highcharts: Highcharts,
                percentualAtraso: 0,
                percentualEmDia: 0,
                percentualProximo: 0,
                riscoAmeaca: 0
            };
        },
        mounted() {
          /*  this.listar();
            this.load();
            this.listR();*/
        },
        methods: {

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
