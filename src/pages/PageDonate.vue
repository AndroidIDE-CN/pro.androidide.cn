
<script setup>
    /**
     * 捐献赞助页面
     */

    import utils from '@/scripts/utils'
    import { onMounted, ref, watch } from 'vue'

    const isBlur = ref(false);
    const payAction = ref('0');
    const currentPrice = ref('0');
    const keyboardStatus = ref(false);
    const otherPriceOnly = ref('其他金额');
    const applicationStore = utils.useApplicationStore();
    const priceList = ref(['5.01','6.66','8.88','23.30','99.9']);

    watch(currentPrice,() => {
        if (currentPrice.value === 'other') {
            keyboardStatus.value = true;
            if (applicationStore.isDeviceMobile) {
                otherPriceOnly.value = '';
            } else otherPriceOnly.value = '0';
        } else otherPriceOnly.value = '其他金额';
    });
    const onKeyboardInput = async (price) => {
        if (otherPriceOnly.value.length <= 3) otherPriceOnly.value = otherPriceOnly.value + price
    };
    const onLeyboardDelete = async () => otherPriceOnly.value = otherPriceOnly.value.slice(0, -1);

    onMounted(async () => {
        document.querySelector("#application").style = `
            min-height: 100%;
            padding-top: 0!important;
        `;
    });

    const onOtherPriceClick = async () => {
        currentPrice.value = 'other';
        if (!isBlur.value) keyboardStatus.value = !keyboardStatus.value;
    }

    const onKeyboardOnBlur = async () => {
        isBlur.value = true;
        keyboardStatus.value = false;
        setTimeout(async () => isBlur.value = false,200);
    }

    document.title = "捐赠我们";
</script>

