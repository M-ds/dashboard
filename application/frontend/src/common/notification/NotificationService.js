import Vue from "vue";
import { POSITION, TYPE } from "vue-toastification";

// https://github.com/Maronato/vue-toastification
class NotificationService {

  onError(errorMessage) {
    Vue.$toast(errorMessage, {
      timeout: 5000,
      position: POSITION.BOTTOM_RIGHT,
      type: TYPE.ERROR,
      pauseOnHover: false
    });
  }

  onSucces(succesMessage) {
    Vue.$toast(succesMessage, {
      timeout: 5000,
      position: POSITION.BOTTOM_RIGHT,
      type: TYPE.SUCCESS,
      pauseOnHover: false
    })
  }

}

export default new NotificationService();