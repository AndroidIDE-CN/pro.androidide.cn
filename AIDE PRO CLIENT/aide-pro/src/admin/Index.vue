<!-- eslint-disable vue/multi-word-component-names -->

<script setup>
    import { ref } from 'vue'
    import None from './None.vue'
    import utils from '@/scripts/utils'
    import axiostool from '@/scripts/axiostool'

    const menuSelect = ref("0");
    const isReadyStatus = ref(false);
    document.title = "AIDE PRO ADMIN";
    const applicationStore = utils.useApplicationStore();

    if (localStorage.getItem('aide_login') === "true" && localStorage.getItem('aide_token') !== null) {
        axiostool.validUserToken().then((response) => {
            if (response.code === 200) isReadyStatus.value = true
        });
    } else location.href = "/login";
    const onMenuSelect = (id) => menuSelect.value = id;
</script>

<template>
    <div v-if="isReadyStatus">
        <div class="page-admin" v-if="!applicationStore.isDeviceMobile">
            <el-container>
                <el-aside width="200px">
                    <p class="admin-title">AIDE PRO ADMIN</p>
                    <SideBar @select="onMenuSelect"/>
                </el-aside>
                <el-container>
                    <el-header>
                        <TopNav :select="menuSelect"/>
                    </el-header>
                    <el-main>
                        <Content :select="menuSelect"/>
                    </el-main>
                </el-container>
            </el-container>
        </div>
        <None v-else/>
    </div>
</template>

<style>
    @import url("./styles/Index.css");
</style>