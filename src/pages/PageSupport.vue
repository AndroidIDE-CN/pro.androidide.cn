<!-- eslint-disable no-undef -->

<script setup>
    /**
     * 支持人员页面
     */

    import utils from '@/scripts/utils'
    import { onMounted, ref } from 'vue'
    import { useRouter } from 'vue-router'

    const router = useRouter();
    const supPersonList = ref([]);

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(utils.apiConfig.support).then((data) => {
            supPersonList.value = data.data.data.list;
        }).catch(async () => {
            ElMessage({ message: '请求接口失败', type: 'error', plain: true});
        });
    });

    document.title = "支持成员 - AIDE Pro";
</script>

<template>
    <div class="page-support page-container">
        <!-- <header class="page-header">支持人员</header> -->
        <van-notice-bar text="由于部分数据丢失显示不全，请谅解。" @click="router.push('/donate')"/>

        <div class="contact-card" v-for="(item,index) in supPersonList" :key="index">
            <img class="avatar" v-lazy="item.img" :alt="item.name">
            <div class="user-content">
                <span class="username">{{ item.name }}</span>
                <p class="userdesc">{{ item.sum }}</p>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/PageSupport.css");
</style>