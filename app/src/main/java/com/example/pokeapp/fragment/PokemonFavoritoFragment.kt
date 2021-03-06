package com.example.pokeapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.adapter.FavoritosListAdapter
import com.example.pokeapp.model.Favorito
import com.example.pokeapp.model.PokemonManager

class PokemonFavoritoFragment : Fragment() {
    interface OnPokemonSelectedListener{
        fun onSelect(pokemon:Favorito)
    }
    private var listener: PokemonFavoritoFragment.OnPokemonSelectedListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PokemonFavoritoFragment.OnPokemonSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_favorito,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val name = bundle!!.getString("name")

        PokemonManager().getPokemonsFavByUserFirebase(name,{ vgList : MutableList<Favorito> ->
            // println(vgList.first().stats.first().base_stat)
            val rviFavoritos = view.findViewById<RecyclerView>(R.id.rviFavoritos)
            rviFavoritos.adapter = FavoritosListAdapter(requireContext(),
                vgList,
                this
            ){
                    fav: Favorito ->
                listener?.onSelect(fav)
            }

        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })

    }
}