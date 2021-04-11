<template>
  <div id="app">
    <router-view :key="$route.path"/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
.center {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
