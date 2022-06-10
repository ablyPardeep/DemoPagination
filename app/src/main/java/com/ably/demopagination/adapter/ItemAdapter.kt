package com.ably.demopagination.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ably.demopagination.R
import com.ably.demopagination.model.ApiResponseItem

import kotlinx.android.synthetic.main.item_view_users_data.view.*


class ItemAdapter : PagingDataAdapter<ApiResponseItem, ItemAdapter.PassengersViewHolder>(
    PassengersComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengersViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view_users_data, parent, false)
        return PassengersViewHolder(v)
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: ApiResponseItem) {

            itemView.text_view_headquarters.text = item.full_name
            itemView.text_view_name_with_trips.text = "${item.name}, ${item.allow_forking} Trips"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<ApiResponseItem>() {
        override fun areItemsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem): Boolean {
            return oldItem == newItem
        }
    }
}
