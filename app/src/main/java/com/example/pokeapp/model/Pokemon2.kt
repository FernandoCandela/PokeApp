package com.example.pokeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon2 (
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "hp")
    val hp : Int,
    @ColumnInfo(name = "attack")
    val attack : String,
    @ColumnInfo(name = "defense")
    val defense : String,
    @ColumnInfo(name = "special_attack")
    val special_attack : Float,
    @ColumnInfo(name = "special_defense")
    val special_defense : Float,
    @ColumnInfo(name = "url")
    val url : String
)