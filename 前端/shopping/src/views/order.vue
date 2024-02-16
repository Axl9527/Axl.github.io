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
const orderList=ref([])

//查询输入框
const queryObj= ref({});

// 查询列表方法
const searchSubmit = async () => {
  //获取查询数据--带参数
  //将获取的数据付给表格绑定的对象
  var result = await axiosAPI.orderList(queryObj.value);
  orderList.value = result.list;
  page.value.total = result.count;

}
//挂载
//选择器
var listOne = ref([]);
listOne.value.push("");
onMounted(async function () {
    var list = await axiosAPI.orderListP();
  console.log("list.value");
  console.log(list);
  console.log("list.value");
  console.log("未进入");

for (var i = 0; i < list.length; i++) {
    console.log("进入");

    listOne.value.push(list[i]);
  
}
console.log(listOne.value)

  var data = await axiosAPI.orderList(page.value);
  orderList.value = data.list;
  page.value.total = data.count;

  console.log(orderList.value)

 

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
  var result = await axiosAPI.orderList(obj);
  //将查询的数据设置指定的对象
  orderList.value = result.list;
  page.value.total = result.count;
};

//编辑窗口的显示
const editGoodsVisible = ref(false);

//定义修改弹出框的内容
const editGoodsObj = ref({});


//开启编辑窗口
const openEidtWin = () => {
  editGoodsVisible.value = true;
}
//关闭编辑窗口
const closeEidtWin = () => {
  editGoodsVisible.value = false;
}


//明细表
var user =JSON.parse(sessionStorage.getItem("user"))

var detail=ref([])

const handleEdit =async (index,row) => {
  openEidtWin();

  var data = await axiosAPI.queryOrder();
  detail.value=[]
  for(var i=0;i<data.length;i++){
  if(row.id==data[i].id){
    console.log(111)
   detail.value=data[i].list
  }
}
  console.log(row.id)
  console.log(detail.value)
}

//发货
const handleDelete=async(index,row)=>{
  var date1 =await axiosAPI.fahuo({id:row.id,state:2})

  var data = await axiosAPI.orderList(page.value);
  orderList.value = data.list;
  page.value.total = data.count;

  
}




</script>


<template>
<el-form :inline="true" :model="formInline" class="demo-form-inline">
  <!-- 查询 -->
  <el-form-item label="名称" :label-width="formLabelWidth">
    <el-select   v-model="queryObj.uid" filterable placeholder="Select">
      <el-option v-for="item in listOne" :key="item.uid" :label="item.uname" :value="item.uid" />
    </el-select>
    <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px;">查询</el-button>

  </el-form-item>
 
</el-form>
  
      <!-- 表格 -->
  <el-table :data="orderList" style="width: 100%">
   
    <el-table-column prop="code" label="订单编号" width="120" text-align: center />
    <el-table-column prop="user.uname" label="用户" width="120"  />
    <el-table-column prop="oaddress" label="地址" width="120"  />
    <el-table-column prop="paymoney" label="付款金额" width="120"  />
    <el-table-column prop="ordertime" label="订单时间" width="120" text-align: center />
    <el-table-column prop="paytime" label="付款时间" width="120" text-align: center />
    <el-table-column prop="state" label="状态" width="120" >
    <template #default="scope">
    <div v-if="scope.row.state==0" >
                <span style="color: red;">待支付</span>
          
              </div>
              <div v-else-if="scope.row.state==1" >
                <span style="color: #ff6700;">待发货</span>
              </div>
              <div v-else-if="scope.row.state==2" >
                <span style="color: brown;">待收货</span>
               
              </div>
              <div v-else-if="scope.row.state==3" >
                <span style="color: chartreuse;">已发货</span>
              </div>
            </template>
            </el-table-column>
    <el-table-column prop="deliverytime" label="发货时间" width="120" text-align: center />
    <el-table-column prop="endtime" label="结束时间" width="120" text-align: center />

    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">查看

        </el-button>
        <div  v-if="scope.row.state==1">
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">发货</el-button>
        </div>
      </template>
    </el-table-column>

  </el-table>
   <!-- 分页工具条 -->
   <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[10, 20, 50]" :small="small"
    :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="page.total"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />

<!-- 弹出的明细对话框组件 -->
<el-dialog v-model="editGoodsVisible" title="明细窗口">
    <el-table :data="detail" style="width: 100%">
   
  
   <el-table-column prop="good.name" label="商品名称" width="120"  />
   <el-table-column prop="good.img" label="图片" width="120"  >
   <template #default="scope">
         <img :src="scope.row.good.img" width="50" />    
      </template>
   </el-table-column>
   <el-table-column prop="count" label="件数" width="120"  />
   <el-table-column prop="good.price" label="单价" width="120" text-align: center />
 

 

 </el-table>
  </el-dialog>


</template>