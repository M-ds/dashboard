import axios from "axios";
import { BaseService } from "@/service/BaseService";

class AuthService extends BaseService {

  async login(person) {
    return await axios.post(
      `${ this.BASE_URL }/user/auth/_log-in`, person
    ).then(response => {
      debugger;
      if (response.data.valid) {
        if (response.data.model.token) {
          localStorage.setItem("personToken", JSON.stringify(response.data.model.token));
        }
        return response.data.model;
      }
      return response.data.error;
    });
  }

  logout() {
    localStorage.removeItem("personToken");
  }
}

export default new AuthService();