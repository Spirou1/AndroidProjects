package com.example.calculamediaapp

import java.io.Serializable

data class Aluno(
    val nome: String,
    val nota1: Double,
    val nota2: Double,
    val frequencia: Int,
    val mediaFinal: Double
) : Serializable
