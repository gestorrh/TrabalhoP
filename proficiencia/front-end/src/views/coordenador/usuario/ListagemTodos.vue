<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="people" title="Usuários"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget title="Lista de Usuários">
          <div slot="widget-content">
            <v-tooltip top>
              <template v-slot:activator="{ on }">
                <v-btn absolute color="blue" dark fab right to="/coordenador/usuarios/adicionar" top v-on="on">
                  <v-icon>person_add</v-icon>
                </v-btn>
              </template>
              <span>Adicionar Usuário</span>
            </v-tooltip>
            <v-text-field
              append-icon="search"
              class="pesquisa"
              hide-details
              label="Buscar Usuários"
              single-line
              v-model="search"
            ></v-text-field>
            <v-data-table
              no-data-text="Nenhum usuário cadastrado"
              :no-results-text="'Nenhum resultado para: ' + search"
              :headers="headers"
              :items="usuarios"
              :search="search"
              hide-actions
            >
              <template v-slot:items="usuario">
                <td class="body-1">
                  <b>{{usuario.item.nome }}</b>
                </td>
                <td class="body-1">{{usuario.item.cpf | cpf}}</td>
                <td class="body-1">{{usuario.item.papeis}}</td>

                <td>
                  <v-btn :to="'/coordenador/usuarios/'+usuario.item.id" color="cyan" outline round>
                    <v-icon dark small>search</v-icon>Detalhes
                  </v-btn>
                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <v-btn
                        color="indigo"
                        fab
                        outline
                        small
                        v-on="on"
                        :to="{name: 'EditarUsuario', params:{id: usuario.item.id}}"                       
                      >
                        <v-icon>edit</v-icon>
                      </v-btn>
                    </template>
                    <span>Editar Usuário</span>
                  </v-tooltip>

                  <v-tooltip top>
                    <template v-slot:activator="{ on }">
                      <v-btn
                        @click="abrirDialogExcluirUsuario(usuario.item)"
                        color="red"
                        fab
                        outline
                        small
                        v-on="on"
                      >
                        <v-icon>delete</v-icon>
                      </v-btn>
                    </template>
                    <span>Excluir Usuário</span>
                  </v-tooltip>
                </td>
              </template>
              <template v-slot:no-results>
                <v-alert
                  :value="true"
                  color="error"
                  icon="warning"
                >O usuario "{{ search }}" não existe.</v-alert>
              </template>
            </v-data-table>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>

    <v-dialog max-width="290" v-model="dialog">
      <v-card>
        <v-card-title class="headline">Excluir Usuário?</v-card-title>

        <v-card-text>Tem certeza que deseja excluir usuário?</v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn @click="dialog = false" color="silver darken-1" flat="flat">Não</v-btn>

          <v-btn @click="excluirUsuario()" color="red darken-1" flat="flat">Sim</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar :color="dCor" right top v-model="dSnackbar">{{ dMensagem }}</v-snackbar>
  </v-container>
</template>
<script>
import axios from "axios";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import VWidget from "../../../components/core/VWidget";

export default {
  props: {
    cor: {
      type: String,
      default: ""
    },
    snackbar: {
      type: Boolean,
      default: false
    },
    mensagem: {
      type: String,
      default: ""
    }
  },
  components: {
    VWidget,
    AppBreadcrumbs
  },
  data() {
    return {
      headers: [
        {
          text: "NOME",
          sortable: true,
          align: "left",
          value: "nome"
        },

        { text: "CPF", value: "cpf" },
        { text: "PAPEL", value: "papeis" },
        { text: "", value: "", sortable: false }
      ],
      search: "",
      usuarios: [],
      usuarioAux: {},

      dialog: false,

      breadcrumbItems: [
        { position: 1, text: "Usuarios", disabled: true, href: "/usuarios" }
      ],

      dCor: this.cor,
      dSnackbar: this.snackbar,
      dMensagem: this.mensagem
    };
  },
  created: function() {
    this.initialize();
  },
  filters: {
    cpf: function(cpf) {
      if (!cpf) return "";
      return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
    }
  },
  methods: {
    initialize() {
      axios.get("usuario/listarUsuarios").then(res => {
        this.usuarios = res.data;
      });
    },

    excluirUsuario() {
      axios
        .delete("/usuario/excluir-usuario/" + this.usuarioAux.id)
        .then(() => {
          this.usuarios.splice(this.usuarios.indexOf(this.usuarioAux), 1);
          this.dialog = false;
          this.usuarioAux = null;
          this.dCor = "success";
          this.dSnackbar = true;
          this.dMensagem = "Usuário excluído com sucesso";
        })
        .catch(error => {
          if (error.response) {
            this.dialog = false;
            this.usuarioAux = null;
            this.dCor = "error";
            this.dSnackbar = true;
            this.dMensagem = error.response.data;
          }
        });
    },

    abrirDialogExcluirUsuario(usuario) {
      this.usuarioAux = usuario;
      this.dialog = true;
    }
  }
};
</script>

<style>
.pesquisa {
  margin-inline: 1%;
}
</style>