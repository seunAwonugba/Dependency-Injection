package com.example.dependencyinjection.repository

import com.example.dependencyinjection.ApiUsersInterface
import javax.inject.Inject

class Repository @Inject constructor(private val instanceOfApiUsersInterface: ApiUsersInterface) {
    suspend fun getUsersInRepository() = instanceOfApiUsersInterface.getUsersInInterface()
}