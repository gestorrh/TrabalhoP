import Vue from 'vue'
import Vuex from 'vuex'
import enums from './modules/enums'
import selecoes from './modules/selecoes'
import auth from './modules/auth'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        selecoes,
        auth,
        enums
    },
})