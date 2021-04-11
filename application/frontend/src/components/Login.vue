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
    <router-link :to="{name: 'NewUser'}">Create new User</router-link>
    <!-- Create new user -->
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
    login: function() {
      const message = `Username: ${this.input.userName} & Password: ${this.input.password}`;
      alert(message);
    }
  }
}
</script>

<style scoped>

</style>