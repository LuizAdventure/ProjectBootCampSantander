package com.example.projectbootcampsantander.data;

import com.example.projectbootcampsantander.domain.Match;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {
    @GET("partidas.json")
    Call<List<Match>> getMatches();
}
