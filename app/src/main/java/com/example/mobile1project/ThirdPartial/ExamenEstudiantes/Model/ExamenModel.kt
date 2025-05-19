package com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Student(
    val id: Int,
    @Json(name = "name") val nombre: String,
    @Json(name = "studentId") val matricula: String,
    @Json(name = "quote") val frase: String,
    @Json(name = "imageName") val imagen: String
)
