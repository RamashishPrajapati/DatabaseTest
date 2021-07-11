package com.creativegalileo.databasetest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativegalileo.databasetest.R
import com.creativegalileo.databasetest.adapter.EmployeeAdapter
import com.creativegalileo.databasetest.databinding.ActivityMainBinding
import com.creativegalileo.databasetest.model.Employee
import com.creativegalileo.databasetest.viewModel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeAdapter: EmployeeAdapter
    private var emplyeeList = ArrayList<Employee>()
    private var defaultEmployeeId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        initializeRVEmployee()
        initlistner()
        setupObserver()
        /*On app start first employee details will get fetch from database*/
        employeeViewModel.getEmployeeDetailsById(defaultEmployeeId)
    }

    /*Initializing recyclerview*/
    private fun initializeRVEmployee() {
        binding.rvEmployeeDetails.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            employeeAdapter = EmployeeAdapter()
            adapter = employeeAdapter


        }
    }

    private fun initlistner() {
        /*initializing button click listener*/
        binding.btnNext.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
    }

    /*Setting up Onserver with viewmodel to observer live data fetching from database*/
    private fun setupObserver() {
        employeeViewModel.employeeDetails().observe(this, Observer {
            it.let {
                /*checking employee object coming from datatabse is null or not, if not null then
                if block get executed or else block will */
                if (it != null) {
                    emplyeeList.add(emplyeeList.size, it)
                    employeeAdapter.submitList(emplyeeList)
                    employeeAdapter.notifyItemInserted(emplyeeList.size - 1)
                    binding.rvEmployeeDetails.scrollToPosition(employeeAdapter.itemCount - 1)
                } else {
                    /*if employee object is empty i.e. database has no more record in it,
                     then showing below toast to user*/
                    Toast.makeText(this, "No record found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNext -> {
                /*On clicking on next button on screen next record from database
                 will get fetch by passing incremented array list value */
                employeeViewModel.getEmployeeDetailsById(emplyeeList.size + 1)
            }

            R.id.btnDelete -> {
                /*On clicking on delete button on screen last record from list will get delete from recyclerview*/
                /*Before delete record from list checking the size of list,
                if list has value then if block will execute or else block will execute*/
                if (emplyeeList.size > 0) {
                    var deleteEmployeePostion = emplyeeList.size - 1
                    emplyeeList.removeAt(deleteEmployeePostion)
                    employeeAdapter.notifyItemRemoved(deleteEmployeePostion)
                } else {
                    /*If user delete all the record from list and no more record to
                    process then below toast message will shown to user*/
                    Toast.makeText(this, "No more record to delete", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


}