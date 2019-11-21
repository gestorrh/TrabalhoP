<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-container fluid grid-list-xl>
        <app-breadcrumbs :items="breadcrumbItems" icon="description" title="Exames"></app-breadcrumbs>
        <v-layout row wrap>
            <v-flex xs12>
                <v-widget title="Lista de exames">
                    <div slot="widget-content">
                        <v-tooltip top>
                            <template v-slot:activator="{ on }">
                                <v-btn absolute color="blue" dark fab right to="/coordenador/exames/adicionar" top v-on="on">
                                    <v-icon>note_add</v-icon>
                                </v-btn>
                            </template>
                            <span>Adicionar Exame</span>
                        </v-tooltip>

                        <v-data-table :custom-sort="sort" :headers="headers" :items="exames" hide-actions no-data-text="Nenhum exame cadastrado">
                            <template v-slot:items="exames">
                                <td class="body-1"><b>{{ exames.item.titulo }}</b></td>
                                <td class="body-1 text-uppercase">{{ getLabelFase(exames.item.fase) }}</td>
                                <td class="body-1">{{ exames.item.inscricoes }} / {{ exames.item.numeroVagas }}</td>

                                <v-btn color="cyan"
                                       outline
                                       round
                                       :to="'/coordenador/exames/' + exames.item.id">
                                    <v-icon dark small>search</v-icon>
                                    Detalhes
                                </v-btn>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on }">
                                        <v-btn color="indigo" fab outline small 
                                        v-bind:to="{name: 'EditarExame', params:{id: exames.item.id}}"
                                               v-on="on">
                                            <v-icon>edit</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Editar Exame</span>
                                </v-tooltip>
                                <v-tooltip top>
                                    <template v-slot:activator="{ on }">
                                        <v-btn :disabled="naoPermiteExclusao(exames.item)"
                                               @click="abrirDialogExcluirExame(exames.item)" color="red" fab outline small
                                               v-on="on">
                                            <v-icon>delete</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>Excluir Exame</span>
                                </v-tooltip>
                            </template>
                        </v-data-table>
                    </div>
                </v-widget>
            </v-flex>
        </v-layout>

        <v-snackbar
                :color="dCor"
                right
                top
                v-model="dSnackbar"
        >{{ dMensagem }}
        </v-snackbar>

        <v-dialog max-width="290" v-model="dialog">
            <v-card>
                <v-card-title class="headline">Excluir Exame</v-card-title>

                <v-card-text>Tem certeza que deseja excluir o exame?</v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn @click="dialog = false" color="silver darken-1" flat="flat">Não</v-btn>

                    <v-btn @click="excluirExame()" color="red darken-1" flat="flat">Sim</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
    import axios from "axios";
    import AppBreadcrumbs from "../../../components/core/AppBreadcrumbs";
    import VWidget from "../../../components/core/VWidget";
    import store from "../../../store";

    export default {
        props: {
            cor: {
                type: String,
                default: ""
            },
            snackbar: {
                type: Boolean,
                default: false
            },
            mensagem: {
                type: String,
                default: ""
            }
        },
        components: {
            VWidget,
            AppBreadcrumbs
        },
        data() {
            return {
                exames: [],
                dialog: false,
                headers: [
                    {text: "TÍTULOS", value: [0], sortable: true},
                    {text: "STATUS", value: [1], sortable: true},
                    {text: "Nº DE INSCRITOS / Nº DE VAGAS", value: [2], sortable: false},
                    {value: null, sortable: false}
                ],
                breadcrumbItems: [
                    {position: 1, text: 'Exames', disabled: true, href: '/coordenador/exames'}
                ],

                // Configuração da mensagem
                dSnackbar: this.snackbar,
                dMensagem: this.mensagem,
                dCor: this.cor
            };
        },

        created: function () {
            this.initialize();
        },

        methods: {
            getLabelFase(fase) {
                return store.getters["enums/getLabelFase"](fase);
            },

            getPosicaoFase(fase) {
              return store.getters["enums/getPosicaoFase"](fase);
            },

            initialize() {
                axios.get("exame/todos").then(res => {
                    this.exames = res.data;
                });
            },

            sort(items, index, isDescending) {
                if (index !== null && isDescending !== null) {
                    switch (index[0]) {
                        case 0:
                            items.sort((itemA, itemB) => {
                                return this.ordenacaoTitulo(itemA, itemB, isDescending);
                            });
                            break;
                        case 1:
                            items.sort((itemA, itemB) => {
                                return this.ordenacaoStatus(itemA, itemB, isDescending);
                            });
                            break;
                        case 2:
                            items.sort((itemA, itemB) => {
                                return this.ordenacaoQuantidade(itemA, itemB, isDescending);
                            });
                            break;
                    }
                }

                return items;
            },

            naoPermiteExclusao(exame) {
                return exame.fase != "INSCRICAO" || exame.inscricoes > 0;
            },

            abrirDialogExcluirExame(exame) {
                if (!this.naoPermiteExclusao(exame)) {
                    this.exameDelete = exame;
                    this.dialog = true;
                }
            },

            excluirExame() {
                axios.delete("/exame/" + this.exameDelete.id)
                    .then(() => {
                        this.exames.splice(this.exames.indexOf(this.exameDelete), 1);
                        this.exameDelete = null;
                        
                        this.dSnackbar = true;
                        this.dMensagem = "Exame excluído com sucesso";
                        this.dCor = "success";
                    })
                    .catch((error) => {
                        this.dSnackbar = true;
                        this.dMensagem = error.response.data;
                        this.dCor = "error";
                    });

                this.dialog = false;
            },

            ordenacaoTitulo(itemA, itemB, isDescending) {
                let mod = isDescending ? -1 : 1;
                if (itemA.titulo > itemB.titulo) {
                    return 1 * mod;
                } else if (itemA.titulo < itemB.titulo) {
                    return -1 * mod;
                }
                return 0;
            },

            ordenacaoStatus(itemA, itemB, isDescending) {
                let mod = isDescending ? -1 : 1;
                if (this.getPosicaoFase(itemA.fase) > this.getPosicaoFase(itemB.fase)) {
                    return 1 * mod;
                } else if (this.getPosicaoFase(itemA.fase) < this.getPosicaoFase(itemB.fase)) {
                    return -1 * mod;
                }
                return 0;
            },

            ordenacaoQuantidade(itemA, itemB, isDescending) {
                let mod = isDescending ? -1 : 1;
                let result = itemA.inscricoes - itemB.inscricoes;
                return result * mod;
            }
        }
    };
</script>

<style>
    h3 {
        color: #3c6781;
        font-weight: 400;
    }

    .aprovado {
        color: #45e276;
    }

    .reprovado {
        color: #ea4c72;
    }

    .em_avalicao {
        color: #565656;
    }
</style>
