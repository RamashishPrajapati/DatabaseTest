package com.creativegalileo.databasetest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creativegalileo.databasetest.dao.EmployeeDao
import com.creativegalileo.databasetest.model.Employee

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */
class EmployeeRepository(private val employeeDao: EmployeeDao) {

    private var employeeDetails = MutableLiveData<Employee>()

    /*Fetching single employee record from database according to employee ID*/
    suspend fun getEmployeeDetailById(id: Int) {
        employeeDetails.value = employeeDao.getEmployeeById(id)
    }

    /*passing the employee details to employee view model*/
    fun employeeDetails(): LiveData<Employee> {
        return employeeDetails
    }
}