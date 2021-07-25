<template>
  <div class="container">
    <div class="centered__box">
      <h1>Login</h1>
      <h3 v-if="(errorMessage !== '')">{{ errorMessage }}</h3>
      <div>
        <label>
          Username
          <input type="text"
                 name="username"
                 v-model="person.username"
                 placeholder="Username"
          />
        </label>
      </div>
      <div>
        <label>
          Password
          <input type="password"
                 name="password"
                 v-model="person.password"
                 placeholder="Password"
          />
        </label>
      </div>
      <button
          @click="login()"
      >Log in
      </button>
      <router-link
          :to="{name: 'NewPerson'}"
          tag="button"
      >New User
      </router-link>
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