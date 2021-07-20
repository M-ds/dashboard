import RegistrationService from "@/person/registration/service/RegistrationService";

export const PersonRegistrationStore = {
  namespaced: true,
  state: {
    success: false,
    errorMessage: ""
  },
  mutations: {
    registrationSuccess(state) {
      state.success = true;
      state.errorMessage = "";
    },
    registrationFailure(state, errorMessage) {
      state.success = false;
      state.errorMessage = errorMessage;
    }
  },
  actions: {
    async registerPerson(context, signupRequest) {
      const result = await RegistrationService.register(signupRequest);
      if (result.valid) {
        context.commit("registrationSuccess");
      } else {
        const errorMessage = result.error.errorMessage;
        context.commit("registrationFailure", errorMessage);
      }
    }
  },
  getters: {
    registrationSuccess: (state) => {
      return state.success;
    },
    getErrorMessage: (state) => {
      return state.errorMessage;
    }
  }
};