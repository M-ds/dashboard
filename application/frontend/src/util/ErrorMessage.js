function generateEmptyUsernamePasswordMessage(username, password) {
  let usernameEmpty = "";
  if (username != null && username.length <= 0) {
    usernameEmpty = "username";
  }

  let passwordEmpty = "";
  if (password != null && password.length <= 0) {
    passwordEmpty = "password";
  }

  let and = "";
  if (usernameEmpty !== "" && passwordEmpty !== "") {
    and = "and";
  }

  return `We need your ${ usernameEmpty } ${ and } ${ passwordEmpty } to login!`;
}

export default { generateEmptyUsernamePasswordMessage };
