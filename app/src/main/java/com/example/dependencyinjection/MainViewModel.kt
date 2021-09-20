package com.example.dependencyinjection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjection.dataclass.UsersDataClassItem
import com.example.dependencyinjection.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) :ViewModel() {
    private val usersInMutableLiveData = MutableLiveData<List<UsersDataClassItem>>()
    val usersInLiveData: LiveData<List<UsersDataClassItem>>
        get() = usersInMutableLiveData

    init {
        getUsersInMainViewModel()

    }

    private fun getUsersInMainViewModel()  = viewModelScope.launch {
        repository.getUsersInRepository().let { response ->
            if (response.isSuccessful){
                usersInMutableLiveData.postValue(response.body())

            }
            else{
                Log.d("TAG", response.code().toString())
            }
        }
    }
}