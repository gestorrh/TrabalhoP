<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-menu v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            lazy
            transition="scale-transition"
            offset-y full-width max-width="290px"
            min-width="290px">
        <template v-slot:activator="{ on }">
            <v-text-field :value="campo"
                          :label="label"
                          :prepend-icon="prependIcon"
                          v-on="on" readonly
                          :error-messages="errorMessages"
            >
                <div slot="append">
                    <v-icon v-if="valor !== ''" :color="errorMessages.length ? 'red' : ''" @click="limpar">clear</v-icon>
                </div>
            </v-text-field>
        </template>
        <v-date-picker locale="brazil"
                       v-model="valor"
                       @input="input"
        ref="picker">
        </v-date-picker>
    </v-menu>
</template>

<script>
    import moment from "moment";

    moment.locale("pt-br");

    export default {
        name: "DatePicker",
        props: {
            value: String,
            label: String,
            prependIcon: {
                type: String,
                default: "event"
            },
            errorMessages: Array,
            birthday: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                menu: false,

                valor: this.value
            }
        },
        computed: {
            campo () {
                return this.valor === "" ? this.valor : moment(this.valor).format("dddd, LL");
            }
        },
        watch: {
            menu (val) {
                val && this.birthday && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            },
            value: function(newValue) {
                if(this.valor !== newValue && newValue !== undefined && newValue !== null && newValue !== "" && moment(newValue).isValid()) {
                    this.valor = newValue
                }
            }
        },
        methods: {
            input () {
                this.menu = false;
                this.$emit("input", this.valor);
            },
            limpar () {
                this.valor = "";
                this.$emit("input", this.valor);
            }
        }
    }
</script>