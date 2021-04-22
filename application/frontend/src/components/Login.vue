<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <div class="card large centered">
          <h1>Login</h1>
          <input type="text"
                 name="userName"
                 v-model="input.userName"
                 placeholder="Username"
          />
          <input type="password"
                 name="password"
                 v-model="input.password"
                 placeholder="Password"
          />
          <button
              class="button"
              @click="login()"
          >Log in
          </button>
          <router-link
              :to="{name: 'NewUser'}"
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
      input: {
        userName: "",
        password: ""
      }
    }
  },
  methods: {
    login() {
      const message = `Username: ${this.input.userName} & Password: ${this.input.password}`;
      alert(message);
      // if correct redirect otherwise error
      this.$router.push({name: "Dashboard"});
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