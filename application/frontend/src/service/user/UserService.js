import axios from "axios";

const baseUserUrl = "http://localhost:1997/rest/user";

class UserService {

  async getUserProfile(userId) {
    let result = await axios.get(`${baseUserUrl}/${userId}/profile`);
    return result.data;
  }

}

export default new UserService();