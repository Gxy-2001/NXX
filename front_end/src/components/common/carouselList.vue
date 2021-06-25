<template>
    <div class="main-border">
      <div class="operations">

        <el-button class="button" type="primary" icon="el-icon-plus" @click="addFormInit();addFormVisible=true">新增</el-button>
        <el-dialog title="新增图片" :visible.sync="addFormVisible" class="dialog">
          <el-form :model="form">
            <el-form-item label="图片ID" :label-width="LabelWidth">
              <el-input v-model="form.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="图片地址" :label-width="LabelWidth">
              <el-input v-model="form.url" aria-required="true"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer">
            <el-button @click="addFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addFormVisible = false;addCarousel(form)">确 定</el-button>
          </div>
        </el-dialog>

<!--批量删除功能，与后端交流有误，未实现-->
<!--        <el-button class="button" type="danger" icon="el-icon-minus" @click="deleteFormVisible=true">批量删除</el-button>-->
<!--        <el-dialog title="删除图片" :visible.sync="deleteFormVisible" class="dialog">-->
<!--          <el-form :model="form" >-->
<!--            <el-form-item label="图片ID" :label-width="idLabelWidth">-->
<!--              <el-input v-model="form.id" autocomplete="off"></el-input>-->
<!--            </el-form-item>-->
<!--          </el-form>-->
<!--          <div slot="footer">-->
<!--            <el-button @click="deleteFormVisible = false">取 消</el-button>-->
<!--            <el-button type="primary" @click="deleteFormVisible = false">确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->
      </div>
      <hr>

        <el-table
                :data="carouselList"
                stripe
                style="width: 100%;color: #5a5c61;">
            <el-table-column
                    prop="carouselId"
                    width="80px"
                    label="轮播图ID"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="carouselUrl"
                    label="图片地址"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="上传时间"
                    show-overflow-tooltip>
            </el-table-column>
          <el-table-column
              label="更新时间"
              prop="updateTime"
              show-overflow-tooltip>
          </el-table-column>
          <el-table-column
              label="图像预览"
              width="100">
            <template slot-scope="scope">
              <el-image
                  :src="srcList[scope.$index]"
                  :preview-src-list="srcList"
              >
              </el-image>
            </template>
          </el-table-column>

            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="success"
                            @click="updateFormVisible=true;updateFormInit(scope.$index)">编辑</el-button>
                  <!--编辑图片地址-->
                  <el-dialog title="编辑信息" :visible.sync="updateFormVisible" >
                    <el-form :model="form">
                      <el-form-item label="图片ID" :label-width="LabelWidth">
                        <el-input v-model="form.id" disabled></el-input>
                      </el-form-item>

                      <el-form-item label="当前图片地址" :label-width="LabelWidth">
                        <el-input aria-placeholder="" v-model="form.url" disabled ></el-input>
                      </el-form-item>

<!--                      <el-form-item label="新地址" :label-width="LabelWidth">-->
<!--                        <el-input aria-placeholder="" v-model="form.newUrl"></el-input>-->
<!--                      </el-form-item>-->

                      <el-upload class="carousel_upLoad"
                          action="http://localhost:8090/file"
                          :on-preview="fileHandlePreview"
                          :on-remove="fileHandleRemove"
                          :on-success="fileHandleSuccess"
                          :limit="1"
                          :on-exceed="handleExceed"
                          accept="image/*">
                        <!--:show-file-list="showFileList"-->

                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text"><em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传图片文件，且不超过500kb(ElementUI2特性)</div>
                      </el-upload>
