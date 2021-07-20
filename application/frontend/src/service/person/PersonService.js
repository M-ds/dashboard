import axios from "axios";
import { BaseService } from "@/service/BaseService";

class PersonProfileService extends BaseService {

  async getPersonProfile(personId) {
    const httpResponse = await axios.get(
      `${ this.BASE_URL }/api/person/${ personId }/profile`,
      { headers: this.getAuthHeader() }
    );
    return httpResponse.data;
  }

}

export default new PersonProfileService();