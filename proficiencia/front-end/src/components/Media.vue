<template>
  <div id="media">
    <div class="layout row">
      <v-layout row wrap>
        <v-flex v-for="item in lista" :key="item.id" xs12 sm6 md3>
          <v-card class="ml-2 mt-2 mr-2 mx-auto elevation-3">
            <v-icon
              id="icone"
              color="primary"
              class="d-flex pt-2 mb-1"
              size="130"
            >far fa-file-pdf</v-icon>
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <div
                  v-on="on"
                  class="text-right pa-3 text-truncate font-weight-bold text-uppercase"
                >{{item.nome}}</div>
              </template>
              <span>{{item.nome}}</span>
            </v-tooltip>
            <v-divider></v-divider>
            <v-tooltip top max-width="270">
              <template v-slot:activator="{ on }">
                <div
                  v-if="item.observacao != ''"
                  v-on="on"
                  class="pa-3 font-weight-regular text-start text-truncate"
                >{{item.observacao}}</div>
                <div v-else class="pa-3 font-weight-regular text-start">-</div>
              </template>
              <span>{{item.observacao}}</span>
            </v-tooltip>

            <v-divider></v-divider>
            <v-card-actions class="white justify-center">
              <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                  <v-btn
                    flat
                    color="green"
                    v-on="on"
                    target="_blank"
                    @click="exibirArquivo(item.id)"
                  >
                    <v-icon id="iconesAcoes">visibility</v-icon>
                  </v-btn>
                </template>
                <span>Visualizar Arquivo</span>
              </v-tooltip>
              <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                  <v-btn flat color="indigo" v-on="on" @click="downloadArquivo(item.id)">
                    <v-icon>cloud_download</v-icon>
                  </v-btn>
                </template>
                <span>Baixar Arquivo</span>
              </v-tooltip>
              <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                  <v-btn 
                  dark
                  flat
                  color="red" 
                  v-on="on" 
                  :disabled="mostrarInputButton" 
                  @click="abrirDialog(item)">
                    <v-icon>delete</v-icon>
                  </v-btn>
                </template>
                <span>Excluir Arquivo</span>
              </v-tooltip>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
      <div v-if="lista.length == 0">
        <span class="listaVazia">Nenhum arquivo adicionado</span>
      </div>
      <v-dialog v-model="dialog" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">Excluir arquivo</v-card-title>
          <v-card-text>Deseja realmente excluir o arquivo?</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="silver darken-1" flat="flat" text @click="dialog = false">CANCELAR</v-btn>
            <v-btn
              color="red darken-1"
              flat="flat"
              text
              @click="excluirArquivo(arquivoExcluir)"
            >APAGAR</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-snackbar :color="snackbarCor" right top v-model="snackbar">{{ snackbarMensagem }}</v-snackbar>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "media",

  props: {
    lista: Array,
    inscricaoId: Number.parseInt(String),
    inscricaoFase: String,
    inscricaoStatus: String
  },

  data: () => ({
    dialog: false,
    arquivoExcluir: null,
    snackbar: false,
    snackbarMensagem: null,
    snackbarCor: null
  }),
  computed: { 
    mostrarInputButton: function(){
      return(this.inscricaoFase != "INSCRICAO" || this.inscricaoStatus != 'INSCRITO');
    },
  },
  filters: {
    truncateNome: function(value) {
      if (value.length > 23) {
        value = value.substring(0, 20) + "...";
      }
      return value;
    },
    truncateObservacao: function(value) {
      if (value.length > 26) {
        value = value.substring(0, 24) + "...";
      }
      return value;
    }
  },
  methods: {
    abrirDialog(arquivoExcluir) {
      this.dialog = true;
      this.arquivoExcluir = arquivoExcluir;
    },

    downloadArquivo(arquivoId) {
      axios({
        url: process.env.VUE_APP_API_URL + "/arquivos/download/" + arquivoId,
        method: "GET",
        responseType: "blob"
      }).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", response.headers.nomearquivo);
        document.body.appendChild(link);
        link.click();
      });
    },
    exibirArquivo(arquivoId) {
      axios({
        url: process.env.VUE_APP_API_URL + "/arquivos/download/" + arquivoId,
        method: "GET",
        responseType: "blob"
      }).then(response => {
        const url = window.URL.createObjectURL(
          new Blob([response.data], { type: "application/pdf" })
        );
        window.open(url);
      });
    },

    excluirArquivo() {
      axios
        .delete(
          "/inscricoes/" + this.arquivoExcluir.id + "/" + this.inscricaoId
        )
        .then(() => {
          this.lista.splice(this.lista.indexOf(this.arquivoExcluir), 1);
          this.arquivoExcluir = null;
          this.dialog = false;
          this.snackbar = true;
          this.snackbarMensagem = "Arquivo excluído com sucesso!";
          this.snackbarCor = "success";
        })
        .catch(error => {
          this.snackbar = true;
          this.snackbarMensagem = error.response.data;
          this.snackbarCor = "error";
        });
    }
  }
};
</script>
<style scoped>
.listaVazia {
  position: absolute;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>