<script setup>
import { ref, onMounted,getCurrentInstance, watchEffect } from 'vue';
import api from '../api'




const stockPie=ref([]) 
const stockColumnName = ref([])
const stockColumnVale = ref([])

onMounted(async function(){
  let {list} =await api.goodsList()
 list.forEach(element => {
 let dataObj = {value: element.stock,name:element.name }
  stockPie.value.push(dataObj)
  stockColumnName.value.push(element.name)
  stockColumnVale.value.push(element.stock)
 });
  pie()
  column()
 console.log("stockPie",stockPie.value)
})

watchEffect(()=>{
  console.log( "监听器", stockPie.value);
})

//获取数据
const instance =getCurrentInstance();


const echarts=instance.appContext.config.globalProperties.$echarts


const pie=()=>{
    var chartDom = document.getElementById('chartspie');
var myChart = echarts.init(chartDom);
var option;

option = {
  title: {
    text: 'Referer of a Website',
    subtext: 'Real Data',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: stockPie.value,
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};

option && myChart.setOption(option);

}

const column=()=>{
 // 基于准备好的dom，初始化echarts实例
     
 var myChart = echarts.init(document.getElementById('charts'));

// 指定图表的配置项和数据
var option = {
  title: {
    text: '库存展示'
  },
  tooltip: {},
  legend: {
    data: ['库存']
  },
  xAxis: 
  {
    data: stockColumnName.value
  },
  yAxis: {},
  series: [
    {
      name: '库存',
      type: 'bar',
      data:stockColumnVale.value
    }
  ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
}





</script>



<template>
<h1>首页</h1>
<div class="item-info" id="box13">
<div id="charts" style="width: 100%; height: 300px;"></div>
</div>
<div class="area" id="box1">
<div id="chartspie" style="width: 100%; height: 300px;"></div>
</div>
    <!-- <router-view></router-view> -->
</template>


<style></style>