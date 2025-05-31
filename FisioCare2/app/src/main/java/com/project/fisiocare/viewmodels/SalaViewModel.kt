package com.project.fisiocare.viewmodels

import androidx.lifecycle.ViewModel
import com.project.fisiocare.model.Cita

class SalaViewModel(private val cita : Cita) : ViewModel() {
    fun getCita(): Cita = cita
}