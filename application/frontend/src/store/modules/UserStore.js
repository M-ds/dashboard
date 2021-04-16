import userService from "@/service/user/UserService";

export const UserStore = {
  namespaced: true,
  state: () => ({
    user: null
  }),
  // These only modify the state
  mutations: {
    setUser(state, user) {
      this.state.user = user;
    }
  },
  // Business logic actions are stored here
  actions: {
    getUserProfile(state, userId) {
      let rawUser = userService.getUserProfile(userId)
      let convertedUser = new User(rawUser);
      state.commit('setUser', convertedUser)
    }
  },
  getters: {
    user: state => state.user,
  }
}
