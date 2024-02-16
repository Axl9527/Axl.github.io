<template>
    <el-upload v-model:file-list="tempFileList" :action="action" list-type="picture-card"
        :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-success="handleUploadSuccess"
        :on-error="handleUploadError">
        <el-icon>
            <Plus />
        </el-icon>
    </el-upload>

    <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" />
    </el-dialog>
</template>
<script setup>
import { ref, watchEffect } from 'vue'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';



let props = defineProps({
    fileList: {
        type: Array,
        default: () => {
            return []
        }
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
let emit = defineEmits(['update:fileList'])


// 图片临时列表
const tempFileList = ref([
    {
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },
    {
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },
])
// 监听
watchEffect(() => {
    tempFileList.value = props.fileList
})




// 文件上传成功时的钩子
let handleUploadSuccess = (response, file, fileList) => {
    let { code, data, msg } = response;
    if (code === 200) {
        const pictureUrl = props.picturePreUrl + data;
        const lastIndex = fileList.length - 1;
        fileList[lastIndex].url = pictureUrl;
        // 预览图片
        emit('update:fileList', fileList);
        // 信息提示
        ElMessage.success(msg);
    }
    console.log(fileList);
}
// 文件上传失败的时的钩子
let handleUploadError = (error, file, fileList) => {
    console.log(error);

    ElMessage.error('上传失败!')
}





// 预览 / 下载 / 删除 图标模块*************************************************************
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
// 点击删除图标
const handleRemove = (uploadFile, uploadFiles) => {
    console.log(uploadFile, uploadFiles)
}
// 点击预览图标
const handlePictureCardPreview = (file) => {
    dialogImageUrl.value = file.url
    dialogVisible.value = true
}
</script>