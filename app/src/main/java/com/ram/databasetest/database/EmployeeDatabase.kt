package com.ram.databasetest.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ram.databasetest.R
import com.ram.databasetest.dao.EmployeeDao
import com.ram.databasetest.model.Employee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */
@Database(
    entities = [Employee::class],
    version = 1,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    private class EmployeeDatabaseCallback(
        private val scope: CoroutineScope,
        private val resources: Resources
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let { database ->
                scope.launch {
                    var employeeDao = database!!.employeeDao()
                    prePopulateDatabase(employeeDao)
                }
            }
        }

        /*prePopulating database, on creation of database inseting all the record
        from employee.json file which is in raw folder of app*/
        private suspend fun prePopulateDatabase(employeeDao: EmployeeDao) {
            val jsonString = resources.openRawResource(R.raw.employee).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<Employee>>() {}.type
            val employeeDetails = Gson().fromJson<List<Employee>>(jsonString, typeToken)
            employeeDao.insertAllEmplyoee(employeeDetails)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(
            context: Context,
            coroutineScope: CoroutineScope,
            resources: Resources
        ): EmployeeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "players_database"
                )
                    .addCallback(EmployeeDatabaseCallback(coroutineScope, resources))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}