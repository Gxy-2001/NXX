<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="release-idle-container">
                <div class="release-idle-container-title">发布闲置</div>
                <div class="release-idle-container-form">
                    <el-input placeholder="请输入闲置名称" v-model="idleItemInfo.idleName"
                              maxlength="30"
                              show-word-limit>
                    </el-input>
                    <el-input
                            class="release-idle-detiles-text"
                            type="textarea"
                            autosize
                            placeholder="请输入闲置的详细介绍..."
                            v-model="idleItemInfo.idleDetails"
                            maxlength="1000"
                            show-word-limit>
                    </el-input>
                    <div class="release-idle-place">
                        <div class="release-tip">您的地区</div>
                        <el-cascader
                                :options="options"
                                v-model="selectedOptions"
                                @change="handleChange"
                                :separator="' '"
                                style="width: 90%;"
                        >
                        </el-cascader>
                    </div>
                    <div style="display: flex; justify-content: space-between;">
                        <div>
                          <div class="release-tip">闲置类别</div>
                            <el-select  v-model="idleItemInfo.idleLabel" placeholder="请选择类别">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
<!--                        <div style="width: 300px;">-->
                        <div>
                          <div class="release-tip">价格</div>
                          <el-input-number v-model="idleItemInfo.idlePrice" :precision="2" :step="10" :max="10000000">
                                <div slot="prepend">价格</div>
                            </el-input-number>
                        </div>
                    </div>

                    <!--联系方式-->
                    <div>
                        <el-input
                                class="release-idle-phoneNumber"
                                placeholder="请输入联系方式"
                                v-model="idleItemInfo.idlePhone"
                                show-word-limit>
                        </el-input>
                    </div>
                    <!-- 上传图片-->
                    <div class="release-idle-container-picture">
                        <div class="release-idle-container-picture-title">上传闲置照片</div>
                        <el-upload
                                action="http://localhost:8090/file"
                                :on-preview="fileHandlePreview"
                                :on-remove="fileHandleRemove"
                                :on-success="fileHandleSuccess"
                                :limit="10"
                                :on-exceed="handleExceed"
                                accept="image/*"
                                drag
                                multiple>
                          <!--:show-file-list="showFileList"-->

                          <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                          <div class="el-upload__tip" slot="tip">只能上传图片文件，且不超过500kb(ElementUI2特性)</div>
                        </el-upload>
                        <div class="picture-list">
                            <el-image style="width: 600px;margin-bottom: 2px;" fit="contain"
                                      v-for="(img,index) in imgList" :src="img" :key="index"
                                      :preview-src-list="imgList"></el-image>
                        </div>
<!--                        <div class="picture-list">-->
<!--                            <el-image style="width: 600px;margin-bottom: 2px;"-->
<!--                                      fit="contain"-->
<!--                                      v-for="(img) in imgList"-->
<!--                                      :src="img"-->
<!--                                      :preview-src-list="imgList">-->

<!--                            </el-image>-->
<!--                        </div>-->
                        <el-dialog :visible.sync="imgDialogVisible">
                            <img width="100%" :src="dialogImageUrl" alt="">
                        </el-dialog>
                    </div>
                    <div style="display: flex;justify-content: center;margin-top: 30px;margin-bottom: 30px;">
                        <el-button type="primary" plain @click="releaseButton">确认发布</el-button>
                    </div>
                </div>
            </div>
        </app-body>
      <app-foot></app-foot>

    </div>
</template>

