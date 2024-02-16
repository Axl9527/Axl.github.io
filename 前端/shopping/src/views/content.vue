<script setup>
import { time } from 'echarts';
import { ref, onMounted } from 'vue';
import dayjs  from 'dayjs';
import router from "../router";
import axiosAPI from '../api';
import { ElMessage, ElMessageBox } from 'element-plus';
import loadimg from '../components/SingleImageUpload.vue'
const info=ref(false);
let time1=ref();
var user1=ref({uname:'',usex:'',uphone:'',email:'',img:''})
//生命周期
onMounted(()=>{

     user1.value =JSON.parse(sessionStorage.getItem("user"))
    //获取当前时间
    time1.value = dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss')
})
//退出登录
//退出登录
const loginOut=()=>{
  ElMessageBox.confirm('确认退出吗？')
  .then(() => {
    sessionStorage.removeItem("user")
   // location.reload()
    router.push('/login')
  
   //this.$route.go(0)
  })
}
const out=()=>{
    router.push('/login')
}
 user1.value =JSON.parse(sessionStorage.getItem("user"))

const userInfo=()=>{
    console.log("详细信息")
    info.value=true;
  }

//编辑用户信息
//注册
const form1=ref({
});


//修改密码
const form2=ref({
});

const editInfo=ref(false);
const editUser=async()=>{
  editInfo.value=true
}
//确认修改
const  eidtSubmit=async()=>{
console.log(user1.value)
  await axiosAPI.editUser(user1.value)

  var  chauser= await axiosAPI.logina(user1.value);
    console.log(chauser);
  sessionStorage.setItem("user",JSON.stringify(chauser))
  user1.value =JSON.parse(sessionStorage.getItem("user"))
  editInfo.value=false
}
//修改密码
const miMa=ref(false);
const eidtMima=async()=>{
  miMa.value=true
}

const eidtSubmitMima=async()=>{
  user1.value =JSON.parse(sessionStorage.getItem("user"))
   form2.value.uname= user1.value.uname
   form2.value.uid= user1.value.uid
console.log(form2)

  if(form2.value.newPassWord==form2.value.upassword1) {
 let data=  await axiosAPI.editUser(form2.value)
if(data==3){
  ElMessage({
        message: '密码不正确！请确认您输入的密码是否正确',
        type: 'fail',
      })
}else{
  ElMessage({
        message: '修改成功',
        type: 'success',
      })
      sessionStorage.removeItem("user")
      miMa.value=false
      location.reload()
      route.push("/")
     
}


}else{
  ElMessage({
        message: '两次密码输入不一致，请重新输入',
        type: 'fail',
      })

}
 
}


</script>


<template>
<div class="header">

<div class="right">
    <div class="time1">{{time1}}</div>
    <div class="line">|</div>
    <div>
  
  <li style="list-style-type:none; margin-top: -10px;" >
    欢迎
   
    <el-avatar :size="25"  @error="errorHandler" @click="userInfo">
      <img :src="user1.img"/> 
    </el-avatar>
    {{ user1.uname }}
  </li>
</div>
</div>



</div>
<div class="wrapper">
<Router-View></Router-View>

</div>




<el-drawer
    v-model="info"
    title="个人信息"
    :direction="direction"
    :before-close="handleClose"
    style="background-color: rgb(255, 207, 207);"
  >
  <div>
    <el-card class="box-card"  style="text-align: center; background-color: rgb(255, 213, 234);">
    <template #header>
      <div class="card-header">
        <el-avatar :size="40"  @click="userInfo">
          <img :src="user1.img"/>
        </el-avatar>
      
      </div>
    </template>
    
    <div><span>昵称：</span><span>{{user1.uname }}</span></div>
    <div><span>手机号：</span><span>{{ user1.uphone }}</span></div>
    <div><span>邮箱：</span><span>{{ user1.email }}</span></div>
    <template #footer>
      <el-button class="button" text @click="loginOut">退出登录</el-button>
        <el-button class="button" text @click="editUser">修改基本信息</el-button>
    </template>

    </el-card>  
  </div>
</el-drawer>

<el-dialog v-model="editInfo" title="编辑窗口">
    <el-form :model="user1" :rules="rules" >
      <el-form-item label="图片" :label-width="formLabelWidth">
      <loadimg v-model:picture="user1.img" :action="action" :picturePreUrl="picturePreUrl"></loadimg>
    </el-form-item>
      <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="user1.uname"  />
      </el-form-item>
     
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-input v-model="user1.usex" autocomplete="off" />
      </el-form-item>
      <el-form-item label="联系方式" :label-width="formLabelWidth">
        <el-input v-model="user1.uphone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电子邮箱" :label-width="formLabelWidth">
        <el-input v-model="user1.email" autocomplete="off" />
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
      <el-button @click="editInfo = false">取消</el-button>
      <el-button type="primary" @click="eidtSubmit">
        提交
      </el-button>
      <el-button type="primary" @click="eidtMima">
        修改密码
      </el-button>
    </span>
  </el-dialog>
<!--修改密码-->
<el-dialog v-model="miMa" title="修改密码">
    <el-form :model="form2" :rules="rules" >
    
      <el-form-item label="旧密码：" :label-width="formLabelWidth " prop="upassword">
          <el-input v-model="form2.upassword"   type="password" />
        </el-form-item>
        <el-form-item label="新密码：" :label-width="formLabelWidth " prop="newPassWord" >
          <el-input v-model="form2.newPassWord"  type="password" />
        </el-form-item>
        <el-form-item label="确认密码：" :label-width="formLabelWidth " prop="upassword1" >
          <el-input v-model="form2.upassword1"  type="password" />
        </el-form-item>
     
    </el-form>
    <span class="dialog-footer">
      <el-button @click="miMa = false">取消</el-button>
      <el-button type="primary" @click="eidtSubmitMima">
        提交
      </el-button>
    
    </span>
  </el-dialog>







</template>


<style scoped>
.header{
    height: 50px;
    background:pink;
}
.wrapper{
    margin: 20px;
}
.right{
    float: right;
    padding-right: 20px;
    padding-top:16px;
    font-size: 16px;
    display: flex;
}
.layout{
   margin-top: 2px;
    
}
.line{
    padding-left: 10px;
    padding-right: 10px;
    color:#fff;
}

</style>