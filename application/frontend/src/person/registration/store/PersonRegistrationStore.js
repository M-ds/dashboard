import RegistrationService from "@/person/registration/service/RegistrationService";

export const PersonRegistrationStore = {
  namespaced: true,
  state: {
    success: false,
    errorMessage: "",
    successMessage: ""
  },
  mutations: {
    registrationSuccess(state) {
      state.success = true;
      state.errorMessage = "";
    },
    registrationFailure(state, errorMessage) {
      state.success = false;
      state.errorMessage = errorMessage;
    },
    setSuccessMessage(state, successMessage) {
      state.successMessage = successMessage;
    }
  },
  actions: {
    async registerPerson(context, signupRequest) {
      const response = await RegistrationService.register(signupRequest);
      // @param - successMessage | String
      if (response.successMessage) {
        context.commit("registrationSuccess");
        context.commit("setSuccessMessage", response.successMessage);
      } else context.commit("registrationFailure", response);
    }
  },
  getters: {
    registrationSuccess: (state) => {
      return state.success;
    },
    getErrorMessage: (state) => {
      return state.errorMessage;
    },
    getSuccessMessage: (state) => {
      return state.successMessage;
    }
  }
};