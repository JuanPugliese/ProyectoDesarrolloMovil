package com.project.fisiocare

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.project.fisiocare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "Aplicación iniciada")

        // Ajustar padding para notch/punchhole
        ViewCompat.setOnApplyWindowInsetsListener(binding.header) { view, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.setPadding(
                view.paddingLeft,
                statusBarHeight,
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Configurar navegación
        binding.bottomNav0.apply {
            gotoInicio.setOnClickListener {
                navController.navigate(R.id.inicioFragment)
            }


            gotoHistoria.setOnClickListener {
                navController.navigate(R.id.consultarHistoriasFragment)
            }

            gotoPerfil.setOnClickListener {
                navController.navigate(R.id.perfilFragment)
            }
        }

        // Listener para cambios de pantalla
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isLogin = destination.id == R.id.loginFragment

            binding.header.visibility = if (isLogin) View.GONE else View.VISIBLE
            binding.bottomNav0.root.visibility = if (isLogin) View.GONE else View.VISIBLE

            binding.title.text = when (destination.id) {
                R.id.consultarHistoriasFragment -> "Historias Clínicas"
                R.id.crearHistoriaFragment -> "Nueva Historia"
                else -> destination.label
            }
        }

        binding.backBtn.setOnClickListener {
            navController.navigateUp()
        }
    }
}