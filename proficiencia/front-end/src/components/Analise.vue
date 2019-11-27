<template>
  <div id="analise">
    <v-container fluid grid-list-xl>
      <v-layout row wrap>
        <v-flex xs12>
          <v-widget title="Informações Básicas" enableActions>
            <div slot="widget-content">
              <v-layout row>
                <v-flex xs12>
                  <span class="title" style="font-weight:bold">{{ inscricao.nome }}</span>
                </v-flex>
              </v-layout>
              <v-divider></v-divider>
              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Data de inscrição</span>
                </v-flex>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Status da Inscrição</span>
                </v-flex>
              </v-layout>
              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-regular">{{ inscricao.dataHoraInscricao | data }}</span>
                </v-flex>
                <v-flex xs6>
                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <span
                        v-on="on"
                        class="font-weight-regular text-uppercase"
                      >{{ getLabelStatusInscricao(inscricao.statusInscricao) }}</span>
                    </template>
                    <span>{{ inscricao.observacaoStatusInscricao }}</span>
                  </v-tooltip>
                </v-flex>
              </v-layout>

              <v-divider></v-divider>
              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Necessidade Especial:</span>
                  <span
                    class="font-weight-regular text-uppercase"
                  >{{ getLabelNecessidade(inscricao.necessidadeEspecial) }}</span>
                </v-flex>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Observação Necessidade:</span>
                  <span class="font-weight-regular">{{ inscricao.observacaoNecessidade }}</span>
                </v-flex>
              </v-layout>
              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">PROGRAMA DE PÓS-GRADUAÇÃO:</span>
                  <span class="font-weight-regular">{{ inscricao.programaPos }}</span>
                </v-flex>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Área de concentração:</span>
                  <span class="font-weight-regular">{{ inscricao.areaConcentracao }}</span>
                </v-flex>
              </v-layout>
              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Linha de pesquisa:</span>
                  <span class="font-weight-regular">{{ inscricao.linhaPesquisa }}</span>
                </v-flex>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Instituição:</span>
                  <span class="font-weight-regular">{{ inscricao.instituicao }}</span>
                </v-flex>
              </v-layout>

              <v-layout row>
                <v-flex xs6>
                  <span class="font-weight-bold text-uppercase">Nota:</span>
                  <span
                    class="font-weight-regular"
                  >{{ inscricao.reavaliacaoSolicitada ? inscricao.notaReavaliacao : inscricao.notaAvaliacao | nota }}</span>
                </v-flex>
              </v-layout>
            </div>
            <div slot="widget-actions">
              <v-layout row>
                <v-flex xs12>
                  <v-btn color="indigo" flat @click="dialog = true">
                    <v-icon small>fas fa-edit</v-icon>Analisar Inscrição
                  </v-btn>
                </v-flex>
              </v-layout>
            </div>
          </v-widget>
        </v-flex>

        <v-flex xs12>
          <v-widget title="Arquivos da Inscrição">
            <div slot="widget-content">
              <media :lista="listaDeArquivos" :inscricaoId="Inscricao.idInscricao"></media>
            </div>
          </v-widget>
        </v-flex>
      </v-layout>

      <v-snackbar :color="cor" right top v-model="snackbar">{{ mensagem }}</v-snackbar>

      <v-dialog max-width="550" persistent v-model="dialog">
        <v-card>
          <v-card-title class="headline">Análise da Inscrição</v-card-title>
          <v-container>
            <v-label>Status da Inscrição</v-label>
            <v-form>
              <v-layout row wrap>
                <v-flex xs12 sm6 md6>
                  <v-radio-group
                    v-model="statusInscricao"
                    :error-messages="errors.collect('StatusInscricao')"
                    data-vv-name="StatusInscricao"
                    v-validate="'required'"
                    column
                  >
                    <v-radio label="DEFERIDO" value="DEFERIDO" color="primary"></v-radio>
                  </v-radio-group>
                </v-flex>
                <v-flex xs12 sm6 md6>
                  <v-radio-group
                    v-model="statusInscricao"
                    row
                    justify="center"
                    :error-messages="errors.collect('StatusInscricao')"
                    data-vv-name="StatusInscricao"
                    v-validate="'required'"
                    column
                  >
                    <v-radio label="INDEFERIDO" value="INDEFERIDO" color="red"></v-radio>
                  </v-radio-group>
                </v-flex>
              </v-layout>

              <v-textarea
                :error-messages="errors.collect('ObservacaoStatusInscricao')"
                label="Observações"
                v-model="observacaoStatusInscricao"
                data-vv-name="ObservacaoStatusInscricao"
                auto-grow
                placeholder="Clique aqui para inserir a observação."
                maxlength="255"
                counter="255"
                :rules="[rules.length(255)]"
              ></v-textarea>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn @click="limparCampos" color="silver darken-1" flat="flat">Fechar</v-btn>

                <v-btn
                  @click="analiseInscricao"
                  v-on:click="eventoAttDados"
                  color="primary"
                  flat="flat"
                >Salvar</v-btn>
              </v-card-actions>
            </v-form>
          </v-container>
        </v-card>
      </v-dialog>
    </v-container>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VWidget from "../components/core/VWidget";
