<template>
  <div class="container">
    <div class="card large centered">
      <form method="post">
        <h1>Create a new user</h1>
        <div class="row">
          <div v-if="(error !== '')" class="col-sm-12 col-md-12">
            {{ error }}
          </div>
          <div class="col-sm-12 col-md-12">
            <label>
              Username
              <input type="text"
                     name="userName"
                     v-model="registrationInput.username"
                     placeholder="Username"
              />
            </label>
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
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12 col-md-12">
            <label>
              Password
              <input type="text"
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
              <input type="text"
                     name="passwordConformation"
                     v-model="registrationInput.passwordConformation"
                     placeholder="Password"
              />
            </label>
          </div>
        </div>
        <button class="u-cf" @click="signup()">Sign up!</button>
      </form>
    </div>
  </div>
</template>

<script>
import RegistrationValidationUtil from "@/person/registration/utils/RegistrationValidationUtil";
import RegistrationInput from "@/person/registration/domain/Registration";

export default {
  name: "NewUser",
  data() {
    return {
      registrationInput: RegistrationInput,
      error: ""
    };
  },
  methods: {
    signup() {
      const valid = RegistrationValidationUtil.validateRegistration(this.registrationInput);
      if (valid !== "") {
        this.error = valid;
      } else {
        this.$router.push({ name: "Dashboard" });
      }
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