
<script setup>
    import utils from '@/scripts/utils';

    const functionStore = utils.useFunctionStore();
    const applicationStore = utils.useApplicationStore();
</script>

<template>
    <div class="footer-container" :class="applicationStore.isDeviceMobile && 'container-mobile'">
        <div class="footer-box">
            <span class="copyrighgt-html">
                <p>©2024 Aide Pro All Rights Reserved OpenLin</p>
            </span>

            <div class="right-box">
                <span class="right-item" @click="functionStore.setIsContacOpen(true)">联系我们</span>
                <span class="right-item" @click="functionStore.setIsSupporOpen(true)">支持人员</span>
                <span class="right-item" @click="functionStore.setIsThanksOpen(true)">特别鸣谢</span>
                <span class="right-item" @click="functionStore.setIsUseAgrOpen(true)">使用协议</span>
                <span class="right-item" @click="functionStore.setIsPriAgrOpen(true)">隐私政策</span>
                <span class="right-item" @click="functionStore.setIsResAgrOpen(true)">免责声明</span>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/FootModel.css");
</style>