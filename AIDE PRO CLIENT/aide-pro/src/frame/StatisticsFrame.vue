
<script setup>
    import utils from '@/scripts/utils'
    const applicationStore = utils.useApplicationStore();
    
    const siteConfig = [{
        title: '累计启动',
        value: '10万次'
    },{
        title: '累计编译',
        value: '10万次'
    },{
        title: '累计构建',
        value: '10万次'
    },{
        title: '累计打包',
        value: '10万次'
    }];
</script>

<template>
    <div class="statistics-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'">
        <div class="statistics-box">
            <h1 class="frame-title">数据统计</h1>
            <p class="frame-desc">无需因陌生而胆怯，我眼里都是灰烬~</p>

            <div class="statistics-data">
                <div class="statistics-item" v-for="(item,index) in siteConfig" :key="index">
                    <span class="item-value">{{ item.value }}</span>
                    <span class="item-title">{{ item.title }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss">
    @import url("./assets/StatisticsFrame.scss");
</style>