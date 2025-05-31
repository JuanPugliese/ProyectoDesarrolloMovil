package com.project.fisiocare.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.fisiocare.model.Cita

class SalaViewModelFactory(private val cita: Cita) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalaViewModel::class.java))
            return SalaViewModel(cita) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}