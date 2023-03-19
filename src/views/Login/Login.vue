<template >

  <div style="height: 100vh;overflow: hidden">
<div style="margin: 100px auto;padding:30px;width: 300px;height: 300px;
      background-color: aliceblue">
  <div style="margin-bottom: 20px; text-align:center ;font-size: 30px;font-weight: bold;color: dodgerblue">用户登录</div>
  <el-form :model="admin"  ref="loginForm" >
    <el-form-item prop="username" >
      <el-input v-model="admin.username"  placeholder="请输入账号" size="medium" prefix-icon="el-icon-user"></el-input>
    </el-form-item>
    <el-form-item prop="username" >
      <el-input v-model="admin.password"  placeholder="请输入密码" size="medium" prefix-icon="el-icon-lock"></el-input>
    </el-form-item>
    <el-button type="primary" @click="login" style="text-align: center;">登录</el-button>
  </el-form>
</div>
  </div>

</template>
<script>
import request from "@/router/request";

export default {
  name:'LOGIN',
  data(){
    return{
      admin:{},
    }
  },
  methods:{
    login(){
      request.post('/admin/login',this.admin).then(res=>{
        if(res.code === '200'){
          this.$notify.success("登陆成功")
          this.$router.push("/")
        }else {
          this.$notify.error(res.msg)
        }
      })
    }

  }
}
</script>