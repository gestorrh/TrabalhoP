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

import EditarExamePage from '../views/coordenador/exame/EditarExameForm'
import DetalhesExameCandidatoPage from '../views/candidato/exame/DetalhesExame'
import DetalhesExameCoordenadorPage from '../views/coordenador/exame/DetalhesExame'
import DetalhesLocalProvaCoordenadorPage from '../views/coordenador/localProva/DetalhesLocalProva'

import SelecoesAbertasPage from '../views/candidato/exame/ListagemAbertos'
import ListagemExamesPage from '../views/coordenador/exame/ListagemTodos'

import CadastrarExamePage from '../views/coordenador/exame/CadastrarExameForm'

import CadastrarUsuarioPage from '../views/coordenador/usuario/CadastrarUsuarioForm'
import DetalhesInscricaoPage from '../views/coordenador/inscricao/DetalhesInscricao'
import ListagemUsuariosPage from '../views/coordenador/usuario/ListagemTodos'

import DetalhesUsuarioPage from '../views/coordenador/usuario/DetalhesUsuario'
import DetalhesInscricaoCandidatoPage from '../views/candidato/inscricao/DetalhesInscricao'
import AtualizarUsuarioPage from '../views/coordenador/usuario/EditarUsuarioForm'

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
                    role: "COORDENADOR",
                    access: false,
                    redirect: 'CoordenadorExames'
                },
                {
                    role: "CANDIDATO",
                    access: false,
                    redirect: 'CandidatoInscricoes'
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
                meta: { permissions: [{ role: "CANDIDATO", access: true }] }
            },

            {
                path: ':id', component: DetalhesInscricaoCandidatoPage,
                meta: { permissions: [{ role: "CANDIDATO", access: true }] }
            },
        ]
    },
    {
        path: '/inscricao-candidato/:id',
        meta: {
            requiresAuth: true,
            permissions: [
                {
                    role: "CANDIDATO",
                    access: true
                },
                {
                    role: "COORDENADOR",
                    access: true
                }
            ]
        },
        name: 'Detalhes Inscricao Candidato',
        component: DetalhesInscricaoCandidatoPage
    },


    {
        path: '/candidato/exames-abertos', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'CandidatoExamesAbertos', component: SelecoesAbertasPage,
                meta: { permissions: [{ role: "CANDIDATO", access: true }] }
            },

            {
                path: ':id', component: DetalhesExameCandidatoPage, props: true,
                meta: { permissions: [{ role: "CANDIDATO", access: true }] }
            },

            {
                path: ':id/inscricao', name: 'RealizarInscricao', props: true, component: RealizarInscricaoPage,
                meta: { permissions: [{ role: "CANDIDATO", access: true }] }
            }
        ]
    },

    {
        path: '/coordenador/exames', props: true, component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'CoordenadorExames',props: true, component: ListagemExamesPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: 'adicionar', props: true, component: CadastrarExamePage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: ':id', name: 'CoordenadorLocalProva', props: true, component: DetalhesExameCoordenadorPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: ':exameId/candidato-inscrito/:id', name: "CoordenadorInscricaoCandidato", props: true, component: DetalhesInscricaoPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: ':id/editar', name: 'EditarExame', props: true, component: EditarExamePage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            

        ]
    },
    {
        path: '/coordenador/localprova', name: "LocalProva", component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', props: true,  component: DetalhesLocalProvaCoordenadorPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }]}
            },
            {
                path: ':id',props: true, component: DetalhesLocalProvaCoordenadorPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },
            {
                path: ':id', props: true,  component: DetalhesLocalProvaCoordenadorPage,
                meta: {permissions: [{role: "SECRETARIO", acess: true}]}

            },
            {
                path: ':idExame/candidato-inscrito/:id', props: true, component: DetalhesInscricaoPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },
            {
                path: ':idExame/candidato-inscrito/:id', props: true,  component: DetalhesInscricaoPage,
                meta: { permissions: [{ role: "SECRETARIO", access: true }] }
            },
        ]
    },
    

    {
        path: '/coordenador/usuarios', component: HomePage, redirect: { name: 'NotFound' },
        meta: { requiresAuth: true },
        children: [
            {
                path: '', name: 'CoordenadorUsuarios', component: ListagemUsuariosPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: 'adicionar', component: CadastrarUsuarioPage,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: ':id', component: DetalhesUsuarioPage, props: true,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
            },

            {
                path: ':id/editar', name: 'EditarUsuario', component: AtualizarUsuarioPage, props: true,
                meta: { permissions: [{ role: "COORDENADOR", access: true }] }
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
                    role: "COORDENADOR",
                    access: true
                },
                {
                    role: "CANDIDATO",
                    access: true
                }
            ]
        },
        name: 'DadosPessoais',
        component: DadosPessoaisPage
    }

];
