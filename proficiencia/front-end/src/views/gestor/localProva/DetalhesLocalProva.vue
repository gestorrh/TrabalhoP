<!--
<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-xl>
    <app-breadcrumbs
      :items="breadcrumbItems"
      icon="mdi-map-marker"
      title="Detalhes do Local de prova"
    />
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget title="Informações Básicas" enableActions>
          <div slot="widget-content">
            <v-layout row>
              <v-flex xs12>
                <span class="title" style="font-weight:bold">{{ locais.nome }}</span>
              </v-flex>
            </v-layout>
            <v-divider></v-divider>
            <v-layout row>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Candidatos Alocados :</span>
                <span class="font-weight-regular text-uppercase">{{ locais.candidatosAlocados }}</span>
              </v-flex>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Capacidade :</span>
                <span class="font-weight-regular text-uppercase">{{ locais.capacidade }}</span>
              </v-flex>
            </v-layout>
          </div>
          <div slot="widget-actions">
            <v-layout row>
              <v-flex xs12>
                <v-btn
                  color="indigo"
                  flat
                  :disabled="naoPermiteEditarLocal"
                  @click="abrirDialogEditarLocal(locais)"
                >
                  <v-icon small>edit</v-icon>Editar
                </v-btn>
                <v-btn
                  color="error"
                  flat
                  :disabled="naoPermiteExclusaoLocal(locais.candidatosAlocados)"
                  @click="abrirDialogExcluirLocalProva(locais)"
                >
                  <v-icon small>delete</v-icon>Deletar
                </v-btn>
              </v-flex>
            </v-layout>
          </div>
          <v-dialog max-width="500px" persistent v-model="dialogEditarLocalProva">
            <v-card>
              <v-card-title class="headline">Editar Local de Prova</v-card-title>
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
                <v-btn @click="dialogEditarLocalProva = false" color="silver darken-1" flat>CANCELAR</v-btn>
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
        </v-widget>
      </v-flex>
      <v-flex xs12>
        <v-widget title="Lista de Candidatos alocados">
          <div slot="widget-content">
            <v-toolbar flat color="white">
              <v-text-field
                append-icon="search"
                class="pesquisa"
                hide-details
                label="Buscar Usuários"
                single-line
                v-model="search"
              ></v-text-field>
            </v-toolbar>
            <v-data-table
              :headers="headersCandidatos"
              :items="candidatos"
              item-key="idInscricao"
              :search="search"
              hide-actions
              no-data-text="Nenhum candidato alocado"
              :no-results-text="'Nenhum resultado para: ' + search"
            >
              <template v-slot:items="candidatos">
                <td class="body-1">
                  <b>{{ candidatos.item.nomeCandidato }}</b>
                </td>
                <td class="body-1">{{ candidatos.item.email }}</td>
                <td class="body-1 text-uppercase">
                  <b>{{ candidatos.item.telefone | telefone }}</b>
                </td>
                <td class="body-1 text-center">{{ candidatos.item.necessidadeEspecial }}</td>
                <div>
                  <v-btn
                    color="cyan"
                    outline
                    round
                    :to="candidatos.item.idExame + '/candidato-inscrito/'+ candidatos.item.idInscricao"
                  >
                    <v-icon small>search</v-icon>Detalhes
                  </v-btn>
                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <v-btn
                        :disabled="mostrarBotaoMoverCandidato(candidatos.item.faseExame)"
                        color="indigo"
                        @click="abrirDialog(candidatos.item.idInscricao, candidatos.item.idExame, candidatos.item)"
                        outline
                        fab
                        small
                        v-on="on"
                      >
                        <v-icon>mdi-exit-to-app</v-icon>
                      </v-btn>
                    </template>
                    <span>Mover candidato</span>
                  </v-tooltip>
                </div>
              </template>
            </v-data-table>
            <v-layout row justify-center>
              <v-dialog v-model="dialog" max-width="850px">
                <v-card>
                  <v-card-title>
                    <span class="headline">Lista de Locais de Prova</span>
                  </v-card-title>
                  <span
                    class="pa-3 caption"
                  >*Selecione um local de prova que deseja mover o candidato</span>
                  <v-card-text>
                    <v-container grid-list-md>
                      <v-layout wrap>
                        <div style="overflow:scroll;height:300px;width:800px;overflow:auto">
                          <v-data-table
                            :headers="headersLocais"
                            :items="listLocalProva"
                            item-key="idInscricao"
                            hide-actions
                            no-data-text="Nenhum local de prova cadastrado"
                            class="elevation-1"
                          >
                            <template v-slot:items="listLocalProva">
                              <tr>
                                <td class="body-1">
                                  <b>{{ listLocalProva.item.nome }}</b>
                                </td>
                                <td class="body-1 text-center">
                                  {{ listLocalProva.item.candidatosAlocados }} /
                                  {{ listLocalProva.item.capacidade }}
                                </td>
                                <td>
                                  <v-tooltip top>
                                    <template v-slot:activator="{ on }">
                                      <v-icon
                                        color="indigo"
                                        @click="submitDadosUsuario(listLocalProva.item.id)"
                                        v-on="on"
                                      >mdi-exit-to-app</v-icon>
                                    </template>
                                    <span>Mover candidato</span>
                                  </v-tooltip>
                                </td>
                              </tr>
                            </template>
                          </v-data-table>
                        </div>
                      </v-layout>
                    </v-container>
                  </v-card-text>
                </v-card>
              </v-dialog>
            </v-layout>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>
    <v-snackbar :color="cor" right top v-model="snackbar">{{ mensagem }}</v-snackbar>
  </v-container>
</template>

<script>
import axios from "axios";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import VWidget from "../../../components/core/VWidget";

