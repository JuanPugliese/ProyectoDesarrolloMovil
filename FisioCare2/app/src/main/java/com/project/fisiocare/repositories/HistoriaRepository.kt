package com.project.fisiocare.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.fisiocare.model.Historia

class HistoriaRepository {
    private var listaHistorias = mutableListOf<Historia>() // Cambiado a var
    private val historiasLiveData = MutableLiveData<List<Historia>>()

    init {
        historiasLiveData.value = emptyList()
    }

    fun obtenerHistorias(): LiveData<List<Historia>> = historiasLiveData

    fun guardarHistoria(historia: Historia, callback: (Boolean) -> Unit) {
        println("🔄 [Repository] Guardando historia: ${historia.paciente}")
        val nuevaLista = ArrayList(listaHistorias) // Crear copia
        val index = nuevaLista.indexOfFirst { it.id == historia.id }

        if (index != -1) {
            nuevaLista[index] = historia
        } else {
            nuevaLista.add(historia)
        }

        listaHistorias = nuevaLista // Ahora funciona porque listaHistorias es var
        historiasLiveData.postValue(nuevaLista)
        callback(true)
        println("📊 [Repository] Total historias después de guardar: ${listaHistorias.size}")
    }

    fun eliminarHistoria(historia: Historia) {
        val nuevaLista = ArrayList(listaHistorias)
        nuevaLista.remove(historia)
        listaHistorias = nuevaLista
        historiasLiveData.postValue(nuevaLista)
    }
}