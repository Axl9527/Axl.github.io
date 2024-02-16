<script setup>
import { ref, onMounted } from 'vue';
import axiosAPI from '../api'
import { ElMessage, ElMessageBox } from 'element-plus';
import ImageUpload from '../components/ImageUpload.vue'
import {
  Check,
  Delete,
  Edit,
  Plus,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
import wangEdit from './wangEdit.vue'

import loadimg from '../components/SingleImageUpload.vue'

//定义弹出窗文本标签的宽度
const formLabelWidth = '50px'
//表格中要显示的数据
const goodsList = ref([]);
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

//将对应行内容传入修改框,用于传入服务端
const handleEdit = (index, row) => {
  openEidtWin();

  editGoodsObj.value.code = row.code;
  editGoodsObj.value.name = row.name;
  editGoodsObj.value.info = row.info;
  editGoodsObj.value.oldprice = row.oldprice;
  editGoodsObj.value.price = row.price;


  editGoodsObj.value.id = row.id;
}

//提交编辑数据
const eidtSubmit = async () => {
  //表单验证
  //提交数据
  var obj = await axiosAPI.goodsEidt(editGoodsObj.value);

  //提示成功
  ElMessage({
    type: 'success',
    message: '编辑成功',
  })
  //将编辑的数据在表格顶部展示

  var data = await axiosAPI.goodsList(page.value);
  goodsList.value = data.list;
  page.value.total = data.count;
  editGoodsVisible.value = false;
}

//新增窗口
const addGoodsVisible = ref(false);

//新增数据
const addGoodsObj = ref({});

//开启新增窗口
//显示对话框
const openAddWin = () => {
  addGoodsVisible.value = true;

  //清空表单数据
  addGoodsObj.value = { };
}
//保存新增数据
const addSubmit = async () => {
  //表单提交
  console.log(addGoodsObj.value.pid);
  var obj = await axiosAPI.goodsAdd(addGoodsObj.value);
  //提示成功
  ElMessage({
    type: 'success',
    message: '保存成功',

  })
  var data = await axiosAPI.goodsList(page.value);
  goodsList.value = data.list;
  page.value.total = data.count;
  addGoodsVisible.value = false;
}


//删除
const handleDelete = (index, row) => {

  //提示是否要删除防止误删
  ElMessageBox.confirm(
    '确定要删除此数据么?',
    '警告,警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
      //点击确定执行的按钮
      //执行删除--方式执行删除操作
      //提示删除成功
      var count = await axiosAPI.goodsDel(row.id);
      ElMessage({
        type: 'success',
        message: '删除成功'

      })
      //更新数据
      goodsList.value.splice(index, 1);
      var data = await axiosAPI.goodsList(page.value);
      goodsList.value = data.list;
      page.value.total = data.count;


    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })


}

//查询
const queryObj= ref({});

// 查询列表方法
const searchSubmit = async () => {
  //获取查询数据--带参数
  //将获取的数据付给表格绑定的对象
  var result = await axiosAPI.goodsList(queryObj.value);
  goodsList.value = result.list;
  page.value.total = result.count;

}



