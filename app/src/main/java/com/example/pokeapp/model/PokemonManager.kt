package com.example.pokeapp.model

import android.content.Context
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
                    println()
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
}