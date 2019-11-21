<template>
    <div id="v-widget">
        <v-card>
            <v-toolbar card color="transparent" dense flat v-if="enableHeader">
                <v-toolbar-title><h4>{{title}}</h4></v-toolbar-title>
                <v-spacer></v-spacer>
                <slot name="widget-header-action"></slot>
            </v-toolbar>
            <v-divider v-if="enableHeader"></v-divider>
            <v-card-text :class="contentBg">
                <slot name="widget-content"></slot>
            </v-card-text>
            <v-divider v-if="enableActions"></v-divider>
            <v-card-actions v-if="enableActions">
                <slot name="widget-actions"></slot>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
    export default {
        name: 'v-widget',
        props: {
            title: {
                type: String,
            },
            enableHeader: {
                type: Boolean,
                default: true
            },
            contentBg: {
                type: String,
                default: 'white'
            },
            enableActions: {
                type: Boolean,
                default: false
            },
        },
        data() {
            return {};
        },
        computed: {},
    };
</script>

