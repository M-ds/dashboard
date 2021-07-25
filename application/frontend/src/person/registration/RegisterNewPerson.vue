<template>
  <div class="container">
    <div class="centered__box">
      <h1>Create a new user</h1>
      <div v-if="errorOccurred">
        <div class="error__text centered__text">
          {{ message }}
        </div>
      </div>
      <div v-if="succesRegistration">
        <div class="  centered__text">
          {{ message }}
        </div>
      </div>
      <div>
        <label>
          Username
          <input type="text"
                 name="userName"
                 v-model="signupRequest.username"
                 placeholder="Username"
          />
        </label>
        <div v-if="this.isInvalidUsername" class="error__text centered__text">
          {{ "Username is not valid!" }}
        </div>
      </div>
      <div>
        <label>
          Email address
          <input type="text"
                 name="email"
                 v-model="signupRequest.email"
                 placeholder="example@email.com"
          />
        </label>
        <div v-if="this.isInvalidEmail" class="error__text centered__text">
          {{ "Email is not valid!" }}
        </div>
      </div>
      <div>
        <label>
          Password
          <input type="password"
                 name="password"
                 v-model="signupRequest.password"
                 placeholder="Password"
          />
        </label>
      </div>
      <div>
        <label>
          Repeat password
          <input type="password"
                 name="passwordConformation"
                 v-model="signupRequest.passwordConformation"
                 placeholder="Password"
          />
        </label>
        <div v-if="this.areNotSimilarPasswords" class="error__text centered__text">
          {{ "Passwords are not similar!" }}
        </div>
      </div>
      <button @click="signup()"
              :disabled="this.areNotSimilarPasswords || this.isInvalidEmail || this.isInvalidUsername || this.inputIsEmpty"
      >Sign up!
      </button>
      <button
          @click="loginScreen()"
      >Login Screen
      </button>
    </div>
  </div>
</template>
<script>

export default {
  name: "NewUser",
  data() {
    return {
      loading: false,
      signupRequest: {
        username: "",
        email: "",
        password: "",
        passwordConformation: ""
      },
      usernameRegex: new RegExp("^[A-Za-z0-9]*$"),
      emailRegex: new RegExp("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"),
      errorOccurred: false,
      succesRegistration: false,
      message: ""
    };
  },
  methods: {
    async signup() {
      this.loading = true;
      try {
        await this.$store.dispatch("PersonRegistrationStore/registerPerson", this.signupRequest);
        if (this.$store.getters["PersonRegistrationStore/registrationSuccess"]) {
          this.loading = false;
          this.succesRegistration = true;
          // this.signupRequest = "";
          this.message = "SUCCES! Please log in!";
        } else {
          this.errorOccurred = true
          this.message = this.$store.getters["PersonRegistrationStore/getErrorMessage"];
        }
        this.loading = false;
      } catch (e) {
        this.loading = false;
      }
    },
    loginScreen() {
      this.$router.push({name: "Login"});
    }
  },
  computed: {
    isInvalidUsername() {
      return !this.usernameRegex.test(this.signupRequest.username);
    },
    areNotSimilarPasswords: function () {
      if (this.signupRequest.password === "" || this.signupRequest.passwordConformation === "") return false;
      return this.signupRequest.password !== this.signupRequest.passwordConformation;
    },
    isInvalidEmail() {
      if (this.signupRequest.email === "") return false;
      return !this.emailRegex.test(this.signupRequest.email);
    },
    inputIsEmpty() {
      return this.signupRequest.email === ""
          || this.signupRequest.username === ""
          || this.signupRequest.password === ""
          || this.signupRequest.passwordConformation === "";
    }
  }
}
</script>

<style scoped>
</style>