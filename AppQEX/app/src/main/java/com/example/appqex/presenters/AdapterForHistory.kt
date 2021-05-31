package com.example.appqex.presenters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appqex.R
import com.example.appqex.use_cases.data.database.Calculations

class AdapterForHistory(_list:List<Calculations>):
    RecyclerView.Adapter<AdapterForHistory.HistoryAdapter>() {
    var list = ArrayList<Calculations>(_list)
    class HistoryAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt = itemView.findViewById<TextView>(R.id.history_txt)
        fun onBind(calculations: Calculations){
            if(calculations.calculation.isNullOrEmpty()){
                txt.text = "ERROR"
            }
            else {
                txt.text = calculations.calculation.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.history_item,
            parent, false)
        return HistoryAdapter(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter, position: Int) {
        holder.onBind(list[position])


    }

    override fun getItemCount():Int{
        return list.size

    }
    fun update(newlist: ArrayList<Calculations>){
        list.removeAll(list)
        list.addAll(newlist)
        notifyDataSetChanged()



    }
}