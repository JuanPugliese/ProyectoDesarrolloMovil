package com.project.fisiocare.repositories

import com.project.fisiocare.repositories.CitaRepository


object CitaRepositorySingleton {
    val instance: CitaRepository by lazy { CitaRepository() }
}