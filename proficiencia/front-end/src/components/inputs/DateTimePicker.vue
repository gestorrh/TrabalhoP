<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-menu
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            max-width="290px"
            min-width="290px">
        <template v-slot:activator="{ on }">
            <v-text-field
                    :label="label"
                    :value="campo"
                    v-on="on"
                    readonly
                    :prepend-icon="prependIcon"
                    :error-messages="errorMessages"
            >
                <div slot="append">
                    <v-icon v-if="data !== '' && hora !== ''" :color="errorMessages.length ? 'red' : ''" @click="limpar">clear</v-icon>
                </div>
            </v-text-field>
        </template>
        <v-tabs v-model="tab" grow>
            <v-tab key="0">Data</v-tab>
            <v-tab key="1">Hora</v-tab>

            <v-tab-item key="0">
                <v-date-picker full-width locale="brazil" v-model="data" @input="inputData"></v-date-picker>
            </v-tab-item>
            <v-tab-item key="1">
                <v-time-picker full-width format="24hr" v-model="hora" @input="inputHora"></v-time-picker>
            </v-tab-item>
        </v-tabs>
    </v-menu>
</template>

<script>
    import moment from "moment";

    moment.locale("pt-br");

    export default {
        name: "DateTimePicker",
        props: {
            value: String,
            label: String,
            prependIcon: {
                type: String,
                default: "mdi-calendar-clock"
            },
            errorMessages: Array
        },
        data() {
            return {
                menu: false,
                tab: 0,

                data: "",
                hora: "",

                valor: this.value
            }
        },
        computed: {
            campo () {
                let dataHora = this.dataHoraIso;
                return dataHora === "" ? dataHora : moment(dataHora).format('LLLL');
            },
            dataHoraIso () {
                return (this.data === "" || this.hora === "") ? "" : moment(this.data + " " + this.hora).format("YYYY-MM-DD HH:mm");
            }
        },
        watch: {
            dataHoraIso (novoValor) {
                this.valor = novoValor;
                this.$emit("input", this.valor);
            },
            value: function(newValue) {
                if(this.valor !== newValue && newValue !== undefined && newValue !== null && newValue !== "" && moment(newValue).isValid()) {
                    this.valor = newValue;
                    this.data = moment(this.valor).format("YYYY-MM-DD");
                    this.hora = moment(this.valor).format("HH:mm");
                }
            }
        },
        methods: {
            inputData () {
                if (this.hora === "") {
                    this.tab = 1;
                } else {
                    this.menu = false;
                }
            },
            inputHora () {
                this.menu = false;
                this.tab = 0;
            },
            limpar () {
                this.data = "";
                this.hora = "";
                this.$emit("input", this.valor);
            }
        }
    }
</script>