import Vue from 'vue';
import Router from 'vue-router';
import paths from './paths';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import {RouteProtect} from './routeProtect'
import store from '../store'


Vue.use(Router);
const router = new Router({
    base: '/',
    mode: 'history',
    linkActiveClass: 'active',
    routes: paths
});
// router gards
router.beforeEach((to, from, next) => {
    NProgress.start();

    if (to.name == 'Root' || to.path == "/")
        NProgress.done();
    next();
});

router.afterEach(() => {
    NProgress.done();
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('token')) {
            next();
            return
        }
        next('/telaInicial')
    } else {
        next()
    }
});

const rp = new RouteProtect(router, store);
router.beforeEach((to, from, next) => rp.resolve(to, from, next));

export default router;



