<script setup>

import {Flag, HomeFilled, Opportunity} from '@element-plus/icons-vue'

defineOptions({
  name: 'Aside'
})

const props = defineProps({
  isCollapse: {
    type: Boolean,
  }
})

</script>

<template>
  <el-scrollbar>
    <el-menu
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="height: 100vh;"
        default-active="/Home"
        :collapse="isCollapse"
        :collapse-transition="false">
      <el-menu-item index="/Home">
        <el-icon><HomeFilled /></el-icon>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-menu-item index="/One">
        <el-icon><Opportunity /></el-icon>
        <span slot="title">导航一</span>
      </el-menu-item>

      <el-menu-item index="/Two">
        <el-icon><Flag /></el-icon>
        <span slot="title">导航二</span>
      </el-menu-item>
    </el-menu>
  </el-scrollbar>
</template>

<style>

</style>