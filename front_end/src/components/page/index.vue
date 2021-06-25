<template>
    <div>
      <div>
        <!--AppHeader组件-->
        <app-head></app-head>

        <app-body>
          <div style="min-height: 200vh;" class="container">
            <!--轮播图-->
            <div class="carousel">
              <carousel></carousel>
              <hr>
            </div>
            <!--一些为了好看的花哨图片，图片素材来自淘宝-->
            <div class="ad">
              <div><img src="../../assets/ads/1.png" style="object-fit:scale-down"></div>
              <div><img src="../../assets/ads/2.png" style="object-fit: scale-down"></div>
              <div><img src="../../assets/ads/3.png" style="object-fit: scale-down"></div>
            </div>

            <!--   el标签页         -->
            <el-tabs v-model="labelName" @tab-click="handleClick">
<!--              <el-button type="primary" class="el-button" icon="el-icon-arrow-down" round name="1">二手书</el-button>-->
<!--              <el-button type="primary" class="el-button" icon="el-icon-arrow-down" round name="2">数码</el-button>-->
<!--              <el-button type="primary" class="el-button" icon="el-icon-arrow-down" round name="3">零食</el-button>-->
<!--              <el-button type="primary" class="el-button" icon="el-icon-arrow-down" round name="4">活动票</el-button>-->
              <el-tab-pane name="0">
                <el-button slot="label" type="primary" class="el-button" icon="el-icon-arrow-down" round name="0">全部</el-button>
              </el-tab-pane>
              <el-tab-pane name="1">
                <el-button slot="label" type="primary" class="el-button" icon="el-icon-arrow-down" round name="1">图书</el-button>
              </el-tab-pane>
              <el-tab-pane name="2">
                <el-button slot="label" type="primary" class="el-button" icon="el-icon-arrow-down" round name="2">数码</el-button>
              </el-tab-pane>
              <el-tab-pane name="3">
                <el-button slot="label" type="primary" class="el-button" icon="el-icon-arrow-down" round name="3">零食</el-button>
              </el-tab-pane>
              <el-tab-pane name="4">
                <el-button slot="label" type="primary" class="el-button" icon="el-icon-arrow-down" round name="4">活动票</el-button>
              </el-tab-pane>
            </el-tabs>

            <!--首页商品列表-->
            <div style="margin: 0 20px;">
              <el-row :gutter="30">
                <el-col :span="6" v-for="(idle,index) in idleList" :key="index">
                  <div class="idle-card" @click="toDetails(idle)">
                    <el-image
                        style="width: 100%; height: 160px"
                        :src="idle.imgUrl"
                        fit="contain">
                      <div slot="error" class="image-slot">
                        <i class="el-icon-picture-outline">无图</i>
                      </div>
                    </el-image>
                    <div class="idle-title">
                      {{idle.idleName}}
                    </div>
                    <el-row style="margin: 5px 10px;">
                      <el-col :span="12">
                        <div class="idle-prive">￥{{idle.idlePrice}}</div>
                      </el-col>
                      <el-col :span="12">
                        <div class="idle-place">{{idle.idlePlace}}</div>
                      </el-col>
                    </el-row>
                    <div class="idle-time">{{idle.timeStr}}</div>
                    <div class="user-info">
                      <el-image
                          style="width: 30px; height: 30px"
                          :src="idle.user.avatar"
                          fit="contain">
                        <div slot="error" class="image-slot">
                          <i class="el-icon-picture-outline">无图</i>
                        </div>
                      </el-image>
                      <div class="user-nickname">{{idle.user.nickname}}</div>
                    </div>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </app-body>

        <!--翻页-->
        <div class="fenye">
          <el-pagination
              background
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :page-size="8"
              layout="prev, pager, next, jumper"
              :total="totalItem">
          </el-pagination>
        </div>
        <!--AppFoot组件-->
        <app-foot></app-foot>
      </div>
    </div>
</template>

