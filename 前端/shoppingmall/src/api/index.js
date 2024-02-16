import axios from 'axios';
import http from '../utils/http.js';

export default {
    categoryList1:()=>{return http({url: 'api/category'})},
    categoryList:(obj)=>{return http({url: 'api/category',params:obj})},
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
    categoryEidt:(obj)=>{return http({
        url:'api/category2/',
        method:'POST',
        data:obj,
        Headers:{
            //定义将数据以json的形式传入后台(Java)
            'Content-Type':'application/json'
        }
    })},
   
        goodsList:(obj)=>{
            return http({
                url:'api/goods',
                method:'get',
                params:obj
          })
        },
        goodsList1:()=>{
            return http({
                url:'api/good',
                method:'get',
             
          })
        },

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
        logina:(obj)=>{
            return http({
                url:'api/cha',
                method:'get',
                params:obj,
              

            })
        },
   
        shoppingcar:(obj)=>{
            return http({
                url:'api/addCar',
                method:'POST',
                data:obj,
                Headers:{
                    //定义将数据以json的形式传入后台(Java)
                    'Content-Type':'application/json'
                }

            })
        },
        seachcar:(obj)=>{return http({url: 'api/car',params:obj})},
        editCountCar:(obj)=>{
            return http({
                url:'api/editcount',
                method:'put',
                data:obj,
                Headers:{
                    //定义将数据以json的形式传入后台(Java)
                    'Content-Type':'application/json'
                }

            })
        },
        deleteCars:(obj)=>{return http({url: 'api/deletecar',method:'post',  data:obj,
        Headers:{
            //定义将数据以json的形式传入后台(Java)
            'Content-Type':'application/json'
        }})},
       
        orderCar:(obj)=>{
            return http({
                url:'api/ordercar',
                method:'POST',
                data:obj,
                Headers:{
                    //定义将数据以json的形式传入后台(Java)
                    'Content-Type':'application/json'
                }

            })
        },
        choosegood:()=>{
            return http({url: 'api/choosegood'})
        },
        
        useraddress:(obj)=>{return http({url: 'api/useradress',params:obj})},
        addAddress:(obj)=>{return http({
            url:"/api/address",
            method:"post",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })},
        jiesuan:(obj)=>{return http({
            url:"/api/addorder",
            method:"post",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })},
        test:(obj)=>{return http({
            url:"/api/test",
            method:"post",
            data:obj,
            headers:{
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })},
        queryOrder:(obj)=>{return http({
            url:"/api/orders",
            method:"get",
            params:obj
        })},
        editOrder:(obj)=>{
            return http({
                url:"/api/editorder",
                method:"put",
                data:obj,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            })
        },
        collectList:(obj)=>{return http({
            url:"/api/chac",
            method:"get",
            params:obj
        })},
      
            addlike:(obj)=>{return http({
                url:"/api/addc",
                method:"post",
                data:obj,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            })},
            historyList:(obj)=>{return http({
                url:"/api/chahistory",
                method:"get",
                params:obj
            })},
            addHistory:(obj)=>{return http({
                url:"/api/addhistory",
                method:"post",
                data:obj,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            })},
            deleteHistory:(obj)=>{return http({
                url:"/api/delhistory",
                method:"delete",
                data:obj,
                headers:{
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            })},
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
            addEdit:(obj)=>{
                return http({
                    url:"/api/editaddress",
                    method:"put",
                    data:obj,
                    headers:{
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                })
            },
            addressDel:(id)=>{return http({url:'api/delAddress/'+id,method:'DELETE'})},

}