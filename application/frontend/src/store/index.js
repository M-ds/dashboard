import Vue from "vue";
import Vuex from "vuex";
import { DashboardStore } from "./modules/DashboardStore";
import { PersonStore } from "./modules/PersonStore";
import { PersonLoginStore } from "@/person/login/store/PersonLoginStore";
import { PersonRegistrationStore } from "@/person/registration/store/PersonRegistrationStore";

Vue.use(Vuex);

// const debug = process.env.NODE_ENV !== "production";

const mainStore = new Vuex.Store({
  modules: {
    DashboardStore,
    PersonLoginStore,
    PersonRegistrationStore,
    PersonStore
  }
});

export default mainStore;