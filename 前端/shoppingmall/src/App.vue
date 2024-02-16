<script setup>
import { ref, onMounted, onUnmounted ,provide,nextTick} from 'vue';
import { useRouter } from 'vue-router';  
import axiosAPI from './api';
import route from "./router";
import Vcode from 'vue3-puzzle-vcode'
import { ElMessage, ElMessageBox } from 'element-plus';
import loadimg from './components/SingleImageUpload.vue'
import Img from './assets/danpin1.jpg'

//获取路由器
let $router = useRouter()
//验证码模态框是否出现，默认不展示
const isShow = ref(false)


//登录的数据
const LoginUser=ref({uname:'',upassword:''});
const isLoginVisible=ref(false);
const info=ref(false);
const editInfo=ref(false);
const rules={
  uname:[
     { required: true, message: '请输入用户名', trigger: 'blur' },
     { min: 3, max: 8, message: '长度3-8', trigger: 'blur' },
  ],
  usex:[
     { required: true, message: '请输入性别', trigger: 'blur' },
  
  ],
  upassword:[
    {required: true, message: '请输入密码', trigger: 'blur'},
      { min: 3, max: 8, message: '长度3-8', trigger: 'blur' },
  ],
  upassword1:[
    {required: true, message: '请输入密码', trigger: 'blur'},
      { min: 3, max: 8, message: '长度3-8', trigger: 'blur' },
  ],
  newPassWord:[
    {required: true, message: '请输入密码', trigger: 'blur'},
      { min: 3, max: 8, message: '长度3-8', trigger: 'blur' },
  ],
  email:[
  { required: true, message: '请输入邮箱地址', trigger: 'blur' },  
    { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }, 
  ],
  uphone: [  
    { required: true, message: '请输入电话号码', trigger: 'blur' },  
    { pattern: /^(\+\d{1,2}\s?)?(1[3-9]\d{9})$/, message: '请输入正确的电话号码格式', trigger: 'blur' }

  ],  
};
//点击登录执行的代码
const login=async ()=>{
  isLoginVisible.value=true;

}
var user1=ref({uname:'',usex:'',uphone:'',email:'',img:''})
var user2=ref({uname:'',usex:'',uphone:'',email:'',img:''})

//登录
var yanZheng=ref(false)
const Login=async ()=>{
  //展现验证码模态框
   isShow.value = true

   isLoginVisible.value=false;
  
}

//用户通过了验证
const success = async(msg) => {
  isShow.value = false
 yanZheng.value=true
 console.log(yanZheng.value)
  console.log('验证通过' + msg)

if(yanZheng.value){
  try{
  var result=await axiosAPI.loginFun(LoginUser.value);
  // if(result!=1){
  //   ElMessage({
  //       message: '注册失败',
  //       type: 'fail',
  //     })
  // }
 }catch(error){
  ElMessage({ type: 'error', message: '账号被封禁'  }); 
 }
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
  var  chauser= await axiosAPI.logina(LoginUser.value);
    console.log(chauser);
  sessionStorage.setItem("user",JSON.stringify(chauser))
    isLoginVisible.value=false;
     user1 =JSON.parse(sessionStorage.getItem("user"))
     user2 =JSON.parse(sessionStorage.getItem("user"))
    
    // location.reload()
}

  //通过验证跳转到home首页
// $router.push('/home')
}

//用户点击遮罩层，关闭模态框
const close = () => {
  isShow.value = false
}
//用户验证失败
const fail = () => {
  console.log('验证失败')
}


// var user1 =JSON.parse(sessionStorage.getItem("user"))
// console.log(user1)

  //重新渲染
  onMounted(function(){
    console.log("加载")
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




     user1.value =JSON.parse(sessionStorage.getItem("user"))
     user2.value =JSON.parse(sessionStorage.getItem("user"))
  })
  const userInfo=()=>{
    console.log("详细信息")
    info.value=true;
  }
//退出登录
  const loginOut=()=>{
  ElMessageBox.confirm('确认退出吗？')
  .then(() => {
    sessionStorage.removeItem("user")
   //
    route.push("/")
    location.reload()
  // this.$route.go(0)
  })
  .catch(() => {
      // catch error
  })
  
}
//注册
const form1=ref({
   

  });


//修改密码
const form2=ref({
  });
const dialog1=ref(false);
  const  register =()=>{
    console.log("注册")
    dialog1.value=true;
  }

