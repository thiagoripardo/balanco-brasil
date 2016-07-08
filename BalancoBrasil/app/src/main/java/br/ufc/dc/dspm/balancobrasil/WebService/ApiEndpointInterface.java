package br.ufc.dc.dspm.balancobrasil.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndpointInterface {
    String BASE_URL = "http://ec2-52-24-201-142.us-west-2.compute.amazonaws.com:80/";

    /* @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);*/


    @POST("/query")
    Call<List<List<String>>> getQuery(@Body Query query);

    @POST("/feed")
    Call<Integer> getFeed(@Body QueryFeed queryFeed);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
}

