package leandrorodrig.com.br.desafioandroid.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import leandrorodrig.com.br.desafioandroid.config.ConfigUtils;
import leandrorodrig.com.br.desafioandroid.model.Item;

public class ReposViewModel extends ViewModel {

    public LiveData<PagedList<Item>> repoList;
    public LiveData<NetworkState> networkState;
    Executor executor;
//    LiveData<ItemKeyedReposDataSource> itemDataSource;

    public ReposViewModel() {
        executor = Executors.newFixedThreadPool(3);
        GitHubReposDataSourceFactory gitHubReposDataSourceFactory = new GitHubReposDataSourceFactory();

//        itemDataSource = gitHubReposDataSourceFactory.getMutableLiveData();


        networkState = Transformations.switchMap(gitHubReposDataSourceFactory.getMutableLiveData(), dataSource -> {
            return dataSource.getNetworkState();
        });

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(ConfigUtils.PER_PAGE)
                        .setPageSize(ConfigUtils.PER_PAGE).build();

        repoList = (new LivePagedListBuilder(gitHubReposDataSourceFactory, pagedListConfig))
                .setBackgroundThreadExecutor(executor)
                .build();
    }

}
