package com.example.appandroiddemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appandroiddemo1.databinding.ActivityComponentsBinding

class Main : AppCompatActivity() {
    private lateinit var binding: ActivityComponentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imprimir()
    }

    fun imprimir(){
        with(binding){
            btnConfirmar.setOnClickListener{
                val nombre = edtNombres.text.toString()
                val apellidos = edtApellidos.text.toString()
                txtResultado.setText(nombre + apellidos)
            }
        }
    }
}