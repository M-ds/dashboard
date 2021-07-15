/* @flow */
import logoutService from "@/person/login/service/LogoutService";
import userService from "@/service/person/PersonService";
import { PersonProfile } from "@/person/profile/domain/PersonProfile";

export const PersonStore = {
  namespaced: true,
  state: {
    personProfile: null,
  },
  // These only modify the state
  mutations: {
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
    logout() {
      logoutService.logout();
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
    personProfile: (state) => {
      return state.personProfile;
    }
  }
};
