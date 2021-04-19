import Vue from "vue";
import Vuex from "vuex";
import {DashboardStore} from "./modules/DashboardStore";
import {UserStore} from "./modules/UserStore";

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== "production";

const mainStore = new Vuex.Store({
  modules: {
    DashboardStore,
    UserStore
  },
  strict: debug
});

export default mainStore;