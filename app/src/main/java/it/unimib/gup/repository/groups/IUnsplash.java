package it.unimib.gup.repository.groups;

import java.util.List;

import it.unimib.gup.model.responses.UnsplashResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface Unsplash {
    @GET("/photos/random?count=1&query=books&orientation=landscape")
    Call<List<UnsplashResponse>> randomImage(@Query("client_id") String authorization);
}