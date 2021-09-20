package com.example.dependencyinjection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjection.databinding.UsersListItemBinding
import com.example.dependencyinjection.dataclass.UsersDataClassItem

class ProjectAdapter: RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: UsersListItemBinding):RecyclerView.ViewHolder(binding.root)

        private val diffCallBack = object : DiffUtil.ItemCallback<UsersDataClassItem>(){
            override fun areItemsTheSame(oldItem: UsersDataClassItem, newItem: UsersDataClassItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UsersDataClassItem, newItem: UsersDataClassItem): Boolean {
                return newItem == oldItem
            }
        }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var users: List<UsersDataClassItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = users[position]

        holder.binding.apply {
            nameTextViewId.text = currentUser.name
            emailTextViewId.text = currentUser.email
            phoneTextViewId.text = currentUser.phone
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}