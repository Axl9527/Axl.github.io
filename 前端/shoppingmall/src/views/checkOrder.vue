
<template>
    <div class="order">
      <!-- 我的订单头部 -->
      <div class="order-header">
        <div class="order-header-content" >
          <p>
           
            我的订单
          </p>
        </div>
      </div>
      <!-- 我的订单头部END -->
  
      <!-- 我的订单主要内容 -->
      <div class="order-content" v-if="orders.length>0">
        <div class="content" v-for="(item,index) in orders" :key="index">
          <ul>
            <!-- 我的订单表头 -->
            <li class="order-info">
              <div class="order-id">订单编号: {{item.code}}</div>
              
              <div v-if="item.state==0" class="order-time" style="margin-left: 20px;">
                <span style="color: red;">待支付</span>
                <el-button style="margin-left: 20px;" @click="pay(item)">支付</el-button>
              </div>
              <div v-else-if="item.state==1" class="order-time" style="margin-left: 20px;">
                <span style="color: #ff6700;">待发货</span>
                <el-button style="margin-left: 20px;" @click="tui(item)">退款</el-button>
              </div>
              <div v-else-if="item.state==2" class="order-time" style="margin-left: 20px;">
                <span style="color: brown;">待收货</span>
                <el-button style="margin-left: 20px;" @click="harvest(item)">收货</el-button>
                <el-button style="margin-left: 20px;" @click="tui(item)">退款</el-button>
              </div>
              <div v-else-if="item.state==3" class="order-time" style="margin-left: 20px;">
                <span style="color: chartreuse;">已收货</span>
                <el-button style="margin-left: 20px;" @click="tui(item)">退款</el-button>
              </div>
              <div class="order-time">下单时间: {{item.ordertime}}</div>
            </li>
            <li class="header">
              <div class="pro-img "  >图片</div>
              <div class="pro-name" >商品名称</div>
              <div class="pro-price" >单价</div>
              <div class="pro-num">数量</div>
              <div class="pro-total">小计</div>
            </li>
            <!-- 我的订单表头END -->
  
            <!-- 订单列表 -->
            <li class="product-list" v-for="(detail,i) in item.list" :key="i">
              <div class="pro-img">
                <router-link :to="'/goods/details/'+detail.good.id">
                  <img :src="detail.good.img" />
                </router-link>
              </div>
              <div class="pro-name">
                <router-link
                  :to="'/goods/details/'+detail.good.id" 
                >{{detail.good.name}}</router-link>
              </div>
              <div class="pro-price">{{detail.price}}元</div>
              <div class="pro-num">{{detail.count}}</div>
              <div class="pro-total pro-total-in">{{detail.price*detail.count}}元</div>
            </li>
          </ul>
          <div class="order-bar">
            <div class="order-bar-left">
              <span class="order-total">
                共
                <span class="order-total-num">{{ totalNum(item) }}</span> 件商品
              </span>
            </div>
            <div class="order-bar-right">
              <span>
                <span class="total-price-title">合计：</span>
                <span class="total-price">{{totalPrice(item)}}元</span>
              </span>
            </div>
            <!-- 订单列表END -->
          </div>
        </div>
        <div style="margin-top:-40px;"></div>
      </div>
      <!-- 我的订单主要内容END -->
  
      <!-- 订单为空的时候显示的内容 -->
      <div v-else class="order-empty">
        <div class="empty">
          <h2>您的订单还是空的！</h2>
          <p>快去购物吧！</p>
        </div>
      </div>
      <!-- 订单为空的时候显示的内容END -->
    </div>

  <!-- 支付界面 -->
  <el-dialog v-model="centerDialogVisible" title="支付" width="30%" center>
    <span>
      确定支付吗？
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="paySubmit">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
 <!-- 退款界面 -->
 <el-dialog v-model="tuiDialogVisible" title="退款" width="30%" center>
    <span>
      确定退款吗？
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="tuiDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="tuiSubmit">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>


  <!-- 收货界面 -->
  <el-dialog v-model="harvestVisiable" title="收货" width="30%" center>
    <span>
      确定收货吗？
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="harvestVisiable = false">取消</el-button>
        <el-button type="primary" @click="harvestSubmit">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>

  </template>
  <script setup>
    import { ref,onMounted } from "vue";
    import axiosAPI from '../api'
    import { ElMessage } from 'element-plus'
    const centerDialogVisible = ref(false)
    const tuiDialogVisible = ref(false)
    const url='http://localhost:80';
    const user=JSON.parse(sessionStorage.getItem("user"));
    const orders=ref([]);
    onMounted(async function(){
        var data=await axiosAPI.queryOrder({uid:user.uid});
        console.log(data)
        orders.value=data
        console.log(orders.value)
    })

    const totalNum=(item)=>{
      var num=0;
      for(var i=0;i<item.list.length;i++){
        num+=item.list[i].count;
      }
      return num;
    }

    const totalPrice=(item)=>{
      
      var price=0;
      for(var i=0;i<item.list.length;i++){
        price+=item.list[i].count*item.list[i].price;
      }
      return price;
    }


    const harvestVisiable=ref(false)
    const payOid=ref("")
    var payMoney=0
    var tuiCount=0
     var items=ref([])
    const harvestOid=ref("")
    const pay=(item)=>{
      items.value=item
      console.log(items.value,"item")
      payOid.value=item.id
      //payMoney.value=item.
      console.log(payOid.value)
      centerDialogVisible.value=true
    }
    var tuiKuai=ref([])
    const tui=(item)=>{
      console.log(item)
      tuiKuai.value=item
      tuiDialogVisible.value=true
    }
    const harvest=(item)=>{
      harvestOid.value=item.id
      harvestVisiable.value=true
    }
    //退货返回的数据
    var tuiData=ref("")
    const tuiSubmit=()=>{
      for(var i=0;i<tuiKuai.value.list.length;i++){
        tuiCount+=tuiKuai.value.list[i].count;
        
      }
      
    }
    const paySubmit=()=>{
       
      for(var i=0;i<items.value.list.length;i++){
        payMoney+=items.value.list[i].count*items.value.list[i].price;
      }
      //确认支付  0->1
      var data=axiosAPI.editOrder({id:payOid.value,state:1,paymoney:payMoney})
      console.log(data)
      // if(data.oid!=null&&data.oid!=""){
        ElMessage({
          message: '支付成功',
          type: 'success',
        })
        centerDialogVisible.value=false
        location. reload()
        this.$router.go(0)
      }
      
    // }
    const harvestSubmit=()=>{
      //确认收货  2->3
      console.log(harvestOid.value)
      var data=axiosAPI.editOrder({id:harvestOid.value,state:3})
      console.log(data)
      // if(data.oid!=null&&data!=""){
        ElMessage({
          message: '收货成功',
          type: 'success',
        })
        harvestVisiable.value=false
        location. reload()
        this.router.go(0)
      }
    // }
  </script>
  <style scoped>
  .order {
    background-color: #f5f5f5;
    padding-bottom: 20px;
  }
  /* 我的订单头部CSS */
  .order .order-header {
    height: 64px;
    border-bottom: 2px solid #ff6700;
    background-color: #fff;
    margin-bottom: 25px;
  }
  .order .order-header .order-header-content {
    width: 1225px;
    margin: 0 auto;
  }
  .order .order-header p {
    font-size: 28px;
    line-height: 58px;
    float: left;
    font-weight: normal;
    color: #424242;
    margin-top: -0px;
  }
  /* 我的订单头部CSS END */
  .order .content {
    width: 1225px;
    margin: 0 auto;
    background-color: #fff;
    margin-bottom: 50px;
  }
  
  .order .content ul {
    background-color: #fff;
    color: #424242;
    line-height: 85px;
  }
  /* 我的订单表头CSS */
  .order .content ul .order-info {
    height: 60px;
    line-height: 60px;
    padding: 0 26px;
    color: #424242;
    border-bottom: 1px solid #ff6700;
  }
  .order .content ul .order-info .order-id {
    float: left;
    color: #ff6700;
  }
  .order .content ul .order-info .order-time {
    float: right;
  }
  
  .order .content ul .header {
    height: 85px;
    padding-right: 26px;
    color: #424242;
  }
  /* 我的订单表头CSS END */
  
  /* 订单列表CSS */
  .order .content ul .product-list {
    height: 85px;
    padding: 15px 26px 15px 0;
    border-top: 1px solid #e0e0e0;
  }
  .order .content ul .pro-img {
    float: left;
    height: 85px;
    width: 120px;
    padding-left: 80px;
  }
  .order .content ul .pro-img img {
    height: 80px;
    width: 80px;
  }
  .order .content ul .pro-name {
    float: left;
    width: 200px;
  }
  .order .content ul .pro-name a {
    color: #424242;
  }
  .order .content ul .pro-name a:hover {
    color: #ff6700;
  }
  .order .content ul .pro-price {
    float: left;
    width: 160px;
    padding-right: 18px;
    text-align: center;
  }
  .order .content ul .pro-num {
    float: left;
    width: 190px;
    text-align: center;
  }
  .order .content ul .pro-total {
    float: left;
    width: 160px;
    padding-right: 81px;
    text-align: right;
  }
  .order .content ul .pro-total-in {
    color: #ff6700;
  }
  
  .order .order-bar {
    width: 1185px;
    padding: 0 20px;
    border-top: 1px solid #ff6700;
    height: 50px;
    line-height: 50px;
    background-color: #fff;
  }
  .order .order-bar .order-bar-left {
    float: left;
  }
  .order .order-bar .order-bar-left .order-total {
    color: #757575;
  }
  .order .order-bar .order-bar-left .order-total-num {
    color: #ff6700;
  }
  .order .order-bar .order-bar-right {
    float: right;
  }
  .order .order-bar .order-bar-right .total-price-title {
    color: #ff6700;
    font-size: 14px;
  }
  .order .order-bar .order-bar-right .total-price {
    color: #ff6700;
    font-size: 30px;
  }
  /* 订单列表CSS END */
  
  /* 订单为空的时候显示的内容CSS */
  .order .order-empty {
    width: 1225px;
    margin: 0 auto;
  }
  .order .order-empty .empty {
    height: 300px;
    padding: 0 0 130px 558px;
    margin: 65px 0 0;
    background: url(../assets/imgs/cart-empty.png) no-repeat 124px 0;
    color: #b0b0b0;
    overflow: hidden;
  }
  .order .order-empty .empty h2 {
    margin: 70px 0 15px;
    font-size: 36px;
  }
  .order .order-empty .empty p {
    margin: 0 0 20px;
    font-size: 20px;
  }
  /* 订单为空的时候显示的内容CSS END */
  </style>