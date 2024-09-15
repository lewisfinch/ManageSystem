import {createApp} from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import './assets/global.css'
import axios from "axios";

const app = createApp(App)

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8090', // 默认基础 URL
    timeout: 1000, // 请求超时时间
    // 其他配置
});

app.provide('$axios', axiosInstance);
app.use(ElementPlus)
app.mount('#app')
