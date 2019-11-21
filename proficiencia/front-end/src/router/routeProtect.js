export class RouteProtect {

    constructor(router, store) {
        this.router = router;
        this.store = store;
    }

    _hasAccess(permission, route) {
        return (typeof permission.access === "boolean" && permission.access) ||
            (typeof permission.access === "function" && permission.access(this.user, route));
    }

    _hasAccessToRoute(route) {
        if (this.user && route.meta.permissions) {
            const permission = route.meta.permissions.find(item => this.user.roles.includes(item.role));
            if (permission && this._hasAccess(permission, route)) {
                return {access: true};
            }
            return {access: false, redirect: permission && permission.redirect ? permission.redirect : "AccessDenied"};
        }
        return {access: true}
    }

    resolve(to, from, next) {
        this.user = this.store.getters["auth/user"];
        const {access, redirect} = this._hasAccessToRoute(to);
        access ? next() : next({name: redirect});
    }

}
