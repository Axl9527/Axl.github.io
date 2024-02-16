<template>
    只给1号角色分配权
    <el-tree
    ref="treeRef"
      :data="data"
      show-checkbox
      node-key="id"
      :default-expanded-keys="[2, 3]"
      :default-checked-keys="[5]"
      :props="defaultProps"
    />
    <el-button type="primary" @click="saverole">保存</el-button>
  </template>
  
  <script lang="ts" setup>
  import axios from '../api';
  import { ElTree } from 'element-plus'
  import type Node from 'element-plus/es/components/tree/src/model/node'
  import {onMounted, ref,} from 'vue';
  const treeRef = ref<InstanceType<typeof ElTree>>()
 async function saverole(){
    
  var nodes=treeRef.value!.getCheckedKeys(false);
  await axios.easyAxios({
        method:'post',
        url:'http://localhost:8080/roleauthority',
        headers: {
                //定义将数据以json的形式传入后台(java)
                'Content-Type': 'application/json'
            },
        data:{
            id:1,
            authorityArr:nodes
        }
    });
    
  }

  onMounted(async function (){
    sessionStorage.setItem("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDUwNDkzOTQsImlhdCI6MTcwNTA0NzU5NCwiaXNzIjoiZWFzeSIsInVzZXJJbmZvIjpudWxsfQ.eIWeRM2b8PdQXsDn-6aZDot31thzxQQwMspHOMgiKks");
    var obj= await axios.easyAxios({
        method:'get',
        url:'http://localhost:8080/authoritylist/-1'
    });
    console.log(obj);
    data.value=obj;
    
  });
  const defaultProps = {
    children: 'childrenList',
    label: 'name',
  }
  const data =ref( [
    {
      id: 1,
      label: 'Level one 1',
      children: [
        {
          id: 4,
          label: 'Level two 1-1',
          children: [
            {
              id: 9,
              label: 'Level three 1-1-1',
            },
            {
              id: 10,
              label: 'Level three 1-1-2',
            },
          ],
        },
      ],
    },
    {
      id: 2,
      label: 'Level one 2',
      children: [
        {
          id: 5,
          label: 'Level two 2-1',
        },
        {
          id: 6,
          label: 'Level two 2-2',
        },
      ],
    },
    {
      id: 3,
      label: 'Level one 3',
      children: [
        {
          id: 7,
          label: 'Level two 3-1',
        },
        {
          id: 8,
          label: 'Level two 3-2',
        },
      ],
    },
  ]);
  </script>