
<script setup>
    import utils from '@/scripts/utils'
    const functionStore = utils.useFunctionStore();
    const applicationStore = utils.useApplicationStore();
</script>

<template>
    <div class="head-container" :style="{ height: functionStore.isMobileHeadMore ? `${75 + 45 * 4}px` : applicationStore.isDeviceMobile ? '65px' : '75px' }">
        <div class="head-box" :style="{ width: applicationStore.isDeviceMobile ? '90%' : 'var(--application-width)', height: applicationStore.isDeviceMobile ? '65px' : '75px' }">
            <span class="aide-title" :style="{ lineHeight: applicationStore.isDeviceMobile ? '65px' : '75px' }">AIDE Pro</span>

            <div class="link-box" v-if="!applicationStore.isDeviceMobile">
                <span class="link-text" @click="functionStore.setIsUpdateOpen(true)">更新日志</span>
                <span class="link-text" @click="functionStore.setIsHisVerOpen(true)">历史版本</span>
                <span class="link-text" @click="functionStore.setIsSponsoOpen(true)">捐助我们</span>
                <span class="link-text" @click="utils.openOtherPage('https://pd.qq.com/g/r90h7p6zo6')">官方频道</span>
            </div>

            <button class="more-btn" v-if="applicationStore.isDeviceMobile" @click="functionStore.setIsMobileHeadMore(!functionStore.isMobileHeadMore)">
                <svg t="1722518834149" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4424" width="32" height="32"><path d="M860.084255 294.438962c0 30.008454-24.26566 54.374398-54.241368 54.374398L218.155067 348.813361c-29.942963 0-54.241368-24.365944-54.241368-54.374398l0 0c0-30.0412 24.298406-54.373375 54.241368-54.373375l587.68782 0C835.818595 240.064564 860.084255 264.396739 860.084255 294.438962L860.084255 294.438962zM860.084255 516.514827c0 30.008454-24.26566 54.373375-54.241368 54.373375L218.155067 570.888202c-29.942963 0-54.241368-24.364921-54.241368-54.373375l0 0c0-30.042223 24.298406-54.374398 54.241368-54.374398l587.68782 0C835.818595 462.140429 860.084255 486.472604 860.084255 516.514827L860.084255 516.514827zM860.084255 729.528292c0 30.0412-24.26566 54.406121-54.241368 54.406121L218.155067 783.934412c-29.942963 0-54.241368-24.364921-54.241368-54.406121l0 0c0-30.0412 24.298406-54.407144 54.241368-54.407144l587.68782 0C835.818595 675.121148 860.084255 699.487092 860.084255 729.528292L860.084255 729.528292z" fill="#343a40" p-id="4425"></path></svg>
            </button>
        </div>

        <div class="head-more" :style="{ height: functionStore.isMobileHeadMore ? `${45 * 4}px` : '0px', paddingBottom: functionStore.isMobileHeadMore ? '10px' : '0px' }">
            <div class="link-item">
                <span class="link-text" @click="functionStore.setIsUpdateOpen(true)">更新日志</span>
            </div>
            <div class="link-item">
                <span class="link-text" @click="functionStore.setIsHisVerOpen(true)">历史版本</span>
            </div>
            <div class="link-item">
                <a class="link-text" href="/donate" title="捐助我们" target="_blank">捐助我们</a>
            </div>

            <div class="link-item">
                <span class="link-text" @click="utils.openOtherPage('https://pd.qq.com/g/r90h7p6zo6')">官方频道</span>
            </div>
        </div>
    </div>
</template>

<style lang="scss">
    @import url("./assets/HeadModel.scss");
</style>