<script setup>
    import { ref } from 'vue'
    import utils from '@/scripts/utils'

    const showBottom = ref(false);
    const functionStore = utils.useFunctionStore();
    const applicationStore = utils.useApplicationStore();

    const onDownSelect = async (item) => utils.openOtherPage(item.url);
    const scrollView = () => document.querySelector(".down-scroll").scrollIntoView();
</script>

<template>
    <div class="main-container" :class="applicationStore.isDeviceMobile && 'container-mobile'" v-if="applicationStore.requestContent">
        <div class="main-box" v-if="!applicationStore.isDeviceMobile">
            <div class="main-mask"></div>
            <div class="container">
                <p class="aide-title accant">AIDE&nbsp;&nbsp;PRO</p>
                <p class="aide-description">旨在为安卓开发者提供强大且便捷的开发环境，让他们能够更轻松地创建、编译和测试安卓应用程序，提高开发效率并实现更好的代码管理</p>
                <p class="button-group">
                    <el-dropdown :style="{ lineHeight: 'unset',verticalAlign: 'unset',height: 'auto',fontSize: 'unset' }" v-if="applicationStore.requestContent.download.length > 1">
                        <button class="aide-btn">立即下载</button>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item v-for="(item, index) in applicationStore.requestContent.download" :key="index" @click="utils.openOtherPage(item.url)">{{ item.name }}</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                    <button class="aide-btn" v-else @click="utils.openOtherPage(applicationStore.requestContent.download[0].url)">立即下载</button>
                    <button class="aide-btn" @click="functionStore.setIsHisVerOpen(true)">历史版本</button>
                    <button class="aide-btn" @click="utils.openOtherPage('https://pd.qq.com/g/r90h7p6zo6')">加入频道</button>
                    <button class="aide-btn btn-border-primary" @click="utils.openOtherPage('https://github.com/AndroidIDE-CN/AIDE-Pro-Release/releases')">获取社区版</button>
                </p>
            </div>
        </div>

        <div class="main-box box-mobile" v-if="applicationStore.isDeviceMobile">
            <div class="container">
                <p class="aide-title accant">AIDE&nbsp;&nbsp;PRO</p>
                <p class="aide-description">旨在提供强大且便捷的开发环境，提高开发效率并实现更好的代码管理</p>
                <p class="button-group">
                    <button class="aide-btn" @click="showBottom = true" v-if="applicationStore.requestContent.download.length > 1">立即下载</button>
                    <button class="aide-btn" v-else @click="utils.openOtherPage(applicationStore.requestContent.download[0].url)">立即下载</button>
                    <button class="aide-btn" @click="functionStore.setIsHisVerOpen(true)">历史版本</button>
                    <button class="aide-btn" @click="utils.openOtherPage('https://pd.qq.com/g/r90h7p6zo6')">加入频道</button>
                    <button class="aide-btn btn-border-primary" @click="utils.openOtherPage('https://github.com/AndroidIDE-CN/AIDE-Pro-Release/releases')">获取社区版</button>
                </p>
            </div>
        </div>
        <van-action-sheet v-model:show="showBottom" :actions="applicationStore.requestContent.download" cancel-text="取消" close-on-click-action @select="onDownSelect" description="请选择下载路线"/>
    </div>
</template>

<style lang="scss">
    @import url("./assets/MainModel.scss");
</style>