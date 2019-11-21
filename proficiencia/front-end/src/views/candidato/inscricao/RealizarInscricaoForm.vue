<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs icon="note_add" :items="breadcrumbItems" title="Realizar Inscrição"></app-breadcrumbs>
    <v-layout row wrap>
      <v-flex xs12>
        <v-widget title="Formulário de Inscrição">
          <div slot="widget-content">
            <v-stepper v-model="stepperSide" vertical>
              <v-form action="/realizarInscricao" lazy-validation method="post" ref="form">
                <v-stepper-step :complete="stepperSide > 1" step="1">
                  Seus dados cadastrais
                  <small>Confirme alguns dados</small>
                </v-stepper-step>
                <v-stepper-content step="1">
                  <v-text-field
                    :error-messages="errors.collect('nome')"
                    label="Nome*"
                    oninput="this.value = this.value.toUpperCase()"
                    v-model="nome"
                    data-vv-name="nome"
                    v-validate="'required'"
                  ></v-text-field>
                  <v-layout row>
                    <v-flex xs6>
                      <v-text-field
                        :error-messages="errors.collect('email')"
                        label="Email*"
                        v-model="email"
                        v-validate="'required|email'"
                        data-vv-name="email"
                      ></v-text-field>
                    </v-flex>
                    <v-flex xs6>
                      <telefone
                        v-model="telefone"
                        label="Telefone*"
                        :error-messages="errors.collect('telefone')"
                        data-vv-name="telefone"
                        v-validate="{required: true, min: 10}"
                      >></telefone>
                    </v-flex>
                  </v-layout>
                  <v-spacer></v-spacer>
                  <v-layout row>
                    <v-flex xs6>
                      <v-select
                        :items="necessidades"
                        item-text="label"
                        item-value="value"
                        label="Necessidade Especial"
                        v-model="necessidadeEspecial"
                        data-vv-name="necessidadeEspecial"
                        clearable
                      ></v-select>
                    </v-flex>
                    <v-flex v-show="necessidadeEspecial!='NENHUMA'" xs6>
                      <v-text-field
                        :disabled="necessidadeEspecial === null || necessidadeEspecial === '' || necessidadeEspecial === undefined"
                        label="Observações sobre a necessidade"
                        v-model="observacaoNecessidade"
                        data-vv-name="observacaoNecessidade"
                      ></v-text-field>
                    </v-flex>
                  </v-layout>
                  <v-spacer></v-spacer>
                  <v-layout row></v-layout>
                  <v-btn @click="stepperSide = 2" color="primary">Próximo</v-btn>
                  <v-btn to="/candidato/exames-abertos">Cancelar</v-btn>
                </v-stepper-content>
                <v-stepper-step :complete="stepperSide > 2" step="2">Dados da inscrição</v-stepper-step>
                <v-stepper-content step="2">
                  <v-layout row>
                    <v-flex xs6>
                      <v-text-field
                        :error-messages="errors.collect('instituicao')"
                        data-vv-name="instituicao"
                        label="Instituição*"
                        v-model="instituicao"
                        v-validate="'required'"
                      ></v-text-field>
                    </v-flex>
                    <v-flex xs6>
                      <v-text-field
                        :error-messages="errors.collect('nomeProgramaPosGrad')"
                        data-vv-name="nomeProgramaPosGrad"
                        label="Nome do programa de pós-graduação*"
                        v-model="nomeProgramaPosGrad"
                        v-validate="'required'"
                      ></v-text-field>
                    </v-flex>
                  </v-layout>
                  <v-layout row>
                    <v-flex xs6>
                      <v-text-field
                        :error-messages="errors.collect('areaConcentracao')"
                        data-vv-name="areaConcentracao"
                        label="Área de concentração*"
                        v-model="areaConcentracao"
                        v-validate="'required'"
                      ></v-text-field>
                    </v-flex>
                    <v-flex xs6>
                      <v-text-field
                        :error-messages="errors.collect('linhaPesquisa')"
                        data-vv-name="linhaPesquisa"
                        label="Linha de pesquisa*"
                        v-model="linhaPesquisa"
                        v-validate="'required'"
                      ></v-text-field>
                    </v-flex>
                  </v-layout>

                  <v-layout row>
                    <v-flex xs3>
                      <v-select
                        :items="formacoes"
                        item-text="label"
                        item-value="value"
                        label="Nível de Formação*"
                        v-model="formacao"
                        data-vv-name="formacao"
                        v-validate="'required'"
                      ></v-select>
                    </v-flex>
                    <v-flex xs3>
                      <v-select
                        :items="prioridades"
                        item-text="label"
                        item-value="value"
                        label="Nível de Prioridade*"
                        v-model="prioridade"
                        data-vv-name="prioridade"
                        v-validate="'required'"
                      ></v-select>
                    </v-flex>

                    <v-flex v-if="prioridade !== null">
                      <v-card class="elevation-0">
                        <v-card-text>
                          <div class="caption">
                            <v-icon>info</v-icon>
                          </div>
                        </v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs6>
                      <v-card class="elevation-0">
                        <v-card-text>
                          <div class="caption">
                            <p class="text-center ma-0">{{ prioridades[prioridade].descricao }}</p>
                          </div>
                        </v-card-text>
                      </v-card>
                    </v-flex>
                  </v-layout>

                  <v-btn @click="submit" color="primary" :disabled="errors.any()">Concluir</v-btn>
                  <v-btn @click="stepperSide = 1">Voltar</v-btn>
                </v-stepper-content>
              </v-form>
            </v-stepper>
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
import VeeValidate from "vee-validate";
import VWidget from "../../../components/core/VWidget";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import Telefone from "../../../components/inputs/Telefone";
import store from "../../../store";

