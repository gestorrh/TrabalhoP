<template>
  <v-container fluid grid-list-xl>
    <app-breadcrumbs :items="breadcrumbItems" icon="person_add" title="Cadastrar Usuário"></app-breadcrumbs>

    <v-flex xs12>
      <v-widget enableActions title="Formulário de usuário">
        <div slot="widget-content">
          <v-form>

            <v-layout row>
              <v-flex md6>
                <v-text-field
                  :error-messages="errors.collect('nome')"
                  label="Nome*"
                  oninput="this.value = this.value.toUpperCase()"
                  v-model="nome"
                  data-vv-name="nome"
                  v-validate="'required'"
                ></v-text-field>
              </v-flex>
              <v-flex md6>
                <v-text-field
                  :error-messages="errors.collect('email')"
                  label="Email*"
                  v-model="email"
                  v-validate="'required|email'"
                  data-vv-name="email"
                ></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex md6>
                <v-text-field
                  :error-messages="errors.collect('cpf')"
                  label="CPF*"
                  mask="###.###.###-##"
                  v-model="cpf"
                  v-validate="'required|cpf'"
                  data-vv-name="cpf"
                ></v-text-field>
              </v-flex>
              <v-flex md6>
                <v-select
                  :items="papeisList"
                  item-text="label"
                  item-value="value"
                  label="Papel*"
                  v-model="papel"
                  data-vv-name="papel"
                  v-validate="'required'"
                  :error-messages="errors.collect('papel')"
                ></v-select>
              </v-flex>
            </v-layout>

           

            <v-layout row>
              <v-flex md5>
                <v-text-field
                  :error-messages="errors.collect('endereco')"
                  :label="label('Endereço')"
                  v-model="endereco"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="endereco"
                ></v-text-field>
              </v-flex>

              <v-flex md3>
                <v-text-field
                  :error-messages="errors.collect('cidade')"
                  :label="label('Cidade')"
                  v-model="cidade"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="cidade"
                ></v-text-field>
              </v-flex>

              <v-flex md1>
                <v-text-field
                  :error-messages="errors.collect('uf')"
                  :label="label('UF')"
                  mask="AA"
                  v-model="uf"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="uf"
                ></v-text-field>
              </v-flex>

              <v-flex md3>
                <v-text-field
                  :error-messages="errors.collect('pais')"
                  :label="label('País')"
                  v-model="pais"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="pais"
                ></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex md3>
                <v-radio-group
                  :label="label('Sexo')"
                  row
                  v-model="sexo"
                  :error-messages="errors.collect('sexo')"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="sexo"
                >
                  <v-radio label="Masculino" value="MASCULINO"></v-radio>
                  <v-radio label="Feminino" value="FEMININO"></v-radio>
                </v-radio-group>
              </v-flex>

              <v-flex md3>
                <date-picker
                  v-model="dataNascimento"
                  :label="label('Data de Nascimento')"
                  birthday
                  :error-messages="errors.collect('dataNascimento')"
                  data-vv-name="dataNascimento"
                  v-validate="{required: papel === 'CANDIDATO'}"
                ></date-picker>
              </v-flex>

              <v-flex md3>
                <v-select
                  :items="estadoCivilList"
                  item-text="label"
                  item-value="value"
                  :label="label('Estado Civil')"
                  v-model="estadoCivil"
                  :error-messages="errors.collect('estadoCivil')"
                  v-validate="{required: papel === 'CANDIDATO'}"
                  data-vv-name="estadoCivil"
                ></v-select>
              </v-flex>

              <v-flex md3>
                <telefone
                  v-model="telefone"
                  :label="label('Telefone')"
                  :error-messages="errors.collect('telefone')"
                  data-vv-name="telefone"
                  v-validate="{required: papel === 'CANDIDATO', min: 10}"
                ></telefone>
              </v-flex>
            </v-layout>
            <v-layout row>
              <v-flex xs4>
                <v-select
                  :items="necessidadeEspecialList"
                  item-text="label"
                  item-value="value"
                  label="Necessidade Especial"
                  v-model="necessidadeEspecial"
                  data-vv-name="necessidadeEspecial"
                  clearable
                ></v-select>
              </v-flex>
              <v-flex xs8>
                <v-text-field
                  :disabled="necessidadeEspecial === null || necessidadeEspecial === '' || necessidadeEspecial === undefined"
                  label="Observações sobre a necessidade"
                  v-model="observacaoNecessidade"
                  data-vv-name="observacaoNecessidade"
                ></v-text-field>
              </v-flex>
            </v-layout>

            <v-btn :disabled="errors.any()" depressed @click="submit" color="primary">CADASTRAR</v-btn>
            <v-btn to="/coordenador/usuarios">VOLTAR</v-btn>
          </v-form>
        </div>
        <div slot="widget-actions">
          <span class="caption">* Campos Obrigatórios</span>
        </div>
      </v-widget>
    </v-flex>

    <v-snackbar right top :color="cor" v-model="snackbar">{{ mensagem }}</v-snackbar>
  </v-container>
