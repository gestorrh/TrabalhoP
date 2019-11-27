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
            <v-flex shrink pa-1></v-flex>
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
                   <!-- &lt;!&ndash; Gerar Matriz &ndash;&gt;
                    <v-card-text v-if="mapaLoaded">
                        <div>
                            <div>
                                <div class="col-11 table-responsive">
                                    <v-flex align-center xs12 sm6>
                                        <v-container grid-list-md text-xs-center>
                                            <div class="p-2 text-uppercase">Impacto</div>
                                        </v-container>
                                        <div>
                                            <div>
                                                <p class="text-uppercase p-2 foo">Probabilidade</p>
                                            </div>
                                            <table class="matriz">
                                                <tbody>
                                                <tr>
                                                    <th></th>
                                                    <th v-for="(i, indexI) in impactos" :key="indexI" scope="row" >
                                                        <v-card color="white" class="dark&#45;&#45;text" mb-2 width="125">
                                                            <v-list two-line subheader>
                                                                <v-list-tile avatar>
                                                                    <v-list-tile-content>
                                                                        <v-list-tile-title>
                                                                            <b>{{i.nome}}</b>
                                                                        </v-list-tile-title>
                                                                        <v-list-tile-title>
                                                                            <b>{{i.valor}}</b>
                                                                        </v-list-tile-title>
                                                                    </v-list-tile-content>
                                                                </v-list-tile>
                                                            </v-list>
                                                        </v-card>
                                                    </th>
                                                </tr>
                                                <tr v-for="(p, indexP) in probabilidades" :key="indexP">
                                                    <th scope="row">
                                                        <v-card color="white" class="dark&#45;&#45;text" mb-2>
                                                            <v-list two-line subheader>
                                                                <v-list-tile avatar>
                                                                    <v-list-tile-content>
                                                                        <v-list-tile-title>
                                                                            <b>{{p.nome}}</b>
                                                                        </v-list-tile-title>
                                                                        <v-list-tile-title>
                                                                            <b>{{p.valor}}</b>
                                                                        </v-list-tile-title>
                                                                    </v-list-tile-content>
                                                                </v-list-tile>
                                                            </v-list>
                                                        </v-card>
                                                    </th>
                                                    <td v-for="(i, indexI) in impactos" :key="indexI">
                                                        <div>
                                                            <v-card>
                                                                <v-list two-line subheader>
                                                                    <v-list-tile
                                                                            v-bind:style="{backgroundColor: pegarNivelRisco(p, i).cor}"
                                                                    >
                                                                        <v-list-tile-content>
                                                                            <v-list-tile-title>{{ mapa[indexP + "" + indexI] }}</v-list-tile-title>
                                                                        </v-list-tile-content>
                                                                    </v-list-tile>
                                                                </v-list>
                                                            </v-card>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </v-flex>
                                </div>
                            </div>
                        </div>
                    </v-card-text>-->
                    <!-- Gráfico Histórico de Riscos-->
                    <!--<v-toolbar card color="white">
                        <h4>
                            <b>Ocorrências</b>
                        </h4>
                        <v-spacer></v-spacer>
                    </v-toolbar>
                    <div>
                        <section class="charts">
                            <vue-highcharts :options="options" ref="lineCharts"></vue-highcharts>
                        </section>
                    </div>-->
                </v-card>
            </v-flex>
            <!-- Cards -->
            <!--<v-flex d-flex xs12 sm6 md3 style="margin-bottom: -80px">
                <v-layout row wrap>
                    <v-flex> &lt;!&ndash; d-flex &ndash;&gt;
                        <v-layout row wrap>
                            <v-flex v-for="(c, indexC) in cards" :key="indexC" xs12> &lt;!&ndash; d-flex &ndash;&gt;
                                <v-card v-bind:style="{backgroundColor: c.cor }">
                                    <v-card-text class="title-md-center">
                                        <h1 class="cardNome">{{c.valor}}</h1>
                                        <h4 class="cardValor">{{c.nome}}</h4>
                                    </v-card-text>
                                </v-card>
                            </v-flex>

                        </v-layout>
                    </v-flex>

                    <v-flex> &lt;!&ndash; d-flex &ndash;&gt;
                        <v-layout row wrap>
                            <v-flex xs12> &lt;!&ndash; d-flex &ndash;&gt;
                                <v-card  color="primary" fixed dark app>
                                    <v-container style="background: #1565C0"
                                                 fluid
                                                 grid-list-lg
                                    >
                                        <h4 style="color: #EFEFEF">Monitoramento:</h4>
                                        <v-layout justify-space-between fill-height wrap class="tms">
                                            <v-flex >
                                                <v-autocomplete v-model="unidadeRisco" :items="unidadeR" item-text="sigla"
                                                                label="Unidade"
                                                                @change="filtroUnidade(unidadeRisco);"
                                                ></v-autocomplete>
                                                &lt;!&ndash;<v-text-field
                                                        id="inputNomeProcesso"
                                                        label="Nome do Processo*"
                                                        placeholder="Nome do processo"
                                                        required
                                                        type="text" @keyup="filtroUnidade(unidadeRisco)"
                                                ></v-text-field>&ndash;&gt;
                                            </v-flex>
                                        </v-layout>
                                    </v-container>
                                    <v-layout row wrap v-if="riscos.length > 0">
                                        <v-flex xs6 style="text-align: center;color: #EFEFEF" >
                                            <h2>{{this.emDia}}</h2>
                                            <h5>({{this.percentualEmDia}}%)</h5>
                                            <h5>Em dia</h5>
                                        </v-flex>

                                        <v-flex xs6 style="text-align: center;color: #EFEFEF">
                                            <h2>{{this.proximoAvencer}}</h2>
                                            <h5>({{this.percentualProximo}}%)</h5>
                                            <h5>Próximos a &nbsp;Vencer</h5>
                                        </v-flex>
                                    </v-layout>
                                    <v-layout row wrap v-if="riscos.length > 0">
                                        <v-flex xs6 style="text-align: center;color: #EFEFEF">
                                            <h2>{{this.atraso}}</h2>
                                            <h5>({{this.percentualAtraso}}%)</h5>
                                            <h5>Atrasados</h5>
                                        </v-flex>
                                    </v-layout>


                                    <v-layout row wrap v-if="riscos.length ===  0">
                                        <v-flex xs6 style="text-align: center;color: #EFEFEF" >
                                            <h2>{{0}}</h2>
                                            <h5>({{0}}%)</h5>
                                            <h5>Em dia</h5>
                                        </v-flex>

                                        <v-flex xs6 style="text-align: center;color: #EFEFEF">
                                            <h2>{{0}}</h2>
                                            <h5>({{0}}%)</h5>
                                            <h5>Próximos a &nbsp;Vencer</h5>
                                        </v-flex>
                                    </v-layout>
                                    <v-layout row wrap v-if="riscos.length ===  0">
                                        <v-flex xs6 style="text-align: center;color: #EFEFEF">
                                            <h2>{{0}}</h2>
                                            <h5>({{0}}%)</h5>
                                            <h5>Atrasados</h5>
                                        </v-flex>
                                    </v-layout>

                                </v-card>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>-->

        </v-layout>

    </v-container>

