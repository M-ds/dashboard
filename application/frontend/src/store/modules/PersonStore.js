import userService from "@/service/user/PersonService";
import {Person} from "@/domain/User";

export const PersonStore = {
  namespaced: true,
  state: {
    person: null
  },
  // These only modify the state
  mutations: {
    setPerson(state, user) {
      state.person = user;
    }
  },
  // Business logic actions are stored here
  actions: {
    async getPersonProfile(context, userId) {
      let rawData = await userService.getPersonProfile(userId);
      if (rawData.valid) {
        let convertedPerson = new Person(rawData.model);
        context.commit("setPerson", convertedPerson);
      }
      return Promise.resolve();
    }
  },
  getters: {
    person: (state) => {
      return state.person;
    }
  }
};
