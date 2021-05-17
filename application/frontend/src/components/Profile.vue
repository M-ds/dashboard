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
                   v-model="person.username"
                   :placeholder="person.username"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Email
            <input type="text"
                   name="email"
                   v-model="person.email"
                   :placeholder="person.email"
                   readonly=""
            />
          </label>
        </div>
        <div class="col-sm-12 col-md-12">
          <label class="input-group vertical">
            Password
            <input type="password"
                   name="password"
                   v-model="person.password"
                   :placeholder="person.password"
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
      person: null
    };
  },
  async created() {
    const personId = await this.$store.getters["PersonStore/person"].id;

    await this.$store.dispatch("PersonStore/getPersonProfile", personId);
    this.person = this.$store.getters["PersonStore/personProfile"];
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