package com.example.permisos_sem4_3

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btcamera = findViewById<Button>(R.id.btactivate)
        btcamera.setOnClickListener{
            checkCaeraPermission()

        }
    }

    private fun checkCaeraPermission() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Ya se cuenta con el permiso",Toast.LENGTH_LONG).show()
        }
        else{
            requestCameraPermission()

        }
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)){
            Toast.makeText(this, "Rechazo el permiso antes. Habilitelo manuelmente",Toast.LENGTH_LONG).show()
        }
        else{
            //en este flujo nunca pedi ni rechase el permiso
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Se acepto el permiso de la CAMARA!!",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Permiso negado",Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

}