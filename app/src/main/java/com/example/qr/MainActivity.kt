package com.example.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanner.setOnClickListener { initScanner() }
    }

    private fun initScanner() {

       val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Prueba de Scann QR") //manda un msj
        integrator.setTorchEnabled(true) //Flas de la camara
        integrator.setBeepEnabled(true)  //sonido al escanear para saber si lo toma al QR

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents == null){
                Toast.makeText(this, "Cancelado!!",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Scanneo Correcto!!${result.contents}",Toast.LENGTH_LONG).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}