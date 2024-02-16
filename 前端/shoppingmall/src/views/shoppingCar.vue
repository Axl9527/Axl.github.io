<script setup>
import{ref,onMounted,getCurrentInstance,inject} from 'vue'
import { useRouter } from 'vue-router';
import API from '../api'

import {
  ArrowLeft,
  ArrowRight,
  Delete,
  Edit,
  Share,
  InfoFilled
} from '@element-plus/icons-vue'
import route from '../router';


var user =JSON.parse(sessionStorage.getItem("user"))
console.log(user.uid)

const getShoppingCart=ref([]);

onMounted(async function(){

    var data=await API.seachcar()
         console.log(data)
      for(var i=0;i<data.length;i++){
       
          if(data[i].userid==user.uid){
            
         
            getShoppingCart.value.push(data[i])
            check.value.push(false)
          }
      }   
  
   console.log( getShoppingCart.value)





})

const countAll =()=>{
  var sum=0
  for(var i=0;i<getShoppingCart.value.length;i++){
    sum+=getShoppingCart.value[i].num

  }
  return sum
}


const choosecount=()=>{
  var sum=0
  for(var i=0;i<getShoppingCart.value.length;i++){
    if(check.value[i]==true){
    sum+=getShoppingCart.value[i].num
  }

  }
  return sum
}


const getTotalPrice=()=>{
  var sum=0
  for(var i=0;i<getShoppingCart.value.length;i++){
    if(check.value[i]==true){
    sum+=getShoppingCart.value[i].num*getShoppingCart.value[i].goodsprice
  }
    console.log(i)
  }
  return sum
}



const checkChange=(event,index)=>{
  console.log(index)
  console.log(event)
  if(event==false){

  }

}


//删除购物车

const deleteCar= async (item)=>{
 console.log(111)
  await API.deleteCars(item)
   
   location. reload()
   

}









var check=ref([])

const handleChange = async (item) => {  
 await API.editCountCar(item)
 
}
//全选，默认不全选，当点击全选通过遍历给每一行选中或不选中
const isAllCheck=ref(false)

const choose =()=>{
  console.log(isAllCheck.value)
  if(isAllCheck.value==false){
  for(var i=0;i<check.value.length;i++){
  check.value[i]=true;

  }


}else{
  for(var i=0;i<check.value.length;i++){
  check.value[i]=false;
  }
}
}

//将选中商品加入购物车

const order=async ()=>{
  
  var ordercar=ref([])
  
  for(var i=0;i<getShoppingCart.value.length;i++){
    if(check.value[i]==true){
     ordercar.value.push(getShoppingCart.value[i])
     
    
  }}
 // await API.seachcar()
   //await API.orderCar(getShoppingCart.value)
  console.log(ordercar.value)
 route.push({
  path:'/confirmOrder',
  query:{join:JSON.stringify(ordercar.value)}
 })
  // sessionStorage.setItem("orderCar",JSON.stringify(ordercar))
  

}











</script>

