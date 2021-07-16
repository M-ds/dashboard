const USERNAME_REGEX = "/[A-Za-z0-9]+/";
const EMAIL_REGEX = "/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/";

function validateRegistration(registration) {
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

function isValidUsername(username) {
  if (username.trim() === "") return false;
  const validUsername = username.match(USERNAME_REGEX);
  return validUsername !== "";
}

function isSimilarPassword(password, conformationPassword) {
  return password === conformationPassword;
}

function isValidEmail(email) {
  if (email.trim() === "") return false;
  const validEmail = email.match(EMAIL_REGEX);
  return validEmail !== "";
}

export default { validateRegistration };