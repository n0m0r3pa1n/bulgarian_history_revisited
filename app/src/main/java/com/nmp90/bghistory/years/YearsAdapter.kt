package com.nmp90.bghistory.years

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.R

class YearsAdapter(private val yearsList: MutableList<Year>) : RecyclerView.Adapter<YearsAdapter.ViewHolder>() {
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(yearsList[position])

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_year, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = yearsList.size

    fun setData(years: List<Year>) {
        yearsList.clear()
        yearsList.addAll(years)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvYear: TextView = view.findViewById(R.id.tv_year)
        private val tvName: TextView = view.findViewById(R.id.tv_name)


        fun bind(year: Year) {
            tvYear.text = tvName.context.getString(R.string.year_period, year.year)
            tvName.text = year.name
        }
    }
}
