<!--
  Vue qui permet de :
    - rechercher un album par son titre ou son artiste
    - ajouter un album à sa bibliothèque
-->

<template>
  <div>
    <form class="mb-4">
    <v-text-field
      label="Recherche"
      hide-details="auto"
      v-model="name"
    ></v-text-field>
    <v-btn
      elevation="2"
      class="mt-4"
      v-on:click="search"
      type="submit"
    >
      Recherche
    </v-btn>
    </form>

    <v-row no-gutters>
        <v-col
          v-for="(item, index) in searchResult"
          :key="index"
          cols="12"
          sm="4"
          class="col">
          <AlbumCard
            color="#385F73"
            v-bind:name="item.name"
            v-bind:artists="item.artists"
            v-bind:releaseDate="item.release_date"
            v-bind:img="item.images[0].url"
            v-bind:spotifyId="item.id"
          />
        </v-col>
    </v-row>
  </div>
</template>

<script>
import AlbumCard from '@/components/AlbumCard.vue'

export default {
  name: 'SearchView',
  components: {
    AlbumCard
  },
  data() {
    return {
      searchResult: [],
      name: null
    }
  },
  created() {
    
  },
  methods: {
    search() { // lancer la recherche
      fetch("https://api.spotify.com/v1/search?q="+ this.name +"&type=album&limit=20", {
        method:'GET', 
        headers: {'Authorization': 'Bearer BQCFrRwpbU6DLxerqVAv43SV8XdJ_8mjsKQu0O_hBPTiYFcYYBiyP00dyw7F2YFijjg2g3HTFI7eP-8b6qiMNd__8W3aJWZ8YbDzJ867dsbqF1GEtkl58B5ib371--Vl8c7KnIoh1bku'}
      })
      .then(response => response.json())
      .then(data => {
        this.searchResult = data.albums.items
      });
    }
  }
}
</script>

<style scoped>
.btn-search {
  margin: 20px 0;
}
</style>
