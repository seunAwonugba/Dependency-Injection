package com.example.dependencyinjection

import com.example.dependencyinjection.Constants.USERS_END_POINT
import com.example.dependencyinjection.dataclass.UsersDataClassItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiUsersInterface {
    @GET(USERS_END_POINT)
    suspend fun getUsersInInterface():Response<List<UsersDataClassItem>>
}