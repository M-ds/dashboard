<template>
  <div class="container">
    <form class="card large centered">
      <h1>Create a new user</h1>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Username
            <input type="text"
                   name="userName"
                   v-model="user.username"
                   placeholder="Username"
            />
          </label>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Email
            <input type="text"
                   name="email"
                   v-model="user.email"
                   placeholder="example@email.com"
            />
          </label>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Password
            <input type="text"
                   name="password"
                   v-model="user.password"
                   placeholder="Password"
            />
          </label>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <label>
            Repeat password
            <input type="text"
                   name="passwordConformation"
                   v-model="user.passwordConformation"
                   placeholder="Password"
            />
          </label>
        </div>
      </div>
      <button class="u-cf" @click="signup()">Sign up!</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "NewUser",
  data() {
    return {
      user: {
        username: "",
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
h1 {
  text-align: center;
}

input {
  width: 98%;
}

.centered {
  position: fixed;
  top: 25%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>