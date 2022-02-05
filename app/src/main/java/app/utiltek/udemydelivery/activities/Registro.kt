package app.utiltek.udemydelivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import app.utiltek.udemydelivery.R
import app.utiltek.udemydelivery.models.ResponseHttp
import app.utiltek.udemydelivery.models.User
import app.utiltek.udemydelivery.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {
    var idBtnRegistrar: Button? = null
    var idImgRegistro: ImageView? = null
    var idEditNombre: EditText? = null
    var idEditApellido: EditText? = null
    var idEditEmailRegistro: EditText? = null
    var idEditTelefono: EditText? = null
    var idEditPasswordRegistro: EditText? = null
    var idEditPasswordConfirmar: EditText? = null

    //llamando a la clase UserProvider
    var userProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        idImgRegistro = findViewById(R.id.id_img_atras)
        idEditNombre = findViewById(R.id.id_edit_nombre)
        idEditApellido = findViewById(R.id.id_edit_apellido)
        idEditEmailRegistro = findViewById(R.id.id_edit_email_registro)
        idEditTelefono = findViewById(R.id.id_edit_telefono)
        idEditPasswordRegistro = findViewById(R.id.id_edit_password_registro)
        idEditPasswordConfirmar = findViewById(R.id.id_edit_password_confirmar)
        idBtnRegistrar = findViewById(R.id.id_btn_registrar)
        idImgRegistro?.setOnClickListener {
            atras()
        }
        idBtnRegistrar?.setOnClickListener { registrar() }
    }

    private fun registrar() {
        val nombre = idEditNombre?.text.toString()
        val apellido = idEditApellido?.text.toString()
        val email = idEditEmailRegistro?.text.toString()
        val telefono = idEditTelefono?.text.toString()
        val password = idEditPasswordRegistro?.text.toString()
        val confirmarPass = idEditPasswordConfirmar?.text.toString()

        if (esValido(nombre, apellido, email, telefono, password, confirmarPass)) {
            // Toast.makeText(this, "Datos correctos", Toast.LENGTH_SHORT).show()

            val user = User(
                name = nombre,
                lastname = apellido,
                email = email,
                phone = telefono,
                password = password
            )

            userProvider.register(user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>,
                    response: Response<ResponseHttp>
                ) {
                    Toast.makeText(this@Registro, response.body()?.message, Toast.LENGTH_SHORT)
                        .show()


                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Toast.makeText(
                        this@Registro,
                        "Error al ejecutarse ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })


        }

    }

    //para validar si es un correo bien escrito
    fun String.esEmailValido(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun esValido(
        nombre: String,
        apellido: String,
        email: String,
        telefono: String,
        password: String,
        confirmar: String
    ): Boolean {
        if (nombre.isBlank()) {
            Toast.makeText(this, "Ingresa el Nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (apellido.isBlank()) {
            Toast.makeText(this, "Ingresa el Apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isBlank()) {
            Toast.makeText(this, "Ingresa el Email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!email.esEmailValido()) {
            Toast.makeText(this, "Email incorrecto", Toast.LENGTH_SHORT).show()
            return false
        }

        if (telefono.isBlank()) {
            Toast.makeText(this, "Ingresa el Teléfono", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isBlank()) {
            Toast.makeText(this, "Ingresa el Password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (confirmar.isBlank()) {
            Toast.makeText(this, "Ingresa la Confirmación del Password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmar) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun atras() {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
}