const addUser=async(form1)=>{
  var verify = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
  var verifyh = /^(\+\d{1,2}\s?)?(1[3-9]\d{9})$/;
if(!verify.test(form1.email)){
  ElMessage({
        message: '请输入正确的邮箱',
        type: 'fail',
      })
      return;
}else if(!verifyh.test(form1.uphone)){
  ElMessage({
        message: '请输入正确的电话',
        type: 'fail',
      })
      return;
}
else{
  let data=await axiosAPI.addUser(form1)
  console.log(form1)
  if(data==3){
   
      ElMessage({
        message: '注册失败',
        type: 'fail',
      })
    }else{
    
      ElMessage({
        
        message: '注册成功',
        type: 'success',
        
      })
      dialog1.value=false;
    }
  }
}

// 图片上传地址 - 可设置为全局变量
const action = ref('http://localhost:80/file')
// 图片获取地址前缀 - 可设置为全局变量
const picturePreUrl = ref('http://localhost:80')

//编辑用户信息
const editUser=async()=>{
  editInfo.value=true
}


//确认修改
const  eidtSubmit=async()=>{
console.log(user2.value)
  await axiosAPI.editUser(user2.value)

  var  chauser= await axiosAPI.logina(user2.value);
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
//管理地址
const  addVisible=ref(false)
const  addVisible1=ref(false)
const addEdit=ref({})
const addList=ref([])
const openEdit1 = async() => {
    user1.value =JSON.parse(sessionStorage.getItem("user"))
    console.log(user1.value.uid)
    addList.value=  await axiosAPI.useraddress({userid:user1.value.uid})
console.log(addEdit.value)
    addVisible1.value = true;
  }

  const openEdit=async(item)=>{
    addEdit.value = {};
    addEdit.value = item;
    console.log(addEdit.value.details);
    addVisible.value = true;
  }
//---点击提交
const addsave = async () => {
    var data = await axiosAPI.addEdit({
      id: addEdit.value.id,
      userid: addEdit.value.userid,
      province: addEdit.value.province,
      city: addEdit.value.city,
      area: addEdit.value.area,
      details: addEdit.value.details,
      def: addEdit.value.def
    });
    if (data == 1) {
      ElMessage({
        type: 'success',
        message: '修改成功',
      })
      addVisible.value = false;
      location.reload();
    } else {
      ElMessage({
        type: 'error',
        message: '修改失败',
      })
    }
  }

</script>

  <template>
        <el-dialog v-model="addVisible1" title="Shipping address" style="width: 400px;">
        <div v-for="item in addList" :key="item" class="text item">
                <el-icon><star-filled /></el-icon>
                <el-tooltip class="box-item" effect="dark"
                  :content="item.province + ' ' + item.city + ' ' + item.area + ' ' + item.details" placement="top-end">
                  {{ item.province + ' ' + item.city + ' ' + item.area + ' ' + item.details }}
                </el-tooltip>
                <el-button text style="margin-left: 20px;" @click="openEdit(item)">管理</el-button>
              </div>
            </el-dialog>
    <!--管理地址弹出层-->

    <el-dialog v-model="addVisible" title="Shipping address" style="width: 400px;">
      <el-form :model="addEdit">
        <el-form-item label="省:" style="margin-left: 20px;">
          <el-input v-model="addEdit.province" autocomplete="off" style="margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="市:" style="margin-left: 20px;">
          <el-input v-model="addEdit.city" autocomplete="off" style="margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="区:" style="margin-left: 20px;">
          <el-input v-model="addEdit.area" autocomplete="off" style="margin-left: 20px;" />
        </el-form-item>
        <el-form-item label="详细地址:">
          <el-input v-model="addEdit.details" autocomplete="off" />
        </el-form-item>
        <el-form-item label="默认地址:">
          <el-radio-group v-model="addEdit.def" class="ml-4">
            <el-radio :label="0">否</el-radio>
            <el-radio :label="1">是</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="danger" @click="addDel" style="float: left;">删除</el-button>
          <el-button @click="addVisible = false">取消</el-button>
          <el-button type="success" @click="addsave">提交</el-button>
        </span>
      </template>
    </el-dialog>
 <!-- 注册窗口 -->
 <el-drawer
    ref="drawerRef"
    v-model="dialog1"
    title="注册"
    :before-close="handleClose"
    direction="ltr"
    class="demo-drawer"
  >
    <div class="demo-drawer__content">
      <el-form :model="form1" :rules="rules" >
        <el-form-item label="图片" :label-width="formLabelWidth">
      <loadimg v-model:picture="form1.img" :action="action" :picturePreUrl="picturePreUrl"></loadimg>
    </el-form-item>
        <el-form-item label="昵称" :label-width="formLabelWidth" prop="uname">
          <el-input v-model="form1.uname" autocomplete="off" />
        </el-form-item>
       
        <el-form-item label="性别" :label-width="formLabelWidth" prop="usex">
          <el-input v-model="form1.usex" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth" prop="email">
          <el-input v-model="form1.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth" prop="uphone">
          <el-input v-model="form1.uphone" autocomplete="off" />
        </el-form-item>
        
        <el-form-item label="密码" :label-width="formLabelWidth " prop="upassword">
          <el-input v-model="form1.upassword"   type="password" />
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth " prop="upassword1" >
          <el-input v-model="form1.upassword1"  type="password" />
        </el-form-item>
      </el-form>
      <div class="demo-drawer__footer">
        <el-button type="primary"  @click="addUser(form1)">注册</el-button>
      </div>
    </div>
  </el-drawer>
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
        <el-button class="button" text @click="openEdit1">管理地址</el-button>
    </template>

    </el-card>  
  </div>
</el-drawer>




  <!-- 弹出的编辑对话框组件 -->
  <el-dialog v-model="editInfo" title="编辑窗口">
    <el-form :model="user2" :rules="rules" >
      <el-form-item label="图片" :label-width="formLabelWidth">
      <loadimg v-model:picture="user2.img" :action="action" :picturePreUrl="picturePreUrl"></loadimg>
    </el-form-item>
      <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="user2.uname"  />
      </el-form-item>
     
      <el-form-item label="性别" :label-width="formLabelWidth">
        <el-input v-model="user2.usex" autocomplete="off" />
      </el-form-item>
      <el-form-item label="联系方式" :label-width="formLabelWidth">
        <el-input v-model="user2.uphone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电子邮箱" :label-width="formLabelWidth">
        <el-input v-model="user2.email" autocomplete="off" />
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


  <div id="app" name="app">
    <el-container>
      <!-- 顶部导航栏 -->
      <div class="topbar">
        <div class="nav">
          <ul>
            <li v-if="user1==null">
              <el-button type="text" @click="login">登录</el-button>
              <span class="sep">|</span>
              <el-button type="text" @click="register">注册</el-button>
            </li>
            <li v-else>
              欢迎
             
              <el-avatar :size="25"  @error="errorHandler" @click="userInfo">
                <img :src="user1.img"/> 
              </el-avatar>
              {{ user1.uname }}
            </li>
            <li>
              <router-link to="/order">我的订单</router-link>
            </li>
            <li>
              <router-link to="/collect">我的收藏</router-link>
            </li>
            <li>
              <router-link to="/history">历史记录</router-link>
            </li>
            <li :class="getNum > 0 ? 'shopCart-full' : 'shopCart'">
              <router-link to="/shoppingCart">
                <i class="el-icon-shopping-cart-full"></i> 购物车
               
              </router-link>
            </li>
          </ul>
        </div>
      </div>
      <!-- 顶部导航栏END -->

      <!-- 顶栏容器 -->
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          active-text-color="#409eff"
          router
        >
          <div class="logo">
            <router-link to="/">
              <img src="./assets/imgs/logo.png" alt />
            </router-link>
          </div>
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/goods">全部商品</el-menu-item>
          <el-menu-item index="/about">关于我们</el-menu-item>

          <!-- <div class="so">
            <el-input placeholder="请输入搜索内容" v-model="search">
              <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
            </el-input>
          </div> -->
        </el-menu>
      </el-header>
      <!-- 顶栏容器END -->

      <!-- 登录模块 -->
      <div id="myLogin">
    <el-dialog title="登录" width="300px" v-model="isLoginVisible" >
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
        </el-form-item>
          <!-- 记住密码 -->
          <el-form-item class="isChecked">
                  <!-- `checked` 为 true 或 false -->
                  <el-checkbox v-model="LoginUser.checked" class="remeberPwd">记住密码</el-checkbox>
              </el-form-item>
        <el-form-item style="position: relative;">
          <el-button size="medium" type="primary" @click="Login" >登录</el-button>
          <Vcode :show="isShow" @success="success" @close="close" @fail="fail" :img="[Img]" style="position: absolute; top: 0; left: 0; z-index: 9999;"></Vcode>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
      <!-- 注册模块 -->
      <MyRegister :register="register" @fromChild="isRegister"></MyRegister>

      <!-- 主要区域容器 -->
      <el-main>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-main>
      <!-- 主要区域容器END -->

      <!-- 底栏容器 -->
      <el-footer>
        <div class="footer">
          <div class="ng-promise-box">
            <div class="ng-promise">
              <p class="text">
                <a class="icon1" href="javascript:;">7天无理由退换货</a>
                <a class="icon2" href="javascript:;">满99元全场免邮</a>
                <a class="icon3" style="margin-right: 0" href="javascript:;">100%品质保证</a>
              </p>
            </div>
          </div>
          <div class="github">
            <a href="https://github.com/hai-27/vue-store" target="_blank">
              <div class="github-but"></div>
            </a>
          </div>
          <div class="mod_help">
            <p>
              <router-link to="/">首页</router-link>
              <span>|</span>
              <router-link to="/goods">全部商品</router-link>
              <span>|</span>
              <router-link to="/about">关于我们</router-link>
            </p>
            <p class="coty">商城版权所有 &copy; 2012-2021</p>
          </div>
        </div>
      </el-footer>
      <!-- 底栏容器END -->
    </el-container>
  </div>


  <!--重新渲染-->

</template>



<style scoped>
/* 全局CSS */

* {
  padding: 0;
  margin: 0;
  border: 0;
  list-style: none;
}
#myLogin .el-form-item:last-child {
  /* 设置验证码界面所在元素的 z-index 值为较高的值 */
  z-index: 9999;
}

