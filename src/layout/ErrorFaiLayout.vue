
<script setup>
    import { ref } from 'vue'

    const showErrorInfo = ref(false);
    const globalError = defineProps({ errorInfo: Error });
</script>

<template>
    <div class="site-error">
        <div class="error-container">
            <h1 class="error-title">系统异常</h1>
            <p class="error-message">抱歉，我们的系统出现了一些问题，请稍后再试或联系我们的支持团队。</p>
            <p class="error-info" v-if="showErrorInfo">{{ globalError.errorInfo }}</p>
            <el-button class="button" type="danger" @click="showErrorInfo = !showErrorInfo">{{ showErrorInfo ? '隐藏错误日志' : '查看错误日志'}}</el-button>
        </div>
    </div>
</template>

<style>
    @import url("./assets/ErrorFaiLayout.css");

    div#application:has(div.site-error) {
        padding-top: 0!important;
    }
</style>