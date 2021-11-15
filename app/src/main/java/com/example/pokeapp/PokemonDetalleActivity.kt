package com.example.pokeapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokeapp.model.Pokemon2
import com.example.pokeapp.model.PokemonManager

class PokemonDetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detalle)

        val pokemon = intent.extras!!.get("data") as Pokemon2

        val entrenador =  intent.getBundleExtra("dataEntrenador")?.getString("name").toString()

        val btnAddFav: Button = findViewById(R.id.btnAddFav)
        val tviTitle: TextView = findViewById(R.id.tviTitle)
        val tviAttack: TextView = findViewById(R.id.tviAttack)
        val tviDefense: TextView = findViewById(R.id.tviDefense)
        val tviSpecialAttack: TextView = findViewById(R.id.tviSpecialAttack)
        val tviSpecialDefense: TextView = findViewById(R.id.tviSpecialDefense)
        val imgPokemon: ImageView = findViewById(R.id.imgPokemon)
        tviTitle.text = pokemon.name
        tviAttack.text = pokemon.attack.toString()
        tviDefense.text = pokemon.defense.toString()
        tviSpecialAttack.text = pokemon.special_attack.toString()
        tviSpecialDefense.text = pokemon.special_defense.toString()

        Glide.with(this).load(pokemon.url).into(imgPokemon)

        btnAddFav.setEnabled(false)

        PokemonManager(this).btnFavIsValid(entrenador, pokemon.name, { isvalid: Boolean ->
            btnAddFav.setEnabled(isvalid)

        }, { error ->
            Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show()
        })
    }
}