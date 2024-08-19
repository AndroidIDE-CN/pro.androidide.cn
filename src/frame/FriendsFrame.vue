
<script setup>
    import utils from '@/scripts/utils'
    import { ref, onMounted, watch } from 'vue'
    import { useInViewport } from 'vue-hooks-plus'
    
    const domRef = ref(null);
    const siteConfig = ref(null);
    const readyStatus = ref(false);
    const applicationStore = utils.useApplicationStore();

    onMounted(async () => {
        let requestNum = 1;
        const [inViewport] = useInViewport(domRef);

        watch(inViewport,async () => {
            if (requestNum == 0) {
                if (!applicationStore.isLoadingStatus && inViewport) {
                    await utils.axiostool.sendHttpGet(utils.apiConfig.friends).then((data) => {
                        siteConfig.value = data;
                        readyStatus.value = true;
                    }).catch(async (error) => {
                        throw new Error(error);
                    });
                }
            }
            requestNum--;
        });
    });
</script>

<template>
    <div class="friends-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'" ref="domRef">
        <div class="friends-box">
            <h1 class="frame-title">友情链接</h1>
            <p class="frame-desc">无需因陌生而胆怯，我眼里都是灰烬~</p>

            <div class="friends-data" v-if="readyStatus">
                <a class="friends-item" v-for="(item,index) in siteConfig" :key="index" :href="item.href" :target="item.target">
                    <img class="friends-image" v-lazy="item.image" :alt="item.title">
                    <div class="friends-content">
                        <span class="friends-name">{{ item.title }}</span>
                        <span class="friends-desc">{{ item.desc }}</span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</template>

<style>
    @import url("./assets/FriendsFrame.css");
</style>