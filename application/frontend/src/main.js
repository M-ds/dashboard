import Vue from "vue";
import App from "./App.vue";
import router from "./Router";
import skeleton from "./assets/styles/skeleton.css";
import normalize from "./assets/styles/normalize.css";

Vue.config.productionTip = false;
Vue.use(skeleton);
Vue.use(normalize);

new Vue({
    router,
    render: (h) => h(App),
}).$mount("#app");
