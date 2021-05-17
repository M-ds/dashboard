import axios from "axios";
import { BaseService } from "@/service/BaseService";

class PersonService extends BaseService {

  async getPersonProfile(personId) {
    return await axios.get(
      `${ this.BASE_URL }/api/person/${ personId }/profile`,
      { headers: this.getAuthHeader() }
    ).then(result => result.data);
  }

}

export default new PersonService();