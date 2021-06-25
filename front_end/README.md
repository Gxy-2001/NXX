完整项目介绍见https://github.com/Gxy-2001/NXX/blob/master/essay.md

### 1.前端启动：

第一步：安装IDE（我们用的是IDEA），为使用npm，还需要安装nodejs

第二步：在idea里通过npm安装各种依赖

第三步：启动

#### 	1.1 在idea的terminal输入npm install 安装各种依赖

速度慢：使用淘宝镜像，（但最好不要用cnpm，cnpm的安装路径与npm可能不太一样）

​	1.1.a 单次

```text
npm install --registry=https://registry.npm.taobao.org
```

​	1.1.b 永久替换

​	$ npm config set registry https://registry.npm.taobao.org

​	 恢复官方默认设置：npm config set registry https://registry.npmjs.org

​	检测安装成功 npm config get registry

#### 	1.2 输入npm run serve启动前端



### 2.前端工具与介绍



| 核心技术               | 简要介绍           | 说明                                                         |
| ---------------------- | ------------------ | ------------------------------------------------------------ |
| Vue                    | 核心框架           | 目前最流行的渐进式JavaScript框架。声明式渲染大大便利了开发和测试，组件机制提高了复用性。此外Vue的生态非常丰富，利于开发。 版本"vue": "^2.6.11" |
| Vue-cli                | 项目启动脚手架     | Vue command line interface，基于 Vue.js 进行快速开发的完整系统。 版本为@vue/cli 4.5.0 |
| Vue-router             | 路由管理           | Vue 官方的路由管理器。                                       |
| ElementUI              | 网页UI组件库       | 易用、美观的UI组件库，版本"element-ui": "^2.15.1"            |
| git+github             | 版本控制           | 协同开发必备                                                 |
| Axios实现restful风格   | 前后端交互         | RESTful是目前最流行的 API 设计规范,即向后端发送请求是GET/POST +宾语的结构 Axios 一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中. (promise是ES6语法提供的JavaScript对象，用来传递异步信息) |
| Babel，Webpack，jQuery | JavaScript构建工具 | Babel 将TypeScript语法编译为ES语法； Webpack：模块打包器，打包、压缩、合并等，将ES6语法转为ES5，让更多的浏览器能够支持项目 jQuery: JavaScript代码库(or JavaScript框架) |
| NPM                    |                    | 目前主流的NodeJs包管理工具，类似于后端里的maven，通过它来安装和管理各种包 |

