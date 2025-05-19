package com.example.mobile1project.ThirdPartial.ExamenEstudiantes.APIService

import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Model.Student
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface StudentApi {

    @GET(
        "ingjromo/2b6648feaf70d1116e6f468b263d73c9/" +
                "raw/50cf034eb87e273d6746cf2ffb9afb5a409f80b2/students.json"
    )
    suspend fun getStudents(): List<Student>

    companion object {
        fun create(): StudentApi {
            val moshi = Moshi.Builder().build()

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/") // dominio raíz
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()

            return retrofit.create(StudentApi::class.java)
        }
    }
}
