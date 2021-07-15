/* @Flow */
import authService from "@/person/login/service/LoginService";

export const PersonLoginStore = {
  namespaced: true,
  state: {
    personLoggedIn: null,
    status: {
      loggedIn: false
    }
  },
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
    }
  },
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
      context.commit("logout")
    }
  },
  getters: {
    person: (state) => {
      return state.personLoggedIn;
    },
    loggedIn: (state) => {
      return state.status.loggedIn;
    }
  }
}