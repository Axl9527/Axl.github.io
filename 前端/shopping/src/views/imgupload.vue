<template>
    <el-upload action="/api/file"   :on-success='easysucces' list-type="picture-card" :auto-upload="true">
      <el-icon><Plus /></el-icon>
  
      <template #file="{ file }">
        <div>
          <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
          <span class="el-upload-list__item-actions">
            <span
              class="el-upload-list__item-preview"
              @click="handlePictureCardPreview(file)"
            >
              <el-icon><zoom-in /></el-icon>
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleDownload(file)"
            >
              <el-icon><Download /></el-icon>
            </span>
            <span
              v-if="!disabled"
              class="el-upload-list__item-delete"
              @click="handleRemove(file)"
            >
              <el-icon><Delete /></el-icon>
            </span>
          </span>
        </div>
      </template>
    </el-upload>
  
    <el-dialog v-model="dialogVisible">
      <img w-full :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>

    <el-input v-model="goods.goods_name" placeholder="Please input" />
    <button @click="submit">提交商品数据</button>
  </template>
  <script  setup>
  import { ref } from 'vue'
  import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
  
  import  UploadFile  from 'element-plus'
  import axiosAPI from '../api'
  const fileurl=ref("");

  const dialogImageUrl = ref('')
  const dialogVisible = ref(false)
  const disabled = ref(false)
  const goods=ref({
    name:'',
    img:''
  })
const submit=async()=>{

  var data=await axiosAPI.goodsAdd(goods.value);
}
//记录文件地址
  const easysucces=(data,res2,res3)=>{
 
    goods.value.img= 'http://127.0.0.1:80' + data.data
    
    console.log(goods.value)
  }
  const handleRemove = ( UploadFile) => {
    console.log(file)
  }
  
  const handlePictureCardPreview = (UploadFile) => {
    dialogImageUrl.value = file.url;
    dialogVisible.value = true
  }
  
  const handleDownload = ( UploadFile) => {
    console.log(file)
  }

  </script>