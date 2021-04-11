<template>
  <div>
    <h1>Create new user</h1>
    <label>
      Username
      <input type="text"
             name="userName"
             v-model="user.userName"
             placeholder="Username"
      />
    </label>
    <br><br>
    <label>
      Email
      <input type="text"
             name="email"
             v-model="user.email"
             placeholder="example@email.com"
      />
    </label>
    <br><br>
    <label>
      Password
      <input type="text"
             name="password"
             v-model="user.password"
             placeholder="Password"
      />
    </label>
    <br><br>
    <label>
      Repeat password
      <input type="text"
             name="passwordConformation"
             v-model="user.passwordConformation"
             placeholder="Password"
      />
    </label>
    <br><br>
    <button @click="signup()">Sign up!</button>
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
      this.$router.push({name: 'Dashboard'});
    }
  }
}
</script>

<style scoped>

</style>