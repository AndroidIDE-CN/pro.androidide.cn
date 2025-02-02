<!-- eslint-disable vue/multi-word-component-names -->

<script setup>
  import { onMounted } from 'vue'
  import utils from '@/scripts/utils'
  import { RouterView } from 'vue-router'

  const applicationStore = utils.useApplicationStore();
  onMounted(async () => await applicationStore.setSiteConfig());
</script>

<template>
  <!-- 顶部安全区 -->
  <div class="van-safe-area-top"></div>
  <LoadingLayout/><RouterView/>
  <ErrorFaiLayout v-if="applicationStore.isSiteErrStatus" :errorInfo="applicationStore.siteErrorDetail"/>
  <!-- 底部安全区 -->
  <div class="van-safe-area-bottom"></div>
</template> 