package com.example.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokeapp.fragment.PokemonFavoritoFragment
import com.example.pokeapp.model.Favorito

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