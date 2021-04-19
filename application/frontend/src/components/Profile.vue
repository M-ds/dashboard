<template>
  <div>
    <h1>Profile</h1>
    <div class="row">
      <div class="one column">
        <Navigation/>
      </div>
      <div class="eleven columns">
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";

export default {
  name: "Profile",
  components: {
    Navigation
  },
  async created() {
    await this.$store.dispatch("UserStore/getUserProfile", 1);
    console.log(this.$store.getters["UserStore/user"]);
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>