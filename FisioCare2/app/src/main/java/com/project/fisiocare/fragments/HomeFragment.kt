package com.project.fisiocare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.project.fisiocare.R
import com.project.fisiocare.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.navHistoriaClinica.setOnClickListener {
            view.findNavController().navigate(R.id.action_inicioFragment_to_consultarHistoriasFragment)
        }




        binding

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}