package com.example.pokeapp.model

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.pokeapp.network.APIPokemonService
import com.example.pokeapp.room.VGAppDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonManager(context: Context) {


    private val db = Room.databaseBuilder(
        context,
        VGAppDatabase::class.java, "db_pokemon"
    ).allowMainThreadQueries().build()

    private val dbFirebase = Firebase.firestore

    private val API_URL = "https://pokeapi.co/api/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: APIPokemonService = retrofit.create(APIPokemonService::class.java)


    fun getPokemonList(callbackOK: (PokeApiResponse) -> Unit, callbackError: (String) -> Unit) {
        val call = service.getPokemonList(20, 0)
        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                callbackOK(response.body()!!)
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                Log.e("PokemonManager", t.message!!)
                callbackError(t.message!!)
            }
        })
    }

    fun getPokemonStats(
        callbackOK: (Pokemon) -> Unit,
        callbackError: (String) -> Unit,
        name: String
    ) {
        val call = service.getPokemonStats(name)
        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call :Call<Pokemon>, response: Response<Pokemon>) {
                callbackOK(response.body()!!)
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.e("PokemonManager", t.message!!)
                callbackError(t.message!!)
            }
        })
    }

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


    fun getProductsByRoom(callbackOK: (List<Pokemon2>) -> Unit, callbackError: (String) -> Unit) {
        val pokemon: List<Pokemon2> = db.pokemonDAO().findAll()
        callbackOK(pokemon)
    }

    private fun saveIntoRoom(pokemons: List<Pokemon2>) {
        pokemons.forEach {
            db.pokemonDAO().insert(it)
        }
    }
}