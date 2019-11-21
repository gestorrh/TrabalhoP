import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/src/stylus/app.styl'
import '../theme/default.styl'

Vue.use(Vuetify, {
    theme: {
        primary: '#1C6EB7',
        secondary: '#E84D1A',
        accent: '#82B1FF',
        error: '#FF5252',
        info: '#2196F3',
        success: '#4CAF50',
        warning: '#FFC107'
    },
    iconfont: 'md',
});
