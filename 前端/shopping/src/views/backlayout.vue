<script setup>
import{ref} from'vue'

import Menu from './menu.vue'

import Content from './content.vue'

</script>

<template>
    <div class="menu">
        <Menu></Menu>

    </div>
    
    <div class="content">
        <Content></Content>
    </div>
</template>
<style scoped>
.menu{
    position: fixed;
    left: 0;
    top:0;
    bottom: 0;
    background-color: aliceblue;
}
.content{
   padding-left: 200px;
}

</style>