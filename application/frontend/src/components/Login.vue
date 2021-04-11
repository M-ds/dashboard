<template>
  <div id="login-container">
    <h1>Login</h1>
    <div>
      <label>
        Username
        <input type="text"
               name="userName"
               v-model="input.userName"
               placeholder="Username"
        />
      </label>
      <br/><br/>
      <label>
        Password
        <input type="password"
               name="password"
               v-model="input.password"
               placeholder="Password"
        />
      </label>
    </div>
    <br><br>
    <button @click="login()">Log in</button>
    <br><br>
    <router-link :to="{name: 'NewUser'}" tag="button">New User</router-link>
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
    login: function () {
      const message = `Username: ${this.input.userName} & Password: ${this.input.password}`;
      alert(message);
      // if correct redirect otherwise error
      this.$router.push({name: 'Dashboard'});
    }
  }
}
</script>

<style scoped>

</style>