<template>
  <div>
    <h1>Profile</h1>
    <div class="row">
      <div class="one column">
        <Navigation/>
      </div>
      <div class="eleven columns">
        <label>
          Username
          <input type="text"
                 name="userName"
                 v-model="user.userName"
                 :placeholder="user.userName"
                 class="u-cf"
          />
        </label>
        <label>
          Email
          <input type="text"
                 name="email"
                 v-model="user.email"
                 :placeholder="user.email"
                 class="u-cf"
          />
        </label>
        <label>
          Password
          <input type="password"
                 name="password"
                 v-model="user.password"
                 :placeholder="user.password"
                 class="u-cf"
          />
        </label>
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
  data() {
    return {
      user: Object
    }
  },
  async created() {
    await this.$store.dispatch("UserStore/getUserProfile", 1);
    this.user = this.$store.getters["UserStore/user"];
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>