Vue.use(VeeValidate);

export default {
  $_veeValidate: {
    validator: "new"
  },
  components: {
    Telefone,
    VWidget,
    AppBreadcrumbs
  },

  data() {
    return {
      nome: null,
      email: null,
      telefone: null,
      necessidadeEspecial: null,
      observacaoNecessidade: null,

      nomeProgramaPosGrad: null,
      areaConcentracao: null,
      linhaPesquisa: null,
      instituicao: "UFC",
      prioridade: 0,
      formacao: null,
      dialog: false,

      stepperSide: 1,
      valid: true,
      idExame: null,
      tituloExame: null,

      dictionary: {
        custom: {
          nome: {
            required: () => "O nome é obrigatório!"
          },
          email: {
            required: () => "O e-mail é obrigatório!",
            email: () => "O email está incorreto!"
          },

          telefone: {
            required: () => "O telefone é obrigatório!",
            min: () => "Telefone incompleto"
          },
          nomeProgramaPosGrad: {
            required: () => "O nome do programa de pós graduação é obrigatório!"
          },
          areaConcentracao: {
            required: () => "A área de concentração é obrigatória!"
          },
          linhaPesquisa: {
            required: () => "A linha de pesquisa é obrigatória!"
          },
          instituicao: {
            required: () => "A instituição é obrigatória!"
          },
          formacao: {
            required: () => "A formação é obrigatória!"
          },
          prioridade: {
            required: () => "A prioridade é obrigatória!"
          }
        }
      },

      breadcrumbItems: [
        {
          position: 1,
          text: "Seleções abertas",
          disabled: false,
          href: "/selecoes-abertas"
        },
        { position: 3, text: "Inscrição", disabled: true }
      ],

      cor: "",
      snackbar: false,
      mensagem: ""
    };
  },

  mounted() {
    this.$validator.localize("pt", this.dictionary);
    this.idExame = this.$route.params.id;
    this.tituloExame = this.$route.params.titulo;

    this.breadcrumbItems.push({
      position: 2,
      text: this.tituloExame != undefined ? this.tituloExame : "Exame",
      disabled: false,
      href: "/inscricao-exame-candidato/" + this.idExame
    });

    axios.get("usuario/authenticated").then(res => {
      this.nome = res.data.nome;
      this.email = res.data.email;
      this.cpf = res.data.cpf;
      this.telefone = res.data.telefone;
      this.necessidadeEspecial = res.data.necessidadeEspecial;
      this.observacaoNecessidade = res.data.observacaoNecessidade;
    });
  },

  computed: {
    necessidades() {
      return store.getters["enums/necessidades"];
    },
    prioridades() {
      return store.getters["enums/prioridades"];
    },
    formacoes() {
      return store.getters["enums/formacoes"];
    }
  },

  methods: {
    submit() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .post("/inscricoes", {
              nome: this.nome,
              email: this.email,
              telefone: this.telefone,
              necessidadeEspecial: this.necessidadeEspecial,
              observacaoNecessidade: this.observacaoNecessidade,
              programaPos: this.nomeProgramaPosGrad,
              areaConcentracao: this.areaConcentracao,
              linhaPesquisa: this.linhaPesquisa,
              instituicao: this.instituicao,
              idExame: this.idExame,
              formacao: this.formacao,
              prioridade: this.prioridade
            })
            .then(response => {
              this.$router.push("/inscricao-candidato/" + response.data);
            })
            .catch(error => {
              this.cor = "error";
              this.snackbar = true;
              this.mensagem = error.response.data;
            });
        } else {
          this.cor = "error";
          this.snackbar = true;
          this.mensagem = "O formulário contém erros!";
        }
      });
    }
  }
};
</script>

