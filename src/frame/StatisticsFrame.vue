
<script setup>
    import utils from '@/scripts/utils'
    import { ref, onMounted } from 'vue'
    
    const siteConfig = ref(null);
    const readyStatus = ref(false);
    const applicationStore = utils.useApplicationStore();

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(utils.apiConfig.statistics).then((data) => {
            readyStatus.value = true;
            siteConfig.value = data.data;
        }).catch(async (error) => {
            throw new Error(error);
        });
    });
</script>

<template>
    <div class="statistics-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'">
        <div class="statistics-box">
            <h1 class="frame-title">数据统计</h1>
            <p class="frame-desc">无需因陌生而胆怯，我眼里都是灰烬~</p>

            <div class="statistics-data" v-if="readyStatus">
                <div class="statistics-item" v-for="(item,index) in siteConfig.data" :key="index">
                    <span class="item-value">{{ item.value }}</span>
                    <span class="item-title">{{ item.title }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/StatisticsFrame.css");
</style>