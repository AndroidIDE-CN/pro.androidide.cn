/* eslint-disable no-undef */

import '@/assets/style.css'
import { Lazyload } from 'vant'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import '@/assets/various/variour.css'

import Application from './Application.vue'
import router from './router'

const application = createApp(Application);

(async () => {
    application.use(createPinia());
    application.use(router);
    application.use(Lazyload, {lazyComponent: true});
    application.mount('#application');
})().catch(() => {
    ElMessage({ message: '系统异常！', type: 'error', plain: true})
});