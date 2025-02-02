
<script setup>
    import { House, Connection, Guide, Discount, MessageBox, Message } from '@element-plus/icons-vue'

    const emitOption = defineEmits(['select']);
    const onMenuSelect = (id) => emitOption('select', id);
</script>


<template>
    <el-menu default-active="0" class="admin-menu" background-color="rgb(48, 65, 86)" text-color="#ffffff" @select="onMenuSelect">
        <el-menu-item index="0">
            <el-icon><House /></el-icon>
            <span>后台首页</span>
        </el-menu-item>

        <el-menu-item index="1">
            <el-icon><Connection /></el-icon>
            <span>友链管理</span>
        </el-menu-item>

        <el-menu-item index="2">
            <el-icon><Guide /></el-icon>
            <span>下载管理</span>
        </el-menu-item>

        <el-menu-item index="3">
            <el-icon><Discount /></el-icon>
            <span>版本管理</span>
        </el-menu-item>

        <el-menu-item index="4">
            <el-icon><MessageBox /></el-icon>
            <span>团队成员</span>
        </el-menu-item>

        <el-menu-item index="5">
            <el-icon><Message /></el-icon>
            <span>联系方式</span>
        </el-menu-item>
    </el-menu>
</template>