<script>
    import AppHead from '../common/AppHeader.vue';
    import AppBody from '../common/AppPageBody.vue'
    import AppFoot from '../common/AppFoot.vue'
    import options from '../common/country-data.js'

    export default {
        name: "release",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                imgDialogVisible:false,
                dialogImageUrl:'',
                // showFileList:true,
                options:options,
                selectedOptions:[],
                options2: [{
                    value: 1,
                    label: '图书'
                }, {
                    value: 2,
                    label: '数码'
                }, {
                    value: 3,
                    label: '零食'
                }, {
                    value: 4,
                    label: '活动票'
                }, {
                    value: 5,
                    label: '其他'
                }],
                imgList:[],
                idleItemInfo:{
                    idleName:'',
                    idleDetails:'',
                    pictureList:'',
                    idlePrice:0,
                    idlePlace:'',
                    idleLabel:'',
                    idlePhone:''
                }
            };
        },
        methods: {
            handleChange(value) {
                console.log(value);
                this.idleItemInfo.idlePlace=value[1];
            },
            //文件移除时
            fileHandleRemove(file, fileList) {
                console.log(file, fileList);
                for(let i=0;i<this.imgList.length;i++){
                    if(this.imgList[i]===file.response.data){
                        this.imgList.splice(i,1);
                    }
                }
            },
            //点击已上传的文件时
            fileHandlePreview(file) {
                console.log(file);
                this.dialogImageUrl=file.response.data;
                this.imgDialogVisible=true;
            },
            //文件成功上传时
            fileHandleSuccess(response, file, fileList){
                //response.data = "http://localhost:8090/img/".concat(response.data.substr(response.data.length - 17))
              console.log("fileHandleSuccess",response.data)
              console.log("lastIndex",response.data.substr(response.data.lastIndexOf("file")))
              response.data = "http://localhost:8090/img/".concat(response.data.substr(response.data.lastIndexOf("file")))
                console.log("file:",response,file,fileList);
                this.imgList.push(response.data);
            },
            releaseButton(){
                this.idleItemInfo.pictureList=JSON.stringify(this.imgList);
                console.log(this.idleItemInfo);
                this.isPhone();
                if(this.idleItemInfo.idlePhone == ""){
                  return;
                }
                if(this.idleItemInfo.idleName&&
                    this.idleItemInfo.idleDetails&&
                    this.idleItemInfo.idlePlace&&
                    this.idleItemInfo.idleLabel&&

                    this.idleItemInfo.idlePrice&&
                    this.idleItemInfo.idlePhone){

                    this.$api.addIdleItem(this.idleItemInfo).then(res=>{
                        if (res.status_code === 1) {
                            this.$message({
                                message: '发布成功！',
                                type: 'success'
                            });
                            console.log(res.data);
                            this.$router.replace({path: '/details', query: {id: res.data.id}});
                        } else {
                            this.$message.error('发布失败！'+res.msg);
                        }
                    }).catch(e=>{
                        this.$message.error('请填写完整信息:尝试添加了，但是添加失败');
                    })
                }else {
                    this.$message.error('请填写完整信息！');
                }

            },
            //超过10张图片限制
            handleExceed(files, fileList) {
                this.$message.warning(`限制10张图片，本次选择了 ${files.length} 张图，共选择了 ${files.length + fileList.length} 张图`);
            },

            //检查号码
            isPhone(){
                const reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
                if(this.idleItemInfo.idlePhone.length === 11){
                    if(!reg.test(this.idleItemInfo.idlePhone)){
                        this.idleItemInfo.idlePhone = "";
                        this.$message.error('请填写正确的号码');
                        console.log('请输入正确的号码');
                    }else {

                        console.log('电话号码输入正确！');
                    }
                }else {
                    this.idleItemInfo.idlePhone = "";
                    this.$message.error('请填写正确的号码');
                    console.log('请输入正确的号码');
                }


            },
        }
    }
</script>

<style scoped>
    .release-idle-container {
        min-height: 85vh;
    }

    .release-idle-container-title {
        font-size: 18px;
        padding: 30px 0;
        font-weight: 600;
        width: 100%;
        text-align: center;
    }

    .release-idle-container-form {
        padding: 0 180px;
    }

    .release-idle-detiles-text {
        margin: 20px 0;
    }
    .release-idle-place{
        margin-bottom: 15px;
    }
    .release-tip{
        color: #555555;
        float: left;
        padding-right: 5px;
        height: 36px;
        line-height: 36px;
        font-size: 14px;
    }
    .release-idle-container-picture{
        margin: 20px 0;

    }
    .release-idle-container-picture-title{
        margin: 10px 0;
        color: #555555;
        font-size: 14px;
    }
    .picture-list {
        margin: 20px 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .release-idle-phoneNumber{
        margin: 20px 0;
    }
</style>

