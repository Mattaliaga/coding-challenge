<template>
  <v-app>
    <v-app-bar
      app
      color="#1e272e"
      dark
      class="navbar"
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://pnggrid.com/wp-content/uploads/2021/05/White-Spotify-Icon-1024x1024.png"
          transition="scale-transition"
          width="40"
        />
      </div>
      
      <router-link
        to="/"
      >
        <v-btn
          target="_blank"
          value="search"
          text
        >
          <span>Recherche</span>
        </v-btn>
      </router-link>

      <router-link
        to="/library"
      >
        <v-btn
          target="_blank"
          value="library"
          text
        >
          <span>Ma bibliothèque</span>
        </v-btn>
      </router-link>
    </v-app-bar>

    <v-main>
      <v-container>
        <router-view/>
      </v-container>
    </v-main>
    
    
  </v-app>
</template>

<script>
export default {
  name: 'App',

  components: {

  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
.content {
  width: 80%;
}
</style>
