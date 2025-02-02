
<script setup>
    import axiostool from '@/scripts/axiostool'
    import { User } from '@element-plus/icons-vue'

    const propsData = defineProps({ select: String });
    const titleList = ['后台首页', '友链管理', '下载管理', '版本管理', '团队成员', '联系方式'];
</script>

<template>
    <div class="admin-topnav">
        <span class="admin-title">{{ titleList[propsData.select] }}</span>
        <div class="action-box">
            <el-dropdown>
                <el-icon :size="20" style="width: 40px; height: 40px;"><User/></el-icon>
                <template #dropdown>
                    <el-dropdown-menu @click="axiostool.sendLogoutRequest">
                      <el-dropdown-item>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
            </el-dropdown>
        </div>
    </div>
</template>