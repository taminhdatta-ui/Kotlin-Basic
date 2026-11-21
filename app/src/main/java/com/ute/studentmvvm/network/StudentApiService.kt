package com.ute.studentmvvm.network

import retrofit2.http.GET

/** Interface Retrofit định nghĩa các Endpoint HTTP. */
interface StudentApiService {

    @GET("users")
    suspend fun getStudents(): List<StudentDto>
}
