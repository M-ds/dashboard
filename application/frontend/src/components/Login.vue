<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="card large centered">
          <h1>Login</h1>
          <h3 v-if="(errorMessage !== '')">{{ errorMessage }}</h3>
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
              class="button"
              @click="login()"
          >Log in
          </button>
          <router-link
              :to="{name: 'NewPerson'}"
              tag="button"
              class="button"
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
      return this.$store.getters["PersonStore/loggedIn"];
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push({ name: "Dashboard" });
    }
  },
  methods: {
    login() {
      this.loading = true;
      const username = this.person.username;
      const password = this.person.password;
      if (username && password) {
        this.$store.dispatch("PersonStore/login", this.person).then(
            () => {
              this.$router.push({ name: "Dashboard" });
            },
            (error) => {
              this.loading = false;
              if (error.errorMessage) {
                this.errorMessage = error.errorMessage;
              }
            }
        );
      } else {
        this.errorMessage = errorMessage.generateEmptyUsernamePasswordMessage(username, password);
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
h1,
h3 {
  text-align: center;
}

input {
  margin-left: 8px;
  margin-right: 8px;
}

.centered {
  position: fixed;
  top: 25%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>