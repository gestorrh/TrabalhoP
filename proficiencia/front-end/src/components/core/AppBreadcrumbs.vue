<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout class="align-center layout px-4 pt-4 app--page-header" row>
        <div class="page-header-left">
            <h3 class="pr-3">{{title}}</h3>
        </div>
        <br>
        <v-icon>{{icon}}</v-icon>
        <v-breadcrumbs :items="items" divider=">">
            <template v-slot:item="props">
                <template v-if="props.item.href && !props.item.disabled">
                    <router-link :to="props.item.href" class="link">{{ props.item.text }}</router-link>
                </template>
                <template v-if="props.item.disabled || !props.item.href">
                    <a class="disabled">{{ props.item.text }}</a>
                </template>
            </template>
        </v-breadcrumbs>
        <v-spacer></v-spacer>
        <div class="page-header-right">
            <v-btn @click="reloadPage" icon>
                <v-icon class="text--secondary">refresh</v-icon>
            </v-btn>
        </div>
    </v-layout>
</template>

<script>
    export default {
        name: "app-breadcrumbs",
        props: {
            title: {
                type: String,
                required: true
            },
            icon: {
                type: String,
                required: false,
                default: 'home'
            },
            items: {
                type: Array,
                required: false
            }
        },
        beforeUpdate() {
            this.items.sort((item1, item2) => item1.position - item2.position);
        },
        methods: {
            reloadPage() {
                this.$router.go();
            }
        }
    };
</script>

<style scoped>
    .link {
        text-decoration: none;
    }

    .disabled {
        color: grey;
        pointer-events: none
    }
</style>