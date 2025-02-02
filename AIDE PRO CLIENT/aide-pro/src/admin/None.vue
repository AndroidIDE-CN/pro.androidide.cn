<!-- eslint-disable vue/multi-word-component-names -->

<script setup>
    import { useRouter } from "vue-router"
    const router = useRouter();
</script>

<template>
    <div class="page-none">
        <el-result icon="info" title="不支持的访问设备">
            <template #sub-title>
              <p>请在任何非移动端浏览此页面</p>
            </template>
            <template #extra>
                <el-button type="primary" @click="router.push('/')">返回主页</el-button>
            </template>
        </el-result>
    </div>
</template>

<style>
    div.page-none {
        width: 100vw;
        height: 100vh;
        display: grid;
        overflow: hidden;
    }
</style>