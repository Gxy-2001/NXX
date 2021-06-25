<!--
@modifier：FYL
-->
<template>
    <div class="header">
<!--容器-->
        <div class="header-container">

<!--logo部分-->
            <div class="app-name">
              <router-link to="/">
                <img src="../../assets/logo.png" alt="nxx" >
              </router-link>
            </div>
<!--搜索框-->
            <div class="search-container">
                  <el-input placeholder="搜索" v-model="searchValue" @keyup.enter.native="searchIdle">
                      <el-button slot="append" icon="el-icon-search" @click="searchIdle"></el-button>
                  </el-input>
            </div>
<!--几个按钮-->
            <div class="btns">
              <el-button type="primary" icon="el-icon-plus"  @click="toRelease">发布闲置</el-button>
              <el-button type="primary" icon="el-icon-chat-dot-round" v-if="IDforPage!==2" @click="toMessage">留言</el-button>
              <el-button type="primary" icon="el-icon-document" v-if="!(isLogin||IDforPage===3)" @click="toLogin">登录</el-button>
              <el-button type="primary" icon="el-icon-tickets" v-if="!(isLogin||IDforPage===4)" @click="toSignin">注册</el-button>
              <el-button type="primary" icon="el-icon-star-on" @click="toGithub">给个Star</el-button>
              <el-button type="danger" icon="el-icon-switch-button" v-if="isLogin" @click="loginOut">退出登录</el-button>

            </div>
<!--头像部分-->
            <div class="log-avatar">
              <!--<router-link v-if="!isLogin" class="user-name-text" to="/login">登录</router-link>-->
              <el-button type="info" v-if="!isLogin" icon="el-icon-user" circle @click="toLogin"></el-button>
