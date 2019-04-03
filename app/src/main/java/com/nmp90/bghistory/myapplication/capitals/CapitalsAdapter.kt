package com.nmp90.bghistory.myapplication.capitals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.databinding.ItemCapitalBinding

class CapitalsAdapter(private val capitals: List<Capital>, private val capitalClickListener: CapitalClickListener) :
    RecyclerView.Adapter<CapitalsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val binding: ItemCapitalBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_capital,
            viewGroup,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {_ ->
            if(viewHolder.adapterPosition == RecyclerView.NO_POSITION) {
                return@setOnClickListener
            }

            capitalClickListener.onCapitalClick(capitals[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount() = capitals.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(capitals[position])
    }

    class ViewHolder(val binding: ItemCapitalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(capital: Capital) {
            binding.capital = capital
            binding.executePendingBindings()
        }
    }

    interface CapitalClickListener {
        fun onCapitalClick(capital: Capital)
    }
}
