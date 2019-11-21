<template>
  <div id="appRoot">
    <template v-if="!$route.meta.public">
      <v-app>
        <app-toolbar></app-toolbar>
        <v-content>
          <v-container fluid>
            <div class="page-wrapper">
              <router-view></router-view>
            </div>
          </v-container>
        </v-content>
        <!-- App Footer -->
        <v-footer height="auto" class="white pa-3 app--footer">
          <span
            class="caption"
          >Núcleo de Práticas em Informática &copy; {{ new Date().getFullYear() }}</span>
          <v-spacer></v-spacer>
          <span class="caption">
            v1.0.0
          </span>
        </v-footer>
      </v-app>
    </template>
    <template v-else>
      <transition>
        <keep-alive>
          <router-view :key="$route.fullpath"></router-view>
        </keep-alive>
      </transition>
     

    </template>
  </div>
  
</template>

<script>
import AppToolbar from "./components/core/AppToolbar";
// import axios from "axios";

export default {
  name: "App",
  components: {
    AppToolbar
  },
};
</script>

<style lang="stylus" scoped>
.page-wrapper {
  min-height: calc(100vh - 64px - 50px - 81px);
}

.v-breadcrumbs {
  padding: 24px !important;
}

.v-content {
    padding-top: 0 !important;
}
</style>
