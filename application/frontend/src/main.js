import Vue from "vue";
import App from "./App.vue";
import router from "./Router";
import store from "@/store/index";
import dashboard from "./assets/styles/dashboard.css";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

Vue.config.productionTip = false;
Vue.use(dashboard);
Vue.use(Toast);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
