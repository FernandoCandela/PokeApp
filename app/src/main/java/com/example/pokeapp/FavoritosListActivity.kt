package com.example.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.fragment.PokemonFavoritoFragment
import com.example.pokeapp.fragment.PokemonFragment
import com.example.pokeapp.model.Favorito
import com.example.pokeapp.model.Pokemon2
import com.example.pokeapp.model.PokemonFavManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritosListActivity : AppCompatActivity(), PokemonFavoritoFragment.OnPokemonSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos_list)

        val entrenador = intent.getBundleExtra("data")?.getString("name").toString()


        /*PokemonFavManager().getPokemonsFavByUserFirebase("fernando")*/
        val fragment = PokemonFavoritoFragment()
        val ft = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("name",entrenador)
        fragment.arguments= bundle
        ft.add(R.id.fraFavoritos,fragment)
        ft.commit()
    }

    override fun onSelect(fav: Favorito) {
       Log.i("onselect","select")
    }


}