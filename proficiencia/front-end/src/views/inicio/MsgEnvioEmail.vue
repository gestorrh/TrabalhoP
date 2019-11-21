<template>
    <v-app id="msgEmail">
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
                            <v-card-text class="organiza">
                                <div class="layout column align-center">
                                    <h1 class="msgRecupEmail">Pedido de alteração de senha</h1>
                                    <span>{{ mensagemEmail + email}}</span>
                                    <span>{{ mensagemEmail2 }}</span>
                                    <p>Não recebeu o email ? <a @click="reenviarEmail">Clique aqui para reenviar</a></p>
                                </div>
                            </v-card-text>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import Axios from 'axios';

    export default {
        props: ['email'],
        data() {
            return {
                mensagemEmail: 'Foi enviado um e-mail para: ',
                mensagemEmail2: 'Confira sua caixa de entrada para redefinir a senha'
            };
        },

        methods: {
            reenviarEmail() {
                Axios.get("public/usuario/recuperacao?email=" + this.email)
            }
        }
    };
</script>
<style lang="css" scoped>
    #logo {
        background: #1c6eb7;
    }

    @media all and (max-width: 319px) {
        #msgEmail {
            background: #efefef;
            background-size: 160px 102px;
        }
    }

    @media all and (max-width: 639px) and (min-width: 320px) {
        #msgEmail {
            background: #efefef;
            background-size: 320px 205px;
        }
    }

    @media all and (max-width: 799px) and (min-width: 640px) {
        #msgEmail {
            background: #efefef url("/static/back-640.png");
            background-size: 640px 410px;
        }
    }

    @media all and (max-width: 1023px) and (min-width: 768px) {
        #msgEmail {
            background: #efefef url("/static/back-800.png");
            background-size: 800px 512px;
        }
    }

    @media all and (max-width: 1279px) and (min-width: 1024px) {
        #msgEmail {
            background: #efefef url("/static/back-1024.png");
            background-size: 1024px 656px;
        }
    }

    @media all and (max-width: 1561px) and (min-width: 1280px) {
        #msgEmail {
            background: #efefef url("/static/back-1280.png");
            background-size: 1280px 819px;
        }
    }

    @media all and (min-width: 1562px) {
        #msgEmail {
            background: #efefef url("/static/back.png");
            background-size: 1562px 1000px;
        }
    }

    #msgEmail {
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
    }

    span {
        font-size: 15px;
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

    .organiza {
        text-align: center;
    }
</style>
