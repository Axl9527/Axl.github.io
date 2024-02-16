
<template>
    <div class="collect">
      <!-- Add a static page for my favorite module -->
      <div class="collect-header">
        <div class="collect-title">
          <i class="el-icon-collection-tag" style="color: #ff6700;"></i>
          我的收藏
        </div>
      </div>
      <div class="content">
        <!-- <div v-if="!isLogin()" class="collect-empty">
        <div class="empty">
          <h2>未登录</h2>
        </div>
      </div> -->
        <div class="goods-list" v-if="collectList.length>0">
        <div >
          
        </div>
          <MyList :list="collectList" :isDelete="true"></MyList>
        </div>
        <!-- 收藏列表为空的时候显示的内容 -->
        <div v-else class="collect-empty">
          <div class="empty">
            <h2>您的收藏还是空的！</h2>
            <p>快去购物吧！</p>
          </div>
        </div>
        <!--  收藏列表为空的时候显示的内容END -->
      </div>
    </div>
  </template>
  <script setup>
    import { onMounted, ref } from "vue";
    import axiosAPI from "../api";
    import MyList from "../components/MyList.vue";
    const user=JSON.parse(sessionStorage.getItem("user"));
    const url='http://localhost:80';
    const collectList=ref([]);
    onMounted(async function(){
      console.log(user);
        var data=await axiosAPI.collectList({uid:user.uid});
        console.log(data.length);
        for(var i=0;i<data.length;i++){
          console.log(data[i].good)
            collectList.value.push(data[i].good);
        }
    })

     //验证登陆状态
     const isLogin=()=>{
      if(user==null){
        return false
      }else{
        return true
      }
    }
  </script>
  <style>
  .collect {
    background-color: #f5f5f5;
  }
  .collect .collect-header {
    height: 64px;
    background-color: #fff;
    border-bottom: 2px solid #ff6700;
  }
  .collect .collect-header .collect-title {
    width: 1225px;
    margin: 0 auto;
    height: 64px;
    line-height: 58px;
    font-size: 28px;
  }
  .collect .content {
    padding: 20px 0;
    width: 1225px;
    margin: 0 auto;
  }
  .collect .content .goods-list {
    margin-left: -13.7px;
    overflow: hidden;
  }
  /* 收藏列表为空的时候显示的内容CSS */
  .collect .collect-empty {
    width: 1225px;
    margin: 0 auto;
  }
  .collect .collect-empty .empty {
    height: 300px;
    padding: 0 0 130px 558px;
    margin: 65px 0 0;
    background: url(../assets/imgs/cart-empty.png) no-repeat 124px 0;
    color: #b0b0b0;
    overflow: hidden;
  }
  .collect .collect-empty .empty h2 {
    margin: 70px 0 15px;
    font-size: 36px;
  }
  .collect .collect-empty .empty p {
    margin: 0 0 20px;
    font-size: 20px;
  }
  /* 收藏列表为空的时候显示的内容CSS END */
  </style>