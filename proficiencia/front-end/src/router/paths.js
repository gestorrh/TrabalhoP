import store from '../store'
import axios from 'axios'

import HomePage from '../components/Home'

import NotFoundPage from '../views/utils/NotFoundPage'
import AccessDeniedPage from '../views/utils/AccessDeniedPage'
import ServerErrorPage from '../views/utils/ServerErrorPage'

import RealizarInscricaoPage from '../views/candidato/inscricao/RealizarInscricaoForm'
import MinhasInscricoesPage from '../views/candidato/inscricao/MinhasInscricoes'

import RecuperarSenhaPage from '../views/inicio/RecuperarSenha'
import MsgEnvioEmailPage from '../views/inicio/MsgEnvioEmail'
import NovaSenhaPage from '../views/inicio/NovaSenha'
import MsgAlteracaoSenhaPage from '../views/inicio/MsgAlteracaoSenha'
import TelaInicialPage from '../views/inicio/TelaInicial'

import DetalhesExameCandidatoPage from '../views/candidato/exame/DetalhesExame'

import SelecoesAbertasPage from '../views/candidato/exame/ListagemAbertos'


import dash from '../views/gestor/dashboard/ListagemTodos'
import CadastrarUsuarioPage from '../views/gestor/usuario/CadastrarUsuarioForm'
import ListagemUsuariosPage from '../views/gestor/usuario/ListagemTodos'
import DetalhesUsuarioPage from '../views/gestor/usuario/DetalhesUsuario'
import DetalhesInscricaoCandidatoPage from '../views/candidato/inscricao/DetalhesInscricao'
import AtualizarUsuarioPage from '../views/gestor/usuario/EditarUsuarioForm'

import CadastrarExame from "../views/medico/CadastrarExame";
import ListagemExames from "../views/medico/ListagemExames";
import DetalhesExame from "../views/candidato/exame/DetalhesExame";
import EditarExame from "../views/medico/EditarExame";

import DadosPessoaisPage from '../views/usuario/Perfil'


export default [
    {
        path: '*',
        meta: {
            public: true,
        },
        redirect: {
            path: '/404'
        }
    },
    {
        path: '/404',
        meta: {
            public: true,
        },
        name: 'NotFound',
        component: NotFoundPage
    },
    {
        path: '/403',
        meta: {
            public: true,
        },
        name: 'AccessDenied',
        component: AccessDeniedPage
    },
    {
        path: '/500',
        meta: {
            public: true,
        },
        name: 'ServerError',
        component: ServerErrorPage
    },
    {
        path: '/telaInicial',
        meta: {
            public: true
        },
        props: true,
        name: 'TelaInicial',
        component: TelaInicialPage
    },
    {
        path: '/recuperacao',
        meta: {
            public: true,
        },
        name: 'RecuperarSenha',
        component: RecuperarSenhaPage
    },
    {
        path: '/msgEnvioEmail',
        meta: {
            public: true,
        },
        props: true,
        name: 'MsgEnvioEmail',
        component: MsgEnvioEmailPage
    },
    {
        path: '/novaSenha',
        meta: {
            public: true,
        },
        name: 'NovaSenha',
        component: NovaSenhaPage,
        beforeEnter: (to, from, next) => {
            axios
                .get(`public/usuario/validar-token?token=${to.query.hash}`)
                .then(function (data) {
                    if (data && data.status == 200) {
                        return next();
                    } else {
                        return next('/tokenInvalido')
                    }
                })
        }
    },
    {
        path: '/msgAlteracaoSenha',
        meta: {
            public: true,
        },
        props: true,
        name: 'MsgAlteracaoSenha',
        component: MsgAlteracaoSenhaPage
    },

    {

        path: '/',
        name: 'Root',
        meta: {
            requiresAuth: true,
            permissions: [
                {
                    role: "GESTOR",
                    access: false,
                    redirect: 'Dashboard'
                },
                {
                    role: "COLABORADOR",
                    access: false,
                    redirect: 'CandidatoInscricoes'
                },
                {
                    role: "MEDICO",
                    access: false,
                    redirect: 'listarExames'
                }
            ]
        }
    },

    {
        path: '/candidato/inscricoes', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'CandidatoInscricoes', component: MinhasInscricoesPage,
                meta: { permissions: [{ role: "COLABORADOR", access: true }] }
            },

            {
                path: ':id', component: DetalhesInscricaoCandidatoPage,
                meta: { permissions: [{ role: "COLABORADOR", access: true }] }
            },
        ]
    },
    {
        path: '/inscricao-candidato/:id',
        meta: {
            requiresAuth: true,
            permissions: [
                {
                    role: "COLABORADOR",
                    access: true
                },
                {
                    role: "GESTOR",
                    access: true
                }
            ]
        },
        name: 'Detalhes Inscricao Candidato',
        component: DetalhesInscricaoCandidatoPage
    },


    {
        path: '/candidato/exames', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'CandidatoExamesAbertos', component: SelecoesAbertasPage,
                meta: { permissions: [{ role: "COLABORADOR", access: true }] }
            },

            {
                path: ':id', component: DetalhesExameCandidatoPage, props: true,
                meta: { permissions: [{ role: "COLABORADOR", access: true }] }
            },

            {
                path: ':id/inscricao', name: 'RealizarInscricao', props: true, component: RealizarInscricaoPage,
                meta: { permissions: [{ role: "COLABORADOR", access: true }] }
            }
        ]
    },


    {
        path: '/gestor/dashboard', props: true, component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'Dashboard',props: true, component: dash,
                meta: { permissions: [{ role: "GESTOR", access: true }] }
            },
        ]
    },

    {
        path: '/gestor/usuarios', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'gestorUsuarios', component: ListagemUsuariosPage,
                meta: { permissions: [{ role: "GESTOR", access: true }] }
            },

            {
                path: 'adicionar', component: CadastrarUsuarioPage,
                meta: { permissions: [{ role: "GESTOR", access: true }] }
            },

            {
                path: ':id', component: DetalhesUsuarioPage, props: true,
                meta: { permissions: [{ role: "GESTOR", access: true }] }
            },

            {
                path: ':id/editar', name: 'EditarUsuario', component: AtualizarUsuarioPage, props: true,
                meta: { permissions: [{ role: "GESTOR", access: true }] }
            }

        ]
    },

    {
        path: '/medico/exames', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: 'listarExames', name: 'examesMedico', component: ListagemExames,
                meta: { permissions: [{ role: "MEDICO", access: true }] }
            },

            {
                path: 'adicionarExame', component: CadastrarExame,
                meta: { permissions: [{ role: "MEDICO", access: true }] }
            },

            {
                path: ':id', component: DetalhesExame, props: true,
                meta: { permissions: [{ role: "MEDICO", access: true }] }
            },

            {
                path: ':id/editar', name: 'EditarExame', component: EditarExame, props: true,
                meta: { permissions: [{ role: "MEDICO", access: true }] }
            }

        ]
    },

    {
        path: '/logout',
        name: 'Logout',
        beforeEnter() {
            store.dispatch('auth/logout')
        }
    },

    {
        path: '/dados-pessoais',
        meta: {
            requiresAuth: true,
            permissions: [
                {
                    role: "GESTOR",
                    access: true
                },
                {
                    role: "COLABORADOR",
                    access: true
                },
                {
                    role: "MEDICO",
                    access: true
                }
            ]
        },
        name: 'DadosPessoais',
        component: DadosPessoaisPage
    }

];