<script>
    import AppHead from '../common/AppHeader.vue';
    import AppBody from '../common/AppPageBody.vue'
    import AppFoot from '../common/AppFoot.vue'
    import Carousel from '../common/Carousel.vue'

    export default {
        name: "index",
        //引用组件
        components: {
            AppHead,
            AppBody,
            AppFoot,
            Carousel,
        },
        //成员变量初始化
        data() {
            return {
                labelName: '0',
                idleList: [],
                currentPage: 1,
                totalItem:1
            };
        },
        //进入网页后加载一次数据
        created() {
            this.findIdleTiem(1)
        },
        //翻页的监听
        watch:{
            $route(to,from){
                this.labelName=to.query.labelName;
                let val=parseInt(to.query.page)?parseInt(to.query.page):1;
                // let totalPage=parseInt(this.totalItem/8)+1;
                // val=parseInt(val%totalPage);
                // val=val===0?totalPage:val;
                this.currentPage=parseInt(to.query.page)?parseInt(to.query.page):1;
                this.findIdleTiem(val);
            }
        },

        methods: {
            //加载商品信息
            findIdleTiem(page){
                const loading = this.$loading({
                    lock: true,
                    text: '加载数据中',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0)'
                });
                if(this.labelName>0){
                    this.$api.findIdleTiemByLable({
                        idleLabel:this.labelName,
                        page: page,
                        nums: 8
                    }).then(res => {
                        console.log(res);
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem=res.data.count;
                        console.log(this.totalItem);
                    }).catch(e => {
                        console.log(e)
                    }).finally(()=>{
                        loading.close();
                    })
                }else{
                    this.$api.findIdleTiem({
                        page: page,
                        nums: 8
                    }).then(res => {
                        console.log(res);
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem=res.data.count;
                        console.log(this.totalItem);
                    }).catch(e => {
                        console.log(e)
                    }).finally(()=>{
                        loading.close();
                    })
                }
            },
            //切换商品种类
            handleClick(tab, event) {
                // console.log(tab,event);
                console.log(this.labelName);
                this.$router.replace({query: {page: 1,labelName:this.labelName}});
            },
            //
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.$router.replace({query: {page: val,labelName:this.labelName}});
            },
            //查看详细商品信息
            toDetails(idle) {
                this.$router.push({path: '/details', query: {id: idle.id}});
            }
        }
    }
</script>

<style scoped>
/*
flex弹性布局
*/
    .idle-card {
        height: 300px;
        border: #eeeeee solid 1px;
        margin-bottom: 15px;
        cursor: pointer;
    }

    .fenye {
        display: flex;
        justify-content: center;
      margin-top: 10px;
      margin-bottom: 10px;
        height: 60px;
    }

    .idle-title {
        font-size: 18px;
        font-weight: 600;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin: 10px;
    }

    .idle-prive {
        font-size: 16px;
        color: red;
    }

    .idle-place {
        font-size: 13px;
        color: #666666;
        float: right;
        padding-right: 20px;

    }

    .idle-time {
        color: #666666;
        font-size: 12px;
        margin: 0 10px;
    }

    .user-nickname {
        color: #999999;
        font-size: 12px;
        display: flex;
        align-items: center;
        height: 30px;
        padding-left: 10px;
    }

    .user-info {
        padding: 5px 10px;
        height: 30px;
        display: flex;
    }

    .carousel{
      height:500px;
      max-width: 80%;
      margin-top: 20px;
      margin-bottom: 20px;
      margin-left: 10%;
      margin-right: 10%;
    }

    .ad{
      display: flex;
      justify-content: space-around;
      height: 200px;
      max-width: 100%;
    }
    .ad div{
      width:30%;
      overflow: hidden;
      border-radius: 10px;
    }
    /*.el-button{*/
    /*  border-bottom: 5px;*/
    /*}*/
    /*按住按钮*/
    .el-button:active{
      background: #67C23A;
    }
</style>
