package com.example.samcomprac.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samcomprac.R
import com.example.samcomprac.databinding.RowDataBinding
import com.example.samcomprac.model.DataModel

class DataAdapter(
    private val context: Context,
    private val modelList: ArrayList<DataModel>,
    private val listener:OnClickListener
) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_data, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding?.model = modelList.get(position)

        holder.itemView.setOnClickListener {
            listener.onClick(holder.adapterPosition)
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var binding: RowDataBinding? = DataBindingUtil.bind(itemView!!)
    }

    interface OnClickListener{
        fun onClick(position: Int)
    }
}