<template>
  <div class="container">
    <div class="row">
      <div class="eight columns offset-by-four">
        <h1>Login</h1>
        <div>
          <label>
            Username
            <input type="text"
                   name="userName"
                   v-model="input.userName"
                   placeholder="Username"
                   class="u-cf"
            />
          </label>
        </div>
        <label>
          Password
          <input type="password"
                 name="password"
                 v-model="input.password"
                 placeholder="Password"
                 class="u-cf"
          />
        </label>
        <button
            class="button u-cf"
            @click="login()"
        >Log in</button>
        <router-link
            :to="{name: 'NewUser'}"
            tag="button"
            class="button u-cf"
        >New User
        </router-link>
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

</style>