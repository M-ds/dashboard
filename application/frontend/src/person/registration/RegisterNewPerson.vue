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
                   v-model="registration.username"
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
                   v-model="registration.email"
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
                   v-model="registration.password"
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
                   v-model="registration.passwordConformation"
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
import registration from "@/person/registration/domain/Registration";

export default {
  name: "NewUser",
  data() {
    return {
      registration: registration
    };
  },
  methods: {
    signup() {
      this.$router.push({ name: "Dashboard" });
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