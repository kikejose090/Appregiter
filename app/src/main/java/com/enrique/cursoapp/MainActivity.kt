package com.enrique.cursoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var authStateListener:FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 : Button = findViewById(R.id.btn1)
        val txteamail : TextView = findViewById(R.id.txtemail)
        val txtpasword : TextView = findViewById(R.id.txtpasword)
        val btnCrearCuenta:TextView = findViewById(R.id.btncrearcuenta)
        firebaseAuth=Firebase.auth
        btn1.setOnClickListener()
        {
            signIn(txteamail.text.toString(), txtpasword.text.toString())
        }
        btnCrearCuenta.setOnClickListener()
        {
            val i = Intent (this,CrearCuenta::class.java)
            startActivities(arrayOf(i))
        }
    }

    private fun signIn(email:String, password: String)
 {
     firebaseAuth.signInWithEmailAndPassword(email, password)
         .addOnCompleteListener (this){ task ->
             if (task.isSuccessful){
                 val user = firebaseAuth.currentUser
                 Toast.makeText(baseContext,"Autentificacion Exitosa", Toast.LENGTH_SHORT).show()
                 //segunda activity vamos
                 val i = Intent( this, MainActivity2::class.java)
                 startActivities(arrayOf(i))
             }
             else
             {
                 Toast.makeText(baseContext, "Error de Email o Contraseña",Toast.LENGTH_SHORT).show()
             }
         }
     }
 }