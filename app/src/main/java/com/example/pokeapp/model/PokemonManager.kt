package com.example.pokeapp.model

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.pokeapp.network.APIPokemonService
import com.example.pokeapp.room.VGAppDatabase
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

    private val API_URL = "https://pokeapi.co/api/v2/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: APIPokemonService = retrofit.create(APIPokemonService::class.java)


    fun getPokemonList(callbackOK: (List<Pokemon>) -> Unit, callbackError: (String) -> Unit) {
        val call = service.getPokemonList(100, 0)
        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                val pokemonList = mutableListOf<Pokemon>()
                response.body()!!.results.forEach { a ->
                    println(a.name)
                    val call = service.getPokemonStats(a.name)
                    call.enqueue(object : Callback<Pokemon> {
                        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                            response.body()?.let { pokemon ->
                                pokemonList.add(pokemon)
                            }
                        }

                        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                            call.cancel()
                        }
                    })
                }
                callbackOK(pokemonList)
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                Log.e("PokemonManager", t.message!!)
                callbackError(t.message!!)
            }
        })
    }

/*  fun getPokemonStats(name:String) {
        //val pokemonInfo = MutableLiveData<Pokemon>()
        val call = service.getPokemonStats(name)
        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }*/


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