import axios from "axios";
import { BaseService } from "@/service/BaseService";

class PersonService extends BaseService {

  async getPersonProfile(userId) {
    let result = await axios.get(`${ this.BASE_URL }/api/person/${ userId }/profile`, { headers: this.getAuthHeader() });
    return result.data;
  }

}

export default new PersonService();