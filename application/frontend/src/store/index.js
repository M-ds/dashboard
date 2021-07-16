import Vue from "vue";
import Vuex from "vuex";
import { DashboardStore } from "./modules/DashboardStore";
import { PersonStore } from "./modules/PersonStore";
import { PersonLoginStore } from "@/person/login/store/PersonLoginStore";

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== "production";

const mainStore = new Vuex.Store({
  modules: {
    DashboardStore,
    PersonLoginStore,
    PersonStore
  },
  strict: debug
});

export default mainStore;