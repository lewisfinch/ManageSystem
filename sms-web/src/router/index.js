// src/router/index.js
import {createRouter, createWebHistory} from 'vue-router'; // 导入 Vue Router 的新 API

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../components/Login.vue') // 懒加载组件
    },
    {
        path: '/Index',
        name: 'index',
        component: () => import('../components/index.vue') // 懒加载组件
    }
];

// 创建路由实例
const router = createRouter({
    history: createWebHistory(), // 使用 HTML5 History 模式
    routes // 路由配置
});

export default router; // 导出路由实例
