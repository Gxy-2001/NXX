import request from '../utils/request';

/**
 * Axios实现restful风格
 * api对象定义在 main.js中
 * 封装了所有与后端通信的方法。
 *
 */
const api = {
    //登录
    userLogin(query) {
        return request({
            url: '/user/login',
            method: 'get',
            params: query
        });
    },
    //登出
    logout(query) {
        return request({
            url: '/user/logout',
            method: 'get',
            params: query
        });
    },
    //注册
    signIn(data) {
        return request({
            url: '/user/sign-in',
            method: 'post',
            data: data
        });
    },
    //获取用户信息
    getUserInfo(query) {
        return request({
            url: '/user/info',
            method: 'get',
            params: query
        });
    },
    //更新用户信息
    updateUserPublicInfo(data) {
        return request({
            url: '/user/info',
            method: 'post',
            data: data
        });
    },
    //更新用户密码
    updatePassword(query) {
        return request({
            url: '/user/password',
            method: 'get',
            params: query
        });
    },
    //添加收货地址
    addAddress(data) {
        return request({
            url: '/address/add',
            method: 'post',
            data: data
        });
    },
    //查询收货地址
    getAddress(query) {
        return request({
            url: '/address/info',
            method: 'get',
            params: query
        });
    },
    //更新收货地址
    updateAddress(data) {
        return request({
            url: '/address/update',
            method: 'post',
            data: data
        });
    },
    //删除收货地址
    deleteAddress(data) {
        return request({
            url: '/address/delete',
            method: 'post',
            data: data
        });
    },
    //添加商品
    addIdleItem(data) {
        return request({
            url: '/idle/add',
            method: 'post',
            data: data
        });
    },
    //以下方法都用于查询商品
    getIdleItem(query) {
        return request({
            url: '/idle/info',
            method: 'get',
            params: query
        });
    },
    getAllIdleItem(query) {
        return request({
            url: '/idle/all',
            method: 'get',
            params: query
        });
    },
    findIdleTiem(query) {
        return request({
            url: '/idle/find',
            method: 'get',
            params: query
        });
    },
    findIdleTiemByLable(query) {
        return request({
            url: '/idle/lable',
            method: 'get',
            params: query
        });
    },
    updateIdleItem(data) {
        return request({
            url: '/idle/update',
            method: 'post',
            data: data
        });
    },
    //添加订单
    addOrder(data) {
        return request({
            url: '/order/add',
            method: 'post',
            data: data
        });
    },
    //获取订单
    getOrder(query) {
        return request({
            url: '/order/info',
            method: 'get',
            params: query
        });
    },
    //更新订单
    updateOrder(data) {
        return request({
            url: '/order/update',
            method: 'post',
            data: data
        });
    },
    //查询属于用户的订单
    getMyOrder(query) {
        return request({
            url: '/order/my',
            method: 'get',
            params: query
        });
    },
    //查询属于用户已卖商品
    getMySoldIdle(query) {
        return request({
            url: '/order/my-sold',
            method: 'get',
            params: query
        });
    },
    //添加订单的收货地址
    addOrderAddress(data) {
        return request({
            url: '/order-address/add',
            method: 'post',
            data: data
        });
    },
    //更新订单的收货地址
    updateOrderAddress(data) {
        return request({
            url: '/order-address/update',
            method: 'post',
            data: data
        });
    },
    //获取订单的收货地址
    getOrderAddress(query) {
        return request({
            url: '/order-address/info',
            method: 'get',
            params: query
        });
    },
    //添加到收藏
    addFavorite(data) {
        return request({
            url: '/favorite/add',
            method: 'post',
            data: data
        });
    },
    //查询我的收藏
    getMyFavorite(query) {
        return request({
            url: '/favorite/my',
            method: 'get',
            params: query
        });
    },
    //删除我的收藏
    deleteFavorite(query) {
        return request({
            url: '/favorite/delete',
            method: 'get',
            params: query
        });
    },
    checkFavorite(query) {
        return request({
            url: '/favorite/check',
            method: 'get',
            params: query
        });
    },
    //发送留言
    sendMessage(data) {
        return request({
            url: '/message/send',
            method: 'post',
            data: data
        });
    },
    //获取留言
    getMessage(query) {
        return request({
            url: '/message/info',
            method: 'get',
            params: query
        });
    },
    getAllIdleMessage(query) {
        return request({
            url: '/message/idle',
            method: 'get',
            params: query
        });
    },
    getAllMyMessage(query) {
        return request({
            url: '/message/my',
            method: 'get',
            params: query
        });
    },
    //删除留言
    deleteMessage(query) {
        return request({
            url: '/message/delete',
            method: 'get',
            params: query
        });
    },
    //以下都是后台管理页面的方法
    //后台获取商品列表
    getGoods(query) {
        return request({
            url: '/admin/idleList',
            method: 'get',
            params: query
        });
    },
    //通过后台更新商品
    updateGoods(query) {
        return request({
            url: '/admin/updateIdleStatus',
            method: 'get',
            params: query
        });
    },
    //后台获取订单列表
    getOrderList(query) {
        return request({
            url: '/admin/orderList',
            method: 'get',
            params: query
        });
    },
    //后台删除订单列表
    deleteOrder(query) {
        return request({
            url: '/admin/deleteOrder',
            method: 'get',
            params: query
        });
    },
    //后台获取用户列表
    getUserData(query) {
        return request({
            url: '/admin/userList',
            method: 'get',
            params: query
        });
    },
    getUserManage(query) {
        return request({
            url: '/admin/list',
            method: 'get',
            params: query
        });
    },
    //后台更新用户状态（是否封号
    updateUserStatus(query){
        return request({
            url: '/admin/updateUserStatus',
            method: 'get',
            params: query
        });
    },
    //添加后台账号
    regAdministrator(data){
        return request({
            url: '/admin/add',
            method: 'post',
            data: data
        });
    },
    //后台登录
    adminLogin(query) {
        return request({
            url: '/admin/login',
            method: 'get',
            params: query
        });
    },
    loginOut(query) {
        return request({
            url: '/admin/loginOut',
            method: 'get',
            params: query
        });
    },
    //轮播图相关
    getCarouselListIndex(query) {
        // console.log("从首页访问轮播图");
        return request({
            url: '/admin/carousels/get',
            method: 'get',
            params: query
        });
    },

    getCarouselList(query) {
        // console.log("从admin页面访问轮播图");
        return request({
            url: '/admin/carousels/list',
            method: 'get',
            params: query
        });
    },
    deleteCarousel(query) {
        return request({
            url: '/admin/carousels/delete',
            method: 'get',
            params: query
        });
    },
    addCarousel(data) {
        return request({
            url: '/admin/carousels/save',
            method: 'post',
            data: data
        });
    },
    updateCarousel(data) {
        return request({
            url: '/admin/carousels/update',
            method: 'post',
            data: data
        });
    },
};

//在创建JavaScript模块时，export 语句用于从模块中导出实时绑定的函数、对象或原始值，以便其他程序可以通过 import 语句使用它们。
export default api;