<template>
    <v-app id="telaInicial">
        <div class="organizarLogo">
            <router-link to="/">
                <v-img alt="Logo Sistema de Proficiência" id="logo" src="/static/logo-03.png"></v-img>
            </router-link>
        </div>
        <v-content>
            <v-container fill-height fluid>
                <v-layout align-center justify-center>
                    <v-flex md4 sm8 xs12>
                        <v-card>
                            <v-tabs v-model="tab" grow centered>
                                <v-tab key="0">Login</v-tab>
                                <v-tab key="1">Cadastro</v-tab>

                                <v-tab-item key="0">
                                    <app-login></app-login>
                                </v-tab-item>
                                <v-tab-item key="1">
                                    <app-cadastro @notificar="notificar" @to-login="toLogin"></app-cadastro>
                                </v-tab-item>
                            </v-tabs>
                        </v-card>
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
        </v-content>
    </v-app>
</template>


<script>
    import Login from "../../components/inicio/AppLogin";
    import CadastrarUsuario from "../../components/inicio/AppCadastrarUsuario"

    export default {
        components: {
            "app-login": Login,
            "app-cadastro": CadastrarUsuario
        },
        data() {
            return {
                tab: 0,

                snackbar: false,
                mensagem: "",
                cor: "",
            };
        },
        methods: {
            toLogin() {
                this.tab = 0;
            },
            notificar(notificacao) {
                this.mensagem = notificacao.mensagem;
                this.cor = notificacao.cor;
                this.snackbar = true;
            }
        }
    };
</script>
<style lang="css" scoped>

    @media all and (max-width: 319px) {
        #telaInicial {
            background: #efefef;
            background-size: 160px 102px;
        }
    }

    @media all and (max-width: 639px) and (min-width: 320px) {
        #telaInicial {
            background: #efefef;
            background-size: 320px 205px;
        }
    }

    @media all and (max-width: 799px) and (min-width: 640px) {
        #telaInicial {
            background: #efefef url("/static/back-640.png");
            background-size: 640px 410px;
        }
    }

    @media all and (max-width: 1023px) and (min-width: 768px) {
        #telaInicial {
            background: #efefef url("/static/back-800.png");
            background-size: 800px 512px;
        }
    }

    @media all and (max-width: 1279px) and (min-width: 1024px) {
        #telaInicial {
            background: #efefef url("/static/back-1024.png");
            background-size: 1024px 656px;
        }
    }

    @media all and (max-width: 1561px) and (min-width: 1280px) {
        #telaInicial {
            background: #efefef url("/static/back-1280.png");
            background-size: 1280px 795px;
        }
    }

    @media all and (min-width: 1562px) {
        #telaInicial {
            background: #efefef url("/static/back.png");
            background-size: 1562px 1000px;
        }
    }

    #telaInicial {
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
    }

    p {
        margin-top: 24px;
        margin-left: 24px;
    }

    .organizarLogo {
        width: 98px;
        height: 34px;
        margin-top: 24px;
        margin-left: 24px;
        position: fixed;
    }

</style>
