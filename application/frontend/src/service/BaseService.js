import NotificationService from "@/common/notification/NotificationService";

export class BaseService {
  BASE_URL = "http://localhost:1997";

  /**
   * This method retrieves the person information from a logged in user.
   * With containing a token which is necessary to do requests.
   */
  getAuthHeader() {
    const personToken = JSON.parse(localStorage.getItem("personToken"));
    if (personToken) return { Authorization: `Bearer ${ personToken }` };
    return "";
  }

  handleResponse(response, onSuccessMessage) {
    if (response.status !== 200) {
      NotificationService.onError(`Error! ${ response.status }`);
    } else {
      const extractedData = response.data;
      if (!extractedData.valid) {
        NotificationService.onError(`Something went wrong! ${ extractedData.error.errorMessage }`);
        return extractedData.error.errorMessage;
      } else {
        NotificationService.onSucces(onSuccessMessage);
        return extractedData.model;
      }
    }
  }
}

