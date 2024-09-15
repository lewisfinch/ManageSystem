<script setup>
import {ArrowDown, Fold, Setting, Switch} from "@element-plus/icons-vue";

defineOptions({
  name: 'Header'
})

function toUser(){
  console.log('to_user')
}

function logout(){
  console.log('logout')
}

const emitCollapse = defineEmits(['doCollapse'])

function collapse(){
  emitCollapse("doCollapse");
}

</script>

<template>
  <div class="toolbar" style="display: flex; line-height: 60px">
    <div>
      <el-icon style="font-size: 20px; margin-top: 25px; cursor: pointer" @click="collapse"><Switch /></el-icon>
    </div>
  <div style="flex: 1; text-align: center; font-size: 20px;">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <span>Tom</span><el-dropdown>
    <el-icon><ArrowDown /></el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click.native="toUser">个人资料</el-dropdown-item>
          <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

  </div>
</template>

<style>

</style>