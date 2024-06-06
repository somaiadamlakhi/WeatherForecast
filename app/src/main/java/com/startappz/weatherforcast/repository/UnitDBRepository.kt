package com.startappz.weatherforcast.repository

import com.startappz.weatherforcast.data.UnitDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.startappz.weatherforcast.model.Unit

class UnitDBRepository @Inject constructor(private val unitDao: UnitDao) {

    fun getUnits(): Flow<List<Unit>> = unitDao.getUnits()
    suspend fun insertUnit(unit: Unit) = unitDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = unitDao.updateUnit(unit)
    suspend fun deleteAllUnits() = unitDao.deleteAllUnits()
    suspend fun deleteUnit(unit: Unit) = unitDao.deleteUnit(unit)
}