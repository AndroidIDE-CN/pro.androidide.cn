
import Pace from "pace-js"
import PageHome from '@/pages/PageHome.vue'
import { useApplicationStore } from '@/stores/application'
import { createRouter, createWebHistory } from 'vue-router'

let status = false;

const isLoadComplete = async () => {
  const applicationStore = useApplicationStore();
  Pace.on("done", async () => {
    status = true;
    import("@/assets/paces/remove.css")
    applicationStore.setIsLoadinfgStatus(false);
    if (applicationStore.isDeviceMobile) {
      document.getElementById("application").style.paddingTop = "65px";
    } else document.getElementById("application").style.paddingTop = "75px";
  });
}

const createRoutes = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    
    {
      path: '/',
      name: 'home',
      component: PageHome,
      beforeEnter: [isLoadComplete],
    },{
       path: '/team',
       name: 'Team',
       component: () => import("@/pages/PageTeam.vue"),
       beforeEnter: [isLoadComplete],
    },{
      path: '/version',
      name: 'Version',
      component: () => import("@/pages/PageVersion.vue"),
      beforeEnter: [isLoadComplete],
    },
    
    {
      path: "/:catchAll(.*)",
      component: () => import("@/layout/NotFouLayout.vue"),
      beforeEnter: [isLoadComplete],
    }
  ],
  scrollBehavior(to, from, savedPosition) {
		if (savedPosition) return savedPosition;
		if (to.meta.scrollToTop) return { top: 0 };
		if (to.hash) return { el: to.hash, behavior: "smooth" };
	}
});

createRoutes.beforeEach(async (to, from, next) => {
	if (to === from) return;
	const applicationStore = useApplicationStore();
  if (to.fullPath !== '/') {
    import("@/assets/paces/remove.css");
  } else {
    Pace.on("start", async () => {
      if (status) return Pace.stop();
      await applicationStore.setIsLoadinfgStatus(true);
      document.getElementById("application").style.paddingTop = "0px";
    });
  }
	next();
});

export default createRoutes
