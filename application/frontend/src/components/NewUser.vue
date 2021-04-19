<template>
  <div class="container">
    <div class="row">
      <div class="twelve columns offset-by-four">
        <h1>Create new user</h1>
        <label>
          Username
          <input type="text"
                 name="userName"
                 v-model="user.userName"
                 placeholder="Username"
                 class="u-cf"
          />
        </label>
        <label>
          Email
          <input type="text"
                 name="email"
                 v-model="user.email"
                 placeholder="example@email.com"
                 class="u-cf"
          />
        </label>
        <label>
          Password
          <input type="text"
                 name="password"
                 v-model="user.password"
                 placeholder="Password"
                 class="u-cf"
          />
        </label>
        <label>
          Repeat password
          <input type="text"
                 name="passwordConformation"
                 v-model="user.passwordConformation"
                 placeholder="Password"
                 class="u-cf"
          />
        </label>
        <button class="u-cf" @click="signup()">Sign up!</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewUser",
  data() {
    return {
      user: {
        userName: "",
        email: "",
        password: "",
        passwordConformation: "",
      }
    }
  },
  methods: {
    signup: function () {
      this.$router.push({name: "Dashboard"});
    }
  }
}
</script>

<style scoped>

</style>