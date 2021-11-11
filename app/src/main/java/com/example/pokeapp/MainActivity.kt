package com.example.pokeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
/*Fernando Candela maldonado
* Renatto Pantoja*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butContinuar: Button = findViewById(R.id.butContinuar)
        val butFav: Button = findViewById(R.id.butFavoritos)
        butContinuar.setOnClickListener { _: View ->
            val intent: Intent = Intent()
            intent.setClass(this, PokemonListActivity::class.java)
            startActivity(intent)
        }
        butFav.setOnClickListener { _: View ->
            val intent: Intent = Intent()
            intent.setClass(this, FavoritosListActivity::class.java)
            startActivity(intent)
        }
    }
}