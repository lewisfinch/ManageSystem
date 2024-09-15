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
    <el-table :data="tableData">
      <el-table-column prop="date" label="Date" width="140"/>
      <el-table-column prop="name" label="Name" width="120"/>
      <el-table-column prop="address" label="Address"/>
    </el-table>
  </el-scrollbar>
</template>

<style>

</style>