package com.project.fisiocare.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.fisiocare.model.Historia
import com.project.fisiocare.repositories.HistoriaRepository

class HistoriaViewModel(private val repository: HistoriaRepository) : ViewModel() {
    val historias: LiveData<List<Historia>> = repository.obtenerHistorias()
    private val _historiaSeleccionada = MutableLiveData<Historia?>()
    val historiaSeleccionada: LiveData<Historia?> get() = _historiaSeleccionada

    fun guardarHistoria(historia: Historia, callback: (Boolean) -> Unit) {
        repository.guardarHistoria(historia, callback)
    }

    fun eliminarHistoria(historia: Historia) {
        repository.eliminarHistoria(historia)
    }

    fun seleccionarHistoria(historia: Historia) {
        _historiaSeleccionada.value = historia
    }
}