<script setup>
import { ref,onMounted } from 'vue';
import MyList from "../components/MyList.vue"
import API from '../api'
import { subMenuProps } from 'element-plus';


//查询
const queryObj= ref({});
// 查询列表方法
const searchSubmit = async () => {
  //获取查询数据--带参数
  //将获取的数据付给表格绑定的对象
  var result = await API.goodsList(queryObj.value);
  totalList.value = result.list;
  page.value.total = result.count;

}

const totalList=ref([]);
const categoryLists=ref();
const categoryList=ref([]);
onMounted(async function(){
    var data =await API.goodsList(page.value)
    console.log(data)
    
    totalList.value=data.list;
    page.value.total=data.count;


    var data1 =await API.categoryList(page.value)
    console.log(data1)
    categoryLists.value=data1.list;
    console.log(categoryLists.value.length)
    for(var i=0;i<categoryLists.value.length;i++){
      console.log(categoryLists.value[i].pid)
      if(categoryLists.value[i].pid!=0){
       // console.log("bbbbbbbbbbbbbbb")
        categoryList.value.push(categoryLists.value[i])

        activeName.value.push(categoryLists.value[i].id)
      }
    }
    //console.log(categoryList.value[1].id)
  
   console.log( activeName.value)
})

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
  var result = await API.goodsList(obj);
//   //将查询的数据设置指定的对象
   totalList.value = result.list;
   page.value.total = result.count;
};

/**二级分类 */
const activeName =ref([]);




//二级分类

const xfenlei= async(TabsPaneContext,Event)=>{
  console.log(TabsPaneContext.props.name)
  console.log(Event)
  
  var count=0
  var data =await API.goodsList({})
    console.log(data.list.length)
    totalList.value=[]
    for(var j=0;j<data.list.length;j++){
      console.log(data.list[j].pid)
      console.log(TabsPaneContext.props.name)
      if(data.list[j].pid==TabsPaneContext.props.name){
        console.log(data.list[j])
        totalList.value.push(data.list[j]);
        count=count+1
      }
    }
   // totalList.value=data.list;
  
    page.value.total=count;
}




</script>
<template>
 <div class="goods" id="goods" name="goods">
    <!-- 面包屑 -->
    <div class="breadcrumb">
      
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item to="/">首页</el-breadcrumb-item>
        <el-breadcrumb-item>全部商品</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">搜索</el-breadcrumb-item>
        <el-breadcrumb-item v-else>分类</el-breadcrumb-item>
        <el-breadcrumb-item v-if="search">{{search}}</el-breadcrumb-item>
          <!-- 查询 -->
  <el-form-item label="名称" :label-width="formLabelWidth">
    <el-input v-model="queryObj.name" placeholder="请输入名称" style="width: 100px;margin-left: 800px;" />
    <el-button type="primary" :icon="Search" @click="searchSubmit" style="margin-left: 10px; background-color:#3d3d3d;">查询</el-button>
  </el-form-item>
      </el-breadcrumb>
    </div>
    <!-- 面包屑END -->

    <!-- 分类标签 -->
     <div class="nav">
      <div class="product-nav">
        <div class="title" >分类</div>
        <el-tabs v-model="activeName" type="card" @tab-click="xfenlei">
          <el-tab-pane
            v-for="item in categoryList"
            :key="item.id"
            :label="item.name"
            :name="''+item.id"
           
          />
        </el-tabs>
      </div>
    </div> 
    <!-- 分类标签END -->

    <!-- 主要内容区 -->
    <div class="main">
      <div class="list">
        <MyList :list="totalList" ></MyList>
      
      </div>
     <!-- 分页工具条 -->
  <el-pagination v-model:current-page="page.page" v-model:page-size="page.size" :page-sizes="[10, 20, 50]" :small="small"
    :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="page.total"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      <!-- 分页END -->
    </div>
    <!-- 主要内容区END -->
  </div>
    <router-view></router-view>
</template>


<style scoped>

.goods {
  background-color: #f5f5f5;
}
/* 面包屑CSS */
.el-tabs--card .el-tabs__header {
  border-bottom: none;
}
.goods .breadcrumb {
  height: 50px;
  background-color: white;
}
.goods .breadcrumb .el-breadcrumb {
  width: 1225px;
  line-height: 30px;
  font-size: 16px;
  margin: 0 auto;
}
/* 面包屑CSS END */

/* 分类标签CSS */
.goods .nav {
  background-color: white;
}
.goods .nav .product-nav {
  width: 1225px;
  height: 40px;
  line-height: 40px;
  margin: 0 auto;
}
.nav .product-nav .title {
  width: 50px;
  font-size: 16px;
  font-weight: 700;
  float: left;
}
/* 分类标签CSS END */

/* 主要内容区CSS */
.goods .main {
  margin: 0 auto;
  max-width: 1225px;
}
.goods .main .list {
  min-height: 650px;
  padding-top: 14.5px;
  margin-left: -13.7px;
  overflow: auto;
}
.goods .main .pagination {
  height: 50px;
  text-align: center;
}
.goods .main .none-product {
  color: #333;
  margin-left: 13.7px;
}
/* 主要内容区CSS END */
</style>