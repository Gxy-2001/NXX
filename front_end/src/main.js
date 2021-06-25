import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import $ from 'jquery'
import 'element-ui/lib/theme-chalk/index.css';
//import '../src/assets/style/purple/theme/index.css';// ElementUI的紫色自定义主题,太丑了——如果想使用，取消该行注释，注释上一行即可——但真的丑

import 'babel-polyfill';

import api from './api/index.js';

//全局可见的用户信息
let globalData = {
    userInfo: {
        nickname: ''
    }
};
//用户状态
let sta = {
    isLogin: false,
    adminName: ''
};

//全局变量区域

//全局可见的对象 api——封装了与后端交互的方法
Vue.prototype.$api = api;

Vue.prototype.$sta = sta;

Vue.prototype.$globalData = globalData;


//开发环境下，Vue 会提供很多警告来帮你对付常见的错误与陷阱。
//而在生产环境下，这些警告语句没有用，反而会增加应用的体积。此外，有些警告检查还有一些小的运行时开销，这在生产环境模式下是可以避免的。
Vue.config.productionTip = false;//阻止启动生产消息。

//引入ElementUI组件库
Vue.use(ElementUI, {
    size: 'medium'
});

//未登录时点击 发布闲置/个人信息/订单，等会重定向至login页面
// 录跳转到登录页面
router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title}`;
    // console.log(to.path,'userInfo:',Vue.prototype.$globalData.userInfo);
    const nickname = Vue.prototype.$globalData.userInfo.nickname;
    if (!nickname
        && (to.path === '/me'
            || to.path === '/message'
            || to.path === '/release'
            || to.path === '/order')) {
        api.getUserInfo().then(res => {
            console.log('getUserInfo:', res);
            if (res.status_code !== 1) {
                next('/login');
            } else {
                res.data.signInTime = res.data.signInTime.substring(0, 10);
                Vue.prototype.$globalData.userInfo = res.data;
                next();
            }
        }).catch(e => {
            next('/login');
        });

    } else {
        next();
    }
});
//创建时，加载App.vue组件，App.vue又通过路由的方式进入index.vue
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
