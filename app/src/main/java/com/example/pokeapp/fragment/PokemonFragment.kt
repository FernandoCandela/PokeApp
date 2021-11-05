package com.example.pokeapp.fragment

import android.content.Context
import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.adapter.PokemonListAdapter
import com.example.pokeapp.model.*

class PokemonFragment : Fragment(){
    interface OnPokemonSelectedListener{
        fun onSelect(pokemon:PokeResult)
    }
    private var listener: OnPokemonSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnPokemonSelectedListener
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PokemonManager(requireActivity().applicationContext).getPokemonList({ vgList : PokeApiResponse ->
            println("POKEMONES TEST")
            println(vgList.results)
            // println(vgList.first().stats.first().base_stat)
            val rviPokemon = view.findViewById<RecyclerView>(R.id.rviPokemones)
            rviPokemon.adapter = PokemonListAdapter(
                vgList.results,
                this
            ){
                pokemon: PokeResult ->
                listener?.onSelect(pokemon)
            }

        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })

    }
}