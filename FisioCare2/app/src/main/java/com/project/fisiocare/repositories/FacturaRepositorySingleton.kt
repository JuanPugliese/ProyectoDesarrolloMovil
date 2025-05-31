package com.project.fisiocare.repositories

object FacturaRepositorySingleton {
    val instance: FacturaRepository by lazy { FacturaRepository() }
}