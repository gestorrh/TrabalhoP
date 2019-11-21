<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-text-field
            :error-messages="errorMessages"
            :label="label"
            :mask="mask"
            :prepend-icon="icon"
            v-model="valor"
            @click:prepend="mudarMascara"
            @input="input">
        <div slot="append">
            <v-tooltip top>
                <template v-slot:activator="{ on }">
                    <v-icon v-on="on">mdi-help-circle-outline</v-icon>
                </template>
                <span>Você pode alternar entre Celular/Fixo clicando no ícone do campo</span>
            </v-tooltip>
        </div>
    </v-text-field>
</template>

<script>
    export default {
        name: "Telefone",
        props: {
            value: String,
            label: String,
            errorMessages: Array
        },
        data () {
            return {
                mask: "(##)#.####-####",
                icon: "fas fa-mobile-alt",
                valor: this.value,
                inicializado: false
            }
        },
        watch: {
            value: function(newValue) {
                if(this.valor !== newValue) {
                    this.valor = newValue;
                    if(!this.inicializado){
                        if(newValue !== null) {
                            this.inicializado = true;
                        } else {
                            this.inicializado = false;
                        }
                    }
                }
            },
            inicializado: function () {
                if(this.inicializado) {
                    if(this.valor.length === 10) {
                        this.mask = "(##)####-####";
                        this.icon = "fas fa-tty";
                    } else {
                        this.mask = "(##)#.####-####";
                        this.icon = "fas fa-mobile-alt";
                    }
                }
            }
        },
        methods: {
            input () {
                this.$emit("input", this.valor);
            },
            mudarMascara() {
                this.valor = "";
                this.input();

                if(this.mask === "(##)#.####-####") {
                    this.mask = "(##)####-####";
                    this.icon = "fas fa-tty";
                } else {
                    this.mask = "(##)#.####-####";
                    this.icon = "fas fa-mobile-alt";
                }
            }
        }
    }
</script>