<template>
  <div style="text-align: center;background-color: #f1f1f3;height: 100%;padding: 0px;margin: 0px;">
    <h1 style="font-size: 50px;">{{ '欢迎你！' + user.name }}</h1>
    <el-descriptions title="个人中心" :column="2" size="default" border>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-s-custom"></i>
          账号
        </template>
        {{ user.no }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-mobile-phone"></i>
          电话
        </template>
        {{ user.phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-location-outline"></i>
          性别
        </template>
        <el-tag :type="user.sex === '1' ? 'primary' : 'danger'" disable-transitions>
          <i :class="user.sex == 1 ? 'el-icon-male' : 'el-icon-female'"></i>
          {{ user.sex == 1 ? "男" : "女" }}
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-tickets"></i>
          角色
        </template>
        <el-tag type="success" disable-transitions>
          {{ user.roleId == 0 ? "超级管理员" : (user.roleId == 1 ? "管理员" : "用户") }}
        </el-tag>
      </el-descriptions-item>
    </el-descriptions>

    <DateUtils></DateUtils>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import DateUtils from './DateUtils.vue'

// 使用 ref 来声明响应式的 user 对象
const user = ref({})

// 定义初始化方法，用于从 sessionStorage 获取用户数据
function init() {
  const storedUser = sessionStorage.getItem('CurUser')
  if (storedUser) {
    user.value = JSON.parse(storedUser)
  }
}

// 在 onMounted 生命周期钩子中调用 init 方法
onMounted(() => {
  init()
})
</script>

<style scoped>
.el-descriptions {
  width: 90%;
  margin: 0 auto;
  text-align: center;
}
</style>
