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
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.model.Pokemon2
import com.example.pokeapp.model.PokemonManager

class PokemonFragment : Fragment(){
    interface OnPokemonSelectedListener{
        fun onSelect(pokemon:Pokemon)
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

        PokemonManager(requireActivity().applicationContext).getPokemonList({vgList: List<Pokemon>->
            val rviPokemon = view.findViewById<RecyclerView>(R.id.rviPokemones)
            rviPokemon.adapter = PokemonListAdapter(
                vgList,
                this
            ){pokemon: Pokemon->
                listener?.onSelect(pokemon)
            }

        },{error ->
            Toast.makeText(activity, "Error: " + error, Toast.LENGTH_SHORT).show()
        })

    }
}