<!--头像部分的下拉菜单-->
              <el-dropdown trigger="click" v-else>
                <div style="cursor:pointer;display: flex;align-items: center;">
                  <div style="font-size: 16px;color: #409EFF;padding-right: 5px;">{{nicknameValue?nicknameValue:nickname}}</div>
                  <el-avatar :src="avatarValue?avatarValue:avatar"></el-avatar>
                </div>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item><div @click="toMe">个人中心</div></el-dropdown-item>
                  <el-dropdown-item v-if="!(IDforPage===1)"><div @click="toMe_IdleItem">发布清单</div></el-dropdown-item>
                  <el-dropdown-item v-if="!(IDforPage===1)"><div @click="toMe_Favorites">我的收藏</div></el-dropdown-item>
                  <el-dropdown-item v-if="!(IDforPage===1)"><div @click="toMe_Order">已购</div></el-dropdown-item>
                  <el-dropdown-item v-if="!(IDforPage===1)"><div @click="toMe_Sold">已卖</div></el-dropdown-item>
                  <el-dropdown-item v-if="!(IDforPage===2)"><div @click="toMessage">留言</div></el-dropdown-item>
                  <el-dropdown-item divided style="color: red;"><div @click="loginOut">退出登录</div></el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        name: 'Header',
        props: ['searchInput','nicknameValue','avatarValue'],
        data() {
            return {
                searchValue: this.searchInput,
                nickname:'登录',
                //默认头像，感觉可以换一换，有点小丑
                //https://img-host-service.oss-cn-shanghai.aliyuncs.com/avatar.jpg?versionId=CAEQIRiBgID784CFyxciIDk4N2U1M2I1NDBlNDRhOGJhOGIwOWVmNzgyMGRmZTYy
                avatar:'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
                isLogin:false,
              IDforPage:0,
              // nowPATH:''
            };
        },
        created(){
            // console.log("header");
            if(! this.$globalData.userInfo.nickname){
                this.$api.getUserInfo().then(res=>{
                    console.log('Header getUserInfo:',res);
                    if(res.status_code===1){
                        this.nickname=res.data.nickname;
                        this.avatar=res.data.avatar;
                        res.data.signInTime=res.data.signInTime.substring(0,10);
                        this.$globalData.userInfo=res.data;
                        this.isLogin=true;
                    }
                })
            }else {
                this.nickname=this.$globalData.userInfo.nickname;
                this.avatar=this.$globalData.userInfo.avatar;
                this.isLogin=true;
            }
            this.hideBtn()
        },

      //方法：第一个是根据页面隐藏按钮，第二个是搜索框控制，第三个是退出登录，其他都是跳转；
        methods: {
          //让 apphead 在不同页面隐藏一些按钮
          hideBtn(){
            let h=location.href;
            let table=[
              {id:0,pattern:''},//其他页面都显示
              {id:1,pattern:'/#/me(?!s)'},//个人中心页面
              {id:2,pattern:'/#/message'},//留言界面
              {id:3,pattern:'/#/login'},//正常登录界面
              {id:4,pattern:'/#/sign-in'},//正常注册界面
            ]
            for (let i = 1; i <table.length ; i++) {
              if(h.search(table[i].pattern)!==-1){
                this.IDforPage=table[i].id;
                break;
              }
              this.IDforPage=0;
            }
            console.log(this.IDforPage);
          },

          searchIdle() {
                if ('/search' !== this.$route.path) {
                    this.$router.push({path: '/search', query: {searchValue: this.searchValue}});
                } else {
                    this.$router.replace({path: '/search', query: {searchValue: this.searchValue}});
                    this.$router.go(0);
                }
          },
          loginOut(){
            this.$api.logout().then(res=>{
              if(res.status_code===1){
                this.$globalData.userInfo={};
                console.log("login out");
                if ('/index' === this.$route.path) {
                  this.$router.go(0);
                }else {
                  this.$router.push({path: '/index'});
                }
              }else {
                this.$message.error('网络或系统异常，退出登录失败！');
              }
            });

          },
            //以下方法都是各种跳转
          toMe() {
            if ('/me' !== this.$route.path) {
              this.$router.push({path: '/me'});
            }
            this.hideBtn();
          },
          toMe_IdleItem() {
            if ('/me' !== this.$route.path) {
              this.$router.push({path: '/me',query:{activeName:'1'}});
            }
            this.hideBtn();
          },
          toMe_Favorites() {
            if ('/me' !== this.$route.path) {
              this.$router.push({path: '/me',query:{activeName:'3'}});
            }
            this.hideBtn();
          },
          toMe_Sold() {
            if ('/me' !== this.$route.path) {
              this.$router.push({path: '/me',query:{activeName:'4'}});
            }
            this.hideBtn();
          },
        toMe_Order() {
            //todo
            // if(nowURL)
            if ('/me' !== this.$route.path) {
              this.$router.push({path: '/me',query:{activeName:'5'}});
            }
          this.hideBtn();

        },
          toMessage(){
            if ('/message' !== this.$route.path) {
              this.$router.push({path: '/message'});
            }
            this.hideBtn();
          },
          toRelease(){
            if ('/release' !== this.$route.path) {
              this.$router.push({path: '/release'});
            }
            this.hideBtn();
          },
          toLogin(){
            this.$router.replace({path: '/login'});
            this.hideBtn();
          },
          toGithub(){
            window.location.href = "https://github.com/Gxy-2001/NXX";
          },
          toSignin(){
            this.$router.replace({path: '/sign-in'});
            this.hideBtn();
          },
        }
    };
</script>

<!--  CSS -->
<style scoped>
    .header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        width: 100%;
        height: 58px;
        background: #ffffff;
        display: flex;
        justify-content: center;
        border-bottom: #eeeeee solid 2px;
      margin-bottom: 20px;
        z-index: 1000;
    }

    .header-container {
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: space-around;
    }

    /*header容器下的所有子div*/
    /*.header-container div {*/
    /*  flex: 1;*/
    /*}*/


    .app-name a{
      /*color: #0072ea;*/
      font-size: 24px;
      text-decoration: none;
    }
    /*小字部分*/
    /*span{*/
    /*  font-family: 楷体;*/
    /*  font-size: 6px;*/
    /*}*/

    .search-container {
      width: 25%;
      border-radius: 5px;
    }

    .btns{
      width: auto;
      display: flex;
    }

    .log-avatar{
      display: flex;
      align-items: center;
    }

</style>
