import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import router from "./router/"
import store from './store'
import axios from 'axios'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import 'font-awesome/css/font-awesome.css'

axios.defaults.baseURL = process.env.VUE_APP_API_URL;

// Adiciona token na requisição
axios.interceptors.request.use(function (config) {
    if (store.getters["auth/isValidDateToken"]) {
      config.headers.Authorization = store.getters["auth/token"];
    } 
    return config;
  }, function (error) {
    return Promise.reject(error);
  });

axios.interceptors.response.use((response) => {
  if(response.headers.hasOwnProperty('authorization')) {
    store.dispatch("auth/updateToken", {
      token: response.headers.authorization
    });
  }
  return response;
}, (error) => {
  if (error.response.status === 401 || error.response.status === 403 ) {
    store.dispatch("auth/removeToken");

    const path = error.response.data.path;
    const to = path.split(window.location.origin).pop();

    router.push({ name: 'TelaInicial', params: { to: to, invalidToken: true}})
  } else {
    throw error;
  }
});

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
