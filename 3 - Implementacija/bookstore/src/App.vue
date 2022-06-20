<template>
  <span id="app">
    <router-view/>
  </span>
</template>

<script>
  export default {
    name: 'App',
    components: {
    }
  }
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav img {
    width : 200px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

h1 {
  font-weight: bold !important;
}

h1, h2, h3, h4, h5, h6, p, a, .routerlink{
  color : rgb(112, 112, 112);
}
</style>
