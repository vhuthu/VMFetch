package com.vhuthu.until

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {

    private val _todoList = mutableStateListOf<FarmerResponse>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<FarmerResponse>
        get() = _todoList

    val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcUBnbWFpbC5jb20iLCJyb2xlcyI6IlJPTEVfRkFSTUVSIiwiaWF0IjoxNjk0NDIwMTU5LCJleHAiOjE2OTgwMjAxNTl9.NeFnAd1JCe3wcZQmpWiWXeLzFsPsWf9qSqgRZbhzaJE"
    fun getTodoList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll(listOf(apiService.getTodos(token = "Bearer $token")))
                // Log each element in _todoList
                for (todo in _todoList) {
                    Log.d("Success A", todo.toString())
                }
               // Log.d("Success z", _todoList.toString())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("Tag",errorMessage)
            }
        }
    }
}