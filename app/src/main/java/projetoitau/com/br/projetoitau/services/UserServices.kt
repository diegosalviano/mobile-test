package projetoitau.com.br.projetoitau.services

import projetoitau.com.br.projetoitau.model.Users
import retrofit2.http.GET

interface UserServices {

    @GET("user.json")
    fun lista(): retrofit2.Call<Users>

}