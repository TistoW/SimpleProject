package com.tisto.simpleapp.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.myhelper.extension.*
import com.tisto.simpleapp.R
import com.tisto.simpleapp.core.source.local.entity.UserEntity
import com.tisto.simpleapp.databinding.ItemUserBinding
import com.tisto.simpleapp.util.getSingleInitial
import com.tisto.simpleapp.util.randomColor

class UserAdapter(
    var onClick: ((data: UserEntity) -> Unit?)? = null
) : ListAdapter<UserEntity, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: UserEntity) {
            with(itemBinding) {
                tvName.text = item.name
                tvEmail.text = item.email
                tvPlaceholder.text = item.name.getSingleInitial()
                tvPlaceholder.setBackgroundColor(root.context.getColor(item.color))
                imageUser.toGone()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }
        }
    }
}