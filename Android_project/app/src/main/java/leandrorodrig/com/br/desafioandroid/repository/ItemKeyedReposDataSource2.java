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
import leandrorodrig.com.br.desafioandroid.model.GitHubReposJava;
import leandrorodrig.com.br.desafioandroid.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemKeyedReposDataSource2 extends ItemKeyedDataSource<Long, Item> {

    public static final String TAG = "ItemKeyedReposDataSource";
    GitHubService gitHubService;
    LoadInitialParams<Long> initialParams;
    LoadParams<Long> afterParams;
    private MutableLiveData networkState;
    private MutableLiveData initialLoading;
    private Executor retryExecutor;
    public static final int PER_PAGE = 10;

    public ItemKeyedReposDataSource2(Executor retryExecutor) {
        gitHubService = GitHubApi.createGitHubService("https://api.github.com");
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        this.retryExecutor = retryExecutor;
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Item> callback) {

        Log.i(TAG, "loadInitial Rang " + 1 + " Count " + params.requestedLoadSize);
        final List<Item> gitHubRepos = new ArrayList();
        initialParams = params;
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        gitHubService.getRepositiries("language:Java", "stars", 1, params.requestedLoadSize)
                .enqueue(new Callback<GitHubReposJava>() {
            @Override
            public void onResponse(Call<GitHubReposJava> call, Response<GitHubReposJava> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    gitHubRepos.addAll(response.body().getItems());
                    callback.onResult(gitHubRepos);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                    initialParams = null;
                } else {
                    Log.e("API CALL", response.message());
                    initialLoading.postValue(new NetworkState(Status.FAILED, response.message()));
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

        Log.i(TAG, "loadAfter Rang " + params.key + " Count " + params.requestedLoadSize);
        final List<Item> gitHubRepos = new ArrayList();
        afterParams = params;
        networkState.postValue(NetworkState.LOADING);

        gitHubService.getRepositiries("language:Java", "stars", params.key, params.requestedLoadSize)
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
