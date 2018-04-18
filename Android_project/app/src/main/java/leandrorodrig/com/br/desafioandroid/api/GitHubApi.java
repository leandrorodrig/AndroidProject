package leandrorodrig.com.br.desafioandroid.api;

import leandrorodrig.com.br.desafioandroid.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApi {

    public static GitHubService createGitHubService(String url) {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    return retrofit.create(GitHubService.class);

    }
}
