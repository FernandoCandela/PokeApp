package com.example.pokeapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PokemonFavManager : ViewModel(){

    private val dbFirebase = Firebase.firestore
    val favoritos = MutableLiveData<MutableList<Favorito>>()

    fun getPokemonsFavByUserFirebase(user: String) {
        dbFirebase.collection("favoritos").whereEqualTo("name_entrenador", user)
            .get()
            .addOnSuccessListener { res ->
                val listFav = mutableListOf<Favorito>()
                for (document in res) {
                    val pk = Favorito(
                        document.id,
                        document.data["name_entrenador"]!! as String,
                        document.data["name_pokemon"]!! as String
                    )
                    listFav.add(pk)
                }
                favoritos.postValue(listFav)
            }
            .addOnFailureListener {
                error(it.message!!)
            }
    }


}