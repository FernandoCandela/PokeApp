package com.example.pokeapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapp.model.PokeInfoManager

class PokemonDetalleActivity : AppCompatActivity() {
    lateinit var viewModel: PokeInfoManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detalle)

        viewModel = ViewModelProvider(this).get(PokeInfoManager::class.java)

        //val id = intent.extras?.get("name") as String

        val tviTitle: TextView = findViewById(R.id.tviTitle)
        val tviAttack: TextView = findViewById(R.id.tviAttack)
        val tviDefense: TextView = findViewById(R.id.tviDefense)
        val tviSpecialAttack: TextView = findViewById(R.id.tviSpecialAttack)
        val tviSpecialDefense: TextView = findViewById(R.id.tviSpecialDefense)
        val imgPokemon: ImageView = findViewById(R.id.imgPokemon)

        viewModel.getPokemonInfo("bulbasaur")

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            tviTitle.text = pokemon.name
            tviAttack.text = pokemon.stats[0].base_stat.toString()
            tviDefense.text = pokemon.stats[1].base_stat.toString()
            tviSpecialAttack.text = pokemon.stats[2].base_stat.toString()
            tviSpecialDefense.text = pokemon.stats[3].base_stat.toString()

            Glide.with(this).load(pokemon.sprites.frontDefault).into(imgPokemon)
        })
    }
}