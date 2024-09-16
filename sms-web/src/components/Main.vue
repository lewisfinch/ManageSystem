<script lang="ts" setup>
import {inject, onBeforeMount, ref} from "vue";
import {Search} from '@element-plus/icons-vue'

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({length: 20}).fill(item))
const pageNum = ref(1)
const pageSize = ref(10)
const name = ref('')
const total = ref(0)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)
const axios = inject('$axios');
const sex = ref('')
const sexes = ref([{
  value: '1',
  label: '男',
},
  {
    value: '0',
    label: '女',
  }])

function loadGet() {
  axios.get('/user/list').then(res => res.data).then(res => {
    console.log(res)
  }).catch(error => {
    console.error(error)
  })
}

function loadPost() {
  axios.post('/user/listPage', {
    pageSize: pageSize.value,
    pageNum: pageNum.value,
    param: {
      name: name.value,
      sex: sex.value
    }
  }).then(res => res.data).then(res => {
    console.log(res)
    if (res.code == 200) {
      tableData.value = res.data
      total.value = res.total
    } else {
      alert('获取数据失败')
    }
  }).catch(error => {
    console.error(error)
  })
}

function resetParam() {
  name.value = ''
  sex.value = ''
  loadPost()
}

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
  pageNum.value = 1;
  pageSize.value = val
  loadPost()
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
  pageNum.value = val
  loadPost()
}

onBeforeMount(() => {
  loadPost()
})

</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input v-model="name" :suffix-icon="Search" placeholder="请输入名字" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-select
          v-model="sex"
          filterable
          placeholder="请选择性别"
          style="width: 240px; margin-left: 5px"
      >
        <el-option
            v-for="item in sexes"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
    </div>
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
    <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 30]"
        :size="size"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<style>

</style>