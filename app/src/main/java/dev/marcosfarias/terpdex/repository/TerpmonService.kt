package dev.marcosfarias.terpdex.repository

import dev.marcosfarias.terpdex.model.Terpmon
import retrofit2.Call
import retrofit2.http.GET

interface TerpmonService {
    @GET("pokemon.json")
    fun get(): Call<List<Terpmon>>
}
