package com.project.fisiocare.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.fisiocare.model.Historia

class HistoriaRepository {
    private val listaHistorias = mutableListOf<Historia>()
    private val historiasLiveData = MutableLiveData<List<Historia>>(listaHistorias)

    fun obtenerHistorias(): LiveData<List<Historia>> = historiasLiveData

    fun guardarHistoria(historia: Historia, callback: (Boolean) -> Unit) {
        val index = listaHistorias.indexOfFirst { it.id == historia.id }
        if (index != -1) {
            listaHistorias[index] = historia
        } else {
            listaHistorias.add(historia)
        }
        callback(true)
        historiasLiveData.value = listaHistorias
    }

    fun eliminarHistoria(historia: Historia) {
        listaHistorias.remove(historia)
        historiasLiveData.value = listaHistorias.toList()
    }
}