import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.ProyectoFinal.Model.Restaurant
import com.example.mobile1project.ProyectoFinal.Repositorio.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val repository = RestaurantRepository()

    var restaurantList by mutableStateOf<List<Restaurant>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadRestaurants()
    }

    fun loadRestaurants() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = repository.fetchRestaurants()
                restaurantList = response.mapIndexed { index, restaurant ->
                    restaurant.copy(id = index)
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error: ${e.message}")
            }
            isLoading = false
        }

    }
}
