<template>
    <v-toolbar app color="primary" dark fixed>
        <router-link :to="{ path: '/'}"><img alt="Logo NPI" src="/static/logo-01.png"></router-link>
        <v-spacer></v-spacer>

        <v-toolbar-items class="ml-3" v-if="isCoordenador">
            <v-btn flat to="/coordenador/exames">Exames</v-btn>
            <v-btn flat to="/coordenador/usuarios">Usuários</v-btn>
        </v-toolbar-items>
        <v-toolbar-items class="ml-3" v-if="isCandidato">
            <v-btn flat to="/candidato/inscricoes">Minhas Inscrições</v-btn>
            <v-btn flat to="/candidato/exames-abertos">Seleções Abertas</v-btn>
        </v-toolbar-items>
        <v-menu :nudge-bottom="10" offset-y origin="center center" transition="scale-transition">
            <v-btn flat icon large slot="activator">
                <v-avatar size="30px">
                    <v-icon>account_circle</v-icon>
                </v-avatar>
            </v-btn>
            <v-list class="pa-0">
                <v-list-tile @click="dados">
                    <v-list-tile-action>
                        <v-icon>face</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Perfil</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <v-list-tile @click="logout">
                    <v-list-tile-action>
                        <v-icon>power_settings_new</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Sair</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>
        </v-menu>
    </v-toolbar>

</template>


<script>
    import {mapGetters} from 'vuex'

    export default {
        name: "app-toolbar",

        computed: {
            ...mapGetters({
                'isCoordenador': "auth/isCoordenador",
                'isCandidato': "auth/isCandidato"
            }),
        },

        methods: {
            logout() {
                this.$router.push("/logout");
            },
            dados() {
                this.$router.push("/dados-pessoais")
            },
        }
    };
</script>



