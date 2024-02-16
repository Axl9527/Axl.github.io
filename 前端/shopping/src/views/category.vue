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
//一级分类
var listOne = ref([]);
//表格中要显示的数据
const categoryList = ref([]);
//控制编辑窗口Category窗口显示的内容
const editCategoryVisible = ref(false);

//控制新增窗口Category窗口显示的内容
const addCategoryVisible = ref(false);

//定义弹出窗文本标签的宽度
const formLabelWidth = '50px'

//用户填写的新增Category的数据
const addCategoryObj = ref({});
const editCategoryObj = ref({ code: '', name: '' });
const queryObj = ref({ code: '', name: '' });

//开启编辑窗口
const openEidtWin = () => {
  editCategoryVisible.value = true;

}
//点击打开新增窗口
//显示对话框
const openAddWin = () => {
  addCategoryVisible.value = true;

  //清空表单数据
  addCategoryObj.value = { code: '', name: "" };

}

//关闭编辑窗口
const closeEidtWin = () => {
  editCategoryVisible.value = false;
}

//将对应行内容传入修改框
const handleEdit = (index, row) => {

  openEidtWin();
  editCategoryObj.value.code = row.code;
  editCategoryObj.value.name = row.name;

  editCategoryObj.value.id = row.id;

}
const eidtSubmit = async () => {
  //表单验证
  //提交数据
  console.log(editCategoryObj.value.id + "uuuuuu");
  var obj = await axiosAPI.categoryEidt(editCategoryObj.value);

  //提示成功
  ElMessage({
    type: 'success',
    message: '编辑成功',

  })
  //将新增的数据在表格顶部展示

  var data = await axiosAPI.categoryList(page.value);
  categoryList.value = data.list;
  page.value.total = data.count;
  editCategoryVisible.value = false;
}
//点击删除代码
const handleDelete = (index, row) => {

  //提示是否要删除防止误删,
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
      //执行删除--发送请求执行删除操作
      //提示删除成功
      var count = await axiosAPI.categoryDel(row.id);
      ElMessage({
        type: 'success',
        message: '删除成功',

      })
      //更新数据
      categoryList.value.splice(index, 1);
      var data = await axiosAPI.categoryList(page.value);
      categoryList.value = data.list;
      page.value.total = data.count;


    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })


  console.log(index);
  console.log(row);
}

// 查询列表方法
const searchSubmit = async () => {
  //获取查询数据--带参数
  //将获取的数据付给表格绑定的对象
  var result = await axiosAPI.categoryList(queryObj.value);
  categoryList.value = result.list;
  page.value.total = result.count;

}


//保存新增窗口中的数据

const addSubmit = async () => {

  //表单验证
  if (addCategoryObj.value.pid == '') {
    addCategoryObj.value.pid = 0;
  }
  //提交数据

  var obj = await axiosAPI.categoryAdd(addCategoryObj.value);
  //提示成功
  ElMessage({
    type: 'success',
    message: '保存成功',

  })
  //将新增的数据在表格顶部展示,更新数据
  //categoryList.value.unshift(obj);

  var data = await axiosAPI.categoryList(page.value);
  categoryList.value = data.list;
  page.value.total = data.count;

  addCategoryVisible.value = false;
}
const closeAddWin = () => {
  addCategoryVisible.value = false;

}

//挂载

onMounted(async function () {
  var list = await axiosAPI.categoryList();

  for (var i = 0; i < list.list.length; i++) {
    console.log("listOne.value");
    if (list.list[i].pid == 0) {
      listOne.value.push(list.list[i]);
    }
  }
  console.log(listOne.value);
  var data = await axiosAPI.categoryList(page.value);
  categoryList.value = data.list;
  page.value.total = data.count;


  console.log(categoryList.value);

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
  var result = await axiosAPI.categoryList(obj);
  //将查询的数据设置指定的对象
  categoryList.value = result.list;
  page.value.total = result.count;
};


//分类显示按钮
const open = ref(false);
</script>


<template>
 
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
  <!-- 查询 -->
  <el-form-item label="编号" :label-width="formLabelWidth">
    <el-input v-model="queryObj.code" placeholder="请输入Code" style="width: 100px;" />
  </el-form-item>
  <el-form-item label="Name" :label-width="formLabelWidth">
    <el-input v-model="queryObj.name" placeholder="请输入Name" style="width: 100px;" />
    <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px;">查询</el-button>
    <!--新增按钮-->
  <el-button type="success" @click="openAddWin" :icon="Plus" style="margin-left: 10px;">添加</el-button>
  </el-form-item>
 
</el-form> 
   


  <!-- 表格 -->
  <el-table :data="categoryList" style="width: 100%">
    <el-table-column prop="code" label="编号" width="120" text-align: center />
    <el-table-column prop="name" label="名称" width="120" text-align: center />
    <el-table-column prop="cate.name" label="类型" width="120" text-align: center />
    <el-table-column prop="time" label="添加时间" width="120" text-align: center />
    <el-table-column prop="admin.name" label="创建人员" width="120" text-align: center />
    <el-table-column prop="edittime" label="修改时间" width="120" text-align: center />
    <el-table-column prop="adminedit.name" label="修改人员" width="120" text-align: center />




    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑

        </el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>

  </el-table>
  <!-- 分页工具条 -->
  <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[10, 20, 50]" :small="small"
    :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="page.total"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
  <!-- 弹出的编辑对话框组件 -->
  <el-dialog v-model="editCategoryVisible" title="编辑窗口">
    <el-form :model="editCategoryObj">
      <el-form-item label="编号" :label-width="formLabelWidth">
        <el-input v-model="editCategoryObj.code" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Name" :label-width="formLabelWidth">
        <el-input v-model="editCategoryObj.name" autocomplete="off" />
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
  <el-dialog v-model="addCategoryVisible" title="新增窗口">
    <!--分类按钮-->
    <el-form :model="addCategoryObj">
      <el-switch v-model="open" class="ml-2" inline-prompt
        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949" active-text="二级" inactive-text="一级"
        @click="bclxChange" />


      <el-select v-model="addCategoryObj.pid" filterable placeholder="Select" v-show="open">
        <el-option v-for="item in listOne" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
      <el-form-item label="编号" :label-width="formLabelWidth">
        <el-input v-model="addCategoryObj.code" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Name" :label-width="formLabelWidth">
        <el-input v-model="addCategoryObj.name" autocomplete="off" />
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


<style scoped>
.footer{
  padding-top: 10px;
  

}

</style>