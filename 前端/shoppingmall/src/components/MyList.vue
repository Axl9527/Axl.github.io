<!--
 * @Description: 列表组件，用于首页、全部商品页面的商品列表
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2020-04-05 13:22:22
 -->
<template>
  <div id="myList" class="myList">
    <ul>
      <li v-for="item in list" :key="item.id">
       
        <router-link :to="'/goods/details/'+item.id" >
          <img :src="item.img" alt @click="history(item)" />
          <h2>{{item.name}}</h2>
         <!-- <h3>{{item.info}}</h3>  -->
          <p>
            <span>{{item.price}}元</span>
            <span
              v-show="item.oldprice != item.price"
              class="del"
            >{{item.oldprice}}元</span>
          </p>
        </router-link>
      </li>
      <li v-show="isMore && list.length>=1" id="more">
        <router-link :to="'/goods/'">
          浏览更多
          <i class="el-icon-d-arrow-right"></i>
        </router-link>
      </li>
    </ul>
  </div>
</template>
<script setup>
import  API from '../api'
import {onMounted, ref} from 'vue'
var user=JSON.parse(sessionStorage.getItem("user"))
var data=defineProps({
  list:{
    type: Array,
  },

  isMore:{},
  isDelete:{}

})
onMounted (function(){
  console.log(data.list)
})


const alist=ref([])
const history=async(item)=>{
  console.log(user.uid)
 await API.addHistory({uid:user.uid,gid:item.id})
}
</script>
<style scoped>
.myList ul li {
  list-style-type:none;
  z-index: 1;
  float: left;
  width: 234px;
  height: 268px;
  padding: 10px 0;
  margin: 0 0 14.5px 3px;
  background-color: white;
  -webkit-transition: all 0.2s linear;
  transition: all 0.2s linear;
  position: relative;
}
.myList ul li:hover {
  z-index: 2;
  -webkit-box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
  -webkit-transform: translate3d(0, -2px, 0);
  transform: translate3d(0, -2px, 0);
}
.myList ul li img {
  display: block;
  width: 160px;
  height: 160px;
  background: url(../assets/imgs/placeholder.png) no-repeat 50%;
  margin: 0 auto;
}
.myList ul li h2 {
  margin: 25px 10px 0;
  font-size: 14px;
  font-weight: 400;
  color: #ff0000;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.myList ul li h3 {
  margin: 5px 10px;
  height: 18px;
  font-size: 12px;
  font-weight: 400;
  color: #b0b0b0;
  text-align: center;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.myList ul li p {
  margin: 10px 10px 10px;
  text-align: center;
  color: #ff6700;
}
.myList ul li p .del {
  margin-left: 0.5em;
  color: #000000;
  text-decoration: line-through;
}
.myList #more {
  text-align: center;
  line-height: 280px;
}
.myList #more a {
  font-size: 18px;
  color: #333;
}
.myList #more a:hover {
  color: #ff6700;
}
.myList ul li .delete {
  position: absolute;
  top: 10px;
  right: 10px;
  display: none;
}
.myList ul li:hover .delete {
  display: block
}
.myList ul li .delete:hover {
  color: #ff6700;
}
</style>