import VeeValidate from "vee-validate";
import moment from "moment";
import store from "../store";
import media from "../components/Media";

Vue.use(VeeValidate);

moment.locale("pt-br");

export default {
  name: "analise",
  components: {
    VWidget,
    media
  },
  props: {
    //idInscricaoComponente: Number.parseInt(String)
    Inscricao: Object
  },

  data() {
    return {
      statusInscricao: null,
      observacaoStatusInscricao: null,
      inscricao: {},
      documentacao: {},
      listaDeArquivos: [],
      inscricaoIndex: null,
      dialog: false,
      snackbar: false,
      mensagem: "",
      cor: "",
      isHidden: false,
      dictionary: {
        custom: {
          StatusInscricao: {
            required: () => "Escolha o status da inscrição!"
          }
        }
      },
      rules: {
        length: len => v =>
          (v || "").length < len || `Tamanho máximo de 255 caracteres.`
      },
    };
  },

  watch: {
    Inscricao(val) {
      if (val != null) {
        this.initialize();
      }
    }
  },

  filters: {
    data: function(data) {
      return data ? moment(data).format("LLL") : "";
    },
    nota: function(nota) {
      return nota ? Number(nota).toFixed(1) : "";
    }
  },

  mounted() {
    this.$validator.localize("pt", this.dictionary);
  },

  methods: {
    getLabelFase(fase) {
      return store.getters["enums/getLabelFase"](fase);
    },
    getLabelNecessidade(necessidade) {
      return store.getters["enums/getLabelNecessidade"](necessidade);
    },
    getLabelStatusInscricao(status) {
      return store.getters["enums/getLabelStatusInscricao"](status);
    },
    initialize() {
      axios
        .get("/inscricoes/" + this.Inscricao.idInscricao + "/gestor")
        .then(res => {
          this.inscricao = res.data;
          this.Inscricao.idInscricao = res.data.id;
          axios
            .get("/arquivos/exibir/" + this.Inscricao.idInscricao)
            .then(res => {
              this.listaDeArquivos = res.data;
            });
        });
    },
    eventoAttDados(event) {
      event.target.statusInscricao;
    },
    analiseInscricao() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .put("/inscricoes/analisar/" + this.inscricao.exameId, {
              statusInscricao: this.statusInscricao,
              observacaoStatusInscricao: this.observacaoStatusInscricao,
              id: this.inscricao.id
            })
            .then(res => {
              if (res.data.local != null) {
                this.$emit("finalizado", {
                  novo: res.data.InscricaoCandidato,
                  antigo: this.Inscricao,
                  local: res.data.local
                });
              } else {
                this.$emit("finalizado", {
                  novo: res.data.InscricaoCandidato,
                  antigo: this.Inscricao
                });
              }
              this.limparCampos();
            })
            .catch(() => {
              this.snackbar = true;
              this.mensagem =
                "Não foi possível deferir a inscrição, pois não existe local de prova com vaga para alocar o colaborador !";
              this.cor = "error";
              this.dialog = false;
              this.limparCampos();
            });
          this.dialog = false;
        }
      });
    },

    limparCampos() {
      this.dialog = false;
      this.statusInscricao = "";
      this.observacaoStatusInscricao = "";
      this.errors.clear();
      this.$validator.reset();
    }
  }
};
</script>

<style>
h3 {
  color: #3c6781;
  font-weight: 400;
}

.APROVADO {
  color: #45e276;
}

.REPROVADO {
  color: #ea4c72;
}

.EM_AVALIACAO {
  color: #565656;
}
</style>