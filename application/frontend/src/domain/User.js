export class Person {
  constructor(rawData) {
    this.userName = rawData.userName;
    this.password = rawData.password;
    this.email = rawData.email;
  }
}