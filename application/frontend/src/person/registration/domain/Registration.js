/* @Flow */
export type Registration = {
  username: string;
  email: string;
  password: string;
  passwordConformation: string;
}

let RegistrationInput = {
  username: "",
  email: "",
  password: "",
  passwordConformation: ""
}

export default RegistrationInput;