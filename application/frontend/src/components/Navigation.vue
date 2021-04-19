<template>
  <div>
    <router-link
        :to="{name: 'Login'}"
        tag="button"
        class="button u-cf"
    >
      Login
    </router-link>
    <router-link
        :to="{name: 'NewUser'}"
        tag="button"
        class="button u-cf"
    >
      New User
    </router-link>
    <router-link
        :to="{name: 'Profile'}"
        tag="button"
        class="button u-cf"
    >
      Profile
    </router-link>
  </div>
</template>

<script>
export default {
  name: "Navigation"
};
</script>

<style scoped>

</style>