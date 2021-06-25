<template>
  <div>
<!--ElementUI提供的轮播图-->
    <el-carousel :interval="3000" arrow="hover" indicator-position="outside" height="450px" >
<!--      暂时方案。静态-->
<!--      <el-carousel-item v-for="item in imgBox" :key="i">-->
<!--        <img :src="imgBox[item.id].src" class="image" alt="加载失败">-->
<!--      </el-carousel-item>-->
      <!--        成熟方案-->
        <el-carousel-item v-for="(url,i) in srcList" :key="i">
        <img :src="url" class="image" alt="加载失败">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "",
  components: {
  },

  data() {
    return {
      total:0,
      srcList:[],
      nowPage:1,
      carouselList:[],
      // imgBox:[
      //   {id:0,src:'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/1.png?versionId=CAEQIRiBgICRseeAzBciIGZiNmMwNjNmYWQ3NTQ5ZmM5MDBiZmMxYTU3YzMxY2Rj'},
      //   {id:1,src:'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/2.png?versionId=CAEQIRiBgMCIseeAzBciIDMyNDdjZDllNmU2ZDRkMDc5NjliZDQ2NmZlNjViYmUx',
      //   },
      //   {id:2,src:'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/3.png?versionId=CAEQIRiBgMCRseeAzBciIGM4YjNjNzhmMThhZDQwMjU5NzUwY2Y4YWI0ODU4NjEz',
      //   },
      //   {id:3,src:'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/4.png?versionId=CAEQIRiBgICJseeAzBciIDc5YTM5YThlZGViODRiMmY4NWM2OGQ4Mzc5ZDk0OGQz',
      //   },
      // ]
    };
  },
  created() {
    this.getCarousel();
  },
  watch:{},
  methods: {
    getCarousel(){
      this.$api.getCarouselListIndex({
        page: this.nowPage,
        nums:8
      }).then(res => {
        if(res.status_code==1){
          this.carouselList = res.data.list;
          this.total = res.data.count;
          //console.log是debug用
          console.log("carousel");
          console.log(this.carouselList);

          this.srcList=this.carouselList.map(value=>value['carouselUrl']);
          console.log("srcList");
          console.log(this.srcList);
        }else {
          this.$message.error(res.msg)
        }
      }).catch(e => {
        console.log(e)
      })
    },
},
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>