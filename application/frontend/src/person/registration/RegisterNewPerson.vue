<template>
  <div class="container">
    <div v-if="loading">
      <div class="spinner-donut large"/>
    </div>
    <div v-else class="card large centered">
      <h1>Create a new user</h1>
      <div v-if="errorOccurred" class="row">
        <div class="col-sm-12 col-md-12 error__text centered__text">
          {{ message }}
        </div>
      </div>
      <div v-if="succesRegistration" class="row">
        <div class="col-sm-12 col-md-12 centered__text">
          {{ message }}
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
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
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Email
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
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Password
            <input type="password"
                   name="password"
                   v-model="signupRequest.password"
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
                   v-model="signupRequest.passwordConformation"
                   placeholder="Password"
            />
          </label>
          <div v-if="this.areNotSimilarPasswords" class="error__text centered__text">
            {{ "Passwords are not similar!" }}
          </div>
        </div>
      </div>
      <button class="u-cf"
              @click="signup()"
              :disabled="this.areNotSimilarPasswords || this.isInvalidEmail || this.isInvalidUsername || this.inputIsEmpty"
      >Sign up!
      </button>
      <button class="u-cf"
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
        console.log(e);
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

.centered__text {
  text-align: center;
}
</style>