package com.enrique.cursoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CrearCuenta : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtnombre_nuevo : TextView =findViewById(R.id.edtNombre)
        val txtemail_nuevo :TextView =findViewById(R.id.edtEmailnew)
        val txtpassword1 : TextView= findViewById(R.id.edtpassword1)
        val txtpassword2 : TextView= findViewById(R.id.edtpassword2)
        val btncrear: Button = findViewById(R.id.btncrear)
        btncrear.setOnClickListener()
        {
        var pass1= txtpassword1.text.toString()
            var pass2=txtpassword2.text.toString()
            if (pass1 == pass2)
            {
                createAccount(txtemail_nuevo.text.toString(),txtpassword1.text.toString())
            }
            else
            {
            Toast.makeText(baseContext, "Error: los passwords no coinciden", Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }
        }
        firebaseAuth= Firebase.auth
    }
    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful)
                {
                    Toast.makeText(baseContext,"Cuenta creada Correctamente",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext, "Algo salio mal, Error:"+ task.exception, Toast.LENGTH_SHORT).show()

                }

            }
    }
}