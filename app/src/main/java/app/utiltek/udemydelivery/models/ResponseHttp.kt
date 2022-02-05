package app.utiltek.udemydelivery.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

//para tener en un solo archivo el tipo de respuestas que me debe arrojar la peticion
class ResponseHttp (
    @SerializedName("message") val message:String,
    @SerializedName("success") val isSuccess:Boolean,
    @SerializedName("data") val data:JsonObject,
    @SerializedName("error") val error:String
    )
{


    override fun toString(): String {
        return "ResponseHttp(message='$message', isSuccess=$isSuccess, data=$data, error='$error')"
    }
}