package it.unimib.gup.repository.groups;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

import it.unimib.gup.model.responses.UnsplashResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

interface Unsplash {
    @GET("/photos/random?count=1&query=study,focus&orientation=landscape")
    Call<List<UnsplashResponse>> randomImage(@Query("client_id") String authorization);
}