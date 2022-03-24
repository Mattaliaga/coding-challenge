<!-- Carte d'information d'un album -->

<template>
  <v-card
    class="card"
    elevation="2"
  >
    <div class="d-flex flex-no-wrap justify-space-between">
      <div>
        <v-card-title
          class="text-h5"
          v-text=name
        ></v-card-title>
        <v-card-subtitle>
          <span v-for="item in artists" :key="item.name">
            {{item.name}}
          </span>
        </v-card-subtitle>
        <v-card-text>
          <div class="text-subtitle-3">
            Date de sortie:  {{releaseDate}}
          </div>
        </v-card-text>
        <v-card-text>
          <v-btn
            elevation="2"
            class="mt-4"
            v-on:click="addToLibrary(spotifyId)"
            type="submit"
          >
            Ajouter
            <v-icon
              dark
              right
            >
            mdi-plus-circle
            </v-icon>
          </v-btn>
        </v-card-text>
      </div>
      
      <v-avatar
        class="ma-3"
        size="125"
        tile
      >
        <v-img :src=img></v-img>
      </v-avatar>
    </div>
  </v-card>
    
</template>

<script>
export default {
  name: 'AlbumCard',
  props: {
    color: String,
    name: String,
    artists: Array,
    releaseDate: String,
    img: String,
    spotifyId: String
  },
  methods: {
    addToLibrary(spotifyId) { // ajout de l'album à la bibliothèque
      fetch("http://localhost:8080/album/", {
        method:'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({"spotifyId" : spotifyId})
      })
    }
  }
}
</script>

<style scoped>
.card {
  margin-bottom: 20px;
  margin-right: 20px;
}
</style>