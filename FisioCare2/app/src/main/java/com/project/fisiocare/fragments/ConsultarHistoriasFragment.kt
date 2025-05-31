package com.project.fisiocare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.fisiocare.R
import com.project.fisiocare.adapters.HistoriaAdapter
import com.project.fisiocare.repositories.HistoriaRepositorySingleton
import com.project.fisiocare.viewmodels.HistoriaViewModel
import com.project.fisiocare.viewmodels.HistoriaViewModelFactory
import androidx.navigation.findNavController

class ConsultarHistoriasFragment : Fragment() {
    private lateinit var viewModel: HistoriaViewModel
    private lateinit var adapter: HistoriaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_consultar_historia, container, false)
        val viewModelFactory = HistoriaViewModelFactory(HistoriaRepositorySingleton.instance)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[HistoriaViewModel::class.java]

        adapter = HistoriaAdapter(
            historias = emptyList(),
            onModificar = { historia ->
                val bundle = Bundle().apply {
                    putString("id", historia.id)
                    putString("paciente", historia.paciente)
                    putString("documento", historia.documento)
                    putString("telefono", historia.telefono)
                    putString("tratamiento", historia.tratamiento)
                    putDouble("valor", historia.valor)
                    putString("profesional", historia.profesional)
                    putString("matricula", historia.matricula)
                }
                view.findNavController().navigate(
                    R.id.action_consultarHistoriasFragment_to_crearHistoriaFragment,
                    bundle
                )
            },
            onEliminar = { historia -> viewModel.eliminarHistoria(historia) }
        )

        view.findViewById<RecyclerView>(R.id.recyclerHistorias).apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewModel.historias.observe(viewLifecycleOwner) { adapter.updateData(it) }

        view.findViewById<FloatingActionButton>(R.id.fabAgregar).setOnClickListener {
            view.findNavController().navigate(R.id.action_consultarHistoriasFragment_to_crearHistoriaFragment)
        }

        view.findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = true
                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filtrarPorNombre(newText ?: "")
                    return true
                }
            }
        )

        return view
    }
}