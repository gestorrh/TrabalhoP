<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="description" title="Lista de Inscrições"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-card>
          <v-data-table
            :headers="headers"
            :items="inscricoes"
            hide-actions
            no-data-text="Nenhum inscrição encontrada"
            :custom-sort="sort"
          >
            <template v-slot:items="inscricoes">
              <td class="body-1">
                <b>{{ inscricoes.item.titulo }}</b>
              </td>
              <td
                class="body-1 font-weight-bold text-uppercase"
              >{{ getLabelFase(inscricoes.item.fase) }}</td>
              <td class="body-1">{{ inscricoes.item.nota ? inscricoes.item.nota : '-' }}</td>
              <td
                class="body-1 font-weight-bold text-uppercase"
                :class="inscricoes.item.status ? inscricoes.item.status.value : ''"
              >
                <b>{{inscricoes.item.status ? inscricoes.item.status.label : '-'}}</b>
              </td>
              <td>
                <v-btn
                  color="cyan"
                  outline
                  round
                  v-bind:to="'/candidato/inscricoes/' + inscricoes.item.id"
                >
                  <v-icon small>search</v-icon>Detalhes
                </v-btn>
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
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import store from "../../../store";

export default {
  components: {
    AppBreadcrumbs
  },

  data() {
    return {
      headers: [
        { text: "TÍTULO", value: "titulo", sortable: true },
        { text: "FASE", value: "fase", sortable: true },
        { text: "NOTA", value: "nota", sortable: true },
        { text: "STATUS", value: "status", sortable: true },
        { text: "", value: "acoes", sortable: false }
      ],
      inscricoes: [],
      breadcrumbItems: [
        { position: 1, text: "Lista de Inscrições", disabled: true }
      ]
    };
  },

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      axios.get("/inscricoes").then(res => {
        const listInscricoes = res.data.map(inscricao => {
          let nota;
          if (inscricao.fase == "FINALIZADO")
            nota = inscricao.reavaliacaoSolicitada
              ? inscricao.notaReavaliacao
              : inscricao.notaAvaliacao;

          return {
            id: inscricao.id,
            titulo: inscricao.titulo,
            fase: inscricao.fase,
            nota,
            status: inscricao.statusAvaliacao
          };
        });
        this.inscricoes = listInscricoes;
      });
    },

    getLabelFase(fase) {
      return store.getters["enums/getLabelFase"](fase);
    },

    getPosicaoFase(fase) {
      return store.getters["enums/getPosicaoFase"](fase);
    },

    getLabelStatusAvaliacao(status) {
      return this.store["enums/getLabelStatusAvaliacao"](status);
    },

    sort(items, index, isDescending) {
      if (index !== null && isDescending !== null) {
        switch (index[0]) {
          case 1:
            items.sort((itemA, itemB) => {
              let mod = isDescending ? -1 : 1;
              if (
                this.getPosicaoFase(itemA.fase) >
                this.getPosicaoFase(itemB.fase)
              ) {
                return 1 * mod;
              } else if (
                this.getPosicaoFase(itemA.fase) <
                this.getPosicaoFase(itemB.fase)
              ) {
                return -1 * mod;
              }
              return 0;
            });
            break;
          default:
            items.sort((itemA, itemB) => {
              let mod = isDescending ? -1 : 1;
              if (itemA.titulo > itemB.titulo) {
                return 1 * mod;
              } else if (itemA.titulo < itemB.titulo) {
                return -1 * mod;
              }
              return 0;
            });
            break;
        }
      }

      return items;
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
