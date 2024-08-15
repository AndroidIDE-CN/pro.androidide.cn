
<script setup>
    import utils from '@/scripts/utils'
    import preview from "./assets/preview.jpg"

    const applicationStore = utils.useApplicationStore();
</script>

<template>
    <van-overlay :show="applicationStore.isLoadingStatus" class="loading-layout" z-index="999999">
        <img v-lazy="preview" class="loading-icon" draggable="false">
    </van-overlay>
</template>

<style scoped>
    div.van-overlay {
        width: 100vw;
        height: 100%;
        display: flex;
        overflow: hidden;
        background: #ffffff!important;
    }

    div.van-overlay img.loading-icon {
        margin: auto;
        width: 135px;
        height: 135px;
        border-radius: 50%;
        position: relative;
    }
</style>