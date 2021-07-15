/* @Flow */
export default class LogoutService {
  static logout() {
    localStorage.removeItem("personToken");
  }
}