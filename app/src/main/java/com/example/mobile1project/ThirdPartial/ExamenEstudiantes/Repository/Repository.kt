package com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Repository

import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.APIService.StudentApi
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Model.Student
import retrofit2.HttpException
import java.net.UnknownHostException

sealed interface Result<out T> {
    data object Loading : Result<Nothing>
    data class Success<T>(val data: T) : Result<T>
    data class Error(val message: String) : Result<Nothing>
}

class StudentRepository(private val api: StudentApi = StudentApi.create()) {
    suspend fun fetchStudents(): Result<List<Student>> = try {
        val students = api.getStudents()
        Result.Success(students)
    } catch (e: UnknownHostException) {
        Result.Error("Sin conexión a Internet")
    } catch (e: HttpException) {
        when (e.code()) {
            404 -> Result.Error("Recurso no encontrado (404)")
            500 -> Result.Error("Error del servidor (500)")
            else -> Result.Error("Error HTTP ${e.code()}")
        }
    } catch (e: Exception) {
        Result.Error("Error inesperado: ${e.localizedMessage}")
    }
}
