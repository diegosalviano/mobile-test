package projetoitau.com.br.projetoitau.api

import projetoitau.com.br.projetoitau.API_USER
import projetoitau.com.br.projetoitau.services.UserServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_USER)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun listaService() = retrofit.create(UserServices::class.java)
}