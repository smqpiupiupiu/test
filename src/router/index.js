import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/user',
    name: 'User',
    component: ()=>import('@/views/User/User')
  },
  {
    path: '/addUser',
    name: 'AddUser',
    component: ()=>import('@/views/User/AddUser')
  },
  {
    path: '/editUser',
    name: 'EditUser',
    component: ()=>import('@/views/User/EditUser')
  },

    //admin
  {
    path: '/admin',
    name: 'Admin',
    component: ()=>import('@/views/Admin/list')
  },
  {
    path: '/addAdmin',
    name: 'AddAdmin',
    component: ()=>import('@/views/Admin/Add')
  },
  {
    path: '/editAdmin',
    name: 'EditAdmin',
    component: ()=>import('@/views/Admin/Edit')
  },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
