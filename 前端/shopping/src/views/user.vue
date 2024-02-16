<script setup>
import { ref, onMounted } from 'vue';
import axiosAPI from '../api'
import { ElMessage, ElMessageBox } from 'element-plus';
import {
  Check,
  Delete,
  Edit,
  Plus,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'

//表格显示的数据
const userList=ref([])

//查询输入框
const queryObj= ref({});

// 查询列表方法
const searchSubmit = async () => {
  //获取查询数据--带参数
  //将获取的数据付给表格绑定的对象
  var result = await axiosAPI.userList(queryObj.value);
  userList.value = result.list;
  page.value.total = result.count;

}
//挂载
onMounted(async function () {

  var data = await axiosAPI.userList(page.value);
  userList.value = data.list;
  page.value.total = data.count;


 

});


//分页的处理
//记录分页数据的对象 当前页码 一页显示的条数  总条数
const page = ref({ page: 1, size: 10, total: 100 });
/* 每一次查询执行都要给total重新赋值 */
//切换分页按钮
const handleCurrentChange = async (pageNum) => {
  //转换页码
  page.value.page = pageNum;
  //将参数合并成一个
  var obj = Object.assign(page.value, queryObj.value);
  //查询数据
  var result = await axiosAPI.userList(obj);
  //将查询的数据设置指定的对象
  userList.value = result.list;
  page.value.total = result.count;
};

const harvestVisiable=ref(false)
const harvestVisiable2=ref(false)
var userid=ref()
const handleEdit=(index, row)=>{
  userid.value=row.uid
  console.log(userid.value)
  harvestVisiable.value=true
}
const handleEdit2=(index, row)=>{
  userid.value=row.uid
  console.log(userid.value)
  harvestVisiable2.value=true
}
const harvestSubmit=async()=>{
  await axiosAPI.editUser({uid:userid.value,ustate:2})
  harvestVisiable.value=false 
  location. reload()
  
}
const harvestSubmit2=async()=>{
  await axiosAPI.editUser({uid:userid.value,ustate:1})
  harvestVisiable2.value=false 
  location. reload()
}

</script>


<template>
<el-form :inline="true" :model="formInline" class="demo-form-inline">
  <!-- 查询 -->
  <el-form-item label="名称" :label-width="formLabelWidth">
    <el-input v-model="queryObj.uname" placeholder="请输入名称" style="width: 100px;" />
    <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px;">查询</el-button>

  </el-form-item>
 
</el-form>

  
      <!-- 表格 -->
  <el-table :data="userList" style="width: 100%">
   
    <el-table-column prop="uname" label="用户名" width="150" text-align: center />
    <el-table-column prop="upassword" label="密码" width="150"  />
    
    <el-table-column prop="uphone" label="电话" width="150" text-align: center />
    <el-table-column prop="email" label="邮箱" width="150" text-align: center />
  



    <el-table-column label="操作">
      <template #default="scope">
      
          <div  v-if="scope.row.ustate==1">
        <el-button size="small" type="danger" @click="handleEdit(scope.$index, scope.row)">封禁</el-button>
        </div>
        <div  v-if="scope.row.ustate==2">
        <el-button size="small" type="success" @click="handleEdit2(scope.$index, scope.row)">解封</el-button>
        </div>
     
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>

  </el-table>
  <!--封禁-->
  <el-dialog v-model="harvestVisiable" title="封禁" width="30%" center>
    <span>
      确定封禁吗？
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
<!--解封-->
  <el-dialog v-model="harvestVisiable2" title="解封" width="30%" center>
    <span>
      确定解封吗？
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="harvestVisiable2 = false">取消</el-button>
        <el-button type="primary" @click="harvestSubmit2">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
   <!-- 分页工具条 -->
   <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[10, 20, 50]" :small="small"
    :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="page.total"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
</template>