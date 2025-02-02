<!-- eslint-disable no-undef -->

<script setup>
    /**
     * 支持人员页面
     */

    
    import utils from '@/scripts/utils'
    import { onMounted, ref } from 'vue'
    import { useRoute } from 'vue-router'

    const route = useRoute();
    const supPersonList = ref([]);

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(import.meta.env.VITE_SERVER_URL + "web/team").then((data) => {
            supPersonList.value = data.list;
        }).catch(async () => {
            ElMessage({ message: '请求接口失败', type: 'error', plain: true});
        });
    });
    
    document.title = "支持人员";
    if (route.query.from !== 'app') location.href = '/';
</script>

<template>
    <div class="page-support page-container">
        <!-- <header class="page-header">支持人员</header> -->
        <van-notice-bar text="由于部分数据丢失显示不全，请谅解。" @click="router.push('/donate')"/>

        <div class="contact-card" v-for="(item,index) in supPersonList" :key="index">
            <img class="avatar" v-lazy="item.avatar" :alt="item.name">
            <div class="user-content">
                <span class="username">{{ item.name }}</span>
                <p class="userdesc">{{ item.support }}</p>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/PageSupport.css");
</style>