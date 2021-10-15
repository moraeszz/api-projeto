package com.example.consumodeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var buttonBuscar: Button
    lateinit var textViewEndereco: TextView
    lateinit var editTextCep: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBuscar = findViewById(R.id.buttonBuscar)
        textViewEndereco = findViewById(R.id.textViewEndereco)
        editTextCep = findViewById(R.id.editTextCep)

        buttonBuscar.setOnClickListener {

            // Obter uma instancia da conexão com o backend
            val remote = RetrofitFactory().retrofitService()

            // Criar uma chamada para o endpoint cep/json
            val call: Call<Cep> = remote.getCEP(editTextCep.text.toString())

            // Executar a chamada para a api
            call.enqueue(object : Callback<Cep>{
                override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                    val cep = response.body()
                    textViewEndereco.text = cep.toString()
                }

                override fun onFailure(call: Call<Cep>, t: Throwable) {
                    Log.i("cep", t.message.toString())
                }

            })
        }
    }
}