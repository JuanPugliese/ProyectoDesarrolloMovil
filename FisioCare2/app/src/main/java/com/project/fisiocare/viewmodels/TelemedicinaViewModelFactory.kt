package com.project.fisiocare.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.fisiocare.repositories.CitaRepository

class TelemedicinaViewModelFactory(
    private val repository: CitaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TelemedicinaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TelemedicinaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}