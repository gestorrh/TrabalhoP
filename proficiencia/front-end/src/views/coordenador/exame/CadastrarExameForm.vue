<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="note_add" title="Cadastrar Exame"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget enableActions title="Formulário de exame">
          <div slot="widget-content">
            <v-form>
              
              <v-layout row>
                <v-flex md6>
                  <v-text-field
                    :error-messages="errors.collect('titulo')"
                    data-vv-name="titulo"
                    label="Título*"
                    v-model="titulo"
                    v-validate="'required'"
                  ></v-text-field>
                </v-flex>
                <v-flex md4>
                  <v-text-field
                    :error-messages="errors.collect('localProva')"
                    data-vv-name="localProva"
                    label="Local da Prova*"
                    v-model="localProva"
                    v-validate="'required'"
                  ></v-text-field>
                </v-flex>
                <v-flex md2>
                  <v-text-field
                    :error-messages="errors.collect('numeroVagas')"
                    data-vv-name="numeroVagas"
                    label="Número de vagas*"
                    type="number"
                    v-model="numeroVagas"
                    v-validate="'required|min_value:1'"
                  ></v-text-field>
                </v-flex>
              </v-layout>

              <v-layout row>
                <v-flex md12>
                  <v-textarea label="Descrição" v-model="descricao"></v-textarea>
                </v-flex>
              </v-layout>

              <v-layout row>
                <v-flex md6>
                  <date-time-picker
                    v-model="inicioInscricoes"
                    label="Início das inscrições*"
                    ref="inicioInscricoes"
                    :error-messages="errors.collect('inicioInscricoes')"
                    data-vv-name="inicioInscricoes"
                    v-validate="'required'"
                  ></date-time-picker>
                </v-flex>

                <v-flex md6>
                  <date-time-picker
                    v-model="terminoInscricoes"
                    label="Término das inscrições*"
                    ref="terminoInscricoes"
                    :error-messages="errors.collect('terminoInscricoes')"
                    data-vv-name="terminoInscricoes"
                    v-validate="'required|dataHoraMaior:inicioInscricoes'"
                  ></date-time-picker>
                </v-flex>
              </v-layout>

            

              <v-layout row>
                <v-flex md6>
                  <date-time-picker
                    v-model="dataHoraProva"
                    label="Data e hora da prova"
                    ref="dataHoraProva"
                    :error-messages="errors.collect('dataHoraProva')"
                    data-vv-name="dataHoraProva"
                    v-validate="'dataHoraMaior:terminoInscricoes'"
                  ></date-time-picker>
                </v-flex>

                <v-flex md6>
                  <date-picker
                    v-model="dataResultado"
                    label="Data do resultado"
                    ref="dataResultado"
                    :error-messages="errors.collect('dataResultado')"
                    data-vv-name="dataResultado"
                    v-validate="'dataHoraMaior:dataHoraProva'"
                  ></date-picker>
                </v-flex>
              </v-layout>

              <v-layout row>
                <v-flex md6>
                  <date-time-picker
                    v-model="inicioReavaliacao"
                    label="Início dos pedidos de reavaliação"
                    ref="inicioReavaliacao"
                    :error-messages="errors.collect('inicioReavaliacao')"
                    data-vv-name="inicioReavaliacao"
                    v-validate="'dataHoraMaior:dataResultado'"
                  ></date-time-picker>
                </v-flex>
                <v-flex md6>
                  <date-time-picker
                    v-model="terminoReavaliacao"
                    label="Término dos pedidos de reavaliação"
                    ref="terminoReavaliacao"
                    :error-messages="errors.collect('terminoReavaliacao')"
                    data-vv-name="terminoReavaliacao"
                    v-validate="'dataHoraMaior:inicioReavaliacao'"
                  ></date-time-picker>
                </v-flex>
              </v-layout>

              <v-btn :disabled="errors.any()" depressed @click="submit" color="primary">CADASTRAR</v-btn>
              <v-btn to="/coordenador/exames">CANCELAR</v-btn>
            </v-form>
          </div>
          <div slot="widget-actions">
            <span class="caption">* Campos Obrigatórios</span>
          </div>
        </v-widget>
      </v-flex>
    </v-layout>

    <v-snackbar right top :color="cor" v-model="snackbar">{{ mensagem }}</v-snackbar>
  </v-container>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import moment from "moment";
import VeeValidate, { Validator } from "vee-validate";
import VWidget from "../../../components/core/VWidget";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import DateTimePicker from "../../../components/inputs/DateTimePicker";
import DatePicker from "../../../components/inputs/DatePicker";

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
  components: {
    DatePicker,
    DateTimePicker,
    VWidget,
    AppBreadcrumbs
  },
  data() {
    return {

      titulo: "",
      localProva: "",
      numeroVagas: "",
      descricao: "",
      inicioInscricoes: "",
      terminoInscricoes: "",
      dataHoraProva: "",
      dataResultado: "",
      inicioReavaliacao: "",
      terminoReavaliacao: "",

      dictionary: {
        custom: {
          titulo: {
            required: () => "O título do exame é obrigatório!"
          },
          localProva: {
            required: () => "O local da prova é obrigatório!"
          },
          numeroVagas: {
            required: () => "O número de vagas do exame é obrigatório!",
            min_value: () => "O exame deve ter ao menos uma vaga!"
          },
          inicioInscricoes: {
            required: () => "O início das inscrições é obrigatória!"
          },
          terminoInscricoes: {
            required: () => "A data de término das inscrições é obrigatória!",
            dataHoraMaior: () =>
              "A data/hora de término das inscrições deve ser maior que a data/hora de início!"
          },
          dataHoraProva: {
            dataHoraMaior: () =>
              "A data/hora da prova deve ser maior que a data/hora término das inscrições!"
          },
          dataResultado: {
            dataHoraMaior: () =>
              "A data do resultado deve ser maior que a data da prova!"
          },
          inicioReavaliacao: {
            dataHoraMaior: () =>
              "A data/hora de início dos pedidos de reavaliação deve ser maior que a data do resultado!"
          },
          terminoReavaliacao: {
            dataHoraMaior: () =>
              "A data/hora de término dos pedidos de reavaliação deve ser maior que a data/hora de início dos pedidos de reavaliação!"
          }
        }
      },

      breadcrumbItems: [
        { position: 1, text: "Exames", disabled: false, href: "/coordenador/exames" },
        { position: 2, text: "Cadastrar Exame", disabled: true }
      ],

      snackbar: false,
      cor: "",
      mensagem: ""
    };
  },
  mounted() {
    this.$validator.localize("pt", this.dictionary);
  },
  methods: {
    submit() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .post("/exame", {
              titulo: this.titulo,
              localProva: this.localProva,
              numeroVagas: this.numeroVagas,
              descricao: this.descricao,
              inicioInscricoes: this.inicioInscricoes,
              terminoInscricoes: this.terminoInscricoes,
              dataHoraProva: this.dataHoraProva,
              dataResultado: this.dataResultado,
              inicioReavaliacao: this.inicioReavaliacao,
              terminoReavaliacao: this.terminoReavaliacao
            })
            .then(res => {
              this.$router.push("/coordenador/exames/" + res.data);
            })
            .catch(err => {
              this.mensagem = err;
              this.cor = "error";
              this.snackbar = true;
            });
        } else {
          this.mensagem = "O formulário contém erros!";
          this.cor = "error";
          this.snackbar = true;
        }
      });
    }
  }
};
</script>