<script setup>
  import { onMounted } from 'vue'
  import utils from '@/scripts/utils'
  import { RouterView } from 'vue-router'
  import { inject } from "@vercel/analytics"
  import LoadingLayout from './layout/LoadingLayout.vue'
  import ErrorFaiLayout from './layout/ErrorFaiLayout.vue'
  import { SpeedInsights } from "@vercel/speed-insights/vue"

  const applicationStore = utils.useApplicationStore();
  onMounted(async () => await applicationStore.setSiteConfig());
</script>

<template>
  <!-- 顶部安全区 -->
  <div class="van-safe-area-top"></div>
  <LoadingLayout/><RouterView/><SpeedInsights/>
  <ErrorFaiLayout v-if="applicationStore.isSiteErrStatus" :errorInfo="applicationStore.siteErrorDetail"/>
  <!-- 底部安全区 -->
  <div class="van-safe-area-bottom"></div><inject/>
</template>