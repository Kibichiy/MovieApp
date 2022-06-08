package com.example.a7junedisneyappcoroutines.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a7junedisneyappcoroutines.model.Repository

class ViewModelFactory(private var repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repository)
    }
}