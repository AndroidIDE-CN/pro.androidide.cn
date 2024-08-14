<!-- eslint-disable no-undef -->

<script setup>
    import utils from '@/scripts/utils'
    import preview from "@/image/preview.jpg"
    import { reactive, ref, onMounted } from 'vue'

    const showBottom = ref(false);
    const readyStatus = ref(false);
    const downloadConfig = ref(null);
    const previewList = reactive([]);
    const functionStore = utils.useFunctionStore();
    const applicationStore = utils.useApplicationStore();

    const letDownload = async (link) => {
        try {
            location.href = link;
        } catch(error) {
            ElMessage({ message: '链接跳转失败', type: 'error', plain: true})
        }
    }
    
    const onDownSelect = async (link) => {
        try {
            location.href = link.href;
        } catch(error) {
            ElMessage({ message: '链接跳转失败', type: 'error', plain: true})
        }
    }

    onMounted(async () => {
        await utils.axiostool.sendHttpGet(utils.apiConfig.download).then(async (data) => {
            downloadConfig.value = data.data;

            await utils.axiostool.sendHttpGet(utils.apiConfig.preview).then((data) => {
                readyStatus.value = true;

                try {
                    data.data.forEach(element => {
                        previewList.push(element.image);
                    });
                } catch(error) {
                    ElMessage({ message: '预览图像处理失败', type: 'error', plain: true});
                }
            }).catch(async (error) => {
                throw new Error(error);
            });
        }).catch(async (error) => {
            throw new Error(error);
        });
    });
</script>

<template>
    <div class="main-container" :style="{ minHeight: applicationStore.isDeviceMobile ? 'calc(100vh - 65px)' : '600px', background: applicationStore.isDeviceMobile ? '#ffffff' : 'rgb(248 250 252)' }" :class="applicationStore.isDeviceMobile && 'container-mobile'" v-if="readyStatus">
        <div class="main-box" v-if="!applicationStore.isDeviceMobile">
            <div class="container">
                <div class="left-introduce">
                    <h2 class="left-title">一款现代化的编辑器</h2>
                    <p class="left-desc">基于原版深度定制、适配本土化的操作习惯，一键快速添加常用的代码，大幅提高开发效率。</p>

                    <div class="button-group">
                        <el-dropdown :style="{ lineHeight: 'unset',verticalAlign: 'unset',height: 'auto',fontSize: 'unset' }" v-if="downloadConfig.length > 1">
                            <a class="button-link" :style="{ background: '#495057', color: '#ffffff', border: '1px solid #495057' }" target="_blank" title="立即下载" :href="downloadConfig[0].href">立即下载</a>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item v-for="(item,index) in downloadConfig" :key="index" @click="letDownload(item.href)">{{ item.name }}</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                        <a class="button-link" :style="{ background: '#495057', color: '#ffffff', border: '1px solid #495057' }" target="_blank" title="立即下载" :href="downloadConfig[0].href" v-else>立即下载</a>
                        <span class="button-link" :style="{ background: '#ffffff', color: '#495057', border: '1px solid #495057' }"  @click="functionStore.setIsHisVerOpen(true)">历史版本</span>
                    </div>
                </div>
                <img class="screenhot" v-lazy="preview" draggable="false" alt="AIDE Pro">
            </div>

            <div class="screenshot-group">
                <el-image v-for="(item,index) in previewList" :key="index" :src="item" :preview-src-list="previewList" :zoom-rate="1.2" :max-scale="7" :min-scale="0.2" :initial-index="4" fit="cover" class="screenshot-item" lazy :alt="item"/>
            </div>
        </div>

        <div class="main-box box-mobile" v-if="applicationStore.isDeviceMobile">
            <div class="container">
                <img class="screenhot" v-lazy="preview" draggable="false" alt="AIDE Pro">
                <div class="left-introduce">
                    <h2 class="left-title">一款现代化的编辑器</h2>
                    <p class="left-desc">基于原版深度定制、适配本土化的操作习惯，一键快速添加常用的代码，大幅提高开发效率。</p>

                    <div class="button-group">
                        <span class="button-link" :style="{ background: '#495057', color: '#ffffff', border: '1px solid #495057' }" @click="showBottom = true" v-if="downloadConfig.length > 1">立即下载</span>
                        <a class="button-link" :style="{ background: '#495057', color: '#ffffff', border: '1px solid #495057' }" target="_blank" title="立即下载" :href="downloadConfig[0].href" v-else>立即下载</a>
                        <span class="button-link" :style="{ background: '#ffffff', color: '#495057', border: '1px solid #495057' }" @click="functionStore.setIsHisVerOpen(true)">历史版本</span>
                    </div>

                    <div class="screenshot-group">
                        <el-image v-for="(item,index) in previewList" :key="index" :src="item" :preview-src-list="previewList" :zoom-rate="1.2" :max-scale="7" :min-scale="0.2" :initial-index="4" fit="cover" class="screenshot-item" lazy :alt="item"/>
                    </div>
                </div>
            </div>
        </div>
        <van-action-sheet v-model:show="showBottom" :actions="downloadConfig" cancel-text="取消" close-on-click-action @select="onDownSelect" description="请选择下载路线"/>
    </div>
</template>

<style>
    @import url("./assets/MainModel.css");
</style>