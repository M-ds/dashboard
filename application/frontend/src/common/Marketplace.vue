<template>
  <div class="container">
    <h1>Marketplace</h1>
    <div class="row">
      <div class="col-lg-1 col-md-1">
        <Navigation/>
      </div>
      <div class="col-lg-11 col-md-11">
        <p>All possible interactables will be displayed here!</p>
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from "@/common/Navigation";

export default {
  name: "Marketplace",
  components: {
    Navigation,
  }
};
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>