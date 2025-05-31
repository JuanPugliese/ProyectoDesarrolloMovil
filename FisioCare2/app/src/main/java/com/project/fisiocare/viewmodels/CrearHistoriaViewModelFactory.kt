package com.project.fisiocare.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.fisiocare.repositories.HistoriaRepository

class HistoriaViewModelFactory(private val repository: HistoriaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoriaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoriaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}