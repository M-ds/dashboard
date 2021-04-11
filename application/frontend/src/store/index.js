import Vue from "vue";
import Vuex from "vuex";
import {DashboardStore} from "./modules/DashboardStore";

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== "production";

const mainStore = new Vuex.Store({
  modules: {
    DashboardStore
  },
  strict: debug
});

export default mainStore;