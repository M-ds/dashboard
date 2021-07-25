<template>
  <div class="container">
    <h1>Profile</h1>
    <div class="row">
      <div class="col-lg-1 col-md-1">
        <Navigation/>
      </div>
      <div class="row">
        <div class="col-lg-12 col-md-12">
          <label>
            Username
            <input type="text"
                   name="userName"
                   v-model="personProfile.username"
                   readonly=""
                   class="text__align_left"
            />
          </label>
        </div>
        <div class="col-lg-12 col-md-12">
          <label>
            Email
            <input type="text"
                   name="email"
                   v-model="personProfile.email"
                   readonly=""
                   class="text__align_left"
            />
          </label>
        </div>
        <div class="col-lg-12 col-md-12">
          <label>
            Password
            <input type="password"
                   name="password"
                   v-model="personProfile.password"
                   readonly=""
                   class="text__align_left"
            />
          </label>
        </div>
      </div>
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
</style>