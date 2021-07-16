import axios from "axios";
import { BaseService } from "@/service/BaseService";

class PersonProfileService extends BaseService {

  async getPersonProfile(personId) {
    const test = await axios.get(
      `${ this.BASE_URL }/api/person/${ personId }/profile`,
      { headers: this.getAuthHeader() }
    ).then((result ) => result.data);
    console.log(test);
    return test;
  }

}

export default new PersonProfileService();