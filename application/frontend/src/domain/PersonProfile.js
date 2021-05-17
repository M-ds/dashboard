export class PersonProfile {
  constructor(rawData) {
    this.username = rawData.username;
    this.password = rawData.password;
    this.email = rawData.email;
  }
}