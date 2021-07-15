/* @Flow */
import { Registration } from "@/person/registration/domain/Registration";

/*@private*/
const USERNAME_REGEX = "/[A-Za-z0-9]+/";
/*@private*/
const EMAIL_REGEX = "/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/";

function validateRegistration(registration: Registration): string {
  debugger;
  const validUsername = isValidUsername(registration.username);
  if (!validUsername) return "Username is not valid!";

  debugger;
  const similarPasswords = isSimilarPassword(
    registration.password,
    registration.passwordConformation
  );
  if (!similarPasswords) return "Please check passwords, these are not similar";

  debugger;
  const validEmail = isValidEmail(registration.email);
  if (!validEmail) return "Please check email! Invalid input!";

  debugger;
  return "";
}

/*@private*/
function isValidUsername(username: string): boolean {
  if (username.trim() === "") return false;
  const validUsername = username.match(this.USERNAME_REGEX);
  return validUsername !== "";
}

/*@private*/
function isSimilarPassword(password: string, conformationPassword: string): boolean {
  return password === conformationPassword;
}

/*@private*/
function isValidEmail(email: string): boolean {
  if (email.trim() === "") return false;
  const validEmail = email.match(this.EMAIL_REGEX);
  return validEmail !== "";
}

export default { validateRegistration };