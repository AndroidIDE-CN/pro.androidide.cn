
<script setup>
    import utils from '@/scripts/utils'
    const applicationStore = utils.useApplicationStore();

    const modelData = [
        {
            "title": "支持团队",
            "description": "拥有强大且专业的支持团队，随时为您提供贴心帮助和优质解决方案"
        },
        {
            "title": "稳定运行",
            "description": "系统持久且稳定运行，确保您的使用体验始终流畅无卡顿"
        },
        {
            "title": "自研技术",
            "description": "独家自研解析器，提供更高效、更准确的解析能力，性能卓越"
        },
        {
            "title": "活跃群聊",
            "description": "高活跃度的群聊社区，方便用户积极交流和分享宝贵经验"
        },
        {
            "title": "丰富生态",
            "description": "拥有丰富且完善的生态系统，满足不同用户的多样化需求和偏好"
        },
        {
            "title": "免费开源",
            "description": "免费且长期开源，用户可以自由使用和灵活修改，无后顾之忧"
        }
    ];
</script>

<template>
    <div class="selectntro-frame" :class="applicationStore.isDeviceMobile && 'frame-mobile'">
        <div class="selectntro-box">
            <div class="selectntro-data">
                <div class="selectntro-item" v-for="(item, index) in modelData" :key="index">
                    <h2 class="item-title">{{ item.title }}</h2>
                    <p class="item-description">{{ item.description }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss">
    @import url("./assets/SelectntroFrame.scss");
</style>