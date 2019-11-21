<template>
    <v-container fluid grid-list-xl>
        <app-breadcrumbs :items="breadcrumbItems" icon="face" title="Dados Pessoais"></app-breadcrumbs>

        <v-layout row>
            <v-flex xs12>
                <v-widget enableActions title="Dados Pessoais">
            <div slot="widget-content">
                <v-form data-vv-scope="form-usuario">
                    <v-layout row>
                        <v-flex md6>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.nome')"
                                    label="Nome*"
                                    oninput="this.value = this.value.toUpperCase()"
                                    v-model="nome"
                                    data-vv-name="nome"
                                    v-validate="'required'"
                            ></v-text-field>
                        </v-flex>
                        <v-flex md6>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.email')"
                                    label="Email*"
                                    v-model="email"
                                    v-validate="'required|email'"
                                    data-vv-name="email"
                            ></v-text-field>
                        </v-flex>
                    </v-layout>
                    <v-layout row>
                        <v-flex md5>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.endereco')"
                                    :label="label('Endereço')"
                                    v-model="endereco"
                                    v-validate="{required: isCandidato}"
                                    data-vv-name="endereco"
                            ></v-text-field>
                        </v-flex>

                        <v-flex md3>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.cidade')"
                                    :label="label('Cidade')"
                                    v-model="cidade"
                                    v-validate="{required: isCandidato}"
                                    data-vv-name="cidade"
                            ></v-text-field>
                        </v-flex>

                        <v-flex md1>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.uf')"
                                    :label="label('UF')"
                                    mask="AA"
                                    v-model="uf"
                                    v-validate="{required: isCandidato}"
                                    data-vv-name="uf"
                            ></v-text-field>
                        </v-flex>

                        <v-flex md3>
                            <v-text-field
                                    :error-messages="errors.collect('form-usuario.pais')"
                                    :label="label('País')"
                                    v-model="pais"
                                    v-validate="{required: isCandidato}"
                                    data-vv-name="pais"
                            ></v-text-field>
                        </v-flex>
                    </v-layout>
                    <v-layout row>
                        <v-flex md3>
                            <v-radio-group :label="label('Sexo')" row v-model="sexo" :error-messages="errors.collect('form-usuario.sexo')" v-validate="{required: isCandidato}" data-vv-name="sexo">
                                <v-radio label="Masculino" value="MASCULINO"></v-radio>
                                <v-radio label="Feminino" value="FEMININO"></v-radio>
                            </v-radio-group>
                        </v-flex>

                        <v-flex md3>
                            <date-picker v-model="dataNascimento"
                                         :label="label('Data de Nascimento')"
                                         birthday
                                         :error-messages="errors.collect('form-usuario.dataNascimento')"
                                         data-vv-name="dataNascimento"
                                         v-validate="{required: isCandidato}">
                            </date-picker>
                        </v-flex>

                        <v-flex md3>
                            <v-select
                                    :items="estadoCivilList"
                                    item-text="label"
                                    item-value="value"
                                    :label="label('Estado Civil')"
                                    v-model="estadoCivil"
                                    :error-messages="errors.collect('form-usuario.estadoCivil')"
                                    v-validate="{required: isCandidato}"
                                    data-vv-name="estadoCivil"
                            ></v-select>

                        </v-flex>

                        <v-flex md3>
                            <telefone v-model="telefone"
                                      :label="label('Telefone')"
                                      :error-messages="errors.collect('form-usuario.telefone')"
                                      data-vv-name="telefone"
                                      v-validate="{required: isCandidato, min: 10}">
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
                </v-form>
            </div>
            <div slot="widget-actions">
                <v-layout row>
                    <v-flex xs12>
                        <v-btn :disabled="errors.any('form-usuario')" @click="submitDados" color="primary" depressed>Alterar</v-btn>
                    </v-flex>
                </v-layout>
            </div>
        </v-widget>
            </v-flex>
        </v-layout>

        <v-layout row>
            <v-flex xs12>
                <v-widget enableActions title="Alterar senha">
            <div slot="widget-content">
                <v-form data-vv-scope="form-senha">
                    <v-container grid-list-xl>
                        <v-layout justify-space-around wrap>
                            <v-flex md4 xs12>

                                <v-text-field
                                        data-vv-name="senhaAtual"
                                        :error-messages="errors.collect('form-senha.senhaAtual')"
                                        v-validate="'required'"
                                        label="Senha Atual"
                                        placeholder="Digite a senha atual"
                                        type="password"
                                        v-model="senhaAtual"
                                ></v-text-field>
                            </v-flex>
                            <v-flex md4 xs12>

                                <v-text-field
                                        :counter="0"
                                        :error-messages="errors.collect('form-senha.novaSenha')"
                                        label="Nova Senha"
                                        data-vv-name="novaSenha"
                                        placeholder="Digite a senha nova"
                                        type="password"
                                        v-model="novaSenha"
                                        v-validate="{required: true, min: 6}"
                                ></v-text-field>
                            </v-flex>
                            <v-flex md4 xs12>
                                <v-text-field
                                        :counter="0"
                                        :error-messages="errors.collect('form-senha.confirmacaoNovaSenha')"
                                        label="Confirmar senha"
                                        data-vv-name="confirmacaoNovaSenha"
                                        placeholder="Digite a senha nova novamente"
                                        type="password"
                                        v-model="confirmacaoNovaSenha"
                                        v-validate="{required: true, confirmed:novaSenha}"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-form>
            </div>
            <div slot="widget-actions">
                <v-layout row>
                    <v-flex xs12>
                        <v-btn :disabled="errors.any('form-senha')" @click="submitSenha" color="primary" depressed>Alterar</v-btn>
                    </v-flex>
                </v-layout>
            </div>
        </v-widget>
            </v-flex>
        </v-layout>

        <v-snackbar
                right
                top
                :color="cor"
                v-model="snackbar"
        >{{ mensagem }}
        </v-snackbar>
    </v-container>
