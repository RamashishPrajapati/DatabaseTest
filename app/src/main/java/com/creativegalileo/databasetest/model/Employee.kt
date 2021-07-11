package com.creativegalileo.databasetest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */

@Entity(tableName = "employee_details")
data class Employee(
    @SerializedName("Emp Id")
    @ColumnInfo(name = "Emp Id") var empId: String?,
    @SerializedName("Emp Name")
    @ColumnInfo(name = "Emp Name") var empName: String?,
    @SerializedName("Current Salary")
    @ColumnInfo(name = "Current Salary") var currentSalary: String?,
    @SerializedName("Joining Date")
    @ColumnInfo(name = "Joining Date") var joiningDate: String?,
    @SerializedName("Location")
    @ColumnInfo(name = "Location") var location: String?,
    @SerializedName("isOnline")
    @ColumnInfo(name = "isOnline") var isOnline: String?,
    @SerializedName("Designation")
    @ColumnInfo(name = "Designation") var designation: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}