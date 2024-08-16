<!-- eslint-disable no-undef -->

<script setup>
    /**
     * 团队成员页面
     */

    import utils from '@/scripts/utils'
    import { onMounted, ref } from 'vue'

    const teanPersonList = ref([]);

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(utils.apiConfig.thanks).then((data) => {
            teanPersonList.value = data.data;
        }).catch(async () => {
            ElMessage({ message: '请求接口失败', type: 'error', plain: true});
        });
    });

    document.title = "团队成员 - AIDE Pro";
</script>

<template>
    <div class="page-member page-container">
        <!-- <header class="page-header">团队成员</header> -->

        <div class="contact-card" v-for="(item,index) in teanPersonList" :key="index">
            <img class="avatar" v-lazy="`https://q2.qlogo.cn/g?b=qq&s=640&nk=${item.qq}`" :alt="item.nick">
            <div class="user-content">
                <span class="username">{{ item.nick }}</span>
                <p class="userdesc">{{ item.desc }}</p>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/PageTeam.css");
</style>