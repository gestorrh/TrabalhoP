<template>
    <v-app id="msgSenha">
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
                            <v-card-text>
                                <div class="layout column align-center">
                                    <h1>Senha alterada com sucesso</h1>
                                </div>
                            </v-card-text>
                            <v-card-actions class="layout column align-center">
                                <v-btn
                                        @click="login"
                                        color="primary"
                                        large
                                        to="/"
                                        type="button"
                                >Login
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>

    export default {
        data() {
            return {};
        },
    };
</script>
<style lang="css" scoped>
    #logo {
        background: #1c6eb7;
    }

    @media all and (max-width: 319px) {
        #msgSenha {
            background: #efefef;
            background-size: 160px 102px;
        }
    }

    @media all and (max-width: 639px) and (min-width: 320px) {
        #msgSenha {
            background: #efefef;
            background-size: 320px 205px;
        }
    }

    @media all and (max-width: 799px) and (min-width: 640px) {
        #msgSenha {
            background: #efefef url("/static/back-640.png");
            background-size: 640px 410px;
        }
    }

    @media all and (max-width: 1023px) and (min-width: 768px) {
        #msgSenha {
            background: #efefef url("/static/back-800.png");
            background-size: 800px 512px;
        }
    }

    @media all and (max-width: 1279px) and (min-width: 1024px) {
        #msgSenha {
            background: #efefef url("/static/back-1024.png");
            background-size: 1024px 656px;
        }
    }

    @media all and (max-width: 1561px) and (min-width: 1280px) {
        #msgSenha {
            background: #efefef url("/static/back-1280.png");
            background-size: 1280px 819px;
        }
    }

    @media all and (min-width: 1562px) {
        #msgSenha {
            background: #efefef url("/static/back.png");
            background-size: 1562px 1000px;
        }
    }

    #msgSenha {
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