</template>

<script>
    /*import axios from "axios";
    import moment from "moment";

    import VueHighcharts from "vue2-highcharts";
    import Highcharts from "highcharts";
    import * as data from "../assets/data.js";*/

    export default {
        components: { /*VueHighcharts*/ },
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
            /*listR(){
                axios.get('/riscos/').then(res => {
                    var riscosAll;
                    riscosAll = res.data;
                    for(let i = 0; i < riscosAll.length; i++){
                        if(i == 0){
                            this.unidadeR.push("Todas");
                            this.unidadeR.push(riscosAll[i].unidade);
                        }
                        else{
                            this.unidadeR.push(riscosAll[i].unidade);
                        }
                    }
                })
            },
            calculaPercentual(a){
                return parseInt((a * 100 / this.riscos.length)*100)/100;
            },
            load() {
                let lineCharts = this.$refs.lineCharts;
                //charts.showLoading('loading');

                // you also can use the delegateMethod()
                lineCharts.delegateMethod("showLoading", "Loading...");
                setTimeout(() => {
                    lineCharts.addSeries(this.addSeriesRisco());
                    lineCharts.hideLoading();
                }, 1000);
            },

            addSeriesRisco() {
                this.$refs.lineCharts.addSeries({
                    name: "ameaças",
                    marker: {
                        symbol: "square"
                    },
                    data: [
                        this.qtdRisco[0].jan,
                        this.qtdRisco[1].fev,
                        this.qtdRisco[2].mar,
                        this.qtdRisco[3].abr,
                        this.qtdRisco[4].mai,
                        this.qtdRisco[5].jun,
                        this.qtdRisco[6].jul,
                        this.qtdRisco[7].ago,
                        this.qtdRisco[8].set,
                        this.qtdRisco[9].out,
                        this.qtdRisco[10].nov,
                        this.qtdRisco[11].dez
                    ]
                });
            },

            async listar() {
                await axios
                    .get("/impactos/")
                    .then(res => {
                        this.impactos = res.data;
                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/probabilidades/")
                    .then(res => {
                        this.probabilidades = res.data;
                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/niveisRisco/listar")
                    .then(res => {
                        this.niveisRisco = res.data;
                        for (let c = 0; c < this.niveisRisco.length; c++) {
                            this.cards.push({
                                nome: "Risco " + this.niveisRisco[c].nome,
                                cor: this.niveisRisco[c].cor,
                                valor: 0,
                                valorInicial: this.niveisRisco[c].valorInicial,
                                valorFinal: this.niveisRisco[c].valorFinal,
                            });
                        }
                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/riscos/")
                    .then(res => {
                        this.riscos = res.data;

                        for(let i=0 ; i < this.riscos.length; i++){
                            if(this.riscos[i].tipo == 'AMEACA'){
                                this.riscoAmeaca += 1;
                            }
                        }
                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/ocorrencias/")
                    .then(res => {
                        this.ocorrencias = res.data;
                        console.log(this.ocorrencias);
                        for(let o = 0; o < this.ocorrencias.length; o++){
                            this.datas.push(this.ocorrencias[o].data);
                        }
                        for(let d = 0; d < this.datas.length; d++){
                            this.meses.push(moment(this.datas[d]).month()+1);
                        }
                        console.log(this.datas);
                        console.log(this.meses);
                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/avaliacoes/avaliacoesRecentes")
                    .then(res => {
                        this.avRisco = res.data;


                        for (let a = 0; a < this.riscos.length; a++) {
                            var dataInicio = new Date(this.avRisco[this.riscos[a].id].data);
                            var dataFim = new Date(new Date().toISOString().substr(0, 10));
                            var diffMilissegundos = dataFim - dataInicio;
                            var diffSegundos = diffMilissegundos / 1000;
                            var diffMinutos = diffSegundos / 60;
                            var diffHoras = diffMinutos / 60;
                            var diffDias = diffHoras / 24;



                            if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "DIARIA" && diffDias >  1){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)});
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMANAL" && diffDias >  7){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "QUINZENAL" && diffDias >  15){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "MENSAL" && diffDias > 30 ){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "BIMESTRAL" && diffDias > 60){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "TRIMESTRAL" && diffDias >  90){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMESTRAL" && diffDias > 180){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;

                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "ANUAL" && diffDias >  360){
                                this.cardsAvaliacao.push({nome: this.avRisco[this.riscos[a].id].risco.nome,
                                    periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                    dias: parseInt(diffDias)})
                                this.atraso += 1;
                            }
                            else{
                                this.emDia += 1;
                            }

                        }

                        this.percentualAtraso = parseInt((this.atraso * 100 / this.riscoAmeaca)*100)/100;
                        this.percentualEmDia = parseInt((this.emDia * 100 / this.riscoAmeaca)*100)/100;

                    })
                    .catch(error => console.log(error));

                await axios
                    .get("/avaliacoes/avaliacoesRecentes")
                    .then(res => {
                        this.avRisco = res.data;


                        for (let a = 0; a < this.riscos.length; a++) {
                            var dataInicio = new Date(this.avRisco[this.riscos[a].id].data);
                            var dataFim = new Date(new Date().toISOString().substr(0, 10));
                            var diffMilissegundos = dataFim - dataInicio;
                            var diffSegundos = diffMilissegundos / 1000;
                            var diffMinutos = diffSegundos / 60;
                            var diffHoras = diffMinutos / 60;
                            var diffDias = diffHoras / 24;


                            var time = new Date(this.avRisco[this.riscos[a].id].data);
                            var outraData = new Date(new Date().toISOString().substr(0, 10));
                            var data;


                            if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "DIARIA"){
                                outraData.setDate(time.getDate() + 1);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMANAL"){
                                outraData.setDate(time.getDate() + 7);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "QUINZENAL"){
                                outraData.setDate(time.getDate() + 15);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "MENSAL"){
                                outraData.setDate(time.getDate() + 60);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "BIMESTRAL"){
                                outraData.setDate(time.getDate() + 60);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "TRIMESTRAL"){
                                outraData.setDate(time.getDate() + 90);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMESTRAL"){
                                outraData.setDate(time.getDate() + 180);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "ANUAL"){
                                outraData.setDate(time.getDate() + 360);

                                data = outraData - dataFim;
                                data = ((((data / 1000) /60)/60)/24);
                                if(parseInt(data) === 0){
                                    this.proximoAvencer += 1;
                                }
                            }
                            else{
                                if(diffDias == 0){
                                    this.proximoAvencer += 1;
                                }
                            }

                        }
                        this.percentualProximo = parseInt((this.proximoAvencer * 100 / this.riscoAmeaca)*100)/100;


                    })
                    .catch(error => console.log(error));


                await axios
                    .get("/avaliacoes/avaliacoesRecentes")
                    .then(res => {
                        this.avaliacoesRisco = res.data;
                        for (let a = 0; a < this.riscos.length; a++) {
                            for (let x = 0; x < this.cards.length; x++) {
                                if (
                                    this.avaliacoesRisco[this.riscos[a].id].probabilidade.valor *
                                    this.avaliacoesRisco[this.riscos[a].id].impacto.valor >=
                                    this.cards[x].valorInicial &&
                                    this.avaliacoesRisco[this.riscos[a].id].probabilidade.valor *
                                    this.avaliacoesRisco[this.riscos[a].id].impacto.valor <=
                                    this.cards[x].valorFinal
                                ) {
                                    this.cards[x].valor += 1;
                                }
                            }
                        }

                        for (let i = 0; i < this.meses.length; i++) {
                            if (this.meses[i] == 1) {
                                this.qtdRisco[0].jan += 1;
                            } else if (this.meses[i] == 2) {
                                this.qtdRisco[1].fev += 1;
                            } else if (this.meses[i] == 3) {
                                this.qtdRisco[2].mar += 1;
                            } else if (this.meses[i] == 4) {
                                this.qtdRisco[3].abr += 1;
                            } else if (this.meses[i] == 5) {
                                this.qtdRisco[4].mai += 1;
                            } else if (this.meses[i] == 6) {
                                this.qtdRisco[5].jun += 1;
                            } else if (this.meses[i] == 7) {
                                this.qtdRisco[6].jul += 1;
                            } else if (this.meses[i] == 8) {
                                this.qtdRisco[7].ago += 1;
                            } else if (this.meses[i] == 9) {
                                this.qtdRisco[8].set += 1;
                            } else if (this.meses[i] == 10) {
                                this.qtdRisco[9].out += 1;
                            } else if (this.meses[i] == 11) {
                                this.qtdRisco[10].nov += 1;
                            } else if ((this.meses[i] == 12)){
                                this.qtdRisco[11].dez += 1;
                            }
                        }
                        console.log(this.qtdRisco);

                    })
                    .catch(error => console.log(error));

                await this.countRisco();

                this.mapaLoaded = true;
            },


            async countRisco() {
                for (let p = 0; p < this.probabilidades.length; p++) {
                    for (let i = 0; i < this.impactos.length; i++) {
                        const nomeProbabilidade = this.probabilidades[p].nome,
                            nomeImpacto = this.impactos[i].nome;
                        await axios
                            .get("/avaliacoes/contarRiscos", {
                                params: {
                                    nomeProbabilidade,
                                    nomeImpacto
                                }
                            })
                            .then(res => {
                                this.mapa[p + "" + i] = res.data;
                            })
                            .catch(error => console.log(error));
                    }
                }
            },

            pegarNivelRisco(p, i) {
                const riscoInerente = p.valor * i.valor;
                let nivel;
                if (
                    this.niveisRisco.find(
                        nivelRisco =>
                            riscoInerente >= nivelRisco.valorInicial &&
                            riscoInerente <= nivelRisco.valorFinal
                    )
                ) {
                    nivel = this.niveisRisco.find(
                        nivelRisco =>
                            riscoInerente >= nivelRisco.valorInicial &&
                            riscoInerente <= nivelRisco.valorFinal
                    );
                    return nivel;
                } else {
                    return this.niveisRisco;
                }
            },

            filtroUnidade(uni){
                axios.get("/avaliacoes/avaliacoesRecentes")
                    .then(res => {
                        this.avRisco = res.data;
                        this.atraso = 0;
                        this.emDia = 0;
                        this.percentualAtraso = 0;
                        this.percentualEmDia = 0;
                        var tamanho = 0;



                        for (let a = 0; a < this.riscos.length; a++) {
                            var dataInicio = new Date(this.avRisco[this.riscos[a].id].data);
                            var dataFim = new Date(new Date().toISOString().substr(0, 10));
                            var diffMilissegundos = dataFim - dataInicio;
                            var diffSegundos = diffMilissegundos / 1000;
                            var diffMinutos = diffSegundos / 60;
                            var diffHoras = diffMinutos / 60;
                            var diffDias = diffHoras / 24;




                            if (this.avRisco[this.riscos[a].id].risco.unidade.sigla === uni || uni == "Todas") {
                                tamanho += 1;
                                if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "DIARIA" && diffDias > 1) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    });
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao == "SEMANAL" && diffDias > 7) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "QUINZENAL" && diffDias > 15) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "MENSAL" && diffDias > 30) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "BIMESTRAL" && diffDias > 60) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "TRIMESTRAL" && diffDias > 90) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMESTRAL" && diffDias > 180) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else if (this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "ANUAL" && diffDias > 360) {
                                    this.cardsAvaliacao.push({
                                        nome: this.avRisco[this.riscos[a].id].risco.nome,
                                        periodicidade: this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao,
                                        dias: parseInt(diffDias)
                                    })
                                    this.atraso += 1;

                                } else {
                                    this.emDia += 1;
                                }


                            }
                            this.percentualAtraso = parseInt((this.atraso * 100 / tamanho)*100)/100;
                            this.percentualEmDia = parseInt((this.emDia * 100 / tamanho)*100)/100;

                            this.calculaProximoAVencer(this.avRisco,uni);
                        }
                    })
                    .catch(error => console.log(error));


            },

            calculaProximoAVencer(x,uni){
                this.avRisco = x;
                this.proximoAvencer = 0;
                this.percentualProximo = 0;
                var tamanho = 0;
                for (let a = 0; a < this.riscos.length; a++) {
                    var dataInicio = new Date(this.avRisco[this.riscos[a].id].data);
                    var dataFim = new Date(new Date().toISOString().substr(0, 10));
                    var diffMilissegundos = dataFim - dataInicio;
                    var diffSegundos = diffMilissegundos / 1000;
                    var diffMinutos = diffSegundos / 60;
                    var diffHoras = diffMinutos / 60;
                    var diffDias = diffHoras / 24;


                    if (this.avRisco[this.riscos[a].id].risco.unidade.sigla === uni || uni == "Todas"){
                        tamanho += 1;
                        var time = new Date(this.avRisco[this.riscos[a].id].data);
                        var outraData = new Date(new Date().toISOString().substr(0, 10));
                        var data;
                        if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "DIARIA"){
                            outraData.setDate(time.getDate() + 1);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMANAL"){
                            outraData.setDate(time.getDate() + 7);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "QUINZENAL"){
                            outraData.setDate(time.getDate() + 15);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "MENSAL"){
                            outraData.setDate(time.getDate() + 30);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "BIMESTRAL"){
                            outraData.setDate(time.getDate() + 60);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "TRIMESTRAL"){
                            outraData.setDate(time.getDate() + 90);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "SEMESTRAL"){
                            outraData.setDate(time.getDate() + 180);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else if(this.avRisco[this.riscos[a].id].risco.periodicidadeAvaliacao === "ANUAL"){
                            outraData.setDate(time.getDate() + 360);

                            data = outraData - dataFim;
                            data = ((((data / 1000) /60)/60)/24);
                            if(parseInt(data) === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                        else{
                            if(diffDias === 0){
                                this.proximoAvencer += 1;
                            }
                        }
                    }
                }
                this.percentualProximo = parseInt((this.proximoAvencer * 100 / tamanho)*100)/100;
            }*/
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
