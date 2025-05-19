package com.example.mobile1project.ThirdPartial.ExamenEstudiantes.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Model.Student
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Repository.Result
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.Repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()

    private val _uiState = MutableStateFlow<Result<List<Student>>>(Result.Loading)
    val uiState: StateFlow<Result<List<Student>>> = _uiState

    fun getStudents() {
        _uiState.value = Result.Loading
        viewModelScope.launch {
            val result = repository.fetchStudents()
            _uiState.value = result
        }
    }
}
