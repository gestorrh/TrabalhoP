<template>
    <v-form @submit.prevent="onSubmit">
        <v-card-text>
            <div class="layout column align-center">
                <span class="body-1 red--text">{{error}}</span>
            </div>
            <v-text-field @keydown="removeError" append-icon="person" label="CPF" name="username" type="text"
                          v-model="username" mask="###.###.###-##"></v-text-field>
            <v-text-field @keydown="removeError" append-icon="lock" id="password" label="Senha" name="password"
                          type="password" v-model="password"></v-text-field>
            <v-card-actions class="layout column align-center">
                <v-btn :loading="loading" color="primary" large type="submit">Entrar</v-btn>
            </v-card-actions>
            <p class="recupSenha">Esqueceu sua senha ? <a href="/recuperacao"> Recuperar senha</a></p>
        </v-card-text>
    </v-form>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "app-login",
        props: ["to", "invalidToken"],
        data() {
            return {
                loading: false,
                username: "",
                password: "",
                error: "",
            };
        },
        created() {
            if (this.invalidToken) {
                this.error = "Seu tempo de conexão expirou. Faça o login novamente.";
            }
        },
        methods: {
            onSubmit() {
                this.loading = true;
                this.$store
                    .dispatch("auth/login", {
                        username: this.username,
                        password: this.password
                    })
                    .then(() => {
                        if (this.$store.getters["auth/isAuthenticated"]) {
                            this.to
                                ? this.$router.push({path: this.to})
                                : this.$router.push({path: "/"});
                        } else {
                            this.error = "Usuário e/ou senha inválidos";
                        }
                    });
                this.loading = false;
            },
            removeError() {
                this.error = '';
            }
        },
        computed: {
            ...mapState({
                token: state => state.auth.token
            })
        }
    };
</script>
<style lang="css" scoped>
    .recupSenha {
        margin-top: 15px;
    }

    a {
        text-decoration: none;
    }

</style>
