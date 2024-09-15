<template>
  <el-container class="layout-container-demo" style="height: 100%">

    <el-aside :width="aside_width" style="height: 100%">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%">

      <el-header style="text-align: right; font-size: 12px; border-bottom: rgba(168,168,168,0.3) 1px solid;">
        <Header @doCollapse="doCollapse"></Header>
      </el-header>

      <el-main style="height: 100%">
        <Main  />
      </el-main>

    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import {ref} from "vue";

const isCollapse = ref(false)
const aside_width = ref('200px')

defineOptions({
  name: 'Index'
})

function doCollapse(){
  isCollapse.value = !isCollapse.value
  if(!isCollapse.value){
    aside_width.value = '200px'
  } else {
    aside_width.value = '64px'
  }
}
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  //background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>
