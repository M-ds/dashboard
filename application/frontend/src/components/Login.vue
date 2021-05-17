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
    }
  },
  methods: {
    login() {
      this.loading = true;
      const username = this.person.username;
      const password = this.person.password;
      if (username && password) {
        this.$store.dispatch("UserStore/login", this.person).then(
            () => {
              console.log(this.$store.getters["UserStore/person"].model);
              this.$router.push({ name: "Dashboard" });
            },
            error => {
              this.loading = false;
              this.errorMessage = `${error.response.data.error}: ${error.response.data.message}`;
            }
        );
      }
      this.loading = false;
    }
  }
};
</script>

<style scoped>
h1 {
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