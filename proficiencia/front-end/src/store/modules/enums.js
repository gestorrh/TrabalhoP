const state = {
    estados: [
        {
            value: "SOLTEIRO",
            label: "Solteiro(a)"
        },
        {
            value: "CASADO",
            label: "Casado(a)"
        },
        {
            value: "DIVORCIADO",
            label: "Divorciado(a)"
        },
        {
            value: "VIUVO",
            label: "Viúvo(a)"
        }
    ],
    fases: [
        {
            value: "INSCRICAO",
            label: "Inscrição",
            posicao: 0
        },
        {
            value: "SELECAO",
            label: "Seleção",
            posicao: 1
        },
        {
            value: "AVALIACAO",
            label: "Avaliação",
            posicao: 2
        },
        {
            value: "FINALIZADO",
            label: "Finalizado",
            posicao: 3
        }
    ],
    necessidades: [
        {
            value: "FISICA",
            label: "Física"
        },
        {
            value: "AUDITIVA",
            label: "Auditiva"
        },
        {
            value: "VISUAL",
            label: "Visual"
        },
        {
            value: "MENTAL",
            label: "Mental"
        },
        {
            value: "MULTIPLA",
            label: "Múltipla"
        }
    ],
    statusAvaliacao: [
        {
            value: "EM_AVALIACAO",
            label: "Em avaliação"
        },
        {
            value: "APROVADO",
            label: "Aprovado"
        },
        {
            value: "REPROVADO",
            label: "Reprovado"
        }
    ],
    statusInscricao: [

        {
            value: "INSCRITO",
            label: "Inscrito"
        },
        {
            value: "DEFERIDO",
            label: "Deferido"
        },
        {
            value: "INDEFERIDO",
            label: "Indeferido"
        }
    ],
    sexos: [

        {
            value: "MASCULINO",
            label: "Masculino"
        },
        {
            value: "FEMININO",
            label: "Feminino"
        }
    ],
    papeis: [
        {
            value: "GESTOR",
            label: "gestor"
        },
        {
            value: "COLABORADOR",
            label: "colaborador"
        },
        {
            value: "MEDICO",
            label: "medico"
        }
    ],
    formacoes: [
        {
            value: "GRADUACAO",
            label: "Graduação"
        },
        {
            value: "MESTRADO",
            label: "Mestrado"
        },
        {
            value: "DOUTORADO",
            label: "Doutorado"
        }
    ],
    prioridades: [
        {
            value: 0,
            label: "1° prioridade",
            descricao: "Alunos de programas de pós-graduação stricto sensu da UFC que estejam matriculados, ou já tenham cursado, o componente curricular “Qualificação” ou “Dissertação/Tese”;"
        },
        {
            value: 1,
            label: "2° prioridade",
            descricao: "Alunos de programas de pós-graduação stricto sensu da UFC que estejam matriculados no componente curricular “Proficiência”;"
        },
        {
            value: 2,
            label: "3° prioridade",
            descricao: "Demais alunos dos programas de pós-graduação stricto sensu da UFC;"
        },
        {
            value: 3,
            label: "4° prioridade",
            descricao: "Alunos externos vinculados a algum programa de pós-graduação stricto sensu;"
        },
        {
            value: 4,
            label: "5° prioridade",
            descricao: "Alunos externos sem vínculos com programas de pós-graduação;"
        }
    ],

};

const getters = {
    estados(state) {
        return state.estados;
    },
    getLabelEstadoCivil(state) {
        return (value) => {
            const estado = state.estados.find(estado => estado.value == value);
            return estado != undefined ? estado.label : value;
        }
    },

    fases(state) {
        return state.fases;
    },
    getFase(state) {
      return (value) => {
          return state.fases.find(fase => fase.value == value);
      }
    },
    getLabelFase(state) {
        return (value) => {
            const fase = state.fases.find(fase => fase.value == value);
            return fase != undefined ? fase.label : value;
        }
    },
    getPosicaoFase(state) {
      return (value) => {
          const fase = state.fases.find(fase => fase.value == value);
          return fase != undefined ? fase.posicao : null;
      }
    },

    papeis(state) {
        return state.papeis;
    },
    getLabelPapel(state) {
        return (value) => {
            const papel = state.papeis.find(papel => papel.value == value);
            return papel != undefined ? papel.label : value;
        }
    },

    necessidades(state) {
        return state.necessidades;
    },
    getLabelNecessidade(state) {
        return (value) => {
            const necessidade = state.necessidades.find(necessidade => necessidade.value == value);
            return necessidade != undefined ? necessidade.label : value;
        }
    },

    statusAvaliacao(state) {
        return state.statusAvaliacao;
    },
    getLabelStatusAvaliacao(state) {
        return (value) => {
            const statusA = state.statusAvaliacao.find(statusA => statusA.value == value);
            return statusA != undefined ? statusA.label : value;
        }
    },

    statusInscricao(state) {
        return state.statusInscricao;
    },
    getLabelStatusInscricao(state) {
        return (value) => {
            const statusI = state.statusInscricao.find(statusI => statusI.value == value);
            return statusI != undefined ? statusI.label : value;
        }
    },

    sexos(state) {
        return state.sexos;
    },
    getLabelSexo(state) {
        return (value) => {
            const sexo = state.sexos.find(sexo => sexo.value == value);
            return sexo != undefined ? sexo.label : value;
        }
    },
    prioridades(state) {
        return state.prioridades;
    },
    getLabelPrioridade(state) {
        return (value) => {
            const prioridade = state.prioridades.find(prioridade => prioridade.value == value);
            return prioridade != undefined ? prioridade.label : value;
        }

    },
    formacoes(state) {
        return state.formacoes;
    }

};

export default {
    namespaced: true,
    state,
    getters
}
