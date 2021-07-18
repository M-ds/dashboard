<template>
  <div class="container">
    <div class="card large centered">
      <form method="post">
        <h1>Create a new user</h1>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <label>
              Username
              <input type="text"
                     name="userName"
                     v-model="registrationInput.username"
                     placeholder="Username"
              />
            </label>
            <div v-if="this.isInvalidUsername" class="error__text">
              {{ "Username is not valid!" }}
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <label>
              Email
              <input type="text"
                     name="email"
                     v-model="registrationInput.email"
                     placeholder="example@email.com"
              />
            </label>
            <div v-if="this.isInvalidEmail" class="error__text">
              {{ "Email is not valid!" }}
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <label>
              Password
              <input type="password"
                     name="password"
                     v-model="registrationInput.password"
                     placeholder="Password"
              />
            </label>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <label>
              Repeat password
              <input type="password"
                     name="passwordConformation"
                     v-model="registrationInput.passwordConformation"
                     placeholder="Password"
              />
            </label>
            <div v-if="this.areNotSimilarPasswords" class="error__text">
              {{ "Passwords are not similar!" }}
            </div>
          </div>
        </div>
        <button class="u-cf" @click="signup()">Sign up!</button>
      </form>
    </div>
  </div>
</template>

<script>
import RegistrationInput from "@/person/registration/domain/Registration";

export default {
  name: "NewUser",
  data() {
    return {
      registrationInput: RegistrationInput,
      usernameRegex: new RegExp("^[A-Za-z0-9]*$"),
      emailRegex: new RegExp("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    };
  },
  methods: {
    signup() {
      this.$router.push({ name: "Dashboard" });
    }
  },
  computed: {
    isInvalidUsername() {
      return !this.usernameRegex.test(this.registrationInput.username);
    },
    areNotSimilarPasswords: function () {
      if (this.registrationInput.password === "" || this.registrationInput.passwordConformation === "") return false;
      return this.registrationInput.password !== this.registrationInput.passwordConformation;
    },
    isInvalidEmail() {
      if (this.registrationInput.email === "") return false;
      return !this.emailRegex.test(this.registrationInput.email);
    }
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
}

input {
  width: 98%;
}

.centered {
  position: fixed;
  top: 25%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>