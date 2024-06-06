package com.example.appandroiddemo1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appandroiddemo1.models.Vehiculo

@Database(entities = [Vehiculo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
        //DEFINIR EL DAO
        abstract fun vehiculoDao(): VehiculoDao

        //DEFINIR INSTANCIA DE BASE DE DATOS
        companion object{
                var instancia: AppDatabase? = null
                //Manejar instancia
                fun getInstancia(context: Context): AppDatabase{
                        if (instancia == null){
                                instancia = Room.databaseBuilder(
                                        context,
                                        AppDatabase::class.java,
                                        name = "bdVehiculos"
                                ).build()
                        }
                        return instancia as AppDatabase
                }
        }
}