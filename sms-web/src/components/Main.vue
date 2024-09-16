<script lang="ts" setup>
import {inject, onBeforeMount, ref} from "vue";
import {Search} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";

const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}

let checkAge = (rule, value, callback) => {
  if (value > 150) {
    callback(new Error('年龄输⼊过⼤'));
  } else {
    callback();
  }
};

let checkDuplicate = (rule, value, callback) => {
  if (form.value.id) {
    return callback();
  }
  axios.get("/user/findByNo?no=" + form.value.no).then(res => res.data).then(res => {
    if (res.code != 200) {
      callback()
    } else {
      callback(new Error('账号已经存在'));
    }
  }).catch(error => {
    console.error("请求出错:", error);
    callback(new Error('验证失败'));
  });
};

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
const rules = ref({
  no: [
    {required: true, message: '请输入名字', trigger: 'blur'},
    {min: 3, max: 8, message: '长度在3到8个字符', trigger: 'blur'},
    {validator: checkDuplicate, trigger: 'blur'}
  ],
  name: [
    {required: true, message: '请输入名字', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 3, max: 8, message: '长度在3到8个字符', trigger: 'blur'}
  ],
  age: [
    {required: true, message: '请输⼊年龄', trigger: 'blur'},
    {min: 1, max: 3, message: '⻓度在 1 到 3 个位', trigger: 'blur'},
    {pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正整数字', trigger: "blur"},
    {validator: checkAge, trigger: 'blur'}
  ],
  phone: [
    {required: true, message: "⼿机号不能为空", trigger: "blur"},
    {
      pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输⼊正确的⼿机号码", trigger: "blur"
    }
  ]
})
const centerDialogVisible = ref(false)
const form = ref({no: '', name: '', password: '', age: '', phone: '', sex: '0', roleId: '1'})

function loadGet() {
  axios.get('/user/list').then(res => res.data).then(res => {
    console.log(res)
  }).catch(error => {
    console.error(error)
  })
}

const formRef = ref(null);

function save() {
  if (formRef.value) {
    formRef.value.validate((valid: boolean) => {
      if (valid) {
        axios.post('/user/save', form.value).then(res => res.data).then(res => {
          console.log(res);

          if (res.code == 200) {
            ElMessage({
              message: '操作成功！',
              type: 'success',
            });
            centerDialogVisible.value = false;
            loadPost();
          } else {
            ElMessage({
              message: '操作失败！',
              type: 'error',
            });
          }
        }).catch(error => {
          console.error('请求出错:', error);
          ElMessage({
            message: '网络错误，操作失败！',
            type: 'error',
          });
        });
      } else {
        console.log('表单验证失败');
      }
    });
  }
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

function add() {
  resetForm()
  centerDialogVisible.value = true;
}

function resetForm() {
  if (formRef.value) {
    formRef.value.resetFields();
  }
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
      <el-button type="success" @click="add">新增</el-button>

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

    <el-dialog
        v-model="centerDialogVisible"
        title="Warning"
        width="500"
        align-center
    >
      <el-form ref="formRef" :model="form" :rules="rules" label-width="auto" style="max-width: 600px">
        <el-form-item label="账号" prop="no">
          <el-col :span="20">
            <el-input v-model="form.no"/>
          </el-col>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"/>
          </el-col>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-col :span="20">
            <el-input v-model="form.age"/>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="20">
            <el-input v-model="form.password"/>
          </el-col>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio value="1">男</el-radio>
            <el-radio value="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-col :span="20">
            <el-input v-model="form.phone"/>
          </el-col>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<style>

</style>