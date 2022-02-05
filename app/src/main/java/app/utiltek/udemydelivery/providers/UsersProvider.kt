package app.utiltek.udemydelivery.providers

import app.utiltek.udemydelivery.api.ApiRoutes
import app.utiltek.udemydelivery.models.ResponseHttp
import app.utiltek.udemydelivery.models.User
import app.utiltek.udemydelivery.routes.UserRoutes
import retrofit2.Call

//para usar las rutas creadas en apiRoutes para lanzar la aplicación
class UsersProvider {
    private var userRoutes:UserRoutes?=null
    init {
        val api=ApiRoutes()
        userRoutes=api.getUserRoutes()

    }

    fun register(user: User):Call<ResponseHttp>?{
        return userRoutes?.register(user)

    }
}