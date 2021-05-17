import authService from "@/service/person/AuthService";
import userService from "@/service/person/PersonService";
import { PersonProfile } from "@/domain/PersonProfile";

export const PersonStore = {
  namespaced: true,
  state: {
    personLoggedIn: null,
    personProfile: null,
    status: {
      loggedIn: false
    }
  },
  // These only modify the state
  mutations: {
    loginSuccess(state, person) {
      state.status.loggedIn = true;
      state.personLoggedIn = person;
    },
    loginFailure(state) {
      state.status.loggedIn = false;
      state.personLoggedIn = null;
    },
    logout(state) {
      state.status.loggedIn = false;
      state.user = null;
    },
    // registerSuccess(state) {
    //   state.status.loggedIn = false;
    // },
    // registerFailure(state) {
    //   state.status.loggedIn = false;
    // },
    setPersonProfile(state, person) {
      state.personProfile = person;
    }
  },
  // Business logic actions are stored here
  actions: {
    async login(context, person) {
      const response = await authService.login(person);
      if (response.error) {
        context.commit("loginFailure");
        return Promise.reject(response.error);
      }
      context.commit("loginSuccess", response);
      return Promise.resolve(response);
    },
    logout(context) {
      authService.logout();
      context.commit('logout');
    },
    async getPersonProfile(context, userId) {
      let rawData = await userService.getPersonProfile(userId);
      if (rawData.valid) {
        let convertedPerson = new PersonProfile(rawData.model);
        context.commit("setPersonProfile", convertedPerson);
      }
      return Promise.resolve();
    }
  },
  getters: {
    person: (state) => {
      return state.personLoggedIn;
    },
    personProfile: (state) => {
      return state.personProfile;
    },
    loggedIn: (state) => {
      return state.status.loggedIn;
    }
  }
};