export default {
  components: {
    VWidget,
    AppBreadcrumbs
  },
  props: ["id"],
  data() {
    return {
      candidatos: [],
      idInscricao: "",
      idUsuario: "",
      idLocal: "",
      nomeLocal: "",
      capacidadeLocal: "",
      identificadorExame: "",
      candidatoAlocado: [],
      headersCandidatos: [
        { text: "NOME", value: "nomeCandidato" },
        { text: "EMAIL", value: "email" },
        { text: "TELEFONE", value: "telefone" },
        { text: "NECESSIDADE ESPECIAL", value: "necessidadeEspecial" },
        { value: null, sortable: false }
      ],
      locais: [],
      localprovaId: "",
      listLocalProva: [],
      headersLocais: [
        { text: "LOCAL", value: "nome" },
        { text: "CANDIDATOS ALOCADOS/CAPACIDADE", value: "candidatosAlocados" },
        { text: "", value: "" }
      ],
      exame: [0],
      search: "",
      searchLocal: "",
      dialog: false,
      dialogEditarLocalProva: false,
      dialogExcluirLocalProva: false,
      dadosLocalProva: "",
      breadcrumbItems: [
        {
          position: 1,
          listCandidatos: [],
          text: "Exames",
          disabled: false,
          href: "/gestor/exames"
        }
      ],
      snackbar: false,
      cor: "",
      mensagem: ""
    };
  },

  computed: {
    naoPermiteEditarLocal() {
      return this.exame[0].fase === "FINALIZADO";
    }
  },
  mounted: function() {
    this.localprovaId = this.$route.params.id;
    this.initialize();
  },
  filters: {
    telefone: function(telefone) {
      if (!telefone) {
        return "";
      } else if (telefone.length === 11) {
        return telefone.replace(/(\d{2})(\d)(\d{4})(\d{4})/g, "($1) $2.$3-$4");
      } else if (telefone.length === 10) {
        return telefone.replace(/(\d{2})(\d{4})(\d{4})/g, "($1) $2-$3");
      }
    }
  },
  methods: {
    initialize() {
      axios.get("local/" + this.localprovaId).then(res => {
        this.locais = res.data.localProva;
        this.candidatos = res.data.candidatos;
        this.exame = res.data.exame;

        this.breadcrumbItems.push({
          position: 2,
          text: this.exame[0].titulo,
          disabled: false,
          href: "/gestor/exames/" + this.exame[0].idExame
        });
        this.breadcrumbItems.push({
          position: 3,
          text: this.locais.nome,
          disabled: true
        });
      });
    },
    mostrarBotaoMoverCandidato(faseExame) {
      if (faseExame === "SELECAO" || faseExame === "AVALIACAO") {
        return false;
      } else {
        return true;
      }
    },

    abrirDialog(inscricaoId, exameId, candidato) {
      axios.get("local/listarLocal/" + exameId).then(res => {
        this.listLocalProva = res.data;
      });
      this.idInscricao = inscricaoId;
      this.candidatoAlocado = candidato;
      this.dialog = true;
    },
    submitDadosUsuario(idLocalProva) {
      axios
        .put("local/moverCandidato/" + this.idInscricao + "/" + idLocalProva)
        .then(() => {
          this.candidatos.splice(
            this.candidatos.indexOf(this.candidatoAlocado),
            1
          );
          this.candidatoAlocado = null;
          this.locais.candidatosAlocados -= 1;

          this.dialog = false;
          this.cor = "success";
          this.snackbar = true;
          this.mensagem = "Candidato realocado";
        })
        .catch(error => {
          this.snackbar = true;
          this.mensagem = error.response.data;
          this.cor = "error";
        });
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
                id: this.identificadorExame
              }
            })
            .then(res => {
              this.snackbar = true;
              this.mensagem = "Local de prova editado com sucesso";
              this.cor = "success";
              this.locais.nome = res.data.nome;

              this.locais.capacidade = res.data.capacidade;
              this.breadcrumbItems.pop({
                position: 3
              });
              this.breadcrumbItems.push({
                position: 3,
                text: this.locais.nome,
                disabled: true
              });
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
              this.identificadorExame = null;
              this.localIndex = null;

              this.dialogEditarLocalProva = false;
              this.limpar();
            });
        }
      });
    },
    excluirLocalProva() {
      axios
        .delete("/local/" + this.idLocal)
        .then(() => {
          this.$router.push({
            name: "CoordenadorLocalProva",
            params: {
              id: this.exame[0].idExame,
              dCor: "success",
              dSnackbar: true,
              dMensagem: "Local de prova excluído com sucesso"
            }
          });
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
    abrirDialogEditarLocal(local) {
      this.idLocal = local.id;
      this.nomeLocal = local.nome;
      this.capacidadeLocal = local.capacidade;

      this.identificadorExame = local.exameId;
      this.dialogEditarLocalProva = true;
    },

    abrirDialogExcluirLocalProva(local) {
      this.idLocal = local.id;
      this.dialogExcluirLocalProva = true;
    },

    limpar() {
      this.errors.clear();
      this.$validator.reset();
    },
    naoPermiteExclusaoLocal(candidatosAlocados) {
      if (
        this.exame[0].fase === "AVALIACAO" ||
        this.exame[0].fase === "FINALIZADO" ||
        candidatosAlocados > 0
      ) {
        return true;
      }
      return false;
    }
  }
};
</script>

<style>
.APROVADO {
  color: #45e276;
  font-weight: 500;
}

.REPROVADO {
  color: #ea4c72;
  font-weight: 500;
}

.EM_AVALIACAO {
  color: #565656;
  font-weight: 500;
}
</style>
-->
