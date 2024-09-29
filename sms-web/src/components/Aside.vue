<script setup>

import {HomeFilled, Setting} from '@element-plus/icons-vue'
import {ref} from "vue";

defineOptions({
  name: 'Aside'
})

const menu = ref([
  {
    menuClick: 'Admin',
    menuName: '管理员管理'
  },
  {
    menuClick: 'User',
    menuName: '用户管理'
  }
])

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
        :collapse-transition="false"
        router>
      <el-menu-item index="/Home">
        <el-icon>
          <HomeFilled/>
        </el-icon>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-menu-item :index="'/'+item.menuClick" v-for="(item, i) in menu" :key="i">
        <el-icon>
          <Setting/>
        </el-icon>
        <span>{{ item.menuName }}</span>
      </el-menu-item>

    </el-menu>
  </el-scrollbar>
</template>

<style>

</style>