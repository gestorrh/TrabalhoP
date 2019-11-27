<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="class" title="Detalhes da Inscrição"></app-breadcrumbs>
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
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Título</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Fase do exame</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Status da inscrição</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Data de inscrição</span>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex xs3>
                <span class="font-weight-regular">{{ inscricao.titulo }}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular text-uppercase">{{ getLabelFase(inscricao.fase) }}</span>
              </v-flex>
              <v-flex xs3>
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
              <v-flex xs3>
                <span class="font-weight-regular">{{ inscricao.dataHoraInscricao | data }}</span>
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
                <span class="font-weight-bold text-uppercase">Status da avaliação:</span>
                <span
                  class="text-uppercase"
                  v-bind:class="inscricao.statusAvaliacao ? inscricao.statusAvaliacao : ''"
                >{{ getLabelStatusAvaliacao(inscricao.statusAvaliacao) }}</span>
              </v-flex>
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
                <v-btn
                  color="primary" 
                  dark
                  flat
                  @click="abrirDialogAnalise()"
                  v-if="!isHidden"
                  v-show="inscricao.instituicao == 'UFC' && bloquearBotaoAnalise(inscricao)"
                >
                  <v-icon small>fas fa-edit</v-icon>Analisar Inscrição
                </v-btn>
              </v-flex>
            </v-layout>
          </div>
        </v-widget>
      </v-flex>

      <v-flex xs12>
        <v-widget title="Arquivos da Inscrição">
          <div slot="widget-content" id="haha">
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn
                  absolute
                  color="blue"
                  dark
                  fab
                  right
                  top
                  v-on="on"
                  :disabled="mostrarInputButton"
                  id="fileInputButton"
                  onclick="document.getElementById('files').click()"
                >
                  <v-icon>note_add</v-icon>
                  <input
                    id="files"
                    ref="files"
                    type="file"
                    accept="application/pdf"
                    style="display:none"
                    v-on:change="selecionarArquivos()"
                  />
                </v-btn>
              </template>
              <span>Adicionar Arquivo</span>
            </v-tooltip>
            <media
              :lista="listaDeArquivos"
              :inscricaoId="inscricaoId"
              :inscricaoFase="inscricao.fase"
              :inscricaoStatus="inscricao.statusInscricao"
            ></media>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>

    <v-snackbar :color="cor" right top v-model="snackbarAnalise">{{ mensagem }}</v-snackbar>

    <v-dialog max-width="550" persistent v-model="dialogAnalise">
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
              placeholder="Clique aqui para inserir a observação."
              maxlength="255"
              counter="255"
              :rules="[rules.length(255)]"
            ></v-textarea>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn @click="limparCampos" color="silver darken-1" flat="flat">Fechar</v-btn>

              <v-btn @click="analiseInscricao" color="primary" flat="flat">Salvar</v-btn>
            </v-card-actions>
          </v-form>
        </v-container>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialog" persistent max-width="500">
      <v-card>
        <v-card-title class="headline">Enviar arquivo</v-card-title>
        <v-card-title v-if="file!=null">
          <div class="text-truncate">Nome do arquivo: {{ file[0].name }}</div>
        </v-card-title>
        <v-card-title>
          <v-textarea
            id="cardObservacao"
            v-model="observacao"
            placeholder="Clique aqui para inserir."
            label="Observação"
            maxlength="255"
            counter="255"
            :rules="[rules.length(255)]"
          ></v-textarea>
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn flat="flat" text @click="limparArquivo()">CANCELAR</v-btn>
          <v-btn color="primary darken-1" flat="flat" text @click="enviarArquivos()">ENVIAR</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar :color="snackbarCor" right top v-model="snackbar">{{ snackbarMensagem }}</v-snackbar>
  </v-container>
</template>


<script>
import Vue from "vue";
import axios from "axios";
import VWidget from "../../../components/core/VWidget";
import VeeValidate from "vee-validate";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import moment from "moment";
import store from "../../../store";
import media from "../../../components/Media";

Vue.use(VeeValidate);
moment.locale("pt-br");

