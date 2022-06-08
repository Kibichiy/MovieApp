package com.example.a7junedisneyappcoroutines.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7junedisneyappcoroutines.R
import com.example.a7junedisneyappcoroutines.databinding.ActivityMainBinding
import com.example.a7junedisneyappcoroutines.model.Repository
import com.example.a7junedisneyappcoroutines.model.dataclasses.Result
import com.example.a7junedisneyappcoroutines.view.adapter.MoviesAdapter
import com.example.a7junedisneyappcoroutines.viewmodel.MovViewModel
import com.example.a7junedisneyappcoroutines.viewmodel.factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movViewModel: MovViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        movViewModel.getAllMovies()
        setupObserver()

    }

    private fun setupViewModel() {
        repository = Repository()
        viewModelFactory = ViewModelFactory(repository)
        movViewModel = ViewModelProvider(this, viewModelFactory)[MovViewModel::class.java]
    }

    private fun setupObserver() {
        movViewModel.resultAllMovies.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    val movieItemAdapter = MoviesAdapter(baseContext, result.value)
                    binding.rv.adapter = movieItemAdapter
                    binding.rv.layoutManager = LinearLayoutManager(this)
                }

                is Result.Error -> {
                    Snackbar.make(
                        binding.mainConstrainLayout,
                        result.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }

        movViewModel.inProgress.observe(this) { inProgress ->
            when (inProgress) {
                is Result.InProgress -> {
                    if (inProgress.value) {
                        binding.progress.visibility = View.VISIBLE
                    } else
                        binding.progress.visibility = View.GONE
                }
                else -> {}
            }
        }
    }
}


