package com.example.app_sem5_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class ContractAdapter(var contract: ArrayList<Contract>): RecyclerView.Adapter<Contractprototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Contractprototype {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_contract_class,parent,false)

        return Contractprototype(view)
    }

    override fun onBindViewHolder(holder: Contractprototype, position: Int) {
        holder.bind(contract.get(position))
    }

    override fun getItemCount(): Int {
        return contract.size

    }

}

class Contractprototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvTelephone = itemView.findViewById<TextView>(R.id.tvTelephone)

    fun bind(contract: Contract){
        tvName.text = contract.name
        tvTelephone.text = contract.telephone
    }
}