<!--跟后端交流有问题-->
<!--                      <el-form-item label="上传照片">-->
<!--                        <el-upload-->
<!--                            action="http://localhost:8090/file"-->
<!--                            :on-preview="fileHandlePreview"-->
<!--                            :on-remove="fileHandleRemove"-->
<!--                            :on-success="fileHandleSuccess"-->
<!--                            :limit="1"-->
<!--                            :on-exceed="handleExceed"-->
<!--                            accept="image/*"-->
<!--                            drag-->
<!--                            multiple>-->
<!--                          <i class="el-icon-upload"></i>-->
<!--                          <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>-->
<!--                          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb(ElementUI2特性)</div>-->
<!--                        </el-upload>-->
<!--                      </el-form-item>-->
                    </el-form>
                    <!-- 上传图片-->


                    <div slot="footer">
                      <el-button  @click="updateFormVisible = false">取 消</el-button>
                      <el-button type="primary"  @click="updateFormVisible = false;updateCarousel(form)">确 定</el-button>
                    </div>
                  </el-dialog>

                  <el-button
                            size="mini"
                            type="danger"
                            @click="deleteCarousel(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--分页-->
        <div class="block">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page.sync="nowPage"
                    :page-size="8"
                    background
                    layout="prev, pager, next,jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "carouselList",
      data(){
        return {
          nowPage: 1,
          total: 0,
          addFormVisible:false,
          updateFormVisible: false,

          form: {
            id: '',
            url:'',
            newUrl:'',
            time:'',
            clear(){
              this.id=''
              this.url=''
              this.newUrl=''
              this.time=''
            }
          },

          LabelWidth:'120px',
          carouselList: [],
          srcList:[],
        }
      },
      created() {
        this.getCarousel();
      },
        methods:{
            //查询所有轮播图
          getCarousel(){
            this.$api.getCarouselList({
              page: this.nowPage,
              nums:8
            }).then(res => {
              if(res.status_code==1){
                this.carouselList = res.data.list;
                this.total = res.data.count;
                // console是debug用
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

            //删除轮播图
          deleteCarousel(index){
                this.$api.deleteCarousel({
                  carouselId:this.carouselList[index].carouselId
                }).then(res=>{
                    if(res.status_code==1){
                        this.getCarousel();
                    }else {
                        this.$message.error(res.msg)
                    }

                }).catch(e => {
                    console.log(e)
                })
            },

          //编辑按钮对应的表单初始化
          updateFormInit(id){
            this.form.clear();
            this.form.id=id;
            this.form.url=this.srcList[id];
            let d=new Date();
            this.form.time=d.toJSON().replace("T"," ").replace("Z","");
            //debug
            console.log("id:"+id);
            console.log("url:"+this.form.url);
            console.log("date:"+this.form.time);
          },

          //新建按钮对应的表单初始化
          addFormInit(){
            this.form.clear();
            this.form.id=this.srcList.length;
              let d=new Date();
              this.form.time=d.toJSON().replace("T"," ").replace("Z","");
              //debug
              console.log("id:"+this.form.id);
              console.log("date:"+this.form.time);
          },

            //更新轮播图
          updateCarousel(form){
                this.$api.updateCarousel({
                  carouselId:form.id,
                  carouselUrl:form.newUrl,
                  updateTime:form.time
                }).then(res=>{
                    if(res.status_code==1){
                        this.getCarousel();
                    }else {
                      this.$message.error(res.msg);
                    }

                }).catch(e => {
                    console.log(e);
                })
            },
            //新增
            //https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png
            //todo
          addCarousel(form){
            console.log(form);
            this.$api.addCarousel({
              carouselId:form.id,
              carouselUrl:form.url,
              redirectUrl:form.url,
              updateTime:form.time,
              createTime:form.time,
              carouselRank:'0',
              isDeleted:'0',
              createUser:'0',
              updateUser:'0',
            }).then(res=>{
              if(res.status_code==1){
                this.getCarousel();
              }else {
                this.$message.error(res.msg);
              }

            }).catch(e => {
              console.log(e);
            })
          },

            //处理页面
            handleCurrentChange(val) {
                this.nowPage = val;
              this.getCarousel();
            },

          //todo
          //文件已上传的钩子
          fileHandlePreview(file) {
            console.log(file);
            this.dialogImageUrl=file.response.data;
            this.imgDialogVisible=true;
          },
          //文件移除
          fileHandleRemove(file, fileList) {
            console.log(file, fileList);
          },
          //文件成功上传
          fileHandleSuccess(response, file, fileList){
            console.log("fileHandleSuccess",response.data)
            console.log("lastIndex",response.data.substr(response.data.lastIndexOf("file")))
            let url = "http://localhost:8090/img/".concat(response.data.substr(response.data.lastIndexOf("file")))
            console.log("file:",response,file,fileList);
            this.form.newUrl = url
          },
          //超出限制时
          handleExceed(files, fileList) {
            this.$message.warning(`只能上传1张图片，这是轮播图!`);
          },
        },

    }
</script>

<style scoped>
/*容器*/
    .main-border{
        background-color: #FFF;
        padding: 10px 30px;
        box-shadow: 0 1px 15px -6px rgba(0,0,0,.5);
        border-radius: 5px;
    }
/*表头排列*/
    el-table{
      display: flex;
      justify-content: space-between;
    }
    .block {
        display: flex;
        justify-content:center;
        padding-top: 15px;
        padding-bottom: 10px;
        width: 100%;
    }
/*两个按钮*/
    .operations{
      width: 25%;
      display: flex;
      justify-content: start;
    }
    .dialog{
      width:60%;
    }
    .carousel_upLoad{
      position: absolute;
      left: 200px;
    }
</style>
