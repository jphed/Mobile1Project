package com.example.mobile1project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.View.StudentListView
import com.example.mobile1project.ThirdPartial.ExamenEstudiantes.ViewModel.StudentViewModel
import com.example.mobile1project.navigation.TabBarNavigationView
import com.example.mobile1project.ui.theme.Mobile1ProjectTheme

class MainActivity : ComponentActivity() {

    // ✅ ViewModel instanciado correctamente
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile1ProjectTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LaunchedEffect(Unit) {
                        studentViewModel.getStudents()
                    }
                    TabBarNavigationView(studentViewModel = studentViewModel)

                }
            }
        }
    }
}
