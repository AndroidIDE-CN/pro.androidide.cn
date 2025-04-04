
<script setup>
    import utils from '@/scripts/utils'
    const applicationStore = utils.useApplicationStore();
</script>

<template>
    <div class="friends-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'" v-if="applicationStore.requestContent">
        <div class="friends-box">
            <h1 class="frame-title">友情链接</h1>
            <p class="frame-desc">无需因陌生而胆怯，我眼里都是灰烬~</p>

            <div class="friends-data">
                <a class="friends-item" v-for="(item,index) in applicationStore.requestContent.friend" :key="index" :href="item.url" target="_blank">
                    <img class="friends-image" v-lazy="item.icon" :alt="item.name">
                    <div class="friends-content">
                        <span class="friends-name">{{ item.name }}</span>
                        <span class="friends-desc">{{ item.description }}</span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</template>

<style lang="scss">
    @import url("./assets/FriendsFrame.scss");
</style>