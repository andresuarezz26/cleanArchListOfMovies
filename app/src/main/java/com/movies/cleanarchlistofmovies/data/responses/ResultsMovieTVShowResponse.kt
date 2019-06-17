package com.movies.cleanarchlistofmovies.data.responses

import com.google.gson.annotations.SerializedName

data class BaseResultsMovieTVShowResponse(val results: List<ResultsMovieTVShowResponse>)
data class ResultsMovieTVShowResponse(
        @SerializedName("vote_count")
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        val title: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        val overview: String?
)
/*
{
    "vote_count": 12,
    "id": 532321,
    "video": false,
    "vote_average": 4.7,
    "title": "Re: Zero kara Hajimeru Isekai Seikatsu - Memory Snow",
    "popularity": 288.706,
    "poster_path": "/xqR4ABkFTFYe8NDJi3knwWX7zfn.jpg",
    "original_language": "ja",
    "original_title": "Re:ゼロから始める異世界生活 Memory Snow",
    "genre_ids": [
    16,
    12
    ],
    "backdrop_path": "/8sNz2DxYiYqGkxk66U8BqvuZZjE.jpg",
    "adult": false,
    "overview": "Subaru and friends finally get a moment of peace, and Subaru goes on a certain secret mission that he must not let anyone find out about! However, even though Subaru is wearing a disguise, Petra and other children of the village immediately figure out who he is. Now that his mission was exposed within five seconds of it starting, what will happen with Subaru's \"date course\" with Emilia?",
    "release_date": "2018-10-06"
}
        */