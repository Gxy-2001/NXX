/*
NOTE：
vue.config.js是vue-cli3之后新增的一个功能，
这个版本里面可以配置webpack相关的属性，
在项目根目录新建vue.config.js这个文件，然后在该文件里面去写入你需要的配置等等。
 */

const webpack = require('webpack')

module.exports = {
    publicPath: './',//根路径 cli3.0以上使用
    assetsDir: 'assets',//静态资源目录(js,css,img,fonts)这些文件都可以写里面
    productionSourceMap: false,// 生产环境是否生成 sourceMap文件 什么是 source map?
//NOTE:source map 直译过来就是资源地图。source map作用是定位。source map定位时浏览器控制台输出语句在项目文件的位置。
    lintOnSave:false,//是否开启eslint检测,默认不开启
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                $: "jquery",
                jQuery: "jquery",
                "windows.jQuery": "jquery"
            })
        ]
    },
    devServer: {
        port: 8081,//指定监听请求的端口号：
    },
};
