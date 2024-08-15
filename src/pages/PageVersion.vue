
<script setup>
    /**
     * 版本列表页面
     */

    import utils from '@/scripts/utils'
    import { onMounted, ref } from 'vue'

    const versionList = ref([]);
    const activeCollapse = ref(['1']);

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(utils.apiConfig.version).then((data) => {
            versionList.value = data.data;
        }).catch(async (error) => {
            throw new Error(error);
        });
    });

    document.title = "版本列表 - AIDE Pro";
</script>

<template>
    <div class="page-version page-container">
        <header class="page-header">版本列表</header>

        <van-collapse v-model="activeCollapse">
            <van-collapse-item v-for="(item,index) in versionList" :key="index" :title="`${item.version}（${item.vercode}）`" :name="item.vercode">
                <p v-html="item.updateLog"></p>
            </van-collapse-item>
        </van-collapse>
    </div>
</template>

<style>
    @import url("./assets/PageVersion.css");
</style>

