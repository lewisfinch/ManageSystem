<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form :model="loginForm" label-width="100px" :rules="rules" ref="loginFormRef">
          <el-form-item label="账号" prop="no">
            <el-input
                style="width: 200px"
                type="text"
                v-model="loginForm.no"
                autocomplete="off"
                size="small"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
                style="width: 200px"
                type="password"
                v-model="loginForm.password"
                show-password
                autocomplete="off"
                size="small"
                @keyup.enter.native="confirm"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确 定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {inject, ref} from 'vue';
import {useRouter} from 'vue-router';

const axios = inject('$axios');

// 创建表单引用
const loginFormRef = ref();
const loginForm = ref({
  no: '',
  password: '',
});
const confirm_disabled = ref(false);
const rules = {
  no: [{required: true, message: '请输入账号', trigger: 'blur'}],
  password: [{required: true, message: '请输密码', trigger: 'blur'}],
};

const router = useRouter();

const confirm = () => {
  confirm_disabled.value = true;
  const form = loginForm.value;

  // 确保 loginFormRef.value 不为 null
  if (loginFormRef.value) {
    loginFormRef.value.validate((valid) => {
      if (valid) {
        axios
            .post('/user/login', form)
            .then((res) => {
              const data = res.data;
              if (data.code === 200) {
                sessionStorage.setItem("CurUser", JSON.stringify(data.data));
                router.replace('/Index');
              } else {
                confirm_disabled.value = false;
                alert('校验失败，用户名或密码错误！');
                return false;
              }
            });
      } else {
        confirm_disabled.value = false;
        console.log('校验失败');
        return false;
      }
    });
  } else {
    console.error('loginFormRef is null');
  }
};
</script>

<style scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #B3C0D1;
}

.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: #fff;
  border-radius: 5%;
}

.login-title {
  margin: 20px 0;
  text-align: center;
}

.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>
