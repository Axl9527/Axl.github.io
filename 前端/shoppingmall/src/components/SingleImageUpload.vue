<template>
    <el-upload :on-success="handleUploadSuccess" :on-error="handleUploadError" :show-file-list="false" :action="action"
        class="avatar-uploader">
       
        <img v-if="picture" :src="picture" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>



</template>

<script setup>
 import {ElMessage} from 'element-plus'
 import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
let props = defineProps({
    picture: {
        type: String,
        default: '',
    },
    action: {
        type: String,
        default: 'http://localhost:8080/common/file',
    },
    picturePreUrl: {
        type: String,
        default: 'http://localhost:8080',
    }
})
let emit = defineEmits(['update:picture'])


// 文件上传失败的时的钩子
let handleUploadError = (error, file, fileList) => {
    console.log(error);

    ElMessage.error('上传失败!')
}
// 文件上传成功时的钩子
let handleUploadSuccess = (response, file, fileList) => {
    let { code, data, msg } = response;
    if (code === 200) {
        const pictureUrl = props.picturePreUrl + data;
        // 预览图片
        emit('update:picture', pictureUrl);
        // 信息提示
        ElMessage.success(msg);
    }
}
</script>

<style  scoped>
.avatar-uploader-icon{
    border-radius: 5px; 
    border: 1px dashed #8c939d;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
  }
   
  .avatar-uploader-icon:hover{
    border: 1px dashed #409eff;
     
  }
  .avatar-uploader .el-upload {
  border: 1px dashed #409eff;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}

</style> 