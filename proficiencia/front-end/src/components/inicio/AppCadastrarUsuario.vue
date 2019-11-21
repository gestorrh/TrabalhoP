<template>
    <v-form>
        <v-stepper v-model="stepper">
            <v-stepper-header>
                <v-stepper-step :complete="stepper > 1" step="1">Dados de acesso</v-stepper-step>

                <v-stepper-step :complete="stepper > 2" step="2">Informações Pessoais</v-stepper-step>
            </v-stepper-header>

            <v-stepper-items>
                <v-stepper-content step="1">
                    <v-text-field
                            :error-messages="errors.collect('nome')"
                            label="Nome*"
                            oninput="this.value = this.value.toUpperCase()"
                            v-model="nome"
                            data-vv-name="nome"
                            v-validate="'required'"
                    ></v-text-field>

                    <v-text-field
                            :error-messages="errors.collect('email')"
                            label="Email*"
                            v-model="email"
                            v-validate="'required|email'"
                            data-vv-name="email"
                    ></v-text-field>

                    <v-text-field
                            :error-messages="errors.collect('cpf')"
                            label="CPF*"
                            mask="###.###.###-##"
                            v-model="cpf"
                            v-validate="'required|cpf'"
                            data-vv-name="cpf"
                    ></v-text-field>

                    <v-text-field
                            :counter="0"
                            :error-messages="errors.collect('senha')"
                            label="Senha*"
                            data-vv-name="senha"
                            type="password"
                            v-model="senha"
                            v-validate="{required: true, min: 6}"
                    ></v-text-field>

                    <v-text-field
                            :counter="0"
                            :error-messages="errors.collect('confirmacaoSenha')"
                            label="Confirmar senha*"
                            data-vv-name="confirmacaoSenha"
                            type="password"
                            v-model="confirmacaoSenha"
                            v-validate="{required: true, confirmed:senha}"
                    ></v-text-field>


                    <v-btn color="primary" depressed @click="stepper = 2">
                        Continuar
                    </v-btn>
                    <v-btn @click="toLogin" depressed>
                        Cancelar
                    </v-btn>
                </v-stepper-content>

                <v-stepper-content step="2">
                    <v-text-field
                            :error-messages="errors.collect('endereco')"
                            label="Endereço*"
                            v-model="endereco"
                            v-validate="'required'"
                            data-vv-name="endereco"
                    ></v-text-field>

                    <v-text-field
                            :error-messages="errors.collect('cidade')"
                            label="Cidade*"
                            v-model="cidade"
                            v-validate="'required'"
                            data-vv-name="cidade"
                    ></v-text-field>

                    <v-text-field
                            :error-messages="errors.collect('uf')"
                            label="UF*"
                            mask="AA"
                            v-model="uf"
                            v-validate="'required'"
                            data-vv-name="uf"
                    ></v-text-field>

                    <v-text-field
                            :error-messages="errors.collect('pais')"
                            label="País*"
                            v-model="pais"
                            v-validate="'required'"
                            data-vv-name="pais"
                    ></v-text-field>

                    <v-radio-group label="Sexo*" row v-model="sexo"
                                   :error-messages="errors.collect('sexo')" v-validate="'required'" data-vv-name="sexo">
                        <v-radio label="Masculino" value="MASCULINO"></v-radio>
                        <v-radio label="Feminino" value="FEMININO"></v-radio>
                    </v-radio-group>

                    <date-picker v-model="dataNascimento"
                                 label="Data de Nascimento*"
                                 birthday
                                 :error-messages="errors.collect('dataNascimento')"
                                 data-vv-name="dataNascimento"
                                 v-validate="'required'">
                    </date-picker>

                    <v-select
                            :items="estadoCivilList"
                            item-text="label"
                            item-value="value"
                            label="Estado Civil*"
                            v-model="estadoCivil"
                            :error-messages="errors.collect('estadoCivil')"
                            v-validate="'required'"
                            data-vv-name="estadoCivil"
                    ></v-select>

                    <telefone v-model="telefone"
                              ref="telefone"
                              v-bind:value.sync="telefone"
                              label="Telefone*"
                              :error-messages="errors.collect('telefone')"
                              data-vv-name="telefone"
                              v-validate="{required: true, min: 10}">
                        >
                    </telefone>

                    <v-select
                            :items="necessidadeEspecialList"
                            item-text="label"
                            item-value="value"
                            label="Necessidade Especial"
                            v-model="necessidadeEspecial"
                            data-vv-name="necessidadeEspecial"
                            clearable
                    ></v-select>

                    <v-text-field
                            :disabled="necessidadeEspecial === null || necessidadeEspecial === '' || necessidadeEspecial === undefined"
                            label="Observações sobre a necessidade"
                            v-model="observacaoNecessidade"
                            data-vv-name="observacaoNecessidade"
                    ></v-text-field>

                    <v-btn :disabled="errors.any()" color="primary" @click="submit" depressed>Cadastrar</v-btn>
                    <v-btn @click="stepper = 1" depressed>Voltar</v-btn>
                </v-stepper-content>
            </v-stepper-items>
        </v-stepper>
    </v-form>
