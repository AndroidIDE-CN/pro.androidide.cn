/* eslint-disable no-undef */

import { ref } from 'vue'
import { defineStore } from 'pinia'
import axiostool from '@/scripts/axiostool'

const isMobile = () => {
  return (navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i) || window.innerWidth <= 720);
}

onresize = async () => {
  let state = isMobile();
  const applicationStore = useApplicationStore();
  applicationStore.setIsDeviceMobile(state);
}

export const useApplicationStore = defineStore('application', () => {
  const isLoadingStatus = ref(false);
  const isSiteErrStatus = ref(false);
  const siteErrorDetail = ref(null);
  const requestContent = ref(null);
  const isDeviceMobile = ref(isMobile());

  const setSiteConfig = async () => {
    try {
      await axiostool.sendHttpGet(import.meta.env.VITE_SERVER_URL + "web/info").then(async (response) => {
        requestContent.value = response;
        if (typeof(response) === 'string') {
          isSiteErrStatus.value = true;
          isLoadingStatus.value = false;
          siteErrorDetail.value = response.data;
          ElMessage({ message: '配置文件解析失败', type: 'error', plain: true});
        }
      });
      
    } catch(error) {
      isSiteErrStatus.value = true;
      siteErrorDetail.value = error;
      ElMessage({ message: '获取配置文件失败', type: 'error', plain: true});
    }
  };

  const setIsDeviceMobile = (value) => isDeviceMobile.value = value;
  const setIsLoadinfgStatus = (value) => isLoadingStatus.value = value;

  return { requestContent, isDeviceMobile, isSiteErrStatus, isLoadingStatus, siteErrorDetail, setIsDeviceMobile, setIsLoadinfgStatus, setSiteConfig }
});
