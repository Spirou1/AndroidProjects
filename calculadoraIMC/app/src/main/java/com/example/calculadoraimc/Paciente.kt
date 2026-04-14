package com.example.calculadoraimc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Paciente(
    val nome: String,
    val peso: Double,
    val altura: Double,
    val imc: Double
) : Parcelable
