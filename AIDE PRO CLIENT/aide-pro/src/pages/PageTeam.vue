<!-- eslint-disable no-undef -->

<script setup>
    /**
     * 团队成员页面
     */

    import utils from '@/scripts/utils'
    import { onMounted, ref } from 'vue'
    import { useRoute } from 'vue-router'

    const route = useRoute();
    const teanPersonList = ref([]);

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(import.meta.env.VITE_SERVER_URL + "web/team").then((data) => {
            teanPersonList.value = data;
        }).catch(async () => {
            ElMessage({ message: '请求接口失败', type: 'error', plain: true});
        });
    });
    
    document.title = "团队成员";
    if (route.query.from !== 'app') location.href = '/';
</script>

<template>
    <div class="page-member page-container">
        <!-- <header class="page-header">团队成员</header> -->

        <div class="contact-card" v-for="(item,index) in teanPersonList" :key="index">
            <img class="avatar" v-lazy="item.avatar" :alt="item.name">
            <div class="user-content">
                <span class="username">{{ item.name }}</span>
                <p class="userdesc">{{ item.support }}</p>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/PageTeam.css");
</style>