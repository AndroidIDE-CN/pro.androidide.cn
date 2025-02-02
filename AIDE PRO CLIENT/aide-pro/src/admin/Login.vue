<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable no-undef -->

<script setup>
    import { reactive } from 'vue'
    import utils from '@/scripts/utils'
    import axiostool from '@/scripts/axiostool'

    const formModel = reactive({
        username: '',
        password: ''
    });

    const sendLoginRequest = async () => {
        if (formModel.username === '' || formModel.password === '') return ElMessage({ message: '请检查输入格式', type: 'warning', plain: true });
        await axiostool.sendLoginRequest(formModel.username, formModel.password).then((response) => {
            if (response.code == 200) {
                ElMessage({ message: response.message, type: 'success', plain: true });
                setTimeout(() => location.href = "/login", 1000);
            } else ElMessage({ message: response.message, type: 'error', plain: true });
        });
    }

    document.title = "AIDE PRO LOGIN";
    const applicationStore = utils.useApplicationStore();
    if (localStorage.getItem('aide_login') === "true") location.href = "/admin";
</script>

<template>
    <div class="page-admin" :class="applicationStore.isDeviceMobile && 'page-mobile'">
        <div class="login-container">
            <p class="login-title">AIDE PRO LOGIN</p>
            <el-form :model="formModel" label-width="auto">
                <el-form-item label="账号">
                    <el-input v-model="formModel.username" placeholder="请输入账号" clearable/>
                </el-form-item>
            </el-form>

            <el-form :model="formModel" label-width="auto">
                <el-form-item label="密码">
                    <el-input type="password" v-model="formModel.password" placeholder="请输入密码" show-password clearable/>
                </el-form-item>
            </el-form>
            <el-button type="primary" @click="sendLoginRequest">登录到系统</el-button>
        </div>
    </div>
</template>

<style>
    @import url("./styles/Login.css");
</style>