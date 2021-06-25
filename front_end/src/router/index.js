import Vue from 'vue';
import Router from 'vue-router';

/*
vue-router重写push和replace方法，解决相同路径跳转报错
NOTE：见 https://www.jianshu.com/p/725c4db3a4f7
 */
//重写 router的replace方法
const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err);
};
//重写push方法
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

//启用vue-Router
Vue.use(Router);

/*在vue中内容都是组件化的，只要把路径和组件对应起来，然后在页面中把组件渲染出来，就可以实现页面内容的显示与隐藏
路由中有三个基本的概念 route, routes, router。
1， route，一条路由，如 Home按钮  => home内容，或者 about按钮 => about 内容。
2， routes 一组路由，把上面的路由组合起来 [{home 按钮 =>home内容 }， { about按钮 => about 内容}]
3， router 管理路由。routes定义了一组路由，但它是静止的，真正来了请求
比如当用户点击home 按钮的时候，router 就起作用了，它到routes 中去查找并返回对应的 home 内容。
 */
export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },// '/'重定向至首页
        {
            path: '/index',
            component: () => import('../components/page/index.vue'),
            meta: { title: '南小咸' }
        },//path:相对路径，meta：描述
        {
            path: '/search',
            component: () => import('../components/page/search.vue'),
            meta: { title: '闲置搜索 | 南小咸' }
        },
        {
            path: '/me',
            component: () => import('../components/page/me.vue'),
            meta: { title: '个人中心 | 南小咸' }
        },
        {
            path: '/message',
            component: () => import('../components/page/message.vue'),
            meta: { title: '消息 | 南小咸' }
        },
        {
            path: '/release',
            component: () => import('../components/page/release.vue'),
            meta: { title: '发布闲置 | 南小咸' }
        },
        {
            path: '/details',
            component: () => import('../components/page/idle-details.vue'),
            meta: { title: '闲置详情 | 南小咸' }
        },
        {
            path: '/order',
            component: () => import('../components/page/order.vue'),
            meta: { title: '订单详情 | 南小咸' }
        },
        {
            path: '/login',
            component: () => import('../components/page/login.vue'),
            meta: { title: '登录 | 南小咸' }
        },
        {
            path: '/sign-in',
            component: () => import('../components/page/sign-in.vue'),
            meta: { title: '注册 | 南小咸' }
        },
        {
            path: '/login-admin',
            component: () => import('../components/page/login-admin.vue'),
            meta: { title: '后台登陆' }
        },
        {
            path: '/platform-admin',
            component: () => import('../components/page/platform-admin.vue'),
            meta: { title: '后台管理' }
        },
        {
            path: '*',
            redirect: '/'
        },//重定向至首页
    ]
});
