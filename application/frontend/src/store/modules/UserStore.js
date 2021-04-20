import userService from "@/service/user/UserService";
import {Person} from "@/domain/User";

export const UserStore = {
  namespaced: true,
  state: {
    user: null
  },
  // These only modify the state
  mutations: {
    setUser(state, user) {
      state.user = user;
    }
  },
  // Business logic actions are stored here
  actions: {
    async getUserProfile(context, userId) {
      let rawData = await userService.getUserProfile(userId);
      if (rawData.valid) {
        let convertedUser = new Person(rawData.model);
        context.commit("setUser", convertedUser);
      }
      return Promise.resolve();
    }
  },
  getters: {
    user: (state) => {
      return state.user;
    }
  }
};
