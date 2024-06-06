package com.example.appandroiddemo1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appandroiddemo1.models.Vehiculo
@Dao
interface VehiculoDao {
    //ESTE DAO SIRVE PARA DEFINIR EL CRUD
    @Insert
    fun insert(vehiculo: Vehiculo): Long
    @Update
    fun update(vehiculo: Vehiculo)
    @Delete
    fun delete(vehiculo: Vehiculo)
    @Query("SELECT * FROM 'tblVehiculo' WHERE id =:idInput")
    fun getVehicle(idInput: Int): List<Vehiculo>
    @Query("SELECT * FROM 'tblVehiculo' ORDER BY id")
    fun getVehicles(): LiveData<List<Vehiculo>>
}