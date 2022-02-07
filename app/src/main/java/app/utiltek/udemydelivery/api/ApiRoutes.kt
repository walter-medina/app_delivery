package app.utiltek.udemydelivery.api

import app.utiltek.udemydelivery.routes.UserRoutes
//hace las veces del server en node para llamar las rutas
class ApiRoutes {
    val API_URL="http://10.142.11.251:8080/api/"
    val retrofit=RetrofitClient()

    //contiene las rutas del usuario
    fun getUserRoutes():UserRoutes{
        return retrofit.getClient(API_URL).create(UserRoutes::class.java)

    }

}