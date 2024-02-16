<script setup>

import { ref } from 'vue';
import axiosAPI from '../api';
import router from "../router";
//登录界面绑定的信息
const logindata=ref({
    uname:'',
    upassword:''
});
//定义登录时执行的方法
const loginSubmit=async ()=>{
    //验证参数格式是否正确
    //提交数据
    var result=await axiosAPI.loginFun(logindata.value);
    //{code,token}根据登录状态执行不同的代码
    //处理数据 token
    console.log(result)
    
    //以键值对的形式存入sessionStorage
    sessionStorage.setItem("token",result)
    //跳转页面
    router.push('/aaa');

}

</script>

<template>
    
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="用户名">
      <el-input v-model="logindata.uname"  placeholder="请输入用户名" clearable />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="logindata.upassword" placeholder="请输入用户名" clearable type="password"/>
    </el-form-item>
    <el-button type="success" @click="loginSubmit">登录</el-button>
    </el-form>


</template>

<style>
</style>