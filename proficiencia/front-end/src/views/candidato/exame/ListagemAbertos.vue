<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-xl>
    <app-breadcrumbs icon="ballot" :items="breadcrumbItems" title="Seleções abertas"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-card>
          <v-data-table
            :headers="headers"
            :items="exame"
            hide-actions
            no-data-text="Nenhum exame aberto"
          >
            <template v-slot:items="exame">
              <td class="body-1">
                <b>{{exame.item.titulo }}</b>
              </td>
              <td class="body-1">{{ exame.item.inicioInscricoes | dataHora}}</td>
              <td class="body-1">{{exame.item.terminoInscricoes | dataHora}}</td>
              <td class="body-1">{{ exame.item.inscricoes }} / {{ exame.item.numeroVagas }}</td>
              <td>
                <v-btn
                  color="cyan"
                  outline
                  round
                  v-bind:to="'/candidato/exames-abertos/' + exame.item.id"
                >
                  <v-icon small>search</v-icon>Detalhes
                </v-btn>
                <v-tooltip top>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      color="success"
                      dark
                      fab
                      outline
                      small
                      :to="{name: 'RealizarInscricao', params:{id: exame.item.id, titulo: exame.item.titulo}}"
                      v-on="on"
                    >
                      <v-icon>note_add</v-icon>
                    </v-btn>
                  </template>
                  <span>Realizar Inscrição</span>
                </v-tooltip>
              </td>
            </template>
          </v-data-table>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";

export default {
  components: {
    AppBreadcrumbs
  },
  data() {
    return {
      candidatoId: "",
      exame: [],
      headers: [
        { text: "TÍTULO", value: [0], sortable: true },
        { text: "INÍCIO DAS INSCRIÇÕES", value: [1], sortable: false },
        { text: "TÉRMINO DAS INSCRIÇÕES", value: [1], sortable: false },
        { text: "Nº DE INSCRITOS / Nº DE VAGAS", value: [2], sortable: false },
        {value: null, sortable: false}
      ],
      dialog: false,
      inscricoes: [],
      breadcrumbItems: [
        { position: 1, text: "Seleções abertas", disabled: true }
      ]
    };
  },
  created: function() {
    this.initialize();
  },
  mounted: function() {
    this.candidatoId = this.$route.params.id;
    this.initialize();
  },
  filters: {
    dataHora: function(dataHora) {
      return dataHora ? moment(dataHora).format("LLL") : "";
    },
  },
  methods: {
    initialize() {
      axios.get("exame/todos-candidato-nao-inscrito").then(res => {
        this.exame = res.data;
      });
    }
  }
};

</script>
