package com.google.advancedsoft

import com.google.advancedsoft.network.DashBoardServiceAPI

class HomeRepo(private val homeNetworkClient: HomeNetworkClient) {
    suspend fun fetchDashboard() = homeNetworkClient.fetchHomeData()
}

class HomeNetworkClient(private val api: DashBoardServiceAPI) {
    suspend fun fetchHomeData() = api.getHomeResponse()
}