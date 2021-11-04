package com.example.pokeapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokeapp.model.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class VGAppDatabase: RoomDatabase() {
    abstract fun pokemonDAO() : PokemonDAO
}