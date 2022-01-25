package com.vaithidroid.appone.designstringtask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaithidroid.appone.designstringtask.adapter.UsersAdapter
import com.vaithidroid.appone.designstringtask.databinding.ActivityMainBinding
import com.vaithidroid.appone.designstringtask.ui.viewmodel.MainViewModel
import com.vaithidroid.appone.designstringtask.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val adapter by lazy { UsersAdapter() }
    private var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        linearLayoutManager = LinearLayoutManager(this)
        setupRecyclerView()
        getApiData()

        binding.btnLoadMore.setOnClickListener {
            pageNumber++
            getApiData()
        }

    }


    private fun setupRecyclerView() {
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = linearLayoutManager
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun getApiData(){
        mainViewModel.getUsers(pageNumber)
        mainViewModel.userResponse.observe(this, {response->
            when(response){
                is NetworkResult.Success ->{
                    binding.progressBar.visibility = View.INVISIBLE
                    response.data?.let {usersResponse->
                        adapter.differ.submitList(usersResponse.data.toList())
                    }
                }
                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }



}