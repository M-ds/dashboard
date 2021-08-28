import { BaseService } from "@/service/BaseService";
import axios from "axios";

class RegistrationService extends BaseService {

  async register(signupRequest) {
    const httpResponse = await axios.post(
      `${ this.BASE_URL }/user/auth/_signup`, signupRequest
    );
    return this.handleResponse(httpResponse, "Successful registered! Please check your email to confirm your registration!")
  }

}

export default new RegistrationService();