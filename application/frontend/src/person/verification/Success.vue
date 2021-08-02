<template>
  <div class="container centered__box">
    <h1>Success!</h1>
    Please go to the login page to continue!

    See you soon!

    <button :click="loginScreen()">Back To Login!</button>
  </div>
</template>

<script>
export default {
  name: "Success",
  methods: {
    loginScreen() {
      this.$router.push({name: "Login"});
    }
  }
};
</script>

<style scoped>

</style>