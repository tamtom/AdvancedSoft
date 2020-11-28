package com.google.advancedsoft.network

import retrofit2.http.GET

interface DashBoardServiceAPI {
    @GET("E4VW")
    suspend fun getHomeResponse(): HomeResponse

}