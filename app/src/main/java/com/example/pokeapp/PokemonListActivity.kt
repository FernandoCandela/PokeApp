package com.example.pokeapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.model.PokeApiResponse
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.PokemonManager

class PokemonListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        PokemonManager(applicationContext).getPokemonList({ vgList : PokeApiResponse ->
            println("POKEMONES TEST")
            println(vgList.results)
           // println(vgList.first().stats.first().base_stat)

        }, { error ->
            //Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })


    }



}