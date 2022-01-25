package app.utiltek.udemydelivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import app.utiltek.udemydelivery.R

class MainActivity : AppCompatActivity() {
    var idImgRegistro:ImageView?=null//inicializar una variable
    var idEditEmal:EditText?=null
    var idEditPassword:EditText?=null
    var idBtnLogin:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        idImgRegistro=findViewById(R.id.id_img_registo)
        idEditEmal=findViewById(R.id.id_edit_email)
        idEditPassword=findViewById(R.id.id_edit_password)
        idBtnLogin=findViewById(R.id.id_btn_login)
        idBtnLogin?.setOnClickListener { login() }
        idImgRegistro?.setOnClickListener {//? si el imageView no esta vacio se ejecuta
            desplazar()
        }

    }

    //para validar si es un correo bien escrito
    fun String.esEmailValido():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }


    private  fun esValido(email:String, password:String):Boolean{
        if(email.isBlank()){
            return false
        }

        if(password.isBlank()){
            return false
        }
        if(!email.esEmailValido()){
            return false
        }
        return true
    }

    private fun login(){
        val email=idEditEmal?.text.toString()//? para evitar el null pointer exception
        val password=idEditPassword?.text.toString()



        if(esValido(email,password)){
            Toast.makeText(this, "Datos correctos ", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Datos incorrectos ", Toast.LENGTH_LONG).show()

        }


    }

    private  fun desplazar(){
        val myIntent=Intent(this,Registro::class.java)
        startActivity(myIntent)
    }
}