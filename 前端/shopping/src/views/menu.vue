<script >

import{ref} from'vue'

export default{
  setup(){
//定义开关
const isCollapse=ref(false)

return{
  isCollapse,
 
}
  }
}




</script>


<template>


  <el-menu  
    default-active="$route.path" router
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    @open="handleOpen"
    @close="handleClose"
    background-color="#f0f8ff"  
    text-color="#000"
    active-text-color="#ffd04b" 
  >
  <el-menu-item  index="/index"><template #title><span>未闻花名管理系统</span></template></el-menu-item>
 
  <el-menu-item index="/index"><template #title> <el-icon><SuitcaseLine /></el-icon>
    <span>系统首页</span></template></el-menu-item>

    <el-sub-menu index="/index">
      <template #title>
        <el-icon><Grid /></el-icon>
        <span>商品管理</span>
      </template>
      <el-menu-item-group>
      
        <el-menu-item index="/list">商品列表</el-menu-item>
        <el-menu-item index="/category">商品分类</el-menu-item>
      </el-menu-item-group>
      
    </el-sub-menu>
    <el-sub-menu index="/index">
      <template #title>
        <el-icon><Grid /></el-icon>
        <span>用户管理</span>
      </template>
      <el-menu-item-group>
      
        <el-menu-item index="/user">用户列表</el-menu-item>
        
      </el-menu-item-group>
      
    </el-sub-menu>

    <el-sub-menu index="/index">
      <template #title>
        <el-icon><Grid /></el-icon>
        <span>订单管理</span>
      </template>
      <el-menu-item-group>
      
        <el-menu-item index="/order">订单列表</el-menu-item>
        
      </el-menu-item-group>
      
    </el-sub-menu>
   
  </el-menu>
<!-- <router-view></router-view> -->
</template>


<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>