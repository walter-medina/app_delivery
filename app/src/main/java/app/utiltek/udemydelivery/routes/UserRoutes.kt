package app.utiltek.udemydelivery.routes
import app.utiltek.udemydelivery.models.ResponseHttp
import app.utiltek.udemydelivery.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

//Las ruta igual que node
interface UserRoutes {
    //creando la peticion post:
    @POST("users/create")
    fun register(@Body user:User):Call<ResponseHttp>


}