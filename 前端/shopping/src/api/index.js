import axios from 'axios';
import http from '../utils/http.js';

export default {
    categoryList:(obj)=>{return http({url: 'api/category',params:obj})},
  
    categoryEidt:(obj)=>{return http({
        url:'api/categoryeidt/',
        method:'POST',
        data:obj,
        Headers:{
            //定义将数据以json的形式传入后台(Java)
            'Content-Type':'application/json'
        }
    })},
    categoryDel:(id)=>{return http({url:'api/category/'+id,method:'DELETE'})},

    categoryAdd:(obj)=>{return http({
        url:'api/category/',
            method:'POST',
            data:obj,
            Headers:{
                //定义将数据以json的形式传入后台(Java)
                'Content-Type':'application/json'
            }

    })},
    goodsList:(obj)=>{return http({url: 'api/goodsHou',params:obj})},
    goodsListP:()=>{return http({url: 'api/goodsparent'})},
    goodsDel:(id)=>{return http({url:'api/delete/'+id,method:'delete'})},
    goodsEidt:(obj)=>{return http({
        url:'api/goodseidt/',
        method:'POST',
        data:obj,
        Headers:{
            //定义将数据以json的形式传入后台(Java)
            'Content-Type':'application/json'
        }
    })},
    goodsAdd:(obj)=>{return http({
        url:'api/goods/',
            method:'POST',
            data:obj,
            Headers:{
                //定义将数据以json的形式传入后台(Java)
                'Content-Type':'application/json'
            }

    })},
    userList:(obj)=>{return http({url: 'api/user',params:obj})},
    
    loginFun:(obj)=>{
        return http({
            url:'api/login',
            method:'POST',
            data:obj,
            Headers:{
                //定义将数据以json的形式传入后台(Java)
                'Content-Type':'application/json'
            }

        })
    },
    orderList:(obj)=>{return http({url: 'api/order',params:obj})},
    orderListP:()=>{return http({url: 'api/userSeach'})},
    logina:(obj)=>{
        return http({
            url:'api/cha',
            method:'get',
            params:obj,
          

        })
    },
    detailList:(obj)=>{
        return http({
            url:'api/detail',
            method:'get',
            params:obj,
          

        })
    },
    queryOrder:(obj)=>{return http({
        url:"/api/orders",
        method:"get",
        params:obj
    })},
    ma:(obj)=>{
        return http({
            url:'api/kaptcha',
            method:'Post',
            data:obj,
            Headers:{
                //定义将数据以json的形式传入后台(Java)
                'Content-Type':'application/json'
            }

        })
    },
    easyAxios:(options)=>{
        return http(options);
    },
    addUser:(obj)=>{return http({
        url:"/api/addUser",
        method:"post",
        data:obj,
        headers:{
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })},
    editUser:(obj)=>{
        return http({
            url:"/api/editUser",
            method:"put",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    fahuo:(obj)=>{
        return http({
            url:"/api/editorder",
            method:"put",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    shangXiaJia:(obj)=>{
        return http({
            url:"/api/goods",
            method:"put",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    addPictrue:(obj)=>{
        return http({
            url:"/api/picture",
            method:"post",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
    },
    
}