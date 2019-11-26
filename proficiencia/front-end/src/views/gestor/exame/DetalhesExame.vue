<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="description" title="Detalhes do Exame" />
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget enableActions title="Informações Básicas">
          <div slot="widget-content">
            <v-layout row>
              <v-flex xs12>
                <span class="title" style="font-weight:bold">{{ exame.titulo }}</span>
              </v-flex>
            </v-layout>
            <v-divider></v-divider>
            <v-layout row>
              <v-flex xs2>
                <span class="font-weight-bold text-uppercase">Status do exame</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Início das inscrições</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Término das inscrições</span>
              </v-flex>
              <v-flex xs2>
                <span class="font-weight-bold text-uppercase">Data da prova</span>
              </v-flex>
              <v-flex xs2>
                <span class="font-weight-bold text-uppercase">Nº de inscritos / Nº de vagas</span>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex xs2>
                <span class="font-weight-regular text-uppercase">{{ exame.fase | labelFase }}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular">{{ exame.inicioInscricoes | dataHora }}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular">{{ exame.terminoInscricoes | dataHora }}</span>
              </v-flex>
              <v-flex xs2>
                <span class="font-weight-regular">{{ exame.dataHoraProva | dataHora }}</span>
              </v-flex>
              <v-flex xs2>
                <span
                  class="font-weight-regular"
                >{{ exame.numeroInscritos + ' / ' + exame.numeroVagas }}</span>
              </v-flex>
            </v-layout>
            <v-divider></v-divider>
            <v-layout row>
              <v-flex xs12>
                <span class="font-weight-bold text-uppercase">Descrição:</span>
                <span class="font-weight-regular">{{ exame.descricao }}</span>
              </v-flex>
            </v-layout>
          </div>
          <div slot="widget-actions">
            <v-layout row>
              <v-flex xs12>
                <v-btn :to="'/gestor/exames/' + exameId + '/editar'" color="indigo" flat>
                  <v-icon small>edit</v-icon>Editar
                </v-btn>
                <v-btn
                  :disabled="naoPermiteExclusaoExame"
                  @click="dialogExcluirExame = true"
                  color="error"
                  flat
                >
                  <v-icon small>delete</v-icon>Deletar
                </v-btn>
                <v-btn
                  @click="dialogEncerrarInscricoes = true"
                  color="black"
                  flat
                  v-if="exameFaseInscricoes"
                >
                  <v-icon small>mdi-close-circle</v-icon>ENCERRAR INSCRIÇÕES
                </v-btn>

                <v-btn
                  @click="dialogEncerrarSelecao = true"
                  color="black"
                  flat
                  v-if="exameFaseSelecao"
                >
                  <v-icon small>mdi-close-circle</v-icon>ENCERRAR SELEÇÃO
                </v-btn>
              </v-flex>
            </v-layout>
          </div>
        </v-widget>
      </v-flex>

      <v-flex xs12>
        <v-widget title="Lista de Candidatos">
          <div slot="widget-content">
            <v-text-field
              append-icon="search"
              class="pesquisa"
              hide-details
              label="Buscar Usuários"
              single-line
              v-model="search"
            ></v-text-field>
            <v-data-table
              :headers="headersCandidatos"
              v-bind:pagination.sync="ordenacaoTabelaCandidatos"
              :items="candidatos"
              :no-results-text="'Nenhum resultado para: ' + search"
              :search="search"
              hide-actions
              no-data-text="Nenhum candidato inscrito"
            >
              <template v-slot:items="candidatos">
                <td class="body-1">
                  <b>{{ candidatos.item.nome }}</b>
                </td>
                <td class="body-1">
                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <span
                        class="text-uppercase"
                        v-on="on"
                      >{{ candidatos.item.statusInscricao | labelStatusInscricao }}</span>
                    </template>

                    <span>{{ candidatos.item.observacaoStatusInscricao }}</span>
                  </v-tooltip>
                </td>

                <td
                  class="body-1 text-uppercase"
                  v-bind:class="candidatos.item.statusAvaliacao ? candidatos.item.statusAvaliacao.value : ''"
                >
                  <b>{{ candidatos.item.statusAvaliacao | labelStatusAvaliacao }}</b>
                </td>
                <td class="body-1">
                  {{ candidatos.item.reavaliacaoSolicitada ? candidatos.item.notaReavaliacao :
                  candidatos.item.notaAvaliacao}}
                </td>
                <td class="body-1">
                  <b>{{ candidatos.item.prioridade | labelPrioridade }}</b>
                </td>
                <div>
                  <v-btn
                    :to=" exameId + '/candidato-inscrito/'+ candidatos.item.idInscricao"
                    color="cyan"
                    outline
                    round
                  >
                    <v-icon small>search</v-icon>Detalhes
                  </v-btn>

                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <v-btn
                        color="indigo"
                        fab
                        outline
                        small
                        v-on="on"
                        @click="abrirDialogAnaliseInscricao(candidatos.item)"
                        v-if="!isHidden"
                        v-show="candidatos.item.statusInscricao == 'INSCRITO' && bloquearBotaoAnalise()"
                      >
                        <v-icon>fas fa-edit</v-icon>
                      </v-btn>
                    </template>
                    <span>Analisar Inscrição</span>
                  </v-tooltip>
                </div>
              </template>
            </v-data-table>
          </div>
        </v-widget>
      </v-flex>

      <v-flex xs12>
        <v-widget title="Locais de Provas">
          <div slot="widget-content">
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn
                  :disabled="naoPermiteAdicionarEditarLocal"
                  @click="dialogAdicionarLocalProva = true"
                  absolute
                  color="blue"
                  dark
                  fab
                  right
                  top
                  v-on="on"
                >
                  <v-icon>add_location</v-icon>
                </v-btn>
              </template>
              <span>Adicionar Local de Prova</span>
            </v-tooltip>

            <v-data-table
              :headers="headersLocais"
              :items="locais"
              hide-actions
              no-data-text="Nenhum local de prova cadastrado"
            >
              <template v-slot:items="locais">
                <td class="body-1">
                  <b>{{ locais.item.nome }}</b>
                </td>
                <td class="body-1">
                  <b>{{ locais.item.candidatosAlocados }} / {{ locais.item.capacidade }}</b>
                </td>

                <v-btn
                  color="cyan"
                  outline
                  round
                  v-bind:to="'/gestor/localprova/'+ locais.item.id"
                >
                  <v-icon small>search</v-icon>Detalhes
                </v-btn>

                <v-tooltip top>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      :disabled="naoPermiteAdicionarEditarLocal"
                      @click="abrirDialogEditarLocal(locais.item)"
                      color="indigo"
                      fab
                      outline
                      small
                      v-on="on"
                    >
                      <v-icon>edit</v-icon>
                    </v-btn>
                  </template>
                  <span>Editar Local de Prova</span>
                </v-tooltip>

                <v-tooltip top>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      :disabled="naoPermiteExclusaoLocal(locais.item.candidatosAlocados)"
                      @click="abrirDialogExcluirLocalProva(locais.item)"
                      color="red"
                      fab
                      flat
                      outline
                      small
                      v-on="on"
                    >
                      <v-icon>delete</v-icon>
                    </v-btn>
                  </template>
                  <span>Deletar</span>
                </v-tooltip>
              </template>
            </v-data-table>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>

    <v-snackbar :color="cor" right top v-model="snackbar">{{ mensagem }}</v-snackbar>

    <v-dialog max-width="290" v-model="dialogExcluirExame">
      <v-card>
        <v-card-title class="headline">Excluir Exame</v-card-title>

        <v-card-text>Tem certeza que deseja excluir o exame?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn @click="dialog = false" color="silver darken-1" flat="flat">Não</v-btn>
          <v-btn @click="excluirExame()" color="red darken-1" flat="flat">Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog max-width="290" v-model="dialogLocal">
      <v-card>
        <v-card-actions>
          <v-btn @click="dialogLocal = false" color="silver darken-1" flat="flat">Não</v-btn>

          <v-btn @click="excluirLocalProva()" color="red darken-1" flat="flat">Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-snackbar :color="cor" right top v-model="snackbarAnalise">{{ mensagemAnalise }}</v-snackbar>

    <v-layout row justify="center">
      <v-dialog
        v-model="dialogAnaliseInscricao"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
      >
        <v-card>
          <v-toolbar dark color="primary">
            <v-btn icon dark @click="fecharDialogAnalise">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>Análise da Inscrição</v-toolbar-title>
          </v-toolbar>
          <analise
            v-on:finalizado="metodoFimAnalise"
            :idExameComponente="exameId"
            :Inscricao="candidato"
          ></analise>
        </v-card>
      </v-dialog>
    </v-layout>

    <v-dialog max-width="500" persistent v-model="dialogEncerrarInscricoes">
      <v-card>
        <v-card-title class="headline">Encerrar Inscrições</v-card-title>

        <v-card-text>Tem certeza que deseja encerrar as inscrições?</v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="dialogEncerrarInscricoes = false" color="silver darken-1" flat>Não</v-btn>
          <v-btn @click="encerrarInscricao" color="primary" flat>Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog max-width="500" persistent v-model="dialogAdicionarLocalProva">
      <v-card>
        <v-card-title class="headline">Adicionar Local</v-card-title>

        <v-card-text>
          <v-container grid-list-sm>
            <v-layout justify-center wrap>
              <v-flex md9 sm12>
                <v-text-field
                  :error-messages="errors.collect('nomeLocal')"
                  data-vv-name="nomeLocal"
                  label="Nome do local*"
                  v-model="nomeLocal"
                  v-validate="'required'"
                ></v-text-field>
              </v-flex>

              <v-flex md3 sm12>
                <v-text-field
                  :error-messages="errors.collect('capacidade')"
                  data-vv-name="capacidade"
                  label="Capacidade*"
                  type="number"
                  v-model="capacidadeLocal"
                  v-validate="'required'"
                ></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>

          <span class="pa-3 caption">*Campos Obrigatórios</span>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="fecharDialogAdicionarLocalProva" color="silver darken-1" flat>CANCELAR</v-btn>
          <v-btn @click="adicionarLocalProva" color="primary" flat>SALVAR</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog max-width="500" persistent v-model="dialogEncerrarSelecao">
      <v-card>
        <v-card-title class="headline">Encerrar Fase de Seleção</v-card-title>
        <v-card-text>Tem certeza que deseja encerrar a fase de seleção?</v-card-text>

        <v-card-text>
          <date-time-picker
            v-model="novaDataHoraProva"
            label="Deseja alterar a data/hora de aplicação da prova?"
            :error-messages="errors.collect('novaDataHoraProva')"
            data-vv-name="novaDataHoraProva"
            v-validate="'dataHoraMaior:novaDataHoraProvaAuxiliar'"
          ></date-time-picker>
        </v-card-text>

        <div v-show="false">
          <date-time-picker
            v-model="exame.terminoInscricoes"
            ref="novaDataHoraProvaAuxiliar"
            :error-messages="errors.collect('')"
          ></date-time-picker>
        </div>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn @click="fecharDialogEncerrarFaseSelecao" color="silver darken-1" flat="flat">Não</v-btn>

          <v-btn
            :disabled="errors.any()"
            @click="encerrarSelecao()"
            v-on:click="isHidden = true"
            color="primary"
            flat="flat"
          >Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog max-width="500" persistent v-model="dialogEditarLocalProva">
      <v-card>
        <v-card-title class="headline">Editar Local</v-card-title>

        <v-card-text>
          <v-container grid-list-sm>
            <v-layout justify-center wrap>
              <v-flex md9 sm12>
                <v-text-field
                  :error-messages="errors.collect('nomeLocal')"
                  data-vv-name="nomeLocal"
                  v-model="nomeLocal"
                  v-validate="'required'"
                ></v-text-field>
              </v-flex>

              <v-flex md3 sm12>
                <v-text-field
                  :error-messages="errors.collect('capacidade')"
                  data-vv-name="capacidade"
                  type="number"
                  v-model="capacidadeLocal"
                  v-validate="'required'"
                ></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
          <span class="pa-3 caption">*Campos Obrigatórios</span>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="fecharDialogEditarLocalProva" color="silver darken-1" flat>CANCELAR</v-btn>
          <v-btn @click="editarLocalProva" color="primary" flat>SALVAR</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog max-width="290" v-model="dialogExcluirLocalProva">
      <v-card>
        <v-card-title class="headline">Excluir Local de Prova</v-card-title>

        <v-card-text>Tem certeza que deseja excluir o local de prova?</v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="dialogExcluirLocalProva = false" color="silver darken-1" flat>Não</v-btn>
          <v-btn @click="excluirLocalProva" color="red darken-1" flat>Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import Vue from "vue";
