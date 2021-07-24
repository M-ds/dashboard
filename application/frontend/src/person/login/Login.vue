<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-offset-3">
        <div class="centered__box">
          <h1>Login</h1>
          <h3 v-if="(errorMessage !== '')">{{ errorMessage }} </h3>
          <input type="text"
                 name="username"
                 v-model="person.username"
                 placeholder="Username"
          />
          <input type="password"
                 name="password"
                 v-model="person.password"
                 placeholder="Password"
          />
          <button
              @click="login()"
              class="button__left"
          >Log in
          </button>
          <router-link
              :to="{name: 'NewPerson'}"
              tag="button"
              class="button__right"
          >New User
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import errorMessage from "@/util/ErrorMessage";

export default {
  name: "Login",
  data() {
    return {
      person: {
        username: "",
        password: ""
      },
      loading: false,
      errorMessage: ""
    };
  },
  computed: {
    loggedIn() {
      return this.$store.getters["PersonLoginStore/loggedIn"];
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push({name: "Dashboard"});
    }
  },
  methods: {
    async login() {
      this.loading = true;
      const username = this.person.username;
      const password = this.person.password;
      if (username && password) {
        const result = await this.$store.dispatch("PersonLoginStore/login", this.person);
        if (result === null) this.loading = false;
        else await this.$router.push({name: "Dashboard"});
      } else {
        this.errorMessage = errorMessage.generateEmptyUsernamePasswordMessage(username, password);
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
</style>