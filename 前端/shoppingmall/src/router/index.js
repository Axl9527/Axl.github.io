//在这个文件中配置路由
//路由访问哪一个地址,要跳转到指定页面
import { createRouter,createWebHashHistory } from "vue-router";
const router=createRouter({
    history:createWebHashHistory(),
    routes:[
      
        // {path:"/home",component:()=>import('../views/home.vue')},
        // {path:"/product",component:()=>import('../views/product.vue')},
        // {path:"/file",component:()=>import('../views/file.vue')},
         {path:"/login",component:()=>import('../views/login.vue')},
         {path:"/error404",component:()=>import('../components/Error.vue')},
         {path:"/",component:()=>import('../views/home.vue')},
           
           {path:"/goods/details/:id",component:()=>import('../views/details.vue')},
            {path:"/goods",component:()=>import('../views/goods.vue')},
            {path:"/car",component:()=>import('../views/shoppingCar.vue')},
            {path:"/mylogin",component:()=>import('../components/MyLogin.vue')},
            {path:"/shoppingCart",component:()=>import('../views/shoppingCar.vue')},
            {
                path: '/confirmOrder',
                name:'confirmOrder',
                component: () => import('../views/order.vue'),
                // meta: {
                //   requireAuth: true // 需要验证登录状态
                // }
              },
              {path:"/order",component:()=>import('../views/checkOrder.vue')},
    
              {path:"/collect",component:()=>import('../views/collection.vue')},
              {path:"/history",component:()=>import('../views/history.vue')},
              {path:"/",component:()=>import('../APP.vue')},
    
    
    

    ]
});
export default router;