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
  }
}
</script>

<style scoped>

</style>