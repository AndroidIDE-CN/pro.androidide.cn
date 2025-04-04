
<script setup>
    import utils from '@/scripts/utils'
    import preview from "./assets/preview.jpg"
    const applicationStore = utils.useApplicationStore();
</script>

<template>
    <van-overlay :show="applicationStore.isLoadingStatus" class="loading-layout" z-index="999999">
        <img :src="preview" class="loading-icon" draggable="false">
    </van-overlay>
</template>

<style lang="scss">
    @import url("./assets/LoadingLayout.scss");
</style>