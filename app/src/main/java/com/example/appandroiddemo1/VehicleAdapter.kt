package com.example.appandroiddemo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appandroiddemo1.databinding.ItemVehiculoBinding
import com.example.appandroiddemo1.models.Vehiculo

//definir como se maneja los datos
//Crear el ViewHolder
//Implementar los métodos del adaptador


class VehicleAdapter(var vehiculos: List<Vehiculo> = emptyList()) :
    RecyclerView.Adapter<VehicleAdapter.VehiculoAdapterViewHolder>() {

    lateinit var setOnClickListenerEditar: (Vehiculo) -> Unit
    lateinit var setOnClickListenerEliminar: (Vehiculo) -> Unit

    //crear el VH
    inner class VehiculoAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ItemVehiculoBinding = ItemVehiculoBinding.bind(itemView)
        fun bind(vehiculo: Vehiculo) {
            with(binding)
            {
                txtAnio.setText(vehiculo.anio)
                txtMarca.setText(vehiculo.marca)
                txtModelo.setText(vehiculo.modelo)
                btnEditar.setOnClickListener {
                    setOnClickListenerEditar(vehiculo)
                }
                btnEliminar.setOnClickListener {
                    setOnClickListenerEliminar(vehiculo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_vehiculo, parent, false)
        return VehiculoAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vehiculos.size
    }

    override fun onBindViewHolder(holder: VehiculoAdapterViewHolder, position: Int) {
        val vehiculo = vehiculos[position]
        holder.bind(vehiculo)
    }

    fun updateListVehiculos(vehiculos: List<Vehiculo>){
        this.vehiculos = vehiculos
        notifyDataSetChanged()
    }
}