<script setup>
const fileurl = ref("");
const easyfilesucces = async (data, res2, res3) => {
    fileurl.value = data.data;

    if (defimg.value) {
        var result = await axiosAPI.addDefImg({ gid: imggid, img_url: fileurl.value });
        if (result == 1) {
            ElMessage({
                type: 'info',
                message: '添加默认图片成功',
            })
        } else {
            ElMessage({
                type: 'info',
                message: '添加默认图片失败',
            })
        }
    }
    else {
        var result = await axiosAPI.addImg({ gid: imggid, img_url: fileurl.value });
        if (result == 1) {
            ElMessage({
                type: 'info',
                message: '添加成功',
            })
            await axiosAPI.logAdd({ gid: imggid, aid: admin.aid, behavior: '为此商品添加了图片' });
        } else {
            ElMessage({
                type: 'info',
                message: '添加失败',
            })
        }
    }
    defimg.value = false;
}

</script>
<template>
     <el-switch v-model="defimg" active-text="Open" inactive-text="设置为默认图片" />
        <el-upload action="/api/imagefile" :on-success='easyfilesucces' list-type="picture-card" :auto-upload="true">
            <el-icon>
                <Plus />
            </el-icon>
        </el-upload>
</template>