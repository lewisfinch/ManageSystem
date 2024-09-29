<template>
  <div style="padding: 30px;font-size: 30px;">
    <span class="time" id="time">
      今天是：<span class="date">{{ nowTime }}</span>
      <span class="hour" style="margin-left: 5px;">{{ time.hour }}</span>
      <a class="split">:</a>
      <span class="minitus">{{ time.minitus }}</span>
      <a class="split">:</a>
      <span class="seconds">{{ time.seconds }}</span>
    </span>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'

// 使用 ref 定义响应式数据
const time = ref({
  hour: '',
  minitus: '',
  seconds: ''
})

const nowTime = ref('')

// 星期数组
const week = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]

// 格式化时间的函数
function getIncrease(num, digit) {
  let increase = ''
  for (let i = 0; i < digit; i++) {
    increase += '0'
  }
  return (increase + num).slice(-digit)
}

// 更新时间的方法
function timeFormate() {
  const newtime = new Date()
  time.value.hour = getIncrease(newtime.getHours(), 2)
  time.value.minitus = getIncrease(newtime.getMinutes(), 2)
  time.value.seconds = getIncrease(newtime.getSeconds(), 2)

  nowTime.value =
      getIncrease(newtime.getFullYear(), 4) +
      '年' +
      getIncrease(newtime.getMonth() + 1, 2) +
      '月' +
      getIncrease(newtime.getDate(), 2) +
      '日 ' +
      week[newtime.getDay()]
}

// 每秒钟更新时间
function dateTime() {
  timeFormate()
  setTimeout(() => {
    dateTime()
  }, 1000)
}

// 使用 onMounted 钩子在组件挂载时启动时间更新
onMounted(() => {
  dateTime()
})
</script>

<style scoped>
.txt-data .time {
  font-size: 1rem;
  margin-right: 0.5rem;
}

.split {
  animation: shark 1s step-end infinite;
  vertical-align: center;
  margin-left: 2px;
  margin-right: 2px;
}

@keyframes shark {
  0%,
  100% {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
}
</style>
