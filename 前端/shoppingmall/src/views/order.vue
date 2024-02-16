<script setup>
import {ref,onMounted} from 'vue'
import API from '../api'
import { useRoute } from 'vue-router';
import { regionData,codeToText} from 'element-china-area-data'

import router from '../router'
const {route,query} = useRoute()



const isVisible=ref(false);



const getCheckGoods=ref([]);
var addressall=ref([])

var user=JSON.parse(sessionStorage.getItem("user"))

onMounted(async function(){
getCheckGoods.value=JSON.parse(query.join)

 console.log(user)
 
  var add=await API.useraddress({userid:user.uid})
  for(var i=0;i<add.length;i++){
          console.log("asdasd");
          console.log(add[i].def);
        if(add[i].def==1){
          order.value=add[i].province+add[i].city+add[i].area+add[i].details
        }
      }

  console.log(add[0].city)
  addressall.value=add
})

const getCheckNum=()=>{
  var sum=0
  for(var i=0;i<getCheckGoods.value.length;i++){
    
    sum+=getCheckGoods.value[i].num
  

  }
  return sum
}
  const getTotalPrice=()=>{
  var sum=0
  for(var i=0;i<getCheckGoods.value.length;i++){
    
    sum+=getCheckGoods.value[i].num*getCheckGoods.value[i].goodsprice
  

  }
  return sum
}




//加地址
const addprovince=async()=>{
  isVisible.value=true;
}
//新增地址数据
const address=ref({
        userid:user.uid,
        province:"",
        city:"",
        area:"",
        details:"",
        def:""
    })


   
    const formLabelWidth = '80px'

    const options = ref(regionData)
    const selectedOptions = ref([])
    //选择省市区
    const addressChange=()=>{
        if (
            selectedOptions.value[0] != null &&
            selectedOptions.value[1] != null &&
            selectedOptions.value[2] != null
        ) {
            const str =
            codeToText[selectedOptions.value[0]] +
            '/' +
            codeToText[selectedOptions.value[1]] +
            '/' +
            codeToText[selectedOptions.value[2]]
            console.log(str)
        }
        address.value.province=codeToText[selectedOptions.value[0]];
        address.value.city=codeToText[selectedOptions.value[1]];
        address.value.area=codeToText[selectedOptions.value[2]];
        address.value.details

    }

    const isDef=ref(false);
    //提交地址（新增）
    const submitAddress=()=>{
        if(isDef==true){
            address.value.def=1;
        }else{
            address.value.def=0;
        }
        var data=API.addAddress(address.value);
        isVisible.value=false
    }

//选择地址
var order=ref("")
const changeAddress=async(item)=>{
  for(var i=0;i<addressall.value.length;i++){
   
    addressall.value[i].def=0
    
  }
  item.def=1
  console.log(item)
 order.value=item.province+item.city+item.area+item.details
 console.log(order.value)
}
//结算

const addOrder=async()=>{
  console.log(getCheckGoods.value)
  var sum=[]
  for(var i=0;i<getCheckGoods.value.length;i++){
    sum.push({goodsid:getCheckGoods.value[i].goodsname.id,count:getCheckGoods.value[i].num,price:getCheckGoods.value[i].goodsprice,userid:user.uid})
  }
console.log(sum)

let paymoney = getTotalPrice()

var data=await API.jiesuan(JSON.stringify({list:sum,oaddress:order.value,uid:user.uid,paymoney}))


router.push({ path: "/order" })
}



</script>



<template><div class="confirmOrder">
    <!-- 头部 -->
    <div class="confirmOrder-header">
      <div class="header-content">
        <p>
          <i class="el-icon-s-order"></i>
        </p>
        <p>确认订单</p> 
        <router-link to></router-link>
      </div>
    </div>
    <!-- 头部END -->

    <!-- 主要内容容器 -->
    <div class="content" >
      <!-- 选择地址 -->
      <div class="section-address" style="list-style-type:none;">
        <p  class="title">收货地址</p>
        <div class="address-body">
          <ul style="list-style-type:none;">
            <li @click="changeAddress(item)"
              :class="item.id == confirmAddress ? 'in-section' : ''"
            
              v-for="item in addressall" :key="item.id" :style="'background-color:' + (item.def == 1 ? 'bisque' : '') + ';'">

           
              <h2>{{user.uname}}</h2>
              <p class="phone">{{user.uphone}}</p>
              <p class="address">{{item.province+item.city+item.area+item.details}}</p>
            </li>
            <li class="add-address" @click="addprovince">
              <i class="el-icon-circle-plus-outline"></i>
              <p>添加新地址</p>

            </li>
          </ul>
        </div>

      </div>
      <el-dialog title="地址" width="800px" v-model="isVisible">
        <el-form :model="address">
      <el-form-item label="省市区" :label-width="formLabelWidth">
        <div id="app">
        <el-cascader
            size="large"
            :options="options"
            v-model="selectedOptions"
            @change="addressChange">
        </el-cascader>
        </div>
      </el-form-item>
      <el-form-item label="详细住址" :label-width="formLabelWidth">
        <el-input v-model="address.details" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-switch
            v-model="isDef"
            class="mb-2"
            style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
            active-text="是否存为默认地址"
        />
      </el-form-item>
     
      <span class="dialog-footer">
        <el-button @click="isVisible = false">取消</el-button>
        <el-button type="primary" @click="submitAddress">
          确定
        </el-button>
      </span>
    
    </el-form>
    </el-dialog>
      <!-- 选择地址END -->

      <!-- 商品及优惠券 -->
      <div class="section-goods">
        <p class="title">商品及优惠券</p>
        <div class="goods-list">
          <ul>
            <li v-for="item in getCheckGoods" :key="item.id" >
              <img :src="item.goodsimg.img" />
              <span class="pro-name">{{item.goodsimg.name}}</span>
              <span class="pro-price">{{item.goodsprice}}元 x {{item.num}}</span>
              <span class="pro-status"></span>
              <span class="pro-total">{{item.goodsprice * item.num}}元</span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 商品及优惠券END -->

      <!-- 配送方式 -->
      <div class="section-shipment">
        <p class="title">配送方式</p>
        <p class="shipment">包邮</p>
      </div>
      <!-- 配送方式END -->

      <!-- 发票 -->
      <div class="section-invoice">
        <p class="title">发票</p>
        <p class="invoice">电子发票</p>
        <p class="invoice">个人</p>
        <p class="invoice">商品明细</p>
      </div>
      <!-- 发票END -->

      <!-- 结算列表 -->
      <div class="section-count">
        <div class="money-box">
          <ul style="list-style-type:none;">
            <li>
              <span class="title">商品件数：</span>
              <span class="value">{{getCheckNum()}}件</span>
            </li>
            <li>
              <span class="title">商品总价：</span>
              <span class="value">{{getTotalPrice()}}元</span>
            </li>
            <li>
              <span class="title">活动优惠：</span>
              <span class="value">-0元</span>
            </li>
            <li>
              <span class="title">优惠券抵扣：</span>
              <span class="value">-0元</span>
            </li>
            <li>
              <span class="title">运费：</span>
              <span class="value">0元</span>
            </li>
            <li class="total">
              <span class="title">应付总额：</span>
              <span class="value">
                <span class="total-price">{{getTotalPrice()}}</span>元
              </span>
            </li>
          </ul>
        </div>
      </div>
      <!-- 结算列表END -->

      <!-- 结算导航 -->
      <div class="section-bar">
        <div class="btn">
          <router-link to="/shoppingCart" class="btn-base btn-return">返回购物车</router-link>
          
          <el-button class="btn-base btn-primary" @click="addOrder">结算</el-button>
        </div>
      </div>
      <!-- 结算导航END -->
    </div>
    <!-- 主要内容容器END -->
  </div>
  </template>

<style scoped>

.confirmOrder {
    background-color: #f5f5f5;
    padding-bottom: 20px;
  }
  /* 头部CSS */
  .confirmOrder .confirmOrder-header {
    background-color: #fff;
    border-bottom: 2px solid #ff6700;
    margin-bottom: 20px;
  }
  .confirmOrder .confirmOrder-header .header-content {
    width: 1225px;
    margin: 0 auto;
    height: 80px;
  }
  .confirmOrder .confirmOrder-header .header-content p {
    float: left;
    font-size: 28px;
    line-height: 80px;
    color: #424242;
    margin-right: 20px;
  }
  .confirmOrder .confirmOrder-header .header-content p i {
    font-size: 45px;
    color: #ff6700;
    line-height: 80px;
  }
  /* 头部CSS END */
  
  /* 主要内容容器CSS */
  .confirmOrder .content {
    width: 1225px;
    margin: 0 auto;
    padding: 48px 0 0;
    background-color: #fff;
  }
  
  /* 选择地址CSS */
  .confirmOrder .content .section-address {
    margin: 0 48px;
    overflow: hidden;
  }
  .confirmOrder .content .section-address .title {
    color: #333;
    font-size: 18px;
    line-height: 20px;
    margin-bottom: 20px;
  }
  .confirmOrder .content .address-body li {
    float: left;
    color: #333;
    width: 220px;
    height: 178px;
    border: 1px solid #e0e0e0;
    padding: 15px 24px 0;
    margin-right: 17px;
    margin-bottom: 24px;
  }
  .confirmOrder .content .address-body li:hover{
    background-color: bisque;
  }

  .confirmOrder .content .address-body .in-section {
    border: 1px solid #ff6700;
  }
  .confirmOrder .content .address-body li h2 {
    font-size: 18px;
    font-weight: normal;
    line-height: 30px;
    margin-bottom: 10px;
  }
  .confirmOrder .content .address-body li p {
    font-size: 14px;
    color: #757575;
  }
  .confirmOrder .content .address-body li .address {
    padding: 10px 0;
    max-width: 180px;
    max-height: 88px;
    line-height: 22px;
    overflow: hidden;
  }
  .confirmOrder .content .address-body .add-address {
    text-align: center;
    line-height: 30px;
  }
  .confirmOrder .content .address-body .add-address i {
    font-size: 30px;
    padding-top: 50px;
    text-align: center;
  }
  /* 选择地址CSS END */
  
  /* 商品及优惠券CSS */
  .confirmOrder .content .section-goods {
    margin: 0 48px;
  }
  .confirmOrder .content .section-goods p.title {
    color: #333;
    font-size: 18px;
    line-height: 40px;
  }
  .confirmOrder .content .section-goods .goods-list {
    padding: 5px 0;
    border-top: 1px solid #e0e0e0;
    border-bottom: 1px solid #e0e0e0;
  }
  .confirmOrder .content .section-goods .goods-list li {
    padding: 10px 0;
    color: #424242;
    overflow: hidden;
  }
  .confirmOrder .content .section-goods .goods-list li img {
    float: left;
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }
  .confirmOrder .content .section-goods .goods-list li .pro-name {
    float: left;
    width: 650px;
    line-height: 30px;
  }
  .confirmOrder .content .section-goods .goods-list li .pro-price {
    float: left;
    width: 150px;
    text-align: center;
    line-height: 30px;
    margin-left: -300px;
  }
  .confirmOrder .content .section-goods .goods-list li .pro-status {
    float: left;
    width: 99px;
    height: 30px;
    text-align: center;
    line-height: 30px;
  }
  .confirmOrder .content .section-goods .goods-list li .pro-total {
    float: left;
    width: 190px;
    text-align: center;
    color: #ff6700;
    line-height: 30px;
  }
  /* 商品及优惠券CSS END */
  
  /* 配送方式CSS */
  .confirmOrder .content .section-shipment {
    margin: 0 48px;
    padding: 25px 0;
    border-bottom: 1px solid #e0e0e0;
    overflow: hidden;
  }
  .confirmOrder .content .section-shipment .title {
    float: left;
    width: 150px;
    color: #333;
    font-size: 18px;
    line-height: 38px;
  }
  .confirmOrder .content .section-shipment .shipment {
    float: left;
    line-height: 38px;
    font-size: 14px;
    color: #ff6700;
  }
  /* 配送方式CSS END */
  
  /* 发票CSS */
  .confirmOrder .content .section-invoice {
    margin: 0 48px;
    padding: 25px 0;
    border-bottom: 1px solid #e0e0e0;
    overflow: hidden;
  }
  .confirmOrder .content .section-invoice .title {
    float: left;
    width: 150px;
    color: #333;
    font-size: 18px;
    line-height: 38px;
  }
  .confirmOrder .content .section-invoice .invoice {
    float: left;
    line-height: 38px;
    font-size: 14px;
    margin-right: 20px;
    color: #ff6700;
  }
  /* 发票CSS END */
  
  /* 结算列表CSS */
  .confirmOrder .content .section-count {
    margin: 0 48px;
    padding: 20px 0;
    overflow: hidden;
  }
  .confirmOrder .content .section-count .money-box {
    float: right;
    text-align: right;
  }
  .confirmOrder .content .section-count .money-box .title {
    float: left;
    width: 126px;
    height: 30px;
    display: block;
    line-height: 30px;
    color: #757575;
  }
  .confirmOrder .content .section-count .money-box .value {
    float: left;
    min-width: 105px;
    height: 30px;
    display: block;
    line-height: 30px;
    color: #ff6700;
  }
  .confirmOrder .content .section-count .money-box .total .title {
    padding-top: 15px;
  }
  .confirmOrder .content .section-count .money-box .total .value {
    padding-top: 10px;
  }
  .confirmOrder .content .section-count .money-box .total-price {
    font-size: 30px;
  }
  /* 结算列表CSS END */
  
  /* 结算导航CSS */
  .confirmOrder .content .section-bar {
    padding: 20px 48px;
    border-top: 2px solid #f5f5f5;
    overflow: hidden;
  }
  .confirmOrder .content .section-bar .btn {
    float: right;
  }
  .confirmOrder .content .section-bar .btn .btn-base {
    float: left;
    margin-left: 30px;
    width: 158px;
    height: 38px;
    border: 1px solid #b0b0b0;
    font-size: 14px;
    line-height: 38px;
    text-align: center;
  }
  .confirmOrder .content .section-bar .btn .btn-return {
    color: rgba(0, 0, 0, 0.27);
    border-color: rgba(0, 0, 0, 0.27);
  }
  .confirmOrder .content .section-bar .btn .btn-primary {
    background: #ff6700;
    border-color: #ff6700;
    color: #fff;
  }
  /* 结算导航CSS */

</style>