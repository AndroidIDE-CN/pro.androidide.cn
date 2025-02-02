<script setup>
    import { useRouter } from "vue-router"
    const router = useRouter();
</script>

<template>
    <div class="not-layout">
        <el-result icon="info" title="页面未找到">
            <template #sub-title>
              <p>暂未找到该页面，请返回主页</p>
            </template>
            <template #extra>
              <el-button type="primary" @click="router.push('/')">返回主页</el-button>
            </template>
      </el-result>
    </div>
</template>

<style>
    @import url("./assets/NotFouLayout.css");
</style>