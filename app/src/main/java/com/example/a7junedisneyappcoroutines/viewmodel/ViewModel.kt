package com.example.a7junedisneyappcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7junedisneyappcoroutines.model.Repository
import com.example.a7junedisneyappcoroutines.model.dataclasses.Data
import com.example.a7junedisneyappcoroutines.model.dataclasses.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovViewModel(private val repository: Repository) : ViewModel() {
    val result = MutableLiveData<List<Data>>()
    val resultAllMovies = MutableLiveData<Result<List<Data>>>()
    val inProgress = MutableLiveData<Result<Boolean>>()
    val error = MutableLiveData<String>()

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllMovies()
                inProgress.postValue(Result.InProgress(true))
                if (response.isSuccessful) {
                    response.body()?.let { moviesResponse ->
                        result.postValue(moviesResponse.data)
                        resultAllMovies.postValue(Result.Success(moviesResponse.data))
                        inProgress.postValue(Result.InProgress(false))
                    }
                } else {
                    error.postValue("Something went wrong, please try again.")
                    resultAllMovies.postValue(
                        Result.Error(
                            "Something went wrong, please try again.",
                            Exception("")
                        )
                    )
                    inProgress.postValue(Result.InProgress(false))
                }
            } catch (e: Exception) {
                inProgress.postValue(Result.InProgress(false))
                error.postValue(e.message.toString())
                resultAllMovies.postValue(Result.Error(e.message, e))
            }
            inProgress.postValue(Result.InProgress(false))
        }
    }
}