</template>


<script>
import axios from "axios";
import Vue from "vue";
import VWidget from "../../../components/core/VWidget";
import VeeValidate, { Validator } from "vee-validate";
import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
import store from "../../../store";
import DatePicker from "../../../components/inputs/DatePicker";
import Telefone from "../../../components/inputs/Telefone";

Vue.use(VeeValidate);

Validator.extend("cpf", {
  validate: value =>
    new Promise(resolve => {
      if (
        value.length !== 11 ||
        value === "00000000000" ||
        value === "11111111111" ||
        value === "22222222222" ||
        value === "33333333333" ||
        value === "44444444444" ||
        value === "55555555555" ||
        value === "66666666666" ||
        value === "77777777777" ||
        value === "88888888888" ||
        value === "99999999999"
      ) {
        return resolve({
          valid: false
        });
      }

      var add = 0;
      var rev;

      for (var i = 0; i < 9; i++) add += parseInt(value.charAt(i)) * (10 - i);
      rev = 11 - (add % 11);
      if (rev == 10 || rev == 11) rev = 0;
      if (rev != parseInt(value.charAt(9))) {
        return resolve({
          valid: false
        });
      }

      add = 0;
      for (var j = 0; j < 10; j++) add += parseInt(value.charAt(j)) * (11 - j);
      rev = 11 - (add % 11);
      if (rev == 10 || rev == 11) rev = 0;
      if (rev != parseInt(value.charAt(10))) {
        return resolve({
          valid: false
        });
      }
      return resolve({
        valid: true
      });
    })
});

export default {
  components: {
    Telefone,
    DatePicker,
    AppBreadcrumbs,
    VWidget
  },

  data() {
    return {
      nome: null,
      email: null,
      cpf: null,
      papel: null,
      endereco: null,
      cidade: null,
      uf: null,
      pais: null,
      sexo: null,
      dataNascimento: "",
      estadoCivil: null,
      telefone: null,
      necessidadeEspecial: null,
      observacaoNecessidade: null,

      snackbar: false,
      mensagem: "",
      cor: "",

      dictionary: {
        custom: {
          nome: {
            required: () => "Campo obrigatório"
          },
          email: {
            required: () => "Campo obrigatório",
            email: () => "O email está incorreto"
          },
          cpf: {
            required: () => "Campo obrigatório",
            cpf: () => "CPF incorreto"
          },
          papel: {
            required: () => "Campo obrigatório"
          },
          endereco: {
            required: () => "Campo obrigatório"
          },
          pais: {
            required: () => "Campo obrigatório"
          },
          uf: {
            required: () => "Campo obrigatório"
          },
          cidade: {
            required: () => "Campo obrigatório"
          },

          telefone: {
            required: () => "Campo obrigatório",
            min: () => "Telefone incompleto"
          },
          sexo: {
            required: () => "Campo obrigatório"
          },
          dataNascimento: {
            required: () => "Campo obrigatório"
          },
          estadoCivil: {
            required: () => "Campo obrigatório"
          }
        }
      },

      breadcrumbItems: [
        { position: 1, text: "Usuários", disabled: false, href: "/coordenador/usuarios" },
        { position: 2, text: "Cadastrar Usuário", disabled: true }
      ]
    };
  },

  computed: {
    papeisList() {
      return store.getters["enums/papeis"];
    },
    estadoCivilList() {
      return store.getters["enums/estados"];
    },
    necessidadeEspecialList() {
      return store.getters["enums/necessidades"];
    },
    papeis() {
      let aux = [];
      this.papel && aux.push(this.papel);
      return aux;
    }
  },

  methods: {
    label(nome) {
      return this.papel === "CANDIDATO" ? nome + "*" : nome;
    },
    submit() {
      this.$validator.validateAll().then(result => {
        if (result) {
          axios
            .post("/usuario", {
              nome: this.nome,
              email: this.email,
              cpf: this.cpf,
              papeis: this.papeis,
              endereco: this.endereco,
              cidade: this.cidade,
              uf: this.uf,
              pais: this.pais,
              sexo: this.sexo,
              dataNascimento: this.dataNascimento,
              estadoCivil: this.estadoCivil,
              telefone: this.telefone,
              necessidadeEspecial: this.necessidadeEspecial,
              observacaoNecessidade: this.observacaoNecessidade
            })
            .then(() => {
              this.$router.push({
                name: "CoordenadorUsuarios",
                params: {
                  cor: "success",
                  snackbar: true,
                  mensagem: "Cadastro realizado"
                }
              });
            })
            .catch(error => {
              this.mensagem = error.response.data;
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
  },

  mounted() {
    this.$validator.localize("pt", this.dictionary);
  }
};
</script>
