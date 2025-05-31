package com.project.fisiocare.repositories

object HistoriaRepositorySingleton {
    val instance: HistoriaRepository by lazy { HistoriaRepository() }
}