#myLogin .el-button {
  /* 设置登录按钮的 z-index 值为较低的值 */
  z-index: 1;
}
.vcode {
  z-index: 9999; /* 添加这一行 */
}
#app .el-header {
  padding: 0;
}
#app .el-main {
  min-height: 300px;
  padding: 20px 0;
}
#app .el-footer {
  padding: 0;
}
a,
a:hover {
  text-decoration: none;
}
/* 全局CSS END */

/* 顶部导航栏CSS */
.topbar {
  height: 40px;
  background-color: #3d3d3d;
  margin-bottom: 20px;
}
.topbar .nav {
  width: 1225px;
  margin: 0 auto;
}
.topbar .nav ul {
  float: right;
}
.topbar .nav li {
  float: left;
  height: 40px;
  color: #b0b0b0;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  margin-left: 20px;
}
.topbar .nav .sep {
  color: #b0b0b0;
  font-size: 12px;
  margin: 0 5px;
}
.topbar .nav li .el-button {
  color: #b0b0b0;
}
.topbar .nav .el-button:hover {
  color: #fff;
}
.topbar .nav li a {
  color: #b0b0b0;
}
.topbar .nav a:hover {
  color: #fff;
}
.topbar .nav .shopCart {
  width: 120px;
  background: #424242;
}
.topbar .nav .shopCart:hover {
  background: #fff;
}
.topbar .nav .shopCart:hover a {
  color: #ff6700;
}
.topbar .nav .shopCart-full {
  width: 120px;
  background: #ff6700;
}
.topbar .nav .shopCart-full a {
  color: white;
}
/* 顶部导航栏CSS END */

