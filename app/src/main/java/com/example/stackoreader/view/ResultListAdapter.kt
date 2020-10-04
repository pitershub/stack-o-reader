package com.example.stackoreader.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stackoreader.R
import com.example.stackoreader.data.Item
import com.example.stackoreader.databinding.ItemQueryBinding

class ResultListAdapter(private val itemList: ArrayList<Item>) :
    RecyclerView.Adapter<ResultListAdapter.ResultViewHolder>() {

    class ResultViewHolder(var view: ItemQueryBinding) : RecyclerView.ViewHolder(view.root)

    fun updateResultsList(newItemList: List<Item>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemQueryBinding>(inflater, R.layout.item_query, parent, false)
        return ResultViewHolder(view)

    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.view.item = itemList[position]
    }
}