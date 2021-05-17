export class BaseService {
  BASE_URL = "http://localhost:1997";

  /**
   * This method retrieves the person information from a logged in user.
   * With containing a token which is necessary to do requests.
   *
   * @returns {{Authorization: string} | {}}
   */
  getAuthHeader() {
    let person = JSON.parse(localStorage.getItem("personToken"));
    debugger;
    if (person && person.accessToken) {
      return { Authorization: 'Bearer ' + person.accessToken };
    } else {
      return {};
    }
  }
}

