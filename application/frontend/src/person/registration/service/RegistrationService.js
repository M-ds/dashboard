import { BaseService } from "@/service/BaseService";
import axios from "axios";

class RegistrationService extends BaseService {

  async register(signupRequest) {
    const httpResponse = await axios.post(
      `${ this.BASE_URL }/user/auth/_signup`, signupRequest
    );
    return httpResponse.data;
  }

}

export default new RegistrationService();