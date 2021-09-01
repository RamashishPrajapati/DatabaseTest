package com.ram.databasetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ram.databasetest.databinding.ItemEmployeeBinding
import com.ram.databasetest.model.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

/**
 * Created by Ramashish Prajapati on 11,July,2021
 */

class EmployeeAdapter :
    ListAdapter<Employee, EmployeeAdapter.ItemViewholder>(EmployeeDiffUtilDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        /*setting required data in textview to show employee details*/
        fun bind(item: Employee) = with(itemView) {
            itemView.tv_setempid.text = item.empId
            itemView.tv_setempname.text = item.empName
            itemView.tv_setcureentsalary.text = item.currentSalary
            itemView.tv_setjoiningdate.text = item.joiningDate
            itemView.tv_setlocation.text = item.location
            itemView.tv_setisonline.text = item.isOnline
            itemView.tv_setdesignation.text = item.designation
        }
    }
}

class EmployeeDiffUtilDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.empId == newItem.empId &&
                oldItem.empName==newItem.empName &&
                oldItem.currentSalary==newItem.currentSalary &&
                oldItem.designation==newItem.designation &&
                oldItem.isOnline==newItem.isOnline
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.empId==newItem.empId &&
                oldItem.empName==newItem.empName &&
                oldItem.currentSalary==newItem.currentSalary &&
                oldItem.designation==newItem.designation &&
                oldItem.isOnline==newItem.isOnline
    }

} 