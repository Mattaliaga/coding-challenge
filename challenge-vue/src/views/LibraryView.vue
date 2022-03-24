<!--
  Vue qui permet de :
    - consulter les albums enregistrés dans sa bibliothèque
    - mettre un album en favoris
    - ajouter un tag à un album
    - supprimer un album de sa bibliothèque
-->

<template>
  <v-data-table
    v-model="selected"
    :headers="headers"
    :items="albums"
    item-key="name"
    class="elevation-2 mt-6"
    show-select
  >
    <template v-slot:top>
      <v-toolbar
        flat
      >
        <v-toolbar-title>Ma bibliothèque</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-text-field
          label="Tag"
          hide-details="auto"
          v-model="tag"
          class="input-tag"
        ></v-text-field>
        <v-btn
          elevation="2"
          v-on:click="addTag"
          type="submit"
          class="mr-14"
        >
          Ajouter le tag
        </v-btn>
        <router-link
          to="/"
        >
          <v-btn
            color="primary"
            dark
          >
            Ajouter un album
          </v-btn>
        </router-link>
        <v-dialog v-model="dialogDelete" max-width="550px">
          <v-card>
            <v-card-title class="text-h5">Voulez-vous vraiment supprimer cet album ?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Annuler</v-btn>
              <v-btn color="blue darken-1" text @click="deleteAlbumConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>

    <template v-slot:[`item.action`]="{ item }">
      <v-icon
        small
        v-on:click="deleteAlbum(item.spotifyId)"
      >
        mdi-delete
      </v-icon>
    </template>

    <template v-slot:[`item.image`]="{ item }">
        <img v-bind:src="item.image" style="width: 50px; height: 50px" />
    </template>

    <template v-slot:[`item.favourite`]="{ item }">
      <v-icon
        v-bind:color="getFavouriteColor(item.favourite)"
        v-on:click="changeFavourite(item, item.favourite)"
      >
      mdi-star
      </v-icon>
    </template>

    <template v-slot:[`item.tags`]="{ item }">
      <v-badge
        v-for="value in item.tags"
        v-bind:key="value.text"
        inline
      >
      <span slot="badge">{{value.text}}</span></v-badge>
    </template>
  </v-data-table>
</template>

<script>
export default {
  name: 'LibraryView',
  components: {

  },
  data() {
    return {
      selected: [],
      toDeleteId: "",
      dialogDelete: false,
      tag: "",
      tags: "",
      headers: [
        {
          text: 'Favoris',
          sortable: true,
          value: 'favourite',
        },
        {
          text: 'ID',
          sortable: true,
          value: 'id',
        },
        {
          text: 'Nom de l\'album',
          sortable: true,
          value: 'name',
        },
        {
          text: 'Artiste',
          sortable: true,
          value: 'artist',
        },
        {
          text: 'Illustration',
          sortable: false,
          value: 'image',
        },
        {
          text: 'Date de sortie',
          sortable: true,
          value: 'releaseDate',
        },
        {
          text: 'Tags',
          sortable: false,
          value: 'tags',
        },
        {
          text: 'Action',
          sortable: false,
          value: 'action',
        }
      ],
      albums: []
    }
  },
  mounted() {
    this.fetchAlbums()
  },
  methods: {
    fetchAlbums() { // récupérer les albums de la bibliothèque
      this.albums = []
      fetch("http://localhost:8080/album", {
        method:'GET', 
      })
      .then(response => response.json())
      .then(data => {
        let album = data;

        data.forEach(element => {

          fetch("http://localhost:8080/tag/ofAlbum/" + element.id, {
            method:'GET', 
          })
          .then(response => response.json())
          .then(data => {
            let tags = data

            fetch("https://api.spotify.com/v1/albums/" + element.spotifyId, {
              method:'GET',
              headers: {'Authorization': 'Bearer BQCy1BTEq9UeBhJ5EtskQoq0MTPkm0tgOpGrBjrucM12TJ9kOJTsHIyczCxJiV8W6JHTC0OFQOfzOCxLhPOs15gYB_SP60uUQ1fkBx2ixjp-FGdhLNRvoqJlqaCZg06U7M0ivIfnZJYq'}
            })
            .then(response => response.json())
            .then(data => {
              this.albums.push({favourite: element.favourite, id: element.id, spotifyId: data.id, name: data.name, artist: data.artists[0].name, image: data.images[0].url, releaseDate: data.release_date, tags: tags, album: album})
            })
          })
        });

      });
    },
    deleteAlbum(spotifyId) { // ouverture de la boite de dialogue pour la suppression d'un album
      this.toDeleteId = spotifyId
      this.dialogDelete = true
    },
    closeDelete() { // annulation de la supression
      this.dialogDelete = false
    },
    deleteAlbumConfirm() { // suppression de l'album
      fetch("http://localhost:8080/album/" + this.toDeleteId, {
        method:'DELETE', 
      })
      .then(() => {
        this.dialogDelete = false
        this.fetchAlbums()})
    },
    addTag() { // ajoute un tag à un ou plusieurs albums
      if (this.selected.length != 0) {
        
        fetch("http://localhost:8080/tag/", {
          method:'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            "tag": {"text": this.tag},
            "albums": this.selected
          })
        })
        .then(() => this.fetchAlbums())
      }
    },
    getFavouriteColor(favourite) { // renvoie la couleur associé au statut favoris
      return favourite ? "warning" : "#ccc"
    },
    changeFavourite(album, favourite) { // changement du statut favoris d'un album
      fetch("http://localhost:8080/album/" + !favourite, {
        method:'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(album)
      })
      .then(() => this.fetchAlbums())
    }
  }
}
</script>

<style scoped>
.hidden {
  display: none;
}

.input-tag {
  max-width: 200px;
}
</style>
