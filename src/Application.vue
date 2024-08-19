<script setup>
  import { onMounted } from 'vue'
  import utils from '@/scripts/utils'
  import { RouterView } from 'vue-router'
  import LoadingLayout from './layout/LoadingLayout.vue'
  import ErrorFaiLayout from './layout/ErrorFaiLayout.vue'

  const applicationStore = utils.useApplicationStore();
  onMounted(async () => await applicationStore.setSiteConfig());
</script>

<template>
  <!-- 顶部安全区 -->
  <div class="van-safe-area-top"></div>
  <LoadingLayout/><RouterView/>
  <!-- <el-watermark content="AIDE Pro">
    <RouterView v-if="!applicationStore.isSiteErrStatus && !applicationStore.isLoadingStatus"/>
  </el-watermark> -->
  <ErrorFaiLayout v-if="applicationStore.isSiteErrStatus" :errorInfo="applicationStore.siteErrorDetail"/>
  <!-- 底部安全区 -->
  <div class="van-safe-area-bottom"></div>
</template>