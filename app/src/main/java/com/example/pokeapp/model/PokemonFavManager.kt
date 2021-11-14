package com.example.pokeapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class PokemonFavManager : ViewModel(){

    private val dbFirebase = Firebase.firestore
    val favoritos = MutableLiveData<Favorito>()

/*    fun getPokemonsFavByUserFirebase(user : String ) {
        dbFirebase.collection("favoritos").whereEqualTo("name_entrenador", )
            .get()
            .addOnSuccessListener { res ->
                for (document in res) {
                    val pk = Favorito(
                        document.id,
                        document.data["name_entrenador"]!! as String,
                        (document.data["name_pokemon"]!! as DocumentReference).get().addOnSuccessListener {
                            it.data!!["name"]!! as String
                        }.toString(),
                    )
                    favoritos.postValue(pk)
                }
            }
            .addOnFailureListener {
                error(it.message!!)
            }
    }*/


}