package com.project.fisiocare.model

data class Historia(
    val id: String = "",
    val fecha: String = "",
    val hora: String = "",
    val paciente: String,
    val tratamiento: String,
    val valor: Double,
    val documento: String = "",
    val telefono: String = "",
    val correo: String = "",
    val profesional: String = "",
    val matricula: String = ""
)