/* 顶栏容器CSS */
.el-header .el-menu {
  max-width: 1225px;
  margin: 0 auto;
}
.el-header .logo {
  height: 60px;
  width: 189px;
  float: left;
  margin-right: 100px;
}
.el-header .so {
  margin-top: 10px;
  width: 300px;
  float: right;
}
/* 顶栏容器CSS END */

/* 底栏容器CSS */
.footer {
  width: 100%;
  text-align: center;
  background: #2f2f2f;
  padding-bottom: 20px;
}
.footer .ng-promise-box {
  border-bottom: 1px solid #3d3d3d;
  line-height: 145px;
}
.footer .ng-promise-box {
  margin: 0 auto;
  border-bottom: 1px solid #3d3d3d;
  line-height: 145px;
}
.footer .ng-promise-box .ng-promise p a {
  color: #fff;
  font-size: 20px;
  margin-right: 210px;
  padding-left: 44px;
  height: 40px;
  display: inline-block;
  line-height: 40px;
  text-decoration: none;
  background: url("./assets/imgs/us-icon.png") no-repeat left 0;
}
.footer .github {
  height: 50px;
  line-height: 50px;
  margin-top: 20px;
}
.footer .github .github-but {
  width: 50px;
  height: 50px;
  margin: 0 auto;
  background: url("./assets/imgs/github.png") no-repeat;
}
.footer .mod_help {
  text-align: center;
  color: #888888;
}
.footer .mod_help p {
  margin: 20px 0 16px 0;
}

.footer .mod_help p a {
  color: #888888;
  text-decoration: none;
}
.footer .mod_help p a:hover {
  color: #fff;
}
.footer .mod_help p span {
  padding: 0 22px;
}
/* 底栏容器CSS END */
</style>
