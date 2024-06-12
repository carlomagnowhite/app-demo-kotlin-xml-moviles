package com.example.appandroiddemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appandroiddemo1.database.AppDatabase
import com.example.appandroiddemo1.databinding.ActivityListadoBinding
import com.example.appandroiddemo1.models.Vehiculo
import com.example.appandroiddemo1.utils.Constantes
import java.util.concurrent.Executors

class Listado : AppCompatActivity() {
    private lateinit var binding: ActivityListadoBinding
    private val adapter: VehicleAdapter by lazy {
        VehicleAdapter().apply {
            setOnClickListenerEditar = { vehiculo ->
                actualizar(vehiculo)
            }
            setOnClickListenerEliminar = { vehiculo ->
                eliminar(vehiculo)
            }
        }
    }
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstancia(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Cargar el Adaptador
        cargarAdaptador()
        //Cargar los datos
        cargarDatos()
        //Manejar los eventos
        cargarEventos()
    }

    private fun cargarEventos() {
        binding.btnNuevo.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }
    }
    private fun cargarDatos() {
        appDatabase.vehiculoDao().getVehicles().observe(this, { vehiculos ->
            adapter.updateListVehiculos(vehiculos)
        })
    }
    private fun cargarAdaptador() {
        binding.rvListado.adapter = adapter
    }

    private fun eliminar(vehiculo: Vehiculo) {
        Executors.newSingleThreadExecutor().execute {
            appDatabase.vehiculoDao().delete(vehiculo)
            runOnUiThread {
                Toast.makeText(this, "Vehiculo Eliminado", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun actualizar(vehiculo: Vehiculo) {
        val intent = Intent(this, Registro::class.java)
        intent.putExtra(Constantes.KEY_VEHICULO, vehiculo)
        startActivity(intent)
    }
}