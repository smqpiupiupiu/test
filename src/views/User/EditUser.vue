<template>
  <div style="width: 80%">
    <div style="padding:10px 20px">
      <i class="el-icon-s-management"></i>
      <span>编辑用户</span>
    </div>
    <el-form :inline="true" :model="form"  label-width="100px">
      <el-form-item label="姓名">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="员工号">
        <el-input v-model="form.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex" placeholder="请输入性别"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="邮件">
        <el-input v-model="form.email" placeholder="请输入邮件"></el-input>
      </el-form-item>
      <el-form-item label="部门">
        <el-input v-model="form.department" placeholder="请输入部门"></el-input>
      </el-form-item>

    </el-form>
    <div style="margin-left:100px">
      <el-button type="primary" @click="save">提交</el-button>
    </div>
  </div>

</template>

<script>
import request from "@/router/request";

export default{
  name:'AddUser',
  data(){
    return {
      form:{}
    }
  },
  created() {
    const id = this.$route.query.id;
    request.get("user/"+id).then(res=>{
      //把后台通过id查到的user对象赋给form
      this.form=res.data
    })
  },
  methods:{
    save(){//提交数据
      request.put('/user/update',this.form).then(res=>{
        if(res.code == '200'){
          this.$notify.success('编辑成功')
          this.$router.push("/user")
          //返回列表界面
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>