</template>

<script>
    import axios from "axios";
    import Vue from "vue";
    import VeeValidate, {Validator} from "vee-validate";
    import {Promise} from "q";
    import store from "../../store";
    import Telefone from "../inputs/Telefone";
    import DatePicker from "../inputs/DatePicker";

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
                for (var j = 0; j < 10; j++)
                    add += parseInt(value.charAt(j)) * (11 - j);
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
        name: "app-cadastro",
        components: {DatePicker, Telefone},
        data() {
            return {
                nome: null,
                email: null,
                cpf: null,
                senha: null,
                confirmacaoSenha: null,
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

                stepper: 1,
                valid: true,

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
                        senha: {
                          required: () => "Campo obrigatório",
                          min: () => "A senha deve ter ao menos 6 dígitos"
                        },
                        confirmacaoSenha: {
                          required: () => "Confirme a senha",
                          confirmed: () => "As senha não correspondem"
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
                        },
                    }
                }
            };
        },

        computed: {
            papeisList() {
                return store.getters["enums/papeis"]
            },
            estadoCivilList() {
                return store.getters["enums/estados"]
            },
            necessidadeEspecialList() {
                return store.getters["enums/necessidades"]
            }
        },

        methods: {
            toLogin() {
                this.$emit('to-login');
            },
            resetarFormulario() {
                this.nome= null;
                this.email= null;
                this.cpf= null;
                this.senha= null;
                this.confirmacaoSenha= null;
                this.endereco= null;
                this.cidade= null;
                this.uf= null;
                this.pais= null;
                this.sexo= null;
                this.dataNascimento= "";
                this.estadoCivil= null;
                this.telefone= null;
                this.necessidadeEspecial= null;
                this.observacaoNecessidade= null;
                this.$validator.reset().then(() => {
                    this.stepper = 1;
                });
            },
            submit() {
                this.$validator.validateAll().then((result) => {

                    if (result) {
                        axios.post("/public/usuario", {
                            nome: this.nome,
                            email: this.email,
                            cpf: this.cpf,
                            senha: this.senha,
                            papeis: ["CANDIDATO"],
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
                        }).then(() => {
                            this.toLogin();
                            this.$emit("notificar", {cor: "success", mensagem: "Cadastro realizado com sucesso"});
                            this.resetarFormulario();
                        }).catch((error) => {
                            this.$emit("notificar", {cor: "error", mensagem: error.response.data});
                        });
                    } else {
                        this.$emit("notificar", {cor: "error", mensagem: "O formulário contém erros!"});
                    }
                });
            }
        },

        mounted() {
            this.$validator.localize("pt", this.dictionary);
        }
    };
</script>


