<!-- eslint-disable vue/multi-word-component-names -->

<script setup>
    import Team from '../Items/Team.vue'
    import Friends from '../Items/Friends.vue'
    import Contact from '../Items/Contact.vue'
    import Download from '../Items/Download.vue'
    import Dashbord from '../Items/Dashbord.vue'
    import Historical from '../Items/Historical.vue'

    const propsData = defineProps({ select: String });
    const contentList = [ Dashbord, Friends, Download, Historical, Team, Contact ]
</script>

<template>
    <div class="admin-content">
        <component :is="contentList[propsData.select]"/>
    </div>
</template>