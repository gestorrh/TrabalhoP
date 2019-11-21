<template>
    <v-app id="recuperarSenha">
        <div class="organizarLogo">
            <router-link to="/">
                <v-img alt="Logo Sistema de Proficiência" id="logo" src="/static/logo-01.png"></v-img>
            </router-link>
        </div>
        <v-content>
            <v-container fill-height fluid>
                <v-layout align-center justify-center>
                    <v-flex md4 sm8 xs12>
                        <v-card class="elevation-1 pa-3">
                            <v-form @submit.prevent="recupSenha">
                                <v-card-text>
                                    <div class="layout column align-center">
                                        <span class="body-1 red--text">{{ error }}</span>
                                        <h1 class="recupSenha">Recuperação de senha</h1>
                                    </div>
                                    <v-text-field
                                            :error-messages="errors.collect('recupSenha.email')"
                                            @keydown="removeError"
                                            append-icon="person"
                                            data-vv-scope="recupSenha"
                                            label="Informe seu email"
                                            name="email"
                                            type="text"
                                            v-model="email"
                                            v-validate="'required|email'"
                                    ></v-text-field>
                                    <v-card-actions class="layout column align-center">
                                        <v-btn
                                                :disabled="errors.any()"
                                                :loading="loading"
                                                @click="recupSenha"
                                                color="primary"
                                                large
                                                type="button"
                                        >Enviar
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
                loading: false,
                email: "",

                alerta: {
                    alert: true
                },
                error: '',

                dictionary: {
                    custom: {
                        email: {
                            required: () => "Campo obrigatório",
                            email: () => "O email está incorreto"
                        },
                    }
                }
            };
        },

        methods: {
            recupSenha() {
                this.$validator.validate("recupSenha").then(resultado => {
                    if (resultado) {
                        this.guiaDeTelas = 2;
                    }
                });
                Axios.get("/public/usuario/recuperacao?email=" + this.email)
                    .then(res => {
                        if (res.data == true) {
                            this.$router.push({name: 'MsgEnvioEmail', params: {email: this.email}});
                        } else {
                            this.error = "Email não cadastrado"
                        }
                    });
            },
            removeError() {
                this.error = '';
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
        #recuperarSenha {
            background: #efefef;
            background-size: 160px 102px;
        }
    }

    @media all and (max-width: 639px) and (min-width: 320px) {
        #recuperarSenha {
            background: #efefef;
            background-size: 320px 205px;
        }
    }

    @media all and (max-width: 799px) and (min-width: 640px) {
        #recuperarSenha {
            background: #efefef url("/static/back-640.png");
            background-size: 640px 410px;
        }
    }

    @media all and (max-width: 1023px) and (min-width: 768px) {
        #recuperarSenha {
            background: #efefef url("/static/back-800.png");
            background-size: 800px 512px;
        }
    }

    @media all and (max-width: 1279px) and (min-width: 1024px) {
        #recuperarSenha {
            background: #efefef url("/static/back-1024.png");
            background-size: 1024px 656px;
        }
    }

    @media all and (max-width: 1561px) and (min-width: 1280px) {
        #recuperarSenha {
            background: #efefef url("/static/back-1280.png");
            background-size: 1280px 819px;
        }
    }

    @media all and (min-width: 1562px) {
        #recuperarSenha {
            background: #efefef url("/static/back.png");
            background-size: 1562px 1000px;
        }
    }

    #recuperarSenha {
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
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
