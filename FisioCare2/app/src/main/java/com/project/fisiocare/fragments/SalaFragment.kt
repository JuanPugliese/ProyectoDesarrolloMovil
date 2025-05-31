package com.project.fisiocare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.project.fisiocare.databinding.FragmentSalaBinding
import com.project.fisiocare.viewmodels.SalaViewModel
import com.project.fisiocare.viewmodels.SalaViewModelFactory
import android.util.Log
import com.project.fisiocare.R
import com.project.fisiocare.fragments.SalaFragmentArgs
import com.project.fisiocare.model.Cita
import com.project.fisiocare.repositories.CitaRepositorySingleton
import com.project.fisiocare.repositories.FacturaRepositorySingleton
import com.project.fisiocare.viewmodels.FacturaViewModel
import com.project.fisiocare.viewmodels.FacturaViewModelFactory
import com.project.fisiocare.viewmodels.TelemedicinaViewModelFactory

class SalaFragment : Fragment() {
    private var _binding: FragmentSalaBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: SalaViewModel
    lateinit var viewModelFactory: SalaViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSalaBinding.inflate(inflater, container, false)
        val view = binding.root
        // Obtener datos del bundle
        val args = arguments
        val cita = Cita(
            id = args?.getString("id") ?: "",
            fecha = args?.getString("fecha") ?: "",
            hora = args?.getString("hora") ?: "",
            paciente = args?.getString("paciente") ?: "",
            tratamiento = args?.getString("tratamiento") ?: "",
            modalidad = args?.getString("modalidad") ?: "",
            profesional = args?.getString("profesional") ?: "",
            disponible = args?.getBoolean("disponible") ?: false
        )

        Log.i("Cita", cita.toString())

        viewModelFactory = SalaViewModelFactory(cita)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SalaViewModel::class.java)
        binding.ingresarLlamadaBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_salaFragment_to_llamadaFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}