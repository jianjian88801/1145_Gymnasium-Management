import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import address from '@/views/modules/address/list'
    import cart from '@/views/modules/cart/list'
    import changdi from '@/views/modules/changdi/list'
    import changdiOrder from '@/views/modules/changdiOrder/list'
    import dictionary from '@/views/modules/dictionary/list'
    import saishi from '@/views/modules/saishi/list'
    import saishiCollection from '@/views/modules/saishiCollection/list'
    import saishiCommentback from '@/views/modules/saishiCommentback/list'
    import saishiOrder from '@/views/modules/saishiOrder/list'
    import shangpin from '@/views/modules/shangpin/list'
    import shangpinCollection from '@/views/modules/shangpinCollection/list'
    import shangpinCommentback from '@/views/modules/shangpinCommentback/list'
    import shangpinOrder from '@/views/modules/shangpinOrder/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryChangdi from '@/views/modules/dictionaryChangdi/list'
    import dictionaryChangdiOrder from '@/views/modules/dictionaryChangdiOrder/list'
    import dictionaryChangdiOrderPayment from '@/views/modules/dictionaryChangdiOrderPayment/list'
    import dictionaryIsdefault from '@/views/modules/dictionaryIsdefault/list'
    import dictionarySaishi from '@/views/modules/dictionarySaishi/list'
    import dictionarySaishiCollection from '@/views/modules/dictionarySaishiCollection/list'
    import dictionarySaishiOrder from '@/views/modules/dictionarySaishiOrder/list'
    import dictionarySaishiOrderPayment from '@/views/modules/dictionarySaishiOrderPayment/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangpin from '@/views/modules/dictionaryShangpin/list'
    import dictionaryShangpinCollection from '@/views/modules/dictionaryShangpinCollection/list'
    import dictionaryShangpinOrder from '@/views/modules/dictionaryShangpinOrder/list'
    import dictionaryShangpinOrderPayment from '@/views/modules/dictionaryShangpinOrderPayment/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryChangdi',
        name: '场地类型',
        component: dictionaryChangdi
    }
    ,{
        path: '/dictionaryChangdiOrder',
        name: '订单类型',
        component: dictionaryChangdiOrder
    }
    ,{
        path: '/dictionaryChangdiOrderPayment',
        name: '订单支付类型',
        component: dictionaryChangdiOrderPayment
    }
    ,{
        path: '/dictionaryIsdefault',
        name: '是否默认地址',
        component: dictionaryIsdefault
    }
    ,{
        path: '/dictionarySaishi',
        name: '赛事类型',
        component: dictionarySaishi
    }
    ,{
        path: '/dictionarySaishiCollection',
        name: '收藏表类型',
        component: dictionarySaishiCollection
    }
    ,{
        path: '/dictionarySaishiOrder',
        name: '订单类型',
        component: dictionarySaishiOrder
    }
    ,{
        path: '/dictionarySaishiOrderPayment',
        name: '订单支付类型',
        component: dictionarySaishiOrderPayment
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangpin',
        name: '商品类型',
        component: dictionaryShangpin
    }
    ,{
        path: '/dictionaryShangpinCollection',
        name: '收藏表类型',
        component: dictionaryShangpinCollection
    }
    ,{
        path: '/dictionaryShangpinOrder',
        name: '订单类型',
        component: dictionaryShangpinOrder
    }
    ,{
        path: '/dictionaryShangpinOrderPayment',
        name: '订单支付类型',
        component: dictionaryShangpinOrderPayment
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/address',
        name: '收货地址',
        component: address
      }
    ,{
        path: '/cart',
        name: '购物车',
        component: cart
      }
    ,{
        path: '/changdi',
        name: '场地',
        component: changdi
      }
    ,{
        path: '/changdiOrder',
        name: '场地预约',
        component: changdiOrder
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/saishi',
        name: '赛事',
        component: saishi
      }
    ,{
        path: '/saishiCollection',
        name: '赛事收藏',
        component: saishiCollection
      }
    ,{
        path: '/saishiCommentback',
        name: '赛事评价',
        component: saishiCommentback
      }
    ,{
        path: '/saishiOrder',
        name: '赛事订单',
        component: saishiOrder
      }
    ,{
        path: '/shangpin',
        name: '商品',
        component: shangpin
      }
    ,{
        path: '/shangpinCollection',
        name: '商品收藏',
        component: shangpinCollection
      }
    ,{
        path: '/shangpinCommentback',
        name: '商品评价',
        component: shangpinCommentback
      }
    ,{
        path: '/shangpinOrder',
        name: '商品订单',
        component: shangpinOrder
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