export default {
  components: {
    VWidget,
    AppBreadcrumbs,
    media
  },

  data() {
    return {
      statusInscricao: null,
      observacaoStatusInscricao: null,
      id: null,
      inscricao: {},
      documentacao: {},
      isHidden: false,
      inscricaoId: "",
      inscricaoIndex: null,

      breadcrumbItems: [
        {
          position: 1,
          text: "Exames",
          disabled: false,
          href: "/coordenador/exames"
        }
      ],
      listaDeArquivos: [],
      snackbarAnalise: false,
      snackbarCor: "",
      mensagem: "",
      cor: "",
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
      file: null,
      observacao: "",
      dialog: false,
      dialogAnalise: false,
      snackbar: false,
      snackbarMensagem: null,
    };
  },

  filters: {
    data: function(data) {
      return data ? moment(data).format("LLL") : "";
    },
    nota: function(nota) {
      return nota ? Number(nota).toFixed(1) : "";
    }
  },

  mounted: function() {
    this.inscricaoId = this.$route.params.id;
    this.initialize();
  },

  computed: {
    mostrarInputButton: function() {
      return (
        this.inscricao.fase != "INSCRICAO" ||
        this.inscricao.statusInscricao != "INSCRITO"
      );
    }
  },

  methods: {
    getLabelStatusInscricao(status) {
      return store.getters["enums/getLabelStatusInscricao"](status);
    },
    getLabelStatusAvaliacao(status) {
      return store.getters["enums/getLabelStatusAvaliacao"](status);
    },
    getLabelFase(fase) {
      return store.getters["enums/getLabelFase"](fase);
    },
    getLabelNecessidade(necessidade) {
      return store.getters["enums/getLabelNecessidade"](necessidade);
    },
    abrirDialogAnalise() {
      this.dialogAnalise = true;
    },
    initialize() {
      axios
        .get("/inscricoes/" + this.inscricaoId + "/coordenador")
        .then(res => {
          this.inscricao = res.data;
          this.breadcrumbItems.push({
            position: 2,
            text: this.inscricao.titulo,
            href: "/coordenador/exames/" + this.inscricao.exameId
          });
          this.breadcrumbItems.push({
            position: 3,
            text: this.inscricao.nome,
            disabled: true
          });
        });

      axios.get("/arquivos/exibir/" + this.inscricaoId).then(res => {
        this.listaDeArquivos = res.data;
      });
    },

    analiseInscricao() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .put("/inscricoes/analisar/" + this.inscricao.exameId, {
              exameId: this.inscricao.exameId,
              id: this.inscricao.id,
              statusInscricao: this.statusInscricao,
              observacaoStatusInscricao: this.observacaoStatusInscricao
            })
            .then(res => {
              this.inscricao.statusInscricao = res.data.InscricaoCandidato.statusInscricao;
              this.inscricao.observacaoStatusInscricao = res.data.InscricaoCandidato.observacaoStatusInscricao;
              if(this.inscricao.statusInscricao == "INDEFERIDO"){
                this.snackbarAnalise = true;
                this.mensagem = "Inscrição indeferida com sucesso !"
                this.cor = "success"
              } else {
                this.snackbarAnalise = true;
                this.mensagem = "Inscrição deferida com sucesso !"
                this.cor = "success"
              }
              
              
              
            })
            .catch(() => {
              this.snackbarAnalise = true;
              this.mensagem =
                "Não foi possível deferir a inscrição, pois não existe local de prova com vaga para alocar o candidato !";
              this.cor = "error";
              this.dialogAnalise = false;
              this.limparCampos();
            });
          this.dialogAnalise = false;
        }
      });
    },
    bloquearBotaoAnalise(exame) {
      if (
        exame.fase == "SELECAO" &&
        this.inscricao.statusInscricao == "INSCRITO"
      ) {
        return true;
      }
      return false;
    },
    limparCampos() {
      this.dialogAnalise = false;
      this.statusInscricao = "";
      this.observacaoStatusInscricao = "";
      this.errors.clear();
      this.$validator.reset();
    },
    selecionarArquivos() {
      let uploadedFiles = this.$refs.files.files;
      this.file = uploadedFiles;
      this.dialog = true;
    },

    limparArquivo() {
      this.file = null;
      this.observacao = "";
      this.dialog = false;
      document.getElementById("files").value = "";
    },

    enviarArquivos() {

      const formData = new FormData();
      formData.append("file", this.file[0]);
      formData.append("observacao", this.observacao);
      axios
        .post("/inscricoes/" + this.inscricaoId, formData)
        .then(res => {
          if (
            this.file[0].name.substr(-4) == ".pdf" ||
            this.file[0].type == "application/pdf"
          ) {
            this.listaDeArquivos.push(res.data);
            this.snackbar = true;
            this.snackbarMensagem = "Arquivo adicionado com sucesso!";
            this.snackbarCor = "success";
            this.limparArquivo();
          }
        })
        .catch(error => {
         
          this.snackbar = true;
          this.snackbarMensagem = error.response.data;
          this.snackbarCor = "error";
          this.limparArquivo();
        });
    }
  }
};
</script>

<style>
.listaVazia {
  position: absolute;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
}

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
