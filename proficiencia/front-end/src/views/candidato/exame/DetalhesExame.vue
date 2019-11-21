<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs icon="description" :items="breadcrumbItems" title="Detalhes do Exame"></app-breadcrumbs>
    <v-layout row wrap>
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
                  <span class="font-weight-bold text-uppercase">Status</span>
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
                <v-flex xs2>
                  <span class="font-weight-bold text-uppercase">Nº de inscritos / Nº de vagas</span>
                </v-flex>
              </v-layout>

              <v-layout row>
                <v-flex xs2>
                  <span class="font-weight-regular text-uppercase">{{ fase }}</span>
                </v-flex>
                <v-flex xs3>
                  <span class="font-weight-regular">{{ inicioInscricoes }}</span>
                </v-flex>
                <v-flex xs3>
                  <span class="font-weight-regular">{{ terminoInscricoes }}</span>
                </v-flex>
                <v-flex xs3>
                  <span class="font-weight-regular">{{ dataHoraProva }}</span>
                </v-flex>
                <v-flex xs2>
                  <span class="font-weight-regular">{{ exame.numeroInscritos + ' / ' + exame.numeroVagas }}</span>
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
                  <v-btn
                    color="success"
                    flat
                    :to="{name: 'RealizarInscricao', params:{id: this.id, titulo: this.exame.titulo}}"
                  >
                    <v-icon small>note_add</v-icon>Inscreva-se
                  </v-btn>
                </v-flex>
              </v-layout>
            </div>
          </v-widget>
        </v-flex>
      </v-layout>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";
import VWidget from "../../../components/core/VWidget";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import store from "../../../store";

export default {
  props: {
    id: String
  },

  components: {
    VWidget,
    AppBreadcrumbs
  },

  data() {
    return {
      exame: {
        terminoInscricoes: null,
        titulo: null,
        inicioInscricoes: null,
        dataHoraProva: null,
        descricao: null,
        localProva: null,
        numeroInscritos: null,
        fase: null
      },
      breadcrumbItems: [
        {
          position: 1,
          text: "Seleções Abertas",
          disabled: false,
          href: "/candidato/exames-abertos"
        }
      ]
    };
  },
  methods: {
    initialize() {
      axios.get("exame/" + this.id).then(res => {
        this.exame = res.data;
        this.breadcrumbItems.push({
          position: 2,
          text: this.exame.titulo,
          disabled: true,
          href: "/exames"
        });
      });
    }
  },

  computed: {
    fase() {
      return store.getters["enums/getLabelFase"](this.exame.fase);
    },
    inicioInscricoes() {
      return this.exame.inicioInscricoes
        ? moment(this.exame.inicioInscricoes).format("LLL")
        : "";
    },
    terminoInscricoes() {
      return this.exame.terminoInscricoes
        ? moment(this.exame.terminoInscricoes).format("LLL")
        : "";
    },
    dataHoraProva() {
      return this.exame.dataHoraProva
        ? moment(this.exame.dataHoraProva).format("LLL")
        : "";
    }
  },

  created: function() {
    this.initialize();
  }
};
</script>
