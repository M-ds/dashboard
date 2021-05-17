<template>
  <div>
    <router-link
        :to="{name: 'Dashboard'}"
        tag="button"
        class="button u-cf nav-button"
    >
      Dashboard
    </router-link>
    <router-link
        :to="{name: 'Profile'}"
        tag="button"
        class="button u-cf nav-button"
    >
      Profile
    </router-link>
    <router-link
        :to="{name: 'Marketplace'}"
        tag="button"
        class="button u-cf nav-button"
    >
      Marketplace
    </router-link>
    <button
        class="button u-cf nav-button"
        @click.prevent="logout()"
    >Log out
    </button>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  methods: {
    logout() {
      this.$store.dispatch("PersonStore/logout");
      this.$router.push("/");
    }
  }
};
</script>

<style scoped>
.nav-button {
  width: 100%;
}
</style>