<template>
   <div class="shoppingCart">
    <!-- 购物车头部 -->
    <div class="cart-header">
      <div class="cart-header-content">
        <p style="margin-top: auto;">我的购物车</p>
        <p>
          <i class="el-icon-shopping-cart-full" style="color:#ff6700; font-weight: 600;"></i>
          
        </p>
        <span>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</span>
      </div>
    </div>
    <!-- 购物车头部END -->

    <!-- 购物车主要内容区 -->
    <div class="content" v-if="getShoppingCart.length>0">
      <ul>
        <!-- 购物车表头 -->
        <li class="header">
          <div class="pro-check" style="margin-left: 0px;">
            <el-checkbox v-model="isAllCheck" @click="choose">全选</el-checkbox>
          </div>
          <div class="pro-img"></div>
          <div class="pro-name">商品名称</div>
          <div class="pro-price">单价</div>
          <div class="pro-action">操作</div>
        </li>
        <!-- 购物车表头END -->

        <!-- 购物车列表 -->
        <li class="product-list" v-for="(item,index) in getShoppingCart" :key="item.id">
          <div class="pro-check" >
            <el-checkbox v-model="check[index]" @change="checkChange($event,index)"></el-checkbox>
          </div>
          <div class="pro-img">
            <router-link :to="'/goods/details/'+item.goodsid" >
              <img :src="item.goodsname.img" />
            </router-link>
          </div>
          <div class="pro-name">
            <router-link
              :to="{ path: '/goods/details', query: {productID:item.productID} }"
            >{{item.goodsname.name}}</router-link>
          </div>
          <div class="pro-price">{{item.goodsprice}}元</div>
          <div class="pro-num">
            <el-input-number v-model="item.num" :min="1" :max="10" @change="handleChange(item)" />
            <!-- <td><button @click="changeNum(item, -1)">-</button><span>{{ item.num }}</span><button @click="changeNum(item, +1)">+</button></td> -->
          </div>
          <div class="pro-total pro-total-in">{{item.goodsprice*item.num}}元</div>
          <div class="pro-action">  
          
  <el-popconfirm
    confirm-button-text="确定"
    cancel-button-text="取消"
    :icon="Delete"
    icon-color="#626AEF"
    title="你确定要删除吗?"
    @confirm="deleteCar(item)"
    @cancel="cancelEvent"
  >
    <template #reference>
      <el-button>删除</el-button>
    </template>
  </el-popconfirm>
    
  </div> 
        </li>
        <!-- 购物车列表END -->
      </ul>
      <div style="height:20px;background-color: #f5f5f5"></div>
      <!-- 购物车底部导航条 -->
      <div class="cart-bar">
        <div class="cart-bar-left">
          <span>
            <router-link to="/goods">继续购物</router-link>
          </span>
          <span class="sep">|</span>
          <span class="cart-total">
            共
            <span class="cart-total-num">{{countAll()}}</span> 件商品，已选择
            <span class="cart-total-num">{{choosecount()}}</span> 件
          </span>
        </div>
        <div class="cart-bar-right">
          <span>
            <span class="total-price-title">合计：</span>
            <span class="total-price">{{getTotalPrice()}}元</span>
          </span>
          <!-- <router-link :to="choosecount()> 0 ? '/confirmOrder' : ''"> -->
            <div :class="choosecount()> 0 ? 'btn-primary' : 'btn-primary-disabled'" @click="order">去结算</div>
          <!-- </router-link> -->
        </div>
      </div>
      <!-- 购物车底部导航条END -->
    </div>
    <!-- 购物车主要内容区END -->

    <!-- 购物车为空的时候显示的内容 -->
    <div v-else class="cart-empty">
      <div class="empty">
        <h2>您的购物车还是空的！</h2>
        <p>快去购物吧！</p>
      </div>
    </div>
    <!-- 购物车为空的时候显示的内容END -->
  </div>
  </template>
<style scoped>
.header{
  list-style-type:none;
}
.shoppingCart {
  list-style-type:none;
  background-color: #f5f5f5;
  padding-bottom: 10px;
}
/* 购物车头部CSS */
.shoppingCart .cart-header {
  height: 64px;
  list-style-type:none;
  border-bottom: 2px solid #ff6700;
  background-color: #fff;
  margin-bottom: 40px;
}
.shoppingCart .cart-header .cart-header-content {
  width: 1225px;
  margin:  auto;
}
.shoppingCart .cart-header p {
  font-size: 28px;
  line-height: 58px;
  float: left;
  font-weight: normal;
  color: #424242;
}
.shoppingCart .cart-header span {
  color: #757575;
  font-size: 12px;
  float: left;
  height: 20px;
  line-height: 20px;
  margin-top: 30px;
  margin-left: 15px;
}
/* 购物车头部CSS END */

/* 购物车主要内容区CSS */
.shoppingCart .content {
  list-style-type:none;
  width: 1225px;
  margin: 0 auto;
  background-color: #fff;
}

