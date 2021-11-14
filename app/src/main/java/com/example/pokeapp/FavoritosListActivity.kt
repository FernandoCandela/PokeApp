package com.example.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokeapp.model.PokemonFavManager

class FavoritosListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos_list)

        /*PokemonFavManager().getPokemonsFavByUserFirebase("fernando")*/

    }


}