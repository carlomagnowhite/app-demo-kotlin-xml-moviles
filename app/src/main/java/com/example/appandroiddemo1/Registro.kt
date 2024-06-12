package com.example.appandroiddemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appandroiddemo1.database.AppDatabase
import com.example.appandroiddemo1.databinding.ActivityRegistroBinding
import com.example.appandroiddemo1.models.Vehiculo
import com.example.appandroiddemo1.utils.Constantes
import java.util.concurrent.Executors

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstancia(this)
    }
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializar datos
        inicializarDatos()
        //Manejar eventos
        eventos()
    }

    private fun inicializarDatos() {
        val bundle: Bundle? = intent.extras
        bundle?.let {
            val vehiculo = bundle.getSerializable(Constantes.KEY_VEHICULO) as Vehiculo
            with(binding) {
                btnRegistrar.setText("Actualizar")
                id = vehiculo.id
                edtMarca.setText(vehiculo.marca)
                edtModelo.setText(vehiculo.modelo)
                edtAnio.setText(vehiculo.anio)
            }
        } ?: kotlin.run {
            with(binding) {
                btnRegistrar.setText("Registrar")
                edtMarca.setText("")
                edtModelo.setText("")
                edtAnio.setText("")
            }
        }
        binding.edtMarca.requestFocus()
    }

    private fun eventos() {
        with(binding) {
            binding.btnRegistrar.setOnClickListener {
                val marca = edtMarca.text.toString()
                val modelo = edtModelo.text.toString()
                val anio = edtAnio.text.toString()
                if (id == 0) {
                    agregar(Vehiculo(0, marca, modelo, anio))
                } else {
                    actualizar(Vehiculo(id, marca, modelo, anio))
                }
            }
        }
    }
    private fun actualizar(vehiculo: Vehiculo) {
        Executors.newSingleThreadExecutor().execute {
            appDatabase.vehiculoDao().update(vehiculo)
            runOnUiThread {
                Toast.makeText(this, "Vehiculo Actualizado", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }
    }
    private fun agregar(vehiculo: Vehiculo) {
        Executors.newSingleThreadExecutor().execute {
            appDatabase.vehiculoDao().insert(vehiculo)
            runOnUiThread {
                Toast.makeText(this, "Vehiculo Registrado", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }
    }
    //Manejar los eventos
}