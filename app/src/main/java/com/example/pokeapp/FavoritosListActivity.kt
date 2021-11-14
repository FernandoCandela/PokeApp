package com.example.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.fragment.PokemonFavoritoFragment
import com.example.pokeapp.fragment.PokemonFragment
import com.example.pokeapp.model.Pokemon2
import com.example.pokeapp.model.PokemonFavManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritosListActivity : AppCompatActivity(), PokemonFavoritoFragment.OnPokemonSelectedListener{
    lateinit var viewModel: PokemonFavManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos_list)


        viewModel = ViewModelProvider(this).get(PokemonFavManager::class.java)

        viewModel.getPokemonsFavByUserFirebase("fernando")
        viewModel.favoritos.observe(this, Observer { pokemon ->
            for (p in pokemon){
                print(p.name_entrenador)
                print(p.name_pokemon)
            }
        })
        val fragment = PokemonFavoritoFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fraFavoritos,fragment)
        ft.commit()
    }

    override fun onSelect(pokemon: Pokemon2) {
       Log.i("onselect","hola")
    }


}