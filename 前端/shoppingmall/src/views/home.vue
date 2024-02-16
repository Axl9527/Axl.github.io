<script setup>

import API from '../api'
import { onMounted ,ref} from "vue";
import image1 from "../assets/1.jpg";
import image2 from "../assets/2.jpg";
import image3 from "../assets/3.jpg";
import imagedan1 from "../assets/danpin1.jpg";
import imagedan2 from "../assets/danpin2.jpg";
import MyList from "../components/MyList.vue"
const imagedanUrl = [
//   { url: imagedan1 },
//   { url: imagedan1 },
  { url: imagedan1},
  { url: imagedan2}];

const imageUrl = [
  { url: image1 },
  { url: image2 },
  { url: image3 },

];
const url ="http://localhost/"


onMounted(async function(){
    var data =await API.goodsList({pid:4})
    console.log(data.list)
    qrList.value=data.list
    var data2 =await API.goodsList({pid:10})
   
    brithdayList.value=data2.list
    var data3 =await API.goodsList({pid:9})
    console.log(data3)
    console.log("***********")
   shipList.value=data3.list
   console.log( shipList.value)
   var data3 =await API.goodsList({pid:8})
   
    kaiyeList.value=data3.list
})

//****商品展示 */
const qrList=ref([])
const brithdayList=ref([])
const shipList=ref([])
const kaiyeList=ref([])
</script>


<template>
    <div class="home" id="home" name="home">
      <!-- 轮播图 动态-->
      <div class="block">
        <el-carousel height="500px" >
          <el-carousel-item v-for="item in imageUrl" :key="item">
            <img style="height:500px;" :src="item.url" alt="" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <!-- 轮播图END -->

<div style="background-color: #ff9dce">
      <div class="main-box" >
        <div class="main">
          <!-- 手机商品展示区域 -->
        <div class="phone">
          <div class="box-hd">
            <div class="title">情人节热销</div>
          </div>
          <div class="box-bd">
            <div class="promo-list">
              <router-link to>
                <img src="http://localhost/easyfile/i_left1.png" />
              </router-link>
            </div>
            <div class="list">
              <MyList :list="qrList" :isMore="true"></MyList>
            </div>
          </div>
        </div>
        <!-- 手机商品展示区域END -->
  
          <!-- 家电商品展示区域 -->
         <div class="appliance" id="promo-menu">
            <div class="box-hd">
              <div class="title">亲情鲜花</div>
              <div class="more" id="more">
                <!-- <MyMenu :val="2" @fromChild="getChildMsg">
                  <span slot="1">热门</span>
                 
                </MyMenu> -->
              </div>
            </div>
            <div class="box-bd">
                <div class="promo-list1" style="padding-left:35px ;">
              <router-link to>
                <img src="http://localhost/easyfile/m2.jpg" />
              </router-link>
            </div>
              <div class="list" >
                <MyList :list=" brithdayList" :isMore="true"></MyList>
              </div>
            </div>
          </div>
          <!-- 家电商品展示区域END -->
  
          <!-- 友情鲜花展示区域 -->
          <div class="accessory" id="promo-menu">
            <div class="box-hd">
              <div class="title" style="margin-right:1000px;padding-top: 18px;">友情鲜花</div>
              <div class="more" id="more">
                <!-- <MyMenu :val="3" @fromChild="getChildMsg2">
                  <span slot="1">热门</span>
                  <span slot="2">保护套</span>
                  <span slot="3">充电器</span>
                </MyMenu> -->
              </div>
            </div>
            <div class="box-bd">
                <div class="promo-list1" style="padding-left:35px ;">
              <router-link to>
                <img src="http://localhost/easyfile/m3.jpg" />
              </router-link>
            </div>
              <div class="list">
                <MyList :list="shipList" :isMore="true"></MyList>
              </div>
            </div>
          </div> 
          <!-- 配件商品展示区域END -->
            <!-- 友情鲜花展示区域 -->
            <div class="accessory" id="promo-menu">
            <div class="box-hd">
              <div class="title" style="margin-right:1000px;padding-top: 16px;">开业花篮</div>
              <div class="more" id="more">
               
              </div>
            </div>
            <div class="box-bd">
                <div class="promo-list1" style="padding-left:35px ;">
              <router-link to>
                <img src="http://localhost/easyfile/m4.jpg" />
              </router-link>
            </div>
              <div class="list">
                <MyList :list="kaiyeList" :isMore="true"></MyList>
              </div>
            </div>
          </div> 
        </div>
      </div>
    </div>
</div>
  </template>

<style scoped>
@import "../assets/css/index.css";


</style>