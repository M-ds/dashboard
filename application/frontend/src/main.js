/* @flow */
import Vue from "vue";
import App from "./App.vue";
import router from "./Router";
import store from "@/store/index";
import mini from "./assets/styles/mini-default.css";

Vue.config.productionTip = false;
Vue.use(mini);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
