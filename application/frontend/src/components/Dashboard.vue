<template>
  <div>
    <h1>Welcome! This is your personal Dashboard!</h1>
    <router-link tag="button" :to="{name: 'Login'}">Login</router-link>
  </div>
</template>

<script>
export default {
  name: "Dashboard"
}
</script>

<style scoped>

</style>