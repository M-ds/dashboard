<template>
  <div class="container">
    <h1>Profile</h1>
    <div class="row">
      <div class="col-sm-1">
        <Navigation/>
      </div>
      <form class="row centered">
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Username
            <input type="text"
                   name="userName"
                   v-model="user.username"
                   :placeholder="user.username"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Email
            <input type="text"
                   name="email"
                   v-model="user.email"
                   :placeholder="user.email"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Password
            <input type="password"
                   name="password"
                   v-model="user.password"
                   :placeholder="user.password"
                   readonly=""
            />
          </label>
        </div>
      </form>
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
    await this.$store.dispatch("UserStore/getPersonProfile", 1);
    this.user = this.$store.getters["UserStore/person"];
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
}

.centered {
  position: fixed;
  top: 25%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>