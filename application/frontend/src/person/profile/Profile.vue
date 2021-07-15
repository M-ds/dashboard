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
                   v-model="personProfile.username"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Email
            <input type="text"
                   name="email"
                   v-model="personProfile.email"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Password
            <input type="password"
                   name="password"
                   v-model="personProfile.password"
                   readonly=""
            />
          </label>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import Navigation from "@/common/Navigation";

export default {
  name: "Profile",
  components: {
    Navigation
  },
  data() {
    return {
      personProfile: null
    };
  },
  async created() {
    const personId = await this.$store.getters["PersonLoginStore/person"].id;

    await this.$store.dispatch("PersonStore/getPersonProfile", personId);
    this.personProfile = this.$store.getters["PersonStore/personProfile"];
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