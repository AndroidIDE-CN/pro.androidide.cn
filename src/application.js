/* eslint-disable no-undef */

import './assets/style.css'
import "@/scripts/ready.js"
import { Lazyload } from 'vant'
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import Toast from "vue-toastification"
import Application from './Application.vue'
import router from './router'

const application = createApp(Application);

const toastOptions = {
    maxToasts: 1,
    closeOnClick: true,pauseOnHover: true,
    closeButton: "button",position: "top-right",
    draggablePercent: 0.6,pauseOnFocusLoss: true,
    icon: true,rtl: false,timeout: 2000,draggable: true,
};

(async () => {
    application.use(createPinia());
    application.use(router);
    application.use(Toast, toastOptions),
    application.use(Lazyload, {lazyComponent: true});

    application.mount('#application')
})().catch(() => {
    ElMessage({ message: '系统异常！', type: 'error', plain: true})
});