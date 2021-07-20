import Vue from "vue";
import App from "./App.vue";
import router from "./Router";
import store from "@/store/index";
import mini from "./assets/styles/mini-default.css";
import Toast from "vue-toastification";
// Import the CSS or use your own!
import "vue-toastification/dist/index.css";

Vue.config.productionTip = false;
Vue.use(mini);
Vue.use(Toast);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
