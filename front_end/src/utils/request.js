import axios from 'axios';
/*
 * Axios实现前后端交互
 */

//创建一个通用配置的axios实例
const service = axios.create({
    timeout: 5000,//5s超时
    baseURL:  'http://localhost:8090',//
    withCredentials:  true // withCredentials:  true 设置cross跨域 并设置访问权限 允许跨域携带cookie信息
});
// http request 请求拦截器
service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);
// http response 响应拦截器
service.interceptors.response.use(
    response => {
        if (response.status === 200) {//200 成功
            return response.data;
        } else {
            Promise.reject();
        }
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);
//从模块中导出实时绑定的函数、对象或原始值，以便其他程序可以通过 import 语句使用它们
//真像C/C++
export default service;
