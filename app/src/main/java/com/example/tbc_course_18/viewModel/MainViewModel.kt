package com.example.tbc_course_18.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_course_18.models.ApartmentsModel
import com.example.tbc_course_18.models.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private val _apiResponse = MutableStateFlow<Resource>(Resource.Loading)
    val apiResponse: StateFlow<Resource> = _apiResponse


    fun getContent(){
        viewModelScope.launch {
            val response = RetrofitClient.getInformation().getInfo()
            if (response.isSuccessful){
                val body:ApartmentsModel? = response.body()
                _apiResponse.emit(Resource.Success(body?.content ?: emptyList()))
            }else{
                val error = response.errorBody()
                _apiResponse.emit(Resource.Error(error?.toString() ?: "Unknown Error"))

            }
        }
    }


    sealed class Resource{
        data class Success(val data: List<ApartmentsModel.Content>) : Resource()
        data class Error(val message:String):Resource()
        object Loading: Resource()

    }
}