<template>
  <div style="width: 80%">
    <div style="padding:10px 20px">
      <i class="el-icon-s-management"></i>
    <span>新增用户</span>
    </div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm"  label-width="100px " >
      <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>

      <el-form-item label="员工号" prop="username" >
        <el-input v-model="form.username" placeholder="请输入员工号" disabled></el-input>
      </el-form-item>

      <el-form-item label="电话" prop="phone">
        <el-input v-model.number="form.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="邮件" prop="email">
        <el-input v-model="form.email" placeholder="请输入邮件"></el-input>
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
  name:'AddAdmin',
  data(){
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('电话不能为空'));
      }
      //console.log(typeof value)
      //报错的话看一下数据类型 string
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else if(parseInt(value)<11){
          callback(new Error('请输入合理的电话号码'));
        } else {
            callback();
          }
      }, 100);
    };
      return {
        form:{},
        rules:{
          name:[
            { required: true, message: '请输入员工姓名', trigger: 'blur' },
            { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
          ],
          phone: [
            { validator: checkPhone, trigger: 'blur' }
          ],
          email:[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ]
        }
      }

  },
  methods:{
    save(){//提交数据
      //post响应500 errAxiosError5
      // console.log(this.form);    //返回object
      //wwww是后台的问题，要用admin不是obj
      this.$refs["ruleForm"].validate((valid) => {//验证
        if (valid) {
          //提交表单
          request.post('/admin/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              // this.form = {}
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>