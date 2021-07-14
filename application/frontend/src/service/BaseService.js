/* @flow */
export class BaseService {
  BASE_URL = "http://localhost:1997";

  /**
   * This method retrieves the person information from a logged in user.
   * With containing a token which is necessary to do requests.
   */
  getAuthHeader() {
    let personToken = JSON.parse(localStorage.getItem("personToken"));
    if (personToken) {
      return { Authorization: `Bearer ${ personToken }` };
    }
    return "";
  }
}

