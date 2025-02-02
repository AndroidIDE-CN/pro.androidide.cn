<!-- eslint-disable vue/multi-word-component-names -->

<script setup>

</script>

<template>
    <div class="content-frame frame-dashboard">
        <div class="flex-line">
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
        </div>
        <div class="flex-line">
            <div class="dash-box" style="width: 100%"></div>
        </div>
        <div class="flex-line">
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
        </div>
        <div class="flex-line">
            <div class="dash-box" style="width: 100%"></div>
        </div>
        <div class="flex-line">
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
            <div class="dash-box" style="width: 23.5%"></div>
        </div>
        <div class="flex-line">
            <div class="dash-box" style="width: 100%"></div>
        </div>
    </div>
</template>

<style>
    div.flex-line {
        width: 100%;
        display: flex;
        margin: 10px 0;
        align-items: center;
        justify-content: space-between;
    }

    div.flex-line div.dash-box {
        height: 100px;
        cursor: pointer;
        border-radius: 5px;
        margin-bottom: 20px;
        background-color: #ffffff;
        border: 1px solid #E4E7ED;
    }
</style>