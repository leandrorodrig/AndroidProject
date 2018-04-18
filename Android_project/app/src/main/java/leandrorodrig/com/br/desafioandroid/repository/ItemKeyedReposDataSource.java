package leandrorodrig.com.br.desafioandroid.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import leandrorodrig.com.br.desafioandroid.api.GitHubApi;
import leandrorodrig.com.br.desafioandroid.api.GitHubService;
import leandrorodrig.com.br.desafioandroid.config.ConfigUtils;
import leandrorodrig.com.br.desafioandroid.model.GitHubReposJava;
import leandrorodrig.com.br.desafioandroid.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static leandrorodrig.com.br.desafioandroid.config.ConfigUtils.PER_PAGE;

public class ItemKeyedReposDataSource extends ItemKeyedDataSource<Long, Item> {

    public static final String TAG = "ItemKeyedReposDataSourc";
    GitHubService gitHubService;
    LoadInitialParams<Long> initialParams;
    LoadParams<Long> afterParams;
    private MutableLiveData networkState;
//    private MutableLiveData initialLoading;
   // private Executor retryExecutor;

    int page=1;

    public ItemKeyedReposDataSource() {

        gitHubService = GitHubApi.createGitHubService(ConfigUtils.BASE_URL);
        networkState = new MutableLiveData();
//        initialLoading = new MutableLiveData();
//        this.retryExecutor = retryExecutor;
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }
//
//    public MutableLiveData getInitialLoading() {
//        return initialLoading;
//    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Item> callback) {

        Log.i(TAG, "loadInitial page " + page + " PER_PAGE " + ConfigUtils.PER_PAGE);
        final List<Item> gitHubRepos = new ArrayList();
        initialParams = params;
//        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        gitHubService.getRepositiries("language:Java", "stars", page, ConfigUtils.PER_PAGE)
                .enqueue(new Callback<GitHubReposJava>() {
            @Override
            public void onResponse(Call<GitHubReposJava> call, Response<GitHubReposJava> response) {
//                if (!response.isSuccessful()){
//                    Log.e(TAG, "Error: " + response.code());
//                }
//                else{
//                    gitHubRepos.addAll(response.body().getItems());
//                    callback.onResult(gitHubRepos);
//                }

                if (response.isSuccessful() && response.code() == 200) {
                    gitHubRepos.addAll(response.body().getItems());
                    callback.onResult(gitHubRepos);
//                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                    initialParams = null;
                } else {
                    Log.e("API CALL", response.message());
//                    initialLoading.postValue(new NetworkState(Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<GitHubReposJava> call, Throwable t) {
                String errorMessage;
                errorMessage = t.getMessage();
                if (t == null) {
                    errorMessage = "unknown error";
                }
                networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });


    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Item> callback) {
        ++page;
        Log.i(TAG, "loadAfter page " + page + " PER_PAGE " + ConfigUtils.PER_PAGE);
        final List<Item> gitHubRepos = new ArrayList();
        afterParams = params;
        networkState.postValue(NetworkState.LOADING);

        gitHubService.getRepositiries("language:Java", "stars", page, ConfigUtils.PER_PAGE)
                .enqueue(new Callback<GitHubReposJava>() {
                    @Override
                    public void onResponse(Call<GitHubReposJava> call, Response<GitHubReposJava> response) {

                        if (response.isSuccessful()) {
                            gitHubRepos.addAll(response.body().getItems());
                            callback.onResult(gitHubRepos);
                            networkState.postValue(NetworkState.LOADED);
                            afterParams = null;
                        } else {
                            networkState.postValue(new NetworkState(Status.FAILED, response.message()));
                            Log.e("API CALL", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<GitHubReposJava> call, Throwable t) {
                        String errorMessage;
                        errorMessage = t.getMessage();
                        if (t == null) {
                            errorMessage = "unknown error";
                        }
                        networkState.postValue(new NetworkState(Status.FAILED, errorMessage));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Item> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull Item item) {
        return item.getId();
    }
}
