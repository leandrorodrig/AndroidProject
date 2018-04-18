package leandrorodrig.com.br.desafioandroid.api;


import java.util.List;

import leandrorodrig.com.br.desafioandroid.model.GitHubReposJava;
import leandrorodrig.com.br.desafioandroid.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("/search/repositories")
    Call<GitHubReposJava> getRepositiries(@Query("q") String language, @Query("sort") String sort, @Query("page") long page, @Query("per_page") int perPage);
}