</template>

<script>
    import axios from 'axios';
    import VWidget from '../../components/core/VWidget';
    import AppBreadcrumbs from '../../components/core/AppBreadcrumbs';
    import Vue from "vue";
    import VeeValidate, {Validator} from "vee-validate";
    import store from "../../store";
    import DatePicker from "../../components/inputs/DatePicker";
    import Telefone from "../../components/inputs/Telefone";

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
        components: {
            VWidget,
            AppBreadcrumbs,
            Telefone,
            DatePicker
        },
        data() {
            return {
                id: null,
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

                senhaAtual: null,
                novaSenha: null,
                confirmacaoNovaSenha: null,

                breadcrumbItems: [
                    {position: 1, text: 'Dados Pessoais', disabled: true, href: '/dados-pessoais'}
                ],

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
                        senhaAtual: {
                            required: () => "Campo obrigatório"
                        },
                        novaSenha: {
                            required: () => "Campo obrigatório",
                            min: () => "A senha deve ter ao menos 6 dígitos"
                        },
                        confirmacaoNovaSenha: {
                            required: () => "Confirme a senha",
                            confirmed: () => "As senha não correspondem"
                        }
                    }
                }
            };
        },
        computed: {
            isCandidato() {
                return store.getters["auth/isCandidato"]
            },
            estadoCivilList() {
                return store.getters["enums/estados"]
            },
            necessidadeEspecialList() {
                return store.getters["enums/necessidades"]
            }
        },
        mounted() {
            this.$validator.localize('pt', this.dictionary);
            this.initialize();
        },
        methods: {
            label(nome) {
                return this.isCandidato ? nome + "*" : nome;
            },

            initialize() {
                axios
                    .get('/usuario/authenticated')
                    .then(res => {
                        this.id = res.data.id;
                        this.nome = res.data.nome;
                        this.email = res.data.email;
                        this.endereco = res.data.endereco;
                        this.cidade = res.data.cidade;
                        this.uf = res.data.uf;
                        this.pais = res.data.pais;
                        this.sexo = res.data.sexo;
                        this.dataNascimento = res.data.dataNascimento;
                        this.estadoCivil = res.data.estadoCivil;
                        this.telefone = res.data.telefone;
                        this.necessidadeEspecial = res.data.necessidadeEspecial;
                        this.observacaoNecessidade = res.data.observacaoNecessidade;
                    });
            },

            submitDados() {
                this.$validator.validateAll("form-usuario").then(resultado => {
                    if (resultado) {
                        axios.put('/usuario', {
                            id: this.id,
                            nome: this.nome,
                            email: this.email,
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
                            this.mensagem = "Dados atualizados com sucesso!";
                            this.cor = "success";
                            this.snackbar = true;
                        }).catch((error) => {
                            this.mensagem = error.response.data;
                            this.cor = "error";
                            this.snackbar = true;
                        });
                    } else {
                        this.mensagem = "O formulário contém erros!";
                        this.cor = "error";
                        this.snackbar = true;
                    }
                })

            },

            submitSenha() {
                this.$validator.validateAll("form-senha").then(resultado => {
                    if (resultado) {
                        axios.put('/usuario/alterarSenha', {
                            senhaAtual: this.senhaAtual,
                            novaSenha: this.novaSenha
                        }).then(() => {
                            this.mensagem = "Senha atualizada com sucesso!";
                            this.cor = "success";
                            this.snackbar = true;

                            this.senhaAtual = null;
                            this.novaSenha = null;
                            this.confirmacaoNovaSenha = null;

                            this.$validator.reset("form-senha")
                        }).catch((error) => {
                            this.mensagem = error.response.data;
                            this.cor = "error";
                            this.snackbar = true;
                        });
                    } else {
                        this.mensagem = "O formulário contém erros!";
                        this.cor = "error";
                        this.snackbar = true;
                    }
                })
            }
        }
    }
</script>