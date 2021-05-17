function generateErrorMessage(username, password) {
  let error = "";
  if (username != null && username.length <= 0) {
    error = "Fill in a username";
  } else {
    error = "The provided username is not known";
  }

  let passwordError = "";
  if (password != null && password.length <= 0) {
    passwordError = "Fill in your password";
  } else {
    passwordError = "The provided password is incorrect";
  }

  if (error === "") {
    return passwordError;
  } else if (passwordError === "") {
    return error;
  } else {
    return `${ error }. ${ passwordError }`;
  }
}

export default { generateErrorMessage };