<template>
    <div :class="`page-donate ${applicationStore.isDeviceMobile && 'page-container'}`" :style="{ paddingTop: '10px' }">
        <!-- <header class="page-header">捐助我们</header> -->

        <van-grid :column-num="3" style="width: 95%; margin: 0 auto;">
            <van-grid-item v-for="(item ,index) in priceList" :key="index" @click="currentPrice = item">
                <div class="price-item" :class="currentPrice === item && 'item-active'">￥{{ item }}元</div>
            </van-grid-item>

            <van-grid-item @click="onOtherPriceClick">
                <div class="price-item" :class="currentPrice === 'other' && 'item-active'" v-if="applicationStore.isDeviceMobile">
                    <span>{{ otherPriceOnly === '' ? '￥0元' : otherPriceOnly === '其他金额' ? otherPriceOnly : "￥" + otherPriceOnly + "元" }}</span>
                </div>

                <div class="price-item" :class="currentPrice === 'other' && 'item-active'" v-if="!applicationStore.isDeviceMobile">
                    <input class="price-input" type="text" v-model="otherPriceOnly"/>
                </div>
            </van-grid-item>
        </van-grid>

        <van-grid :column-num="3" style="width: 95%; margin: 20px auto;">
            <div class="input-group">
                <input class="input-container" type="text" placeholder="请输入你的昵称">
                <input class="input-container" type="text" placeholder="请输入QQ号，留空表示匿名捐助">
            </div>
        </van-grid>
        
        <van-grid :column-num="3" style="width: 95%; margin: 20px auto;">
            <input class="input-container" type="text" placeholder="请输入留言">
        </van-grid>

        <van-grid :column-num="3" style="width: 95%; margin: 20px auto;">
            <van-grid-item @click="payAction = '1'">
                <div :class="`pay-item ${payAction == '1' && 'item-active'}`" style="--color: #00c250; --shadow: rgba(8,187,8,0.2);">
                    <svg style="margin-top: 3px;" t="1724169297620" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4244" width="26" height="26"><path d="M404.511405 600.865957c-4.042059 2.043542-8.602935 3.223415-13.447267 3.223415-11.197016 0-20.934798-6.169513-26.045189-15.278985l-1.959631-4.296863-81.56569-178.973184c-0.880043-1.954515-1.430582-4.14746-1.430582-6.285147 0-8.251941 6.686283-14.944364 14.938224-14.944364 3.351328 0 6.441713 1.108241 8.94165 2.966565l96.242971 68.521606c7.037277 4.609994 15.433504 7.305383 24.464181 7.305383 5.40101 0 10.533914-1.00284 15.328104-2.75167l452.645171-201.459315C811.496653 163.274644 677.866167 100.777241 526.648117 100.777241c-247.448742 0-448.035176 167.158091-448.035176 373.361453 0 112.511493 60.353576 213.775828 154.808832 282.214547 7.582699 5.405103 12.537548 14.292518 12.537548 24.325012 0 3.312442-0.712221 6.358825-1.569752 9.515724-7.544837 28.15013-19.62599 73.202209-20.188808 75.314313-0.940418 3.529383-2.416026 7.220449-2.416026 10.917654 0 8.245801 6.692423 14.933107 14.944364 14.933107 3.251044 0 5.89015-1.202385 8.629541-2.7793l98.085946-56.621579c7.377014-4.266164 15.188934-6.89913 23.790846-6.89913 4.577249 0 9.003048 0.703011 13.174044 1.978051 45.75509 13.159718 95.123474 20.476357 146.239666 20.476357 247.438509 0 448.042339-167.162184 448.042339-373.372709 0-62.451354-18.502399-121.275087-51.033303-173.009356L407.778822 598.977957 404.511405 600.865957z" fill="#00C800" p-id="4245"></path></svg>
                    <span class="item-name">微信支付</span>
                </div>
            </van-grid-item>

            <van-grid-item @click="payAction = '2'">
                <div :class="`pay-item ${payAction == '2' && 'item-active'}`" style="--color: #1677ff; --shadow: rgba(0,170,238,0.2);">
                    <svg style="margin-top: 1.45px;" t="1724206537619" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5288" width="22" height="22"><path d="M1024.0512 701.0304V196.864A196.9664 196.9664 0 0 0 827.136 0H196.864A196.9664 196.9664 0 0 0 0 196.864v630.272A196.9152 196.9152 0 0 0 196.864 1024h630.272a197.12 197.12 0 0 0 193.8432-162.0992c-52.224-22.6304-278.528-120.32-396.4416-176.64-89.7024 108.6976-183.7056 173.9264-325.3248 173.9264s-236.1856-87.2448-224.8192-194.048c7.4752-70.0416 55.552-184.576 264.2944-164.9664 110.08 10.3424 160.4096 30.8736 250.1632 60.5184 23.1936-42.5984 42.496-89.4464 57.1392-139.264H248.064v-39.424h196.9152V311.1424H204.8V267.776h240.128V165.632s2.1504-15.9744 19.8144-15.9744h98.4576V267.776h256v43.4176h-256V381.952h208.8448a805.9904 805.9904 0 0 1-84.8384 212.6848c60.672 22.016 336.7936 106.3936 336.7936 106.3936zM283.5456 791.6032c-149.6576 0-173.312-94.464-165.376-133.9392 7.8336-39.3216 51.2-90.624 134.4-90.624 95.5904 0 181.248 24.4736 284.0576 74.5472-72.192 94.0032-160.9216 150.016-253.0816 150.016z" fill="#009FE8" p-id="5289"></path></svg>
                    <span class="item-name">支付宝</span>
                </div>
            </van-grid-item>

            <van-grid-item @click="payAction = '3'">
                <div :class="`pay-item ${payAction == '3' && 'item-active'}`" style="--color: #0070ba; --shadow: rgba(5, 83, 114, 0.2);">
                    <svg t="1724206656124" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8515" width="20" height="20"><path d="M449.28 241.002667H738.133333c155.093333 0 213.461333 78.506667 204.458667 194.005333-14.869333 190.442667-130.005333 295.744-282.730667 295.744h-77.098666c-20.906667 0-35.008 13.824-40.704 51.413333l-33.066667 218.325334c-2.154667 14.165333-9.6 22.506667-20.821333 23.509333h-181.12c-17.045333 0-23.104-13.034667-18.645334-41.28l110.613334-700.288c4.416-28.074667 19.776-41.429333 50.282666-41.429333z" fill="#009DE2" p-id="8516"></path><path d="M268.714667 0H557.866667c81.450667 0 178.026667 2.645333 242.645333 59.626667 43.157333 38.058667 65.792 98.709333 60.586667 163.84C843.306667 444.202667 711.253333 567.893333 534.186667 567.893333h-142.613334c-24.277333 0-40.362667 16.064-47.210666 59.626667l-39.786667 253.141333c-2.56 16.426667-9.706667 26.112-22.698667 27.264H103.765333c-19.754667 0-26.773333-15.104-21.610666-47.850666L210.410667 48.042667C215.530667 15.488 233.344 0 268.714667 0z" fill="#113984" p-id="8517"></path><path d="M348.522667 602.005333l50.496-319.573333c4.416-28.074667 19.776-41.429333 50.282666-41.429333H738.133333c47.808 0 86.442667 7.466667 116.714667 21.226666-29.013333 196.48-156.096 305.664-322.538667 305.664H389.76c-18.773333 0-32.789333 9.6-41.216 34.112z" fill="#172C70" p-id="8518"></path></svg>
                    <span class="item-name">Paypal</span>
                </div>
            </van-grid-item>
        </van-grid>

        <van-grid :column-num="3" style="width: 95%; margin: 20px auto;">
            <button class="donate-button">立即捐助</button>
        </van-grid>

        <van-number-keyboard v-if="applicationStore.isDeviceMobile" :show="keyboardStatus" theme="custom" :extra-key="['00', '.']" close-button-text="完成" @blur="onKeyboardOnBlur" @input="onKeyboardInput" @delete="onLeyboardDelete"/>
    </div>
</template>

<style>
    @import url("./assets/PageDonate.css");
</style>