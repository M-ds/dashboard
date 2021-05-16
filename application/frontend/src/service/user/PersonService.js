import axios from "axios";

const baseUserUrl = "http://localhost:1997/api/person";

class PersonService {

  async getPersonProfile(userId) {
    let result = await axios.get(`${baseUserUrl}/${userId}/profile`);
    return result.data;
  }

}

export default new PersonService();