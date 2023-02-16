package com.example.myapplicationdoctor;

import com.example.myapplicationdoctor.model.Root;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DoctorsRetrofitApi {
    public interface DoctorsRetrofitService {
        @GET("search/v4/place")
        Call<Root> getRoot(@Query("location")Double location, @Query("sort")String sort, @Query("feedback")boolean feedback,@Query("key")String key,@Query("q")String q);
    }
   // private final static String BASE_URL="https://api.themoviedb.org/3/";

    private final static String BASE_URL="https://www.mapquestapi.com/search/v4/place?";

    private DoctorsRetrofitApi(){}
    private static DoctorsRetrofitApi INSTANCE = null;
    public static DoctorsRetrofitApi getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DoctorsRetrofitApi();
        }
        return INSTANCE;
    }

    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
