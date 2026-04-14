package com.example.drinkmixerapp

import java.io.Serializable

data class Drink(
    val nome: String,
    val ingredientes: List<String>,
    val preparo: String
): Serializable