.shoppingCart .content ul {
  list-style-type:none;
  background-color: #fff;
  color: #424242;
  line-height: 85px;
}
/* 购物车表头及CSS */
.shoppingCart .content ul .header {
  height: 85px;
  padding-right: 26px;
  color: #424242;
}
.shoppingCart .content ul .product-list {
  height: 85px;
  padding: 15px 26px 15px 0;
  border-top: 1px solid #e0e0e0;
}
.shoppingCart .content ul .pro-check {
  float: left;
  height: 85px;
  width: 110px;
  
}
.shoppingCart .content ul .pro-check .el-checkbox {
  font-size: 16px;
  margin-left: 20px;
}
.shoppingCart .content ul .pro-img {
  float: left;
  height: 85px;
  width: 120px;
}
.shoppingCart .content ul .pro-img img {
  height: 80px;
  width: 80px;
}
.shoppingCart .content ul .pro-name {
  float: left;
  width: 200px;
}
.shoppingCart .content ul .pro-name a {
  color: #424242;
}
.shoppingCart .content ul .pro-name a:hover {
  color: #ff6700;
}
.shoppingCart .content ul .pro-price {
  float: left;
  width: 140px;
  padding-right: 18px;
  text-align: center;
}
.shoppingCart .content ul .pro-num {
  float: left;
  width: 150px;
  text-align: center;
}
.shoppingCart .content ul .pro-total {
  float: left;
  width: 120px;
  padding-right: 81px;
  text-align: right;
}
.shoppingCart .content ul .pro-total-in {
  color: #ff6700;
}
.shoppingCart .content ul .pro-action {
  float: left;
  width: 80px;
  text-align: center;
}
.shoppingCart .content ul .pro-action i:hover {
  color: #ff6700;
}
/* 购物车表头及CSS END */

/* 购物车底部导航条CSS */
.shoppingCart .cart-bar {
  width: 1225px;
  height: 50px;
  line-height: 50px;
  background-color: #fff;
}
.shoppingCart .cart-bar .cart-bar-left {
  float: left;
}
.shoppingCart .cart-bar .cart-bar-left a {
  line-height: 50px;
  margin-left: 32px;
  color: #757575;
}
.shoppingCart .cart-bar .cart-bar-left a:hover {
  color: #ff6700;
}
.shoppingCart .cart-bar .cart-bar-left .sep {
  color: #eee;
  margin: 0 20px;
}
.shoppingCart .cart-bar .cart-bar-left .cart-total {
  color: #757575;
}
.shoppingCart .cart-bar .cart-bar-left .cart-total-num {
  color: #ff6700;
}
.shoppingCart .cart-bar .cart-bar-right {
  float: right;
}
.shoppingCart .cart-bar .cart-bar-right .total-price-title {
  color: #ff6700;
  font-size: 14px;
}
.shoppingCart .cart-bar .cart-bar-right .total-price {
  color: #ff6700;
  font-size: 30px;
}
.shoppingCart .cart-bar .cart-bar-right .btn-primary {
  float: right;
  width: 200px;
  text-align: center;
  font-size: 18px;
  margin-left: 50px;
  background: #ff6700;
  color: #fff;
}
.shoppingCart .cart-bar .cart-bar-right .btn-primary-disabled {
  float: right;
  width: 200px;
  text-align: center;
  font-size: 18px;
  margin-left: 50px;
  background: #e0e0e0;
  color: #b0b0b0;
}
.shoppingCart .cart-bar .cart-bar-right .btn-primary:hover {
  background-color: #f25807;
}
/* 购物车底部导航条CSS END */
/* 购物车主要内容区CSS END */

/* 购物车为空的时候显示的内容CSS */
.shoppingCart .cart-empty {
  width: 1225px;
  margin: 0 auto;
}
.shoppingCart .cart-empty .empty {
  height: 300px;
  padding: 0 0 130px 558px;
  margin: 65px 0 0;
  background: url(../assets/imgs/cart-empty.png) no-repeat 124px 0;
  color: #b0b0b0;
  overflow: hidden;
}
.shoppingCart .cart-empty .empty h2 {
  margin: 70px 0 15px;
  font-size: 36px;
}
.shoppingCart .cart-empty .empty p {
  margin: 0 0 20px;
  font-size: 20px;
}
/* 购物车为空的时候显示的内容CSS END */
</style>