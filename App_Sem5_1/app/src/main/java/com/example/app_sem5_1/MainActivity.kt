package com.example.app_sem5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var contacts = ArrayList<Contract>()
    var contractAdapter =   ContractAdapter(contacts)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadContracts()
        initView()
    }

    private fun initView() {
        val rvContract = findViewById<RecyclerView>(R.id.rvcontact)
        rvContract.adapter = contractAdapter
        rvContract.layoutManager = LinearLayoutManager(this)
        // sirve para que no se salga si del contenedor
        // rvContract.setHasFixedSize(true)
    }

    private fun loadContracts() {
        contacts.add(Contract("Americo", "11223344"))
        contacts.add(Contract("Juan", "985674123"))
        contacts.add(Contract("Pedro", "967412358"))
        contacts.add(Contract("Natalan", "975462813"))
    }
}