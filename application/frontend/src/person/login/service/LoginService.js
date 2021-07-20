import axios from "axios";
import { BaseService } from "@/service/BaseService";

class LoginService extends BaseService {

  async login(person) {
    const httpResponse = await axios.post(`${ this.BASE_URL }/user/auth/_log-in`, person);
    const result = this.handleResponse(httpResponse, "Successful login!");
    if (result.token) {
      localStorage.setItem("personToken", JSON.stringify(result.token));
      return result
    }
    // This will be the error message if we come this far.
    debugger;
    return result;
  }
}

export default new LoginService();