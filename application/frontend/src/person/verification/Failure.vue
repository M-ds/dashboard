<template>
  <div class="container centered__box">
    <h1>Something went wrong!</h1>
    Could be the verification token has expired! Please request a new token!

    <button :on="requestNewToken()">Request a new token</button>
    <button :click="loginScreen()">Back To Login!</button>
  </div>
</template>

<script>
export default {
  name: "Error",
  methods: {
    loginScreen() {
      this.$router.push({name: "Login"});
    },
    requestNewToken() {
      console.log("NEED TO IMPLEMENT!")
    }
  }
};
</script>

<style scoped>

</style>