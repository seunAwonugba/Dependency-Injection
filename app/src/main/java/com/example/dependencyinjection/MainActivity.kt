package com.example.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjection.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var instanceOfDataBinding : ActivityMainBinding
    private val instanceOfMainViewModel: MainViewModel by viewModels()
    private lateinit var instanceOfAdapter: ProjectAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instanceOfDataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(instanceOfDataBinding.root)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        instanceOfAdapter = ProjectAdapter()

        instanceOfDataBinding.recyclerViewId.apply {
            adapter = instanceOfAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        instanceOfMainViewModel.usersInLiveData.observe(this, {listOfUsers ->
            instanceOfAdapter.users = listOfUsers

        })
    }

}