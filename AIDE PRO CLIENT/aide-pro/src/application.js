/* eslint-disable no-undef */

import { Lazyload } from 'vant'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import '@/assets/various/variour.css'

import router from './router'
import Application from './Application.vue'

const application = createApp(Application);

(async () => {
    application.use(createPinia());
    application.use(router);
    application.use(Lazyload, {lazyComponent: true});
    application.mount('#application');
})().catch(() => {
    ElMessage({ message: '系统异常！', type: 'error', plain: true})
});