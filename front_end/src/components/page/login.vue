<template>
  <div>
    <app-head></app-head>
      <div class="login-container">
        <el-card class="box-card">
          <div class="login-body">
            <div class="login-title" @click="toIndex">
              <img src="../../assets/logo.png" alt="nxx" >
              <br>登录
            </div>
            <el-form ref="form" :model="userForm">
              <el-input placeholder="请输入手机号..." v-model="userForm.accountNumber" class="login-input">
                <template slot="prepend">
                  <div class="el-icon-user-solid"></div>
                </template>
              </el-input>
              <el-input placeholder="请输入密码..." v-model="userForm.userPassword" class="login-input"
                        @keyup.enter.native="login"  show-password>
                <template slot="prepend">
                  <div class="el-icon-lock"></div>
                </template>
              </el-input>
              <div class="login-submit">
                <el-button type="primary" @click="login">登录</el-button>
              </div>
              <div class="other-submit">
                <router-link to="/sign-in" class="sign-in-text">注册</router-link>
                <router-link to="/login-admin" class="sign-in-text">后台登录</router-link>
              </div>
            </el-form>
          </div>
        </el-card>
      </div>
    <app-foot></app-foot>
  </div>
</template>

<script>
import AppHead from '../common/AppHeader.vue';
import AppFoot from '../common/AppFoot.vue'

    export default {
        name: "login",
      components:{
        AppHead,
        AppFoot
      },
      //默认登录密码，实际使用时要改一改
        data() {
            return {
                userForm: {
                    accountNumber: '111111',
                    userPassword: '123456'
                }
            };
        },

        methods: {
            login() {
                this.$api.userLogin({
                    accountNumber: this.userForm.accountNumber,
                    userPassword: this.userForm.userPassword
                }).then(res => {
                    console.log(res);
                    if (res.status_code === 1) {
                        res.data.signInTime=res.data.signInTime.substring(0,10);
                        this.$globalData.userInfo = res.data;
                        this.$router.replace({path: '/index'});
                    } else {
                        this.$message.error(res.msg);
                    }
                });
            },
            toIndex() {
                this.$router.replace({path: '/index'});
            }
        }
    }
</script>

<style scoped>
    .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        width: 100%;
        background-color: #f1f1f1;
    }

    .login-body {
        padding: 30px;
        width: 400px;
        height: 100%;
    }

    .login-title {
        padding-bottom: 30px;
        text-align: center;
        font-weight: 600;
        font-size: 20px;
        color: #409EFF;
        cursor: pointer;
    }

    .login-input {
        margin-bottom: 20px;
    }

    .login-submit {
        display: flex;
        justify-content: center;
    }

    .sign-in-container {
        padding: 0 10px;
    }

    .sign-in-text {
        color: #409EFF;
        font-size: 16px;
        text-decoration: none;
        line-height:28px;
    }
    .other-submit{
        display:flex;
        justify-content: space-between;
        margin-top: 10px;
    }
</style>