//二级分类
var listOne = ref([]);
listOne.value.push("");
//挂载
onMounted(async function () {
  var list = await axiosAPI.goodsListP();
  console.log("list.value");
  console.log(list);
  console.log("list.value");
for (var i = 0; i < list.length; i++) {


    listOne.value.push(list[i]);
  
}
console.log("listOne.value");
  console.log(listOne.value);
  console.log("listOne.value");
  var data = await axiosAPI.goodsList(page.value);

  console.log("---------")
  console.log(data)
  goodsList.value = data.list;
  page.value.total = data.count;


  console.log(goodsList.value);

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
  var result = await axiosAPI.goodsList(obj);
  //将查询的数据设置指定的对象
  goodsList.value = result.list;
  page.value.total = result.count;
};

const url="http://localhost:80"




// 1111
// 图片上传地址 - 可设置为全局变量
const action = ref('http://localhost:80/file')
// 图片获取地址前缀 - 可设置为全局变量
const picturePreUrl = ref('http://localhost:80')


//价格验证
const rules= ref({
  oldprice: [
    { required: true, message: '请输入价格', trigger: 'blur' },
    {pattern: /^(([1-9]{1}\d{0,9})|(0{1}))(\.\d{1,2})?$/,
message: "请输入合法的金额数字，最多两位小数",
trigger: "blur"},
  ],
  price: [
    { required: true, message: '请输入价格', trigger: 'blur' },
    {pattern: /^(([1-9]{1}\d{0,9})|(0{1}))(\.\d{1,2})?$/,
message: "请输入合法的金额数字，最多两位小数",
trigger: "blur"},
  ],
  num: [
    { required: true, message: '请输入数量', trigger: 'blur' },
    { min: 1, max:5, message: '长度1-5', trigger: 'blur' },
  ],
})

//上下架
const handleShang=async(index, row)=>{
  await axiosAPI.shangXiaJia({id:row.id,state:0})
  location.reload()
}
const handleXia=async(index, row)=>{
  await axiosAPI.shangXiaJia({id:row.id,state:1})
  location.reload()
}

//接收wangEdit数据
const  sendEditor=(val)=>{

  addGoodsObj.value.info=val
  console.log(addGoodsObj.value.info)
}
//多图片
const fileList=ref([])
const editPictrueVisible = ref(false);
//新增数据
var pictureId=ref()
const addPictrueObj = ref({});
const handlePictrue=(index, row)=>{
  editPictrueVisible.value=true
  pictureId.value=row.id

  
}
const eidtSubmitPic=async(index, row)=>{
  console.log("商品id：",pictureId.value)
  fileList.value.forEach(element => {
      element.goodsid = pictureId.value
 });
console.log("多图片列表：",fileList.value)
 await axiosAPI.addPictrue({list:fileList.value})
 editPictrueVisible.value=false
}

</script>


<template>
<!-- <div v-for="item in goodsList">
  <img :src="url+item.img" />
</div> -->

<el-form :inline="true" :model="formInline" class="demo-form-inline"> 
  <!-- 查询 
 <el-form-item label="名称" :label-width="formLabelWidth">
    <el-input v-model="queryObj.name" placeholder="请输入名称" style="width: 100px;" />
  </el-form-item>-->
  <!-- <el-form-item label="类型" :label-width="formLabelWidth">
    <el-select   v-model="queryObj.pid" filterable placeholder="Select">
      <el-option v-for="item in listOne" :key="item.id" :label="item.name" :value="item.id" />
    </el-select> 
    <el-table-column label="操作"> -->
      <!--  <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px;">查询</el-button>-->
    
      <!--  <el-button type="success" @click="openAddWin" :icon="Plus" style="margin-left: 10px;">添加</el-button>
      
   </el-table-column>-->
    
 <!-- </el-form-item>
  <span class="footer">
  </span>--> 
</el-form>
<el-form :inline="true" :model="formInline" class="demo-form-inline">
  <!-- 查询 -->
  <el-form-item label="名称" :label-width="formLabelWidth">
    <el-input v-model="queryObj.name" placeholder="请输入花名" style="width: 100px;" />
  </el-form-item>
  <el-form-item label="类型" :label-width="formLabelWidth">
    <el-select   v-model="queryObj.pid" filterable placeholder="Select">
      <el-option v-for="item in listOne" :key="item.id" :label="item.name" :value="item.id" />
    </el-select> 
    
    <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px;">查询</el-button>
    <!--新增按钮-->
  <el-button type="success" @click="openAddWin" :icon="Plus" style="margin-left: 10px;">添加</el-button>
  </el-form-item>
 

</el-form> 
   
 
  <!-- 表格 -->
  <el-table :data="goodsList" style="width: 100%; text-align: center" >
    <!-- <el-table-column prop="code" label="编号" width="90" text-align: center /> -->
    <el-table-column prop="name" label="名称" width="90" text-align: center />
    <el-table-column  prop="img" label="图片" width="90" >
      <template #default="scope">
         <img :src="scope.row.img" width="90" />    
      </template>
    </el-table-column>    

    <el-table-column prop="oldprice" label="价格" width="80" text-align: center />
    <el-table-column prop="price" label="促销价格" width="80" text-align: center />
    <el-table-column prop="info" label="花语" width="90" text-align: center />
    <el-table-column prop="stock" label="库存" width="90" text-align: center />
    <el-table-column prop="category.name" label="类别" width="90" text-align: center />
      
    <el-table-column prop="jointime" label="添加时间" width="90" text-align: center />
    <el-table-column prop="initAdmin.name" label="创建人员" width="90" text-align: center />
    <el-table-column prop="edittime" label="修改时间" width="90" text-align: center />
    <el-table-column prop="editAdmin.name" label="修改人员" width="90" text-align: center />

    <el-table-column label="操作"  width="280">
     
      <template #default="scope">
        <el-button v-if="scope.row.state==0" size="small" @click="handleXia(scope.$index, scope.row)">下架  </el-button>
        <el-button v-if="scope.row.state==1" size="small"  type="danger"  @click="handleShang(scope.$index, scope.row)">上架  </el-button>
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑 </el-button>
        <el-button size="small" @click="handlePictrue(scope.$index, scope.row)">图片 </el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>

  </el-table>
  <!-- 分页工具条 -->
  <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[10, 20, 50]" :small="small"
    :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="page.total"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />

  <!-- 弹出的多图片对话框组件 -->
    <el-dialog v-model="editPictrueVisible" title="图片窗口">
    <el-form :model="fileList" :rules="rules">
      <el-form-item label="图片" :label-width="formLabelWidth">
        <ImageUpload v-model:fileList="fileList" :action="action" :picturePreUrl="picturePreUrl"></ImageUpload>
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
      <el-button @click="editPictrueVisible = false">取消</el-button>
      <el-button type="primary" @click="eidtSubmitPic">
        提交
      </el-button>
    </span>
  </el-dialog>
  <!-- 弹出的编辑对话框组件 -->
  <el-dialog v-model="editGoodsVisible" title="编辑窗口">
    <el-form :model="editGoodsObj" :rules="rules">
      <el-form-item label="编号" :label-width="formLabelWidth">
        <el-input v-model="editGoodsObj.code" autocomplete="off" />
      </el-form-item>
      <el-form-item label="名称" :label-width="formLabelWidth" >
        <el-input v-model="editGoodsObj.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="价格" :label-width="formLabelWidth" prop="oldprice">
        <el-input v-model="editGoodsObj.oldprice" autocomplete="off" />
      </el-form-item>
      <el-form-item label="促销价格" :label-width="formLabelWidth" prop="price">
        <el-input v-model="editGoodsObj.price" autocomplete="off" />
      </el-form-item>
      <el-form-item label="花语" :label-width="formLabelWidth">
        <el-input v-model="editGoodsObj.info" autocomplete="off" />
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
      <el-button @click="closeEidtWin">取消</el-button>
      <el-button type="primary" @click="eidtSubmit">
        提交
      </el-button>
    </span>
  </el-dialog>

  <!-- 弹出的增加对话框组件 -->
    <el-dialog v-model="addGoodsVisible" title="新增窗口">
      <el-form :model="addGoodsObj" :rules="rules">
    <el-form-item label="编号" :label-width="formLabelWidth">
      <el-input v-model="addGoodsObj.code" autocomplete="off" />
    </el-form-item>
    <el-form-item label="名称" :label-width="formLabelWidth">
      <el-input v-model="addGoodsObj.name" autocomplete="off" />
    </el-form-item>
    <el-form-item label="图片" :label-width="formLabelWidth">
      <loadimg v-model:picture="addGoodsObj.img" :action="action" :picturePreUrl="picturePreUrl"></loadimg>
    </el-form-item>
    <el-form-item label="类型" :label-width="formLabelWidth">
    <el-select v-model="addGoodsObj.pid" filterable placeholder="Select">
      <el-option v-for="item in listOne" :key="item.id" :label="item.name" :value="item.id" />
    </el-select>
  </el-form-item>
    <el-form-item label="花语" :label-width="formLabelWidth">
      <wangEdit @sendEditor="sendEditor"></wangEdit>
      <!-- <el-input v-model="addGoodsObj.info" autocomplete="off" /> -->
    </el-form-item>
    <el-form-item label="价格" :label-width="formLabelWidth">
      <el-input v-model="addGoodsObj.oldprice" autocomplete="off" />
    </el-form-item>
    <el-form-item label="促销" :label-width="formLabelWidth">
      <el-input v-model="addGoodsObj.price" autocomplete="off" />
    </el-form-item>
    <el-form-item label="库存" :label-width="formLabelWidth">
      <el-input v-model="addGoodsObj.stock" autocomplete="off" />
    </el-form-item>
  </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeAddWin">取消</el-button>
        <el-button type="primary" @click="addSubmit">
          提交
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>


<style></style>
