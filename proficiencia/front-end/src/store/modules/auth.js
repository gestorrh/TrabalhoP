import axios from 'axios'
import router from '../../router'

// state
const state = {
    token: localStorage.getItem("token"),
    token_update_date: new Date(localStorage.getItem("token_update_date")),
    user: {
        roles: JSON.parse(localStorage.getItem("papeis")) ? JSON.parse(localStorage.getItem("papeis")) : []
    }
};

// actions
const actions = {
    login({commit}, authData) {
        return new Promise((resolve) => {
            axios.post('/login', {
                cpf: authData.username,
                password: authData.password
            })
                .then(res => {
                    const date = new Date();
                    localStorage.setItem('token', res.data.token);
                    localStorage.setItem('papeis', JSON.stringify(res.data.papeis));
                    localStorage.setItem('token_update_date', date);
                    commit('AUTHENTICATE', {token: res.data.token, papeis: res.data.papeis, date},)
                })
                .finally(() => resolve())

        })
    },

    logout({commit}) {
        localStorage.removeItem('token');
        localStorage.removeItem('papeis');
        localStorage.removeItem('token_update_date');
        commit('CLEAR_AUTH_DATA');
        router.push('/telaInicial')
    },

    updateToken({commit}, tokenData) {
        const date = new Date();
        localStorage.setItem('token', tokenData.token);
        localStorage.setItem('token_update_date', date);
        commit('UPDATE_AUTH_DATA', {token: tokenData.token, date});
    },

    removeToken({commit}) {
        localStorage.removeItem('token');
        localStorage.removeItem('papeis');
        localStorage.removeItem('token_update_date');
        commit('CLEAR_AUTH_DATA')
    }
};

// getters
const getters = {
    isAuthenticated(state) {
        return state.token !== null
    },
    token(state) {
        return state.token
    },
    isValidDateToken(state) {
        const MINUTOS_VALIDADE_TOKEN = 60;
        const minutos = parseInt(((new Date() - state.token_update_date) / 1000) / 60);
        return minutos < MINUTOS_VALIDADE_TOKEN;
    },
    user(state) {
        return state.user
    },
    isCoordenador(state) {
        return state.user.roles.includes('COORDENADOR');
    },
    isCandidato(state) {
        return state.user.roles.includes('CANDIDATO');
    }
};

// mutations
const mutations = {
    AUTHENTICATE: (state, authUser) => {
        state.token = authUser.token;
        state.user.roles = authUser.papeis;
        state.token_update_date = authUser.date;
    },
    CLEAR_AUTH_DATA: (state) => {
        state.token = null;
        state.user.roles = null;
        state.token_update_date = null;
    },
    UPDATE_AUTH_DATA: (state, tokenData) => {
        state.token = tokenData.token;
        state.token_update_date = tokenData.date;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
