import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout";
Vue.use(VueRouter)

const routes = [
    //登录
  { path: '/login',
    name: 'Login',
    component: ()=> import('@/views/Login/Login')

  },
    //主页
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/home',
    children:[
      {
        path : 'home',
        name : 'Home',
        component:()=>import('@/views/Home/HomeView')

      },
        {
          path: 'user',
          name: 'User',
          component: ()=>import('@/views/User/User')
        },
  {
    path: 'addUser',
    name: 'AddUser',
    component: ()=>import('@/views/User/AddUser')
  },
  {
    path: 'editUser',
    name: 'EditUser',
    component: ()=>import('@/views/User/EditUser')
  },

  //admin
  {
    path: 'admin',
    name: 'Admin',
    component: ()=>import('@/views/Admin/list')
  },
  {
    path: 'addAdmin',
    name: 'AddAdmin',
    component: ()=>import('@/views/Admin/Add')
  },
  {
    path: 'editAdmin',
    name: 'EditAdmin',
    component: ()=>import('@/views/Admin/Edit')
  },
 ],}
]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
