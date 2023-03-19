<template>
  <div>
    <div style="margin-bottom:10px">
      <el-input style="width: 300px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i></el-button>
      <el-button style="margin-left: 5px" type="primary" @click="reset"><i class="el-icon-refresh"></i></el-button>
    </div>
    <el-table :data="tableData" stripe>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="username" label="员工号"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="email" label="邮件"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="department" label="部门"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
<!--scope.row当前行数据 跳转页面         -->
          <el-button type="warning" icon="el-icon-edit" circle @click="$router.push('/editUser?id=' + scope.row.id)" ></el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定进行删除操作吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" icon="el-icon-delete" circle slot="reference"></el-button>
          </el-popconfirm>
        </template>

      </el-table-column>

    </el-table>
    <!-- 分页 -->
    <el-pagination
        :page-size="params.pageSize"
        :current-page="params.pageNum"
        @current-change="handleCurrentChange"
        layout="prev, pager, next"
        :total="total">
    </el-pagination>
  </div>
</template>

<script>
// import {request} from "axios";
import request from "@/router/request"

export default {
  name: 'User',
  data() {
    return {
      tableData: [],//数组中定义对象 假数据
      // { name: 's', sex: '女', age: 20, phone: 'ds' }, //department:1,email:'statfsSync,,adress:'s''
      params: {
        name: '',
        pageNum: 1,
        pageSize: 5,
      }
    }
  },
  created() {
    this.load() //页面加载后调用，数据渲染
  },
  methods: {
    load() {//查询数据的方法,用js自带的fetch api访问后台test 但是会跨域错误 加@CrossOrigin
      // fetch('http://localhost:9090/user/list').then(res => res.json()).then(res => {
      //   console.log(res)
      //   this.tableData = res
      // })
      request.get('/user/page', {
        params: this.params //带着前台数据以参数形式向后台发送请求 网络中get 200
      }).then(res => {
        this.tableData = res.data.list;
        this.total = res.data.total;

      })
      //后台返回result 所以要进行解析
    },
    reset() {
      this.params = {
        name: '',
        pageNum: 1,
        pageSize: 5,
      },
          this.load();

    },
    handleCurrentChange(pageNum){
      //点击触发分页
      //console.log(pageNum)
      this.params.pageNum = pageNum
      this.load()
    },
    del(id){
      request.delete("/user/delete/"+id).then(res=>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style  scoped>
</style>