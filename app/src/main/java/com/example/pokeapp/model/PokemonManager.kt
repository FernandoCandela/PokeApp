package com.example.pokeapp.model

import android.content.Context
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PokemonManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getPokemonsFirebase(callbackOK : (List<Pokemon2>) -> Unit, callbackError : (String) -> Unit) {
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
    fun getPokemonsFavByUserFirebase(name_entrenador: String?, callbackOK : (List<Favorito>) -> Unit, callbackError : (String) -> Unit) {
        dbFirebase.collection("favoritos")
            .whereEqualTo("name_entrenador",name_entrenador)
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

/*    fun getProductsFirebase(callbackOK : (List<Videogame>) -> Unit, callbackError : (String) -> Unit) {
        dbFirebase.collection("videogames")
            .get()
            .addOnSuccessListener { res ->
                val products = arrayListOf<Videogame>()
                for (document in res) {
                    println()
                    val vg = Videogame(
                        document.id.toLong(),
                        document.data["nombre"]!! as String,
                        (document.data["categoria"]!! as DocumentReference).id.toLong(),
                        document.data["consolas"]!! as String,
                        document.data["desarrollador"]!! as String,
                        (document.data["ranking"]!! as Double).toFloat(),
                        (document.data["precio"]!! as Long).toFloat(),
                        document.data["url"]!! as String
                    )
                    products.add(vg)
                }
                callbackOK(products)
            }
            .addOnFailureListener {
                callbackError(it.message!!)
            }
    }*/

}