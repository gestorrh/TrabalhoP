<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs icon="class" :items="breadcrumbItems" title="Detalhes da Inscrição"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget title="Informações Básicas">
          <div slot="widget-content">
            <v-layout row>
              <v-flex xs12>
                <span class="title" style="font-weight:bold">{{ inscricao.titulo }}</span>
              </v-flex>
            </v-layout>
            <v-divider></v-divider>
            <v-layout row>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Status do exame</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Início das inscrições</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Término das inscrições</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-bold text-uppercase">Data da prova</span>
              </v-flex>
            </v-layout>

            <v-layout row>
              <v-flex xs3>
                <span class="font-weight-regular text-uppercase">{{ getLabelFase(inscricao.fase) }}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular">{{ inscricao.inicioInscricoes | dataHora}}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular">{{ inscricao.terminoInscricoes | dataHora}}</span>
              </v-flex>
              <v-flex xs3>
                <span class="font-weight-regular">{{ inscricao.dataHoraProva | dataHora}}</span>
              </v-flex>
            </v-layout>

            <v-divider></v-divider>

            <v-layout row>
              <v-flex xs12>
                <span class="font-weight-bold text-uppercase">Descrição:</span>
                <span class="font-weight-regular">{{ inscricao.descricao }}</span>
              </v-flex>
            </v-layout>

            <v-divider></v-divider>

            <v-layout row>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Data da inscrição:</span>
                <span class="font-weight-regular">{{ inscricao.dataHoraInscricao | dataHora}}</span>
              </v-flex>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Situação da inscrição:</span>
                <span
                  class="font-weight-regular text-uppercase"
                >{{ getLabelStatusInscricao(inscricao.statusInscricao) }}</span>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Nome do programa de pós-graduação:</span>
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
                <span class="font-weight-bold text-uppercase">Prioridade:</span>
                <span class="font-weight-regular">{{ inscricao.prioridade }}</span>
              </v-flex>
              <v-flex xs6>
                <span class="font-weight-bold text-uppercase">Formação:</span>
                <span class="font-weight-regular">{{ inscricao.formacao }}</span>
              </v-flex>
            </v-layout>
          </div>
        </v-widget>
      </v-flex>
      <v-flex xs12>
        <v-widget title="Arquivos da Inscrição">
          <div slot="widget-content">
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn
                  absolute
                  color="blue"
                  dark
                  fab
                  right
                  top
                  :disabled="mostrarInputButton"
                  v-on="on"
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
            <media :lista="listaDeArquivos" :inscricaoId="inscricaoId" :inscricaoFase="inscricao.fase" :inscricaoStatus="inscricao.statusInscricao"></media>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>
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
import axios from "axios";
import moment from "moment";
import VWidget from "../../../components/core/VWidget";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import store from "../../../store";
import media from "../../../components/Media";

moment.locale("pt-br");
export default {
  components: {
    VWidget,
    AppBreadcrumbs,
    media
  },
  data() {
    return {
      file: null,
      observacao: "",
      dialog: false,
      inscricaoId: "",
      inscricao: {},
      
      breadcrumbItems: [
        {
          position: 1,
          text: "Minhas Inscrições",
          disabled: false,
          href: "/candidato/inscricoes"
        }
      ],
      rules: {
        length: len => v => (v || '').length < len || `Tamanho máximo de 255 caracteres.`
      },
      listaDeArquivos: [],
      snackbar: false,
      snackbarMensagem: null,
      snackbarCor: null
    };
  },
  created: function() {
    this.inscricaoId = this.$route.params.id;
    this.initialize();
  },
  filters: {
    dataHora: function(dataHora) {
      if (!dataHora) return "";
      return moment(dataHora).format("LLL");
    }
  },
  formatarData() {
    moment.locale("pt-br");
    let Data = this.exame.dataHoraProva;
    this.exame.dataHoraProva = this.exame.dataHoraProva
      ? moment(Data).format("LLL")
      : "-";
  },
  computed: { 
    mostrarInputButton: function(){
      return (this.inscricao.fase != "INSCRICAO");
    },
  },

  methods: {
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
    },

    getLabelFase(fase) {
      return store.getters["enums/getLabelFase"](fase);
    },
    getLabelStatusInscricao(status) {
      return store.getters["enums/getLabelStatusInscricao"](status);
    },
    initialize() {
      axios.get("/inscricoes/" + this.inscricaoId + "/candidato").then(res => {
        this.inscricao = res.data;
        this.breadcrumbItems.push({
          position: 2,
          text: this.inscricao.titulo,
          disabled: true
        });
      });
      axios.get("/arquivos/exibir/" + this.inscricaoId).then(res => {
        this.listaDeArquivos = res.data;
      });
    }
  }
};
</script>