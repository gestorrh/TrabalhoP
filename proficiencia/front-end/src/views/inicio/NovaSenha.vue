<template>
    <v-app id="novaSenha">
        <div class="organizarLogo">
            <v-img alt="Logo Sistema de Proficiência" id="logo" src="/static/logo-01.png"></v-img>
        </div>
        <v-content>
            <v-container fill-height fluid>
                <v-layout align-center justify-center>
                    <v-flex md4 sm8 xs12>
                        <v-card class="elevation-1 pa-3">
                            <v-form @submit.prevent='novaSenha'>
                                <v-card-text>
                                    <div class="layout column align-center">
                                        <h1>Redefinição de senha</h1>
                                    </div>

                                    <v-text-field
                                            :error-messages="errors.collect('_senha')"
                                            append-icon="lock"
                                            label="Informe a nova senha"
                                            name="_senha"
                                            type="password"
                                            v-model="token.usuario.senha"
                                            v-validate="{ required: true, min: 6}"
                                    ></v-text-field>

                                    <v-text-field
                                            :error-messages="errors.collect('_confirmarSenha')"
                                            append-icon="lock"
                                            label="Confirme a senha"
                                            name="_confirmarSenha"
                                            type="password"
                                            v-model="conf_senha"
                                            v-validate="{ required: true, confirmed:token.usuario.senha }"
                                    ></v-text-field>

                                    <v-card-actions class="layout column align-center">
                                        <v-btn
                                                :disabled="(token.usuario.senha != conf_senha) || (token.usuario.senha.length < 6)"
                                                :loading="loading"
                                                @click="novaSenha"
                                                color="primary"
                                                large
                                                type="button"
                                        >Salvar
                                        </v-btn>
                                    </v-card-actions>
                                </v-card-text>
                            </v-form>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import Axios from "axios";

    export default {
        data() {
            return {
                token: {
                    hash: '',
                    validate: '',
                    usuario: {
                        senha: ''
                    }
                },


                loading: false,
                senha: "",
                conf_senha: "",

                dictionary: {
                    custom: {
                        _senha: {
                            required: () => "Campo obrigatório",
                            min: () => "Por favor, adicione uma senha no minimo 6 digitos",
                        },
                        _confirmarSenha: {
                            required: () => "Campo obrigatório",
                            confirmed: () => "Verifique se digitou corretamente"
                        }
                    }
                }
            };
        },

        methods: {
            novaSenha() {
                this.token.hash = this.$route.query.hash;
                Axios.post('public/usuario/nova-senha', this.token)
                    .then(() => {
                        this.$router.push({path: '/msgAlteracaoSenha'})
                    })
            }
        },

        mounted() {
            this.$validator.localize("en", this.dictionary);
        }
    };
</script>
<style lang="css" scoped>
    #logo {
        background: #1c6eb7;
    }

    @media all and (max-width: 319px) {
        #novaSenha {
            background: #efefef;
            background-size: 160px 102px;
        }
    }

    @media all and (max-width: 639px) and (min-width: 320px) {
        #novaSenha {
            background: #efefef;
            background-size: 320px 205px;
        }
    }

    @media all and (max-width: 799px) and (min-width: 640px) {
        #novaSenha {
            background: #efefef url("/static/back-640.png");
            background-size: 640px 410px;
        }
    }

    @media all and (max-width: 1023px) and (min-width: 768px) {
        #novaSenha {
            background: #efefef url("/static/back-800.png");
            background-size: 800px 512px;
        }
    }

    @media all and (max-width: 1279px) and (min-width: 1024px) {
        #novaSenha {
            background: #efefef url("/static/back-1024.png");
            background-size: 1024px 656px;
        }
    }

    @media all and (max-width: 1561px) and (min-width: 1280px) {
        #novaSenha {
            background: #efefef url("/static/back-1280.png");
            background-size: 1280px 819px;
        }
    }

    @media all and (min-width: 1562px) {
        #novaSenha {
            background: #efefef url("/static/back.png");
            background-size: 1562px 1000px;
        }
    }

    #novaSenha {
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
    }


    a {
        text-decoration: none;
    }

    .organizarLogo {
        width: 100px;
        height: 50px;
        margin-top: 24px;
        margin-left: 24px;
        position: sticky;
        top: 0;
    }

    h1 {
        margin-bottom: 30px;
        font-size: 25px;
    }
</style>