import VeeValidate, { Validator } from "vee-validate";
import axios from "axios";
import moment from "moment";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import VWidget from "../../../components/core/VWidget";
import store from "../../../store";
import analise from "../../../components/Analise";
import DateTimePicker from "../../../components/inputs/DateTimePicker";

moment.locale("pt-br");

Vue.use(VeeValidate);

Validator.extend(
  "dataHoraMaior",
  (value, otherValue) => {
    if (otherValue[0] == "") {
      return true;
    }
    return moment(value).isAfter(otherValue[0]);
  },
  {
    hasTarget: true
  }
);

export default {
  props: {
    dCor: {
      type: String,
      default: ""
    },
    dSnackbar: {
      type: Boolean,
      default: false
    },
    dMensagem: {
      type: String,
      default: ""
    }
  },
  components: {
    VWidget,
    AppBreadcrumbs,
    analise,
    DateTimePicker
  },

  data() {
    return {
      teste: "",
      candidato: {},
      candidatos: [],
      headersCandidatos: [
        { text: "NOME", value: "nome" },
        { text: "STATUS", value: "status" },
        { text: "RESULTADO", value: "resultado" },
        { text: "NOTA", value: "nota" },
        { text: "PRIORIDADE", value: "prioridade" },
        { value: null, sortable: false }
      ],

      ordenacaoTabelaCandidatos: { sortBy: "prioridade", descending: false },

      locais: [],
      headersLocais: [
        { text: "LOCAL", value: "nome" },
        { text: "CANDIDATOS ALOCADOS / CAPACIDADE", value: "capacidade" },
        { value: null, sortable: false }
      ],
      exameId: null,
      exame: {},
      capacidade: null,
      nomeLocal: null,
      localIndex: null,
      search: "",
      usuario: [],
      idInscricao: null,
      statusCandidatoAnalisado: [],
      dialogExcluirExame: false,
      dialogAdicionarLocalProva: false,
      dialogEditarLocalProva: false,
      dialogExcluirLocalProva: false,
      capacidadeLocal: null,
      dialog: false,
      dialog1: false,
      dialogLocal: false,
      dialogAnaliseInscricao: false,
      isHidden: false,
      dialogEncerrarInscricoes: false,
      novaDataHoraProva: null,
      novaDataHoraProvaAuxiliar: null,
      idLocal: null,
      dialogEncerrarSelecao: false,

      snackbar: this.dSnackbar,
      cor: this.dCor,
      mensagem: this.dMensagem,

      breadcrumbItems: [
        {
          position: 1,
          text: "Exames",
          disabled: false,
          href: "/gestor/exames"
        }
      ],

      snackbarAnalise: false,
      mensagemAnalise: "",

      dictionary: {
        custom: {
          nomeLocal: {
            required: () => "O nome do local é obrigatório!"
          },
          capacidade: {
            required: () => "O numero de vagas é obrigatório!"
          }
        }
      }
    };
  },

  computed: {
    naoPermiteExclusaoExame() {
      return this.exame.fase !== "INSCRICAO" || this.exame.numeroInscritos > 0;
    },
    naoPermiteAdicionarEditarLocal() {
      return this.exame.fase === "FINALIZADO";
    },
    exameFaseInscricoes() {
      return this.exame.fase === "INSCRICAO";
    },
    exameFaseSelecao() {
      return this.exame.fase === "SELECAO";
    },
    terminoInscricoes() {
      return this.exame.terminoInscricoes;
    }
  },

  mounted: function() {
    this.$validator.localize("pt", this.dictionary);
    this.exameId = this.$route.params.id;
    this.initialize();
  },

  getLabelStatusAvaliacao(status) {
    return store.getters["enums/getLabelStatusAvaliacao"](status);
  },
  getLabelFase(fase) {
    return store.getters["enums/getLabelFase"](fase);
  },

  naoPermiteExclusao() {
    //return this.exame.fase != "INSCRICAO" || this.exame.numeroInscritos > 0;
    return false;
  },
  naoPermiteExclusaoLocal(local) {
    if (this.exame.fase == "FINALIZADO" || local > 0) {
      return true;
    } else {
      return false;
    }
  },

  filters: {
    dataHora: function(value) {
      return value ? moment(value).format("LLL") : "-";
    },
    labelStatusInscricao(value) {
      return value
        ? store.getters["enums/getLabelStatusInscricao"](value)
        : "-";
    },
    labelStatusAvaliacao(value) {
      return value
        ? store.getters["enums/getLabelStatusAvaliacao"](value)
        : "-";
    },
    labelFase(value) {
      return value ? store.getters["enums/getLabelFase"](value) : "-";
    },
    labelPrioridade(value) {
      return value ? store.getters["enums/getLabelPrioridade"](value) : "-";
    }
  },

  methods: {
    metodoFimAnalise(map) {
      this.dialogAnaliseInscricao = false;
      if (map.novo.statusInscricao == "INDEFERIDO") {
        this.snackbarAnalise = true;
        this.mensagemAnalise = "Inscrição indeferida com sucesso !";
        this.cor = "success";
      } else {
        this.snackbarAnalise = true;
        this.mensagemAnalise = "Inscrição deferida com sucesso !";
        this.cor = "success";
      }

      this.candidatos.splice(this.candidatos.indexOf(map.antigo), 1, map.novo);
      if (map.local != null) {
        this.locais.splice(
          this.locais.indexOf(
            this.locais.find(element => {
              return element.id === map.local.id;
            })
          ),
          1,
          map.local
        );
      }
    },
    initialize() {
      axios.get("exame/" + this.exameId).then(res => {
        this.exame = res.data;
        this.breadcrumbItems.push({
          position: 3,
          text: this.exame.titulo,
          disabled: true
        });
      });
      axios.get("inscricoes/exame/" + this.exameId).then(res => {
        this.candidatos = res.data;
        //this.idInscricao = res.data[0].idInscricao;
      });
      axios.get("local/listarLocal/" + this.exameId).then(res => {
        this.locais = res.data;
      });
    },
    excluirLocalProva() {
      axios
        .delete("/local/" + this.idLocal)
        .then(() => {
          this.locais.splice(this.localIndex, 1);

          this.snackbar = true;
          this.mensagem = "Local de prova excluído com sucesso";
          this.cor = "success";
        })
        .catch(error => {
          this.snackbar = true;
          this.mensagem = error.response.data;
          this.cor = "error";
        })
        .finally(() => {
          this.idLocal = null;
          this.localIndex = null;
          this.dialogExcluirLocalProva = false;
        });
    },
    naoPermiteExclusaoLocal(candidatosAlocados) {
      return (
        this.exame.fase === "AVALIACAO" ||
        this.exame.fase === "FINALIZADO" ||
        candidatosAlocados > 0
      );
    },
    fecharDialogEncerrarFaseSelecao() {
      this.dialogEncerrarSelecao = false;
      this.novaDataHoraProva = this.exame.dataHoraProva;
      this.limpar();
    },

    bloquearBotaoAnalise() {
      if (this.exame.fase == "SELECAO") {
        return true;
      }
      return false;
    },

    abrirDialog() {
      this.dialog1 = true;
    },

    abrirDialogExcluirLocalProva(local) {
      this.idLocal = local.id;
      this.localIndex = this.locais.indexOf(local);
      this.dialogExcluirLocalProva = true;
    },

    abrirDialogAnaliseInscricao(candidato) {
      this.dialogAnaliseInscricao = true;
      this.candidato = candidato;
    },

    fecharDialogAnalise() {
      this.dialogAnaliseInscricao = false;
    },

    limpar() {
      this.errors.clear();
      this.$validator.reset();
    },
    excluirExame() {
      axios
        .delete("/exame/" + this.exameId)
        .then(() => {
          this.$router.push({
            name: "CoordenadorExames",
            params: {
              cor: "success",
              snackbar: true,
              mensagem: "Exame excluído com sucesso"
            }
          });
        })
        .catch(error => {
          this.snackbar = true;
          this.mensagem = error.response.data;
          this.cor = "error";
          this.dialogExcluirExame = false;
        });
    },

    encerrarInscricao() {
      axios
        .put("/exame/encerrarInscricao/" + this.exameId)
        .then(res => {
          this.exame = res.data;

          this.snackbar = true;
          this.mensagem = "Inscrições encerradas";
          this.cor = "success";
        })
        .catch(error => {
          this.mensagem = error.response.data;
          this.cor = "error";
          this.snackbar = true;
        })
        .finally(() => {
          this.dialogEncerrarInscricoes = false;
        });
    },
    adicionarLocalProva() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .post("local/" + this.exameId, {
              nome: this.nomeLocal,
              capacidade: this.capacidadeLocal
            })
            .then(res => {
              this.locais.push(res.data);
            })
            .catch(error => {
              this.snackbar = true;
              this.mensagem = error.response.data;
              this.cor = "error";
            })
            .finally(() => {
              this.dialogAdicionarLocalProva = false;
              this.nomeLocal = null;
              this.capacidadeLocal = null;
              this.limpar();
            });
        }
      });
    },
    encerrarSelecao() {
      if (this.exame.dataHoraProva != null) {
        this.exame.dataHoraProva = this.novaDataHoraProva;
      }
      axios
        .put("/exame/encerrarSelecao/" + this.exameId, {
          id: this.exameId,
          dataHoraProva: this.exame.dataHoraProva
        })
        .then(res => {
          this.exame = res.data;

          this.snackbar = true;
          this.mensagem = "Fase de Seleção Encerrada";
          this.cor = "success";
        })
        .catch(error => {
          this.mensagem = error.response.data;
          this.cor = "error";
          this.snackbar = true;
        })
        .finally(() => {
          this.dialogEncerrarSelecao = false;
        });
    },
    fecharDialogAdicionarLocalProva() {
      this.dialogAdicionarLocalProva = false;
      this.nomeLocal = null;
      this.capacidadeLocal = null;
      this.limpar();
    },

    abrirDialogEditarLocal(local) {
      this.idLocal = local.id;
      this.nomeLocal = local.nome;
      this.capacidadeLocal = local.capacidade;
      this.localIndex = this.locais.indexOf(local);
      this.dialogEditarLocalProva = true;
    },

    fecharDialogEditarLocalProva() {
      this.idLocal = null;
      this.nomeLocal = null;
      this.capacidadeLocal = null;
      this.localIndex = null;
      this.dialogEditarLocalProva = false;
      this.limpar();
    },

    editarLocalProva() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .put("/local", {
              id: this.idLocal,
              nome: this.nomeLocal,
              capacidade: this.capacidadeLocal,
              exame: {
                id: this.exameId
              }
            })
            .then(res => {
              this.locais.splice(this.localIndex, 1, res.data);
            })
            .catch(error => {
              this.snackbar = true;
              this.mensagem = error.response.data;
              this.cor = "error";
            })
            .finally(() => {
              this.idLocal = null;
              this.nomeLocal = null;
              this.capacidadeLocal = null;
              this.localIndex = null;
              this.dialogEditarLocalProva = false;
              this.limpar();
            });
        }
      });
    }
  }
};
</script>
