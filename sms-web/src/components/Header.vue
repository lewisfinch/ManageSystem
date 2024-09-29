<script setup>
import {ArrowDown, Switch} from "@element-plus/icons-vue";
import router from "@/router/index.js";

defineOptions({
  name: 'Header'
})

function toUser() {
  console.log('to_user')
  router.push("/Home")
}

function logout() {
  console.log('logout')
  router.push("/")
  sessionStorage.clear()
}

const emitCollapse = defineEmits(['doCollapse'])
const user = JSON.parse(sessionStorage.getItem('CurUser'))

function collapse() {
  emitCollapse("doCollapse");
}

router.push('/Home')

</script>

<template>
  <div class="toolbar" style="display: flex; line-height: 60px">
    <div>
      <el-icon style="font-size: 20px; margin-top: 25px; cursor: pointer" @click="collapse">
        <Switch/>
      </el-icon>
    </div>
    <div style="flex: 1; text-align: center; font-size: 20px;">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <span>{{ user.name }}</span>
    <el-dropdown>
      <el-icon>
        <ArrowDown/>
      </el-icon>
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