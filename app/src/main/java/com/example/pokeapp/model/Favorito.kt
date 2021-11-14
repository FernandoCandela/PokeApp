package com.example.pokeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorito (
    @PrimaryKey
    val id : String,
    @ColumnInfo(name = "name_entrenador")
    val name_entrenador : String,
    @ColumnInfo(name = "name_pokemon")
    val name_pokemon : String
)
