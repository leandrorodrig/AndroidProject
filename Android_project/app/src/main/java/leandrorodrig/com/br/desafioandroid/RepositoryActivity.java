package leandrorodrig.com.br.desafioandroid;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import leandrorodrig.com.br.desafioandroid.model.GitHubReposJava;
import leandrorodrig.com.br.desafioandroid.model.Item;
import leandrorodrig.com.br.desafioandroid.repository.ListItemClickListener;
import leandrorodrig.com.br.desafioandroid.repository.ReposAdapter;
import leandrorodrig.com.br.desafioandroid.repository.ReposViewModel;
import leandrorodrig.com.br.desafioandroid.adapter.*;

public class RepositoryActivity extends AppCompatActivity {//implements ListItemClickListener {

    private ReposViewModel viewModel;
    private String TAG = "RepositoryActivity";
    @BindView(R.id.lstRepo) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);
//        RecyclerView recyclerView = findViewById(R.id.lstRepo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration  mDividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        viewModel = ViewModelProviders.of(this).get(ReposViewModel.class);

//        final ReposAdapter reposAdapter = new ReposAdapter(this);
           final ItemAdapter itemAdapter = new ItemAdapter();

              viewModel.repoList.observe(this, pagedList -> {
            itemAdapter.setList(pagedList);
        });

//        viewModel.repoList.observe(this, pagedList -> {
//            reposAdapter.setList(pagedList);
//        });

//        viewModel.networkState.observe(this, networkState -> {
//            reposAdapter.setNetworkState(networkState);
//            Log.d(TAG, "Network State Change");
//        });

        recyclerView.setAdapter(itemAdapter);

    }

//    @Override
//    public void onRetryClick(View view, int position) {
//        Log.d(TAG, "Position " + position);
//    }
}
