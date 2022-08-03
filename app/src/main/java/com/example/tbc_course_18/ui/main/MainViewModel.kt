package com.example.tbc_course_18.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_course_18.models.ApartmentsModel
import com.example.tbc_course_18.models.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _apiResponse = MutableStateFlow<List<ApartmentsModel.Content>>(emptyList())
    val apiResponse: StateFlow<List<ApartmentsModel.Content>> get() = _apiResponse


    fun getContent(){
        viewModelScope.launch {
            val response = RetrofitClient.getInformation().getInfo()
            if (response.isSuccessful){
                val body:ApartmentsModel? = response.body()
                Log.d("response", "$body")
                _apiResponse.value = body?.content ?: emptyList()
            }else{
                val error = response.errorBody()
                Log.d("errorResponse", "$error")
            }
        }
    }
}