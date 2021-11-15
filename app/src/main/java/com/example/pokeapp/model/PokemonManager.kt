package com.example.pokeapp.model

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PokemonManager() {
    private val dbFirebase = Firebase.firestore

    fun getPokemonsFirebase(callbackOK: (List<Pokemon2>) -> Unit, callbackError: (String) -> Unit) {
        dbFirebase.collection("pokemon")
            .get()
            .addOnSuccessListener { res ->
                val products = arrayListOf<Pokemon2>()
                for (document in res) {
                    val pk = Pokemon2(
                        document.id,
                        document.data["name"]!! as String,
                        (document.data["hp"]!! as Long).toInt(),
                        (document.data["attack"]!! as Long).toFloat(),
                        (document.data["defense"]!! as Long).toFloat(),
                        (document.data["special_attack"]!! as Long).toFloat(),
                        (document.data["special_defense"]!! as Long).toFloat(),
                        document.data["url"]!! as String
                    )
                    products.add(pk)
                }
                callbackOK(products)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

    fun getPokemonsFavByUserFirebase(
        name_entrenador: String?,
        callbackOK: (MutableList<Favorito>) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("favoritos")
            .whereEqualTo("name_entrenador", name_entrenador)
            .get()
            .addOnSuccessListener { res ->
                val favoritos = arrayListOf<Favorito>()
                for (document in res) {
                    val pk = Favorito(
                        document.id,
                        document.data["name_entrenador"]!! as String,
                        document.data["name_pokemon"]!! as String
                    )
                    favoritos.add(pk)
                }
                callbackOK(favoritos)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

    fun btnFavIsValid(
        name_entrenador: String?,
        name_pokemon: String?,
        callbackOK: (Boolean) -> Unit,
        callbackError: (String) -> Unit
    ) {
        dbFirebase.collection("favoritos")
            .whereEqualTo("name_entrenador", name_entrenador)
            .whereEqualTo("name_pokemon", name_pokemon)
            .get()
            .addOnSuccessListener { res ->
                val favoritos = arrayListOf<Favorito>()
                for (document in res) {
                    val pk = Favorito(
                        document.id,
                        document.data["name_entrenador"]!! as String,
                        document.data["name_pokemon"]!! as String
                    )
                    favoritos.add(pk)
                }
                if (favoritos.size > 0) callbackOK(false) else callbackOK(true)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }

    fun btnDeleteFav(
        id: String
    ) {
        dbFirebase.collection("favoritos").document(id)
            .delete()
            .addOnSuccessListener { Log.d("eliminado", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("error", "Error deleting document", e) }
    }
}