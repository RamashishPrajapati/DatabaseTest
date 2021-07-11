package com.creativegalileo.databasetest.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.creativegalileo.databasetest.model.Employee

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */
@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEmplyoee(employee: List<Employee>)

    @Query("SELECT * FROM employee_details WHERE id = :id")
    suspend fun getEmployeeById(id: Int): Employee
}