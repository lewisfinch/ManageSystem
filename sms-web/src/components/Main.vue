<script setup>
import {inject, onBeforeMount, ref} from "vue";

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({length: 20}).fill(item))
const axios = inject('$axios');

function loadGet() {
  axios.get('/user/list').then(res => res.data).then(res => {
    console.log(res)
  }).catch(error => {
    console.error(error)
  })
}

function loadPost() {
  axios.post('/user/listP', {}).then(res => res.data).then(res => {
    console.log(res)
    if (res.code == 200) {
      tableData.value = res.data
    } else {
      alert('获取数据失败')
    }
  }).catch(error => {
    console.error(error)
  })
}


onBeforeMount(() => {
  loadPost()
})

</script>

<template>
  <el-scrollbar>
    <el-table :data="tableData"
              :header-cell-style="{background: '#f2f5fc'}"
              border
    >
      <el-table-column prop="id" label="ID" width="60"/>
      <el-table-column prop="no" label="账号" width="180"/>
      <el-table-column prop="name" label="姓名" width="180"/>
      <el-table-column prop="age" label="年龄" width="80"/>
      <el-table-column prop="sex" label="性别" width="80">
        <template v-slot="scope">
          <el-tag
              :type="scope.row.sex === 1 ? 'primary' : 'success'"
              disable-transitions>
            {{ scope.row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="120">
        <template v-slot="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions>
            {{
              scope.row.roleId === 0 ? '超级管理员' :
                  (scope.row.roleId === 1 ? '管理员' : '用户')
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="180"/>
      <el-table-column prop="operate" label="操作">
        <el-button size="small" type="success">编辑</el-button>
        <el-button size="small" type="danger">删除</el-button>
      </el-table-column>
    </el-table>
  </el-scrollbar>
</template>

<style>

</style>