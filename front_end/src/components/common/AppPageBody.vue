<template>
<!-- 一个容器，主要是为了设置颜色和flex布局-->
    <div class="main-container">
        <div class="main-content">
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PageBody"
    }
</script>

<style scoped>
    .main-container{
        margin-top: 80px;
        display: flex;
        justify-content: center;
      width: 100%;
      /*position: fixed;*/
      left: 0;
      right: 0;
      background-color: #f5f8f8;
      background-image: linear-gradient(0deg, #8EC5FC 0%, #E0C3FC 100%);
    }
    .main-content{
        width: 80%;
      /*页面主体颜色*/
      background-color: #FFFFFF;
        min-height: 90vh;
      border-radius: 10px;
    }
</style>
