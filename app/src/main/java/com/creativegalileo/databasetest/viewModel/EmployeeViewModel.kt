package com.creativegalileo.databasetest.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.creativegalileo.databasetest.database.EmployeeDatabase
import com.creativegalileo.databasetest.model.Employee
import com.creativegalileo.databasetest.repository.EmployeeRepository
import kotlinx.coroutines.launch

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */
class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeRepository: EmployeeRepository

    init {
        val employeeDao =
            EmployeeDatabase.getDatabase(application, viewModelScope, application.resources)
                .employeeDao()
        employeeRepository = EmployeeRepository(employeeDao)
    }

    fun getEmployeeDetailsById(id: Int) {
        /*calling  suspend getEmployeeDetails() of employee repository  fun inside coroutinescope
        * so it doesn't block the UI, and database operation get executed */
        viewModelScope.launch {
            employeeRepository.getEmployeeDetailById(id)
        }
    }

    /*passing the employee details to main activity*/
    fun employeeDetails(): LiveData<Employee> {
        return employeeRepository.employeeDetails()
    }

}