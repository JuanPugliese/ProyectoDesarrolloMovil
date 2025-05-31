package com.project.fisiocare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.project.fisiocare.R
import com.project.fisiocare.model.Historia
import com.project.fisiocare.repositories.HistoriaRepositorySingleton
import com.project.fisiocare.viewmodels.HistoriaViewModel
import com.project.fisiocare.viewmodels.HistoriaViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class CrearHistoriaFragment : Fragment() {
    private lateinit var viewModel: HistoriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crear_historia, container, false)
        val viewModelFactory = HistoriaViewModelFactory(HistoriaRepositorySingleton.instance)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[HistoriaViewModel::class.java]

        // Referencias a las vistas
        val nombrePacienteInput = view.findViewById<EditText>(R.id.nombrePacienteInput)
        val documentoInput = view.findViewById<EditText>(R.id.idDocumento)
        val telefonoInput = view.findViewById<EditText>(R.id.idTelefono)
        val tratamientoInput = view.findViewById<EditText>(R.id.tratamientoInput)
        val profesionalInput = view.findViewById<EditText>(R.id.profesionalInput)
        val motivoInput = view.findViewById<EditText>(R.id.motivoInput)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)
        val btnCancelar = view.findViewById<Button>(R.id.btnCancelar)


        // Cargar datos si existen argumentos
        val args = arguments
        if (args != null) {
            nombrePacienteInput.setText(args.getString("paciente"))
            tratamientoInput.setText(args.getString("tratamiento"))
            documentoInput.setText(args.getString("documento"))
            telefonoInput.setText(args.getString("telefono"))
            profesionalInput.setText(args.getString("profesional"))
            motivoInput.setText(args.getString("motivo"))
            tratamientoInput.setText(args.getString("tratamiento"))
        }

        btnGuardar.setOnClickListener {
            val historia = Historia(
                id = args?.getString("id") ?: System.currentTimeMillis().toString(),
                paciente = nombrePacienteInput.text.toString(),
                documento = documentoInput.text.toString(),
                telefono = telefonoInput.text.toString(),
                tratamiento = tratamientoInput.text.toString(),
                valor = 0.0, // Valor por defecto ya que eliminamos este campo
                profesional = profesionalInput.text.toString(),
                matricula = motivoInput.text.toString(),
                fecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                hora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            )

            if (historia.paciente.isNotBlank() && historia.tratamiento.isNotBlank()) {
                viewModel.guardarHistoria(historia) { success ->
                    if (success) {
                        Toast.makeText(context, "Historia guardada ✅", Toast.LENGTH_SHORT).show()
                        viewModel.seleccionarHistoria(historia)
                        view.findNavController().navigate(R.id.action_crearHistoriaFragment_to_consultarHistoriasFragment)
                    } else {
                        Toast.makeText(context, "Error al guardar ❌", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Complete los campos obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancelar.setOnClickListener {
            view.findNavController().navigate(R.id.action_crearHistoriaFragment_to_consultarHistoriasFragment)
        }

        return view
    }
}