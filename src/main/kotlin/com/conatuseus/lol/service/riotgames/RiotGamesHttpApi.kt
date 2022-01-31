package com.conatuseus.lol.service.riotgames

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotGamesHttpApi {
    @GET("lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummonerBySummonerName(@Path("summonerName") summonerName: String): Call<SummonerResponse>

    @GET("lol/summoner/v4/summoners/by-puuid/{puuid}")
    fun getSummonerByPUUID(@Path("puuid") puuid: String): Call<SummonerResponse>

    @GET("lol/summoner/v4/summoners/{summonerId}")
    fun getSummonerBySummonerId(@Path("summonerId") summonerId: String): Call<SummonerResponse>
}
