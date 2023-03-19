<template>
  <div id="app">
    <!-- 头部 -->
    <div style="height:60px; line-height:60px; background-color:aliceblue;;margin-bottom: 5px;">
      <img src="@/assets/logo.png" alt="" style="width: 40px;position: relative;top: 10px;left: 20px;">
      <span style="margin-left: 20px;font-size: 20px;">员工管理系统</span>
    </div>
    
    <div style="display: flex;min-height: calc(100vh - 65px);overflow: hidden;margin-right: 5px;background-color: aliceblue;">
    <!-- 侧边栏导航 -->
    <div style="display:flex">
      <el-menu :default-active="$route.path" :default-openeds="['/']"  router class="el-menu-demo" >
        <!-- 通过路由方式切换数据 -->
    <el-menu-item index="/">
      <i class="el-icon-s-management"></i>
      <span>首页</span>
    </el-menu-item>
    <el-submenu index="/">
      <template slot="title">
        <i class="el-icon-s-management"></i>
        <span>工作台</span>
      </template>
      <el-menu-item index="/user">员工列表</el-menu-item>
      <el-menu-item index="/addUser">新增员工</el-menu-item>
      </el-submenu>
      </el-menu>
      </div>
    <!-- 主体 -->
      <div style="flex:1;background-color: aliceblue;padding: 10px;">
        <router-view/>
      </div>
    </div>

  </div>
</template>

