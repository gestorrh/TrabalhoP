import axios from 'axios'


// state
const state = {
    all: []
};

// actions
const actions = {
    GET_ALL({commit}) {
        axios
            .get('/selecoes')
            .then(res => {
                commit('SET_SELECOES', res.data)
            })
    },

    ADD({commit}, selecao) {
        commit('ADD_SELECAO', selecao)
    }
};

// getters
const getters = {};

// mutations
const mutations = {
    SET_SELECOES(state, selecoes) {
        state.all = selecoes;
    },

    ADD_SELECAO(state, selecao) {
        state.selecoes.push(selecao);
    }
};


export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}