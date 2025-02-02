
<script setup>
    import utils from '@/scripts/utils'
    const applicationStore = utils.useApplicationStore();

    const modelData = ['强大的支持团队','持久且稳定运行','独家自研解析器','高活跃度的群聊','拥有丰富的生态','免费且长期开源']
</script>

<template>
    <div class="selectntro-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'">
        <div class="selectntro-box">
            <h1 class="frame-title">为何选择</h1>
            <p class="frame-desc">无需因陌生而胆怯，我眼里都是灰烬~</p>
            <div class="selectntro-data">
                <div class="selectntro-item" v-for="(item, index) in modelData" :key="index">{{ item }}</div>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/SelectntroFrame.css");
</style>