import { BaseService } from "@/service/BaseService";
import axios from "axios";
import NotificationService from "@/common/notification/NotificationService";

class RegistrationService extends BaseService {

  async register(signupRequest) {
    const httpResponse = await axios.post(
      `${ this.BASE_URL }/user/auth/_signup`, signupRequest
    );
    NotificationService.onSucces("Successful registered! Please login to continue!");
    return httpResponse.data;
  }

}

export default new RegistrationService();