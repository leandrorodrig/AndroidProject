package leandrorodrig.com.br.desafioandroid.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import java.util.concurrent.Executor;

public class GitHubReposDataSourceFactory implements DataSource.Factory  {

    MutableLiveData<ItemKeyedReposDataSource> mutableLiveData;
    ItemKeyedReposDataSource itemKeyedReposDataSource;
    //Executor executor;

    public GitHubReposDataSourceFactory() {
        this.mutableLiveData = new MutableLiveData<ItemKeyedReposDataSource>();
//        this.executor = executor;
    }

    @Override
    public DataSource create() {
        itemKeyedReposDataSource = new ItemKeyedReposDataSource();
        mutableLiveData.postValue(itemKeyedReposDataSource);
        return itemKeyedReposDataSource;
    }

    public MutableLiveData<ItemKeyedReposDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
