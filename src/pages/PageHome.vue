<!-- eslint-disable no-undef -->

<script setup>
    import { reactive, ref } from 'vue';
    import utils from '@/scripts/utils';
    import HeadModel from '@/model/HeadModel.vue';
    import MainModel from '@/model/MainModel.vue';
    import FootModel from '@/model/FootModel.vue';
    import FriendsFrame from '@/frame/FriendsFrame.vue';
    import SelectntroFrame from '@/frame/SelectntroFrame.vue';
    import StatisticsFrame from '@/frame/StatisticsFrame.vue';

    const historyModel = ref(['null']);
    const modelConfig = utils.siteConfig;
    const functionStore = utils.useFunctionStore();
    const applicationStore = utils.useApplicationStore();

    const letGoContack = async (link) => {
        try {
            location.href = link;
        } catch(error) {
            ElMessage({ message: '链接跳转失败', type: 'error', plain: true})
        }
    }

    const requestDatas = reactive({
        isThanksOpen: null,
        isContacOpen: null,
        isHisVerOpen: null,
        isSupporOpen: null,
    });

    const requestStatus = reactive({
        isThanksOpen: false,
        isContacOpen: false,
        isHisVerOpen: false,
        isSupporOpen: false,
    });

    const isHisVerOpen = async () => {
        functionStore.setIsMobileHeadMore(false);
        if (!requestStatus.isHisVerOpen) {
            await utils.axiostool.sendHttpGet(utils.apiConfig.version).then((data) => {
                requestDatas.isHisVerOpen = data;
                requestStatus.isHisVerOpen = true;
            }).catch(async () => {
                ElMessage({ message: '请求接口失败', type: 'error', plain: true});
            });
        }
    }

    const isContacOpen = async () => {
        functionStore.setIsMobileHeadMore(false);
        if (!requestStatus.isContacOpen) {
            await utils.axiostool.sendHttpGet(utils.apiConfig.contack).then((data) => {
                requestDatas.isContacOpen = data;
                requestStatus.isContacOpen = true;
            }).catch(async () => {
                ElMessage({ message: '请求接口失败', type: 'error', plain: true});
            });
        }
    }

    const isThanksOpen = async () => {
        functionStore.setIsMobileHeadMore(false);
        if (!requestStatus.isThanksOpen) {
            await utils.axiostool.sendHttpGet(utils.apiConfig.thanks).then((data) => {
                requestDatas.isThanksOpen = data;
                requestStatus.isThanksOpen = true;
            }).catch(async () => {
                ElMessage({ message: '请求接口失败', type: 'error', plain: true});
            });
        }
    }

    const isSupporOpen = async () => {
        functionStore.setIsMobileHeadMore(false);
        if (!requestStatus.isSupporOpen) {
            await utils.axiostool.sendHttpGet(utils.apiConfig.support).then((data) => {
                requestStatus.isSupporOpen = true;
                requestDatas.isSupporOpen = data.list;
            }).catch(async () => {
                ElMessage({ message: '请求接口失败', type: 'error', plain: true});
            });
        }
    }
</script>

<template>
    <HeadModel/><MainModel/><StatisticsFrame/><SelectntroFrame/><FriendsFrame/><FootModel/>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isUseAgrOpen" title="使用协议" :width="applicationStore.isDeviceMobile ? '90%' : '720px'" @open="functionStore.setIsMobileHeadMore(false)">
        <div class="innerHtml" v-html="modelConfig.useAgrHtml"></div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isPriAgrOpen" title="隐私政策" :width="applicationStore.isDeviceMobile ? '90%' : '720px'" @open="functionStore.setIsMobileHeadMore(false)">
        <div class="innerHtml" v-html="modelConfig.priAgrHtml"></div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isResAgrOpen" title="免责声明" :width="applicationStore.isDeviceMobile ? '90%' : '720px'" @open="functionStore.setIsMobileHeadMore(false)">
        <div class="innerHtml" v-html="modelConfig.resAgrHtml"></div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isUpdeteOpen" title="更新日志" :width="applicationStore.isDeviceMobile ? '90%' : '520px'" @open="isHisVerOpen">
        <div class="innerHtml" v-loading="!requestStatus.isHisVerOpen" :style="{ height: requestStatus.isHisVerOpen ? 'auto' : '200px' }">
            <div v-if="requestStatus.isHisVerOpen">
                <h3 style="margin: 0;">版本信息</h3>
                <p>最新版本：{{ requestDatas.isHisVerOpen[0].version }}（{{ requestDatas.isHisVerOpen[0].vercode }}）</p>
                <p>安装包大小：{{ requestDatas.isHisVerOpen[0].versize }}</p>
                <h3 style="margin: 0;">更新日志</h3>
                <p v-html="requestDatas.isHisVerOpen[0].updateLog"></p>
            </div>
        </div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isHisVerOpen" title="历史版本" :width="applicationStore.isDeviceMobile ? '90%' : '520px'" @open="isHisVerOpen">
        <div class="innerHtml" v-loading="!requestStatus.isHisVerOpen" :style="{ height: requestStatus.isHisVerOpen ? 'auto' : '200px' }">
            <el-collapse v-model="historyModel">
                <el-collapse-item v-for="(item,index) in requestDatas.isHisVerOpen" :key="index" :title="`${item.version}（${item.vercode}）`" :name="item.vercode">
                    <p v-html="item.updateLog"></p>
                </el-collapse-item>
            </el-collapse>
        </div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isThanksOpen" title="特别鸣谢" :width="applicationStore.isDeviceMobile ? '90%' : '520px'" @open="isThanksOpen">
        <div class="special-thanks" v-loading="!requestStatus.isThanksOpen" :style="{ height: requestStatus.isThanksOpen ? 'auto' : '200px' }">
            <div class="special-thanks-item" v-for="(item,index) in requestDatas.isThanksOpen" :key="index">
                <img class="user-avatar" v-lazy="`https://q2.qlogo.cn/g?b=qq&s=640&nk=${item.qq}`" :alt="item.nick">
                <div class="user-content">
                    <h3 class="user-nick">{{ item.nick }}</h3>
                    <p class="user-desc">{{ item.desc }}</p>
                </div>
            </div>
        </div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isContacOpen" title="联系我们" :width="applicationStore.isDeviceMobile ? '90%' : '520px'" @open="isContacOpen">
        <div class="contack-us" v-loading="!requestStatus.isContacOpen" :style="{ height: requestStatus.isContacOpen ? 'auto' : '200px' }">
            <van-cell  v-for="(item,index) in requestDatas.isContacOpen" :key="index" :title="item.name" :label="item.desc" class="contack-item" @click="letGoContack(item.href)"/>
        </div>
    </el-dialog>

    <el-dialog align-center destroy-on-close class="application-dialog" v-model="functionStore.isSupporOpen" title="支持人员" :width="applicationStore.isDeviceMobile ? '90%' : '520px'" @open="isSupporOpen">
        <div class="special-thanks" v-loading="!requestStatus.isSupporOpen" :style="{ height: requestStatus.isSupporOpen ? 'auto' : '200px' }">
            <div class="special-thanks-item" v-for="(item,index) in requestDatas.isSupporOpen" :key="index">
                <img class="user-avatar" v-lazy="item.img" :alt="item.name">
                <div class="user-content">
                    <h3 class="user-nick">{{ item.name }}</h3>
                    <p class="user-desc">{{ item.sum }}</p>
                </div>
            </div>
        </div>
    </el-dialog>
</template>

<style>
    @import url("./assets/PageHome.css");
</style>