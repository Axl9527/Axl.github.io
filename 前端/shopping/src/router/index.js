//在这个文件中配置路由
//路由访问哪一个地址,要跳转到指定页面
import { createRouter,createWebHashHistory } from "vue-router";
const router=createRouter({
    history:createWebHashHistory(),
    routes:[
      
      
        {path:"/back",name:"back",
        component:()=>import('../views/backlayout.vue'),
        children:[
            {path:"/list",name:"list",component:()=>import("../views/goodslist.vue")},
            {path:"/category",name:"category",component:()=>import('../views/category.vue')},
            {path:"/index",name:"index",component:()=>import('../views/index.vue')},
            {path:"/user",name:"user",component:()=>import('../views/user.vue')},
            {path:"/order",component:()=>import('../views/order.vue')},

        ]
    },
      

    {path:"/login",component:()=>import('../views/login.vue')},
   
    {path:"/role",component:()=>import('../views/role.vue')},
    ]
});
export default router;