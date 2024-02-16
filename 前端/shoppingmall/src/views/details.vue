<script setup>
import {onMounted ,ref}from "vue"
import imagedan1 from "../assets/danpin1.jpg";
import imagedan2 from "../assets/danpin2.jpg";
import API from "../api"
import Vcode from 'vue3-puzzle-vcode'
const oneList=ref([]);
const goods=ref("");
import { ElMessage, ElMessageBox } from 'element-plus';
import {useRoute} from "vue-router"; 

const imageUrl = [
//   { url: imagedan1 },
//   { url: imagedan1 },
  { url: imagedan1},
  { url: imagedan2}];

  const LoginUser=ref({uname:'',upassword:''});
const isLoginVisible=ref(false);
const rules={
  uname:[
    { required: true, message: '请输入账号', trigger: 'blur'},
  ],
  upassword:[
    {required: true, message: '请输入密码', trigger: 'blur'},
  ],
};
//点击登录执行的代码
//验证码模态框是否出现，默认不展示
const isShow = ref(false)
const login=async ()=>{
  isLoginVisible.value=true;
   
}
var user1=ref()
//滑块验证登录
var yanZheng=ref(false)
const Login=async ()=>{
  isLoginVisible.value=false;
isShow.value=true
}
const success = async(msg) => {
  yanZheng.value=true
if(yanZheng.value){
  isShow.value=false
  var result=await API.loginFun(LoginUser.value);
    //{code,token}根据登录状态执行不同的代码
    //处理数据 token
    console.log(result)
    if (LoginUser.value.checked) {
    console.log("已记住密码")
              localStorage.setItem("loginForm", JSON.stringify(LoginUser));
             
          } else {
            console.log("未记住密码")
              localStorage.setItem("loginForm", JSON.stringify({}));
          }
    //以键值对的形式存入sessionStorage
    sessionStorage.setItem("token",result)
    //获得登录信息，存入用户信息
  var  chauser= await API.logina(LoginUser.value);
    console.log(chauser);
  sessionStorage.setItem("user",JSON.stringify(chauser))
   
     user1 =JSON.parse(sessionStorage.getItem("user"))
}

location.reload()
}
//   
// }

const id = ref(""); // 商品id  
const route = useRoute() // 当前路由  




onMounted(async function(){
    console.log(route.params)
    id.value =route.params.id;  
    console.log(id.value)
    
    var data =await API.goodsList1()

 
   goods.value=data;
    
    for(var i=0;i<goods.value.length;i++){
      console.log(goods.value[i])
      if(goods.value[i].id==id.value){
        console.log(goods.value[i].id)
        oneList.value.push(goods.value[i])
      }
    }
  
//在setup中，用来加载页面时，查看账户密码是否存在
if (localStorage.getItem("loginForm")!=null&&Object.keys(localStorage.getItem("loginForm")).length>2 ) {
  
  LoginUser.value.checked = true;
 var userPwdInfo = JSON.parse(localStorage.getItem("loginForm"));
 console.log(userPwdInfo._value.uname,111111111)
  LoginUser.value.uname=userPwdInfo._value.uname;
  LoginUser.value.upassword=userPwdInfo._value.upassword;

} else {
  LoginUser.value.checked = false;
}

     console.log(oneList.value)

})



var user =JSON.parse(sessionStorage.getItem("user"))
const addShoppingCart=async()=>{
  if(user==null){
    console.log(111111)
    isLoginVisible.value=true;
  }else{
  const data=await API.shoppingcar({goodsid:id.value,userid:user.uid,num:1,goodsprice:oneList.value[0].price});
  ElMessage({
        type: 'success',
        message: '已成功加入购物车',
        
      })
    }
console.log(oneList.value[0].price)
}


const addCollect=async ()=>{
console.log(8888,user.uid)
 let params={uid:user.uid,gid:id.value}
  let data=  await API.addlike(params)
  console.log(data)
  if(data==3){
    ElMessage({
        type: 'success',
        message: '已收藏过',

      })
  }else{
    ElMessage({
        type: 'success',
        message: '已成功加入收藏',
        
      })
  }
  
}




</script>
<template> 
<div id="details" >
    <!-- 头部 -->
    <div class="page-header">
      <!-- <div class="title">
        <p>{{totalList.name}}</p>
        <div class="list">
          <ul>
            <li>
              <router-link to>概述</router-link>
            </li>
            <li>
              <router-link to>参数</router-link>
            </li>
            <li>
              <router-link to>用户评价</router-link>
            </li>
          </ul>
        </div>
      </div> -->
    </div>
    <!-- 头部END -->

    <!-- 主要内容 -->
    <div class="main">
      <!-- 左侧商品轮播图 -->
    <div class="block">
        <el-carousel height="560px" >
            <el-carousel-item v-for="item in oneList" >
            <!-- <img style="height:500px;" :src="oneList.img" alt="" /> -->
            <img :src="item.img" style="height:500px;"/>
          </el-carousel-item>
        </el-carousel>
        <!-- <div v-if="productPicture.length==1">
          <img
            style="height:560px;"
            :src="$target + productPicture[0].product_picture"
            :alt="productPicture[0].intro"
          />
        </div> -->
      </div> 
      <!-- 左侧商品轮播图END -->

      <div class="content" v-for="totalList in oneList">
        <h1 class="name">{{totalList.name}}</h1>
      
          <p class="store">  {{totalList.cailiao}}</p>
          <p class="store">{{totalList.baozhuang}}</p>
        
        <p class="store">{{totalList.info}}</p>
        <p class="store">未闻花名</p>
        <div class="price">
          <span>{{totalList.price}}元</span>
          <span
            v-show="totalList.oldprice !=totalList.price"
            class="del"
          >{{totalList.oldprice}}元</span>
        </div>
        <div class="pro-list">
          <span class="pro-name">{{totalList.name}}</span>
          <span class="pro-price">
            <span>{{totalList.price}}元</span>
            <span
              v-show="totalList.oldprice != totalList.price"
              class="pro-del"
            >{{totalList.oldprice}}元</span>
          </span>
          <p class="price-sum">总计 : {{totalList.price}}元</p>
        </div>
        <!-- 内容区底部按钮 -->
        <div class="button">
          <el-button class="shop-cart" :disabled="dis" @click="addShoppingCart">加入购物车</el-button>
          <el-button class="like" @click="addCollect">喜欢</el-button>
        </div>
        <!-- 内容区底部按钮END -->
        <div class="pro-policy">
          <ul>
            <li>
              <i class="el-icon-circle-check"></i> 未闻花名
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 顺丰发货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天有理由退货
            </li>
            <li>
              <i class="el-icon-circle-check"></i> 7天价格不保护
            </li>
          </ul>
        </div>
      </div>
      <!-- 右侧内容区 -->
      
      <!-- 右侧内容区END -->
    </div>
    <!-- 主要内容END -->
  </div>
     <!-- 登录模块 -->
     <div id="myLogin">
    <el-dialog title="登录" width="300px" v-model="isLoginVisible">
      <el-form :model="LoginUser" :rules="rules" status-icon ref="ruleForm" class="demo-ruleForm">
        <el-form-item prop="uname">
          <el-input prefix-icon="el-icon-user-solid" placeholder="请输入账号" v-model="LoginUser.uname"></el-input>
        </el-form-item>
        <el-form-item prop="upassword">
          <el-input
            prefix-icon="el-icon-view"
            type="password"
            placeholder="请输入密码"
            v-model="LoginUser.upassword"
          ></el-input>
          <el-form-item class="isChecked">
                  <!-- `checked` 为 true 或 false -->
                  <el-checkbox v-model="LoginUser.checked" class="remeberPwd">记住密码</el-checkbox>
              </el-form-item>
        </el-form-item>
        <Vcode :show="isShow" @success="success" @close="close" @fail="fail" :img="[Img]" style="position: absolute; top: 0; left: 0; z-index: 9999;"></Vcode>
        <el-form-item>
          <el-button size="medium" type="primary" @click="Login" style="width:100%;">登录</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
  
  </template>


<style>
/* 头部CSS */
#details .page-header {
  height: 64px;
  margin-top: -20px;
  z-index: 4;
  background: #fff;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
}
#details .page-header .title {
  width: 1225px;
  height: 64px;
  line-height: 64px;
  font-size: 18px;
  font-weight: 400;
  color: #212121;
  margin: 0 auto;
}
#details .page-header .title p {
  float: left;
}
#details .page-header .title .list {
  height: 64px;
  float: right;
}
#details .page-header .title .list li {
  float: left;
  margin-left: 20px;
}
#details .page-header .title .list li a {
  font-size: 14px;
  color: #616161;
}
#details .page-header .title .list li a:hover {
  font-size: 14px;
  color: #ff6700;
}
/* 头部CSS END */

/* 主要内容CSS */
#details .main {
  width: 1225px;
  height: 560px;
  padding-top: 30px;
  margin: 0 auto;
}
#details .main .block {
  float: left;
  width: 560px;
  height: 560px;
}
#details .el-carousel .el-carousel__indicator .el-carousel__button {
  background-color: rgba(163, 163, 163, 0.8);
}
#details .main .content {
  float: left;
  margin-left: 25px;
  width: 640px;
}
#details .main .content .name {
  height: 30px;
  line-height: 30px;
  font-size: 24px;
  font-weight: normal;
  color: #212121;
}
#details .main .content .intro {
  color: #b0b0b0;
  padding-top: 10px;
}
#details .main .content .store {
  color: #ff6700;
  padding-top: 10px;
}
#details .main .content .price {
  display: block;
  font-size: 18px;
  color: #ff6700;
  border-bottom: 1px solid #e0e0e0;
  padding: 25px 0 25px;
}
#details .main .content .price .del {
  font-size: 14px;
  margin-left: 10px;
  color: #b0b0b0;
  text-decoration: line-through;
}
#details .main .content .pro-list {
  background: #f9f9fa;
  padding: 30px 60px;
  margin: 50px 0 50px;
}
#details .main .content .pro-list span {
  line-height: 30px;
  color: #616161;
}
#details .main .content .pro-list .pro-price {
  float: right;
}
#details .main .content .pro-list .pro-price .pro-del {
  margin-left: 10px;
  text-decoration: line-through;
}
#details .main .content .pro-list .price-sum {
  color: #ff6700;
  font-size: 24px;
  padding-top: 20px;
}
#details .main .content .button {
  height: 55px;
  margin: 10px 0 20px 0;
}
#details .main .content .button .el-button {
  float: left;
  height: 55px;
  font-size: 16px;
  color: #fff;
  border: none;
  text-align: center;
}
#details .main .content .button .shop-cart {
  width: 340px;
  background-color: #ff6700;
}
#details .main .content .button .shop-cart:hover {
  background-color: #f25807;
}

#details .main .content .button .like {
  width: 260px;
  margin-left: 40px;
  background-color: #b0b0b0;
}
#details .main .content .button .like:hover {
  background-color: #757575;
}
#details .main .content .pro-policy li {
  float: left;
  margin-right: 20px;
  color: #b0b0b0;
}
/* 主要内容CSS END */
</style>