package leandrorodrig.com.br.desafioandroid.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import leandrorodrig.com.br.desafioandroid.R;
import leandrorodrig.com.br.desafioandroid.model.Item;
import leandrorodrig.com.br.desafioandroid.repository.ReposAdapter;

public class ItemAdapter extends PagedListAdapter<Item, RecyclerView.ViewHolder> {


    public ItemAdapter() {
        super(Item.DiffCallback);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == R.layout.item_repo_act) {
            view = layoutInflater.inflate(R.layout.item_repo_act, parent, false);
            return new ItemAdapter.ReposItemViewHolder(view);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
            case R.layout.item_repo_act:
                ((ReposItemViewHolder) holder).bindTo(getItem(position));
                break;
             }
    }

     @Override
    public int getItemViewType(int position) {
          return R.layout.item_repo_act;
          }

    static class ReposItemViewHolder extends RecyclerView.ViewHolder {
        //        TextView txtNomeRepo, txtDescRepo;
        @BindView(R.id.txtNomeRepo) TextView txtNomeRepo;
        @BindView(R.id.txtDescRepo) TextView txtDescRepo;

        public ReposItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

//            txtNomeRepo = itemView.findViewById(R.id.txtNomeRepo);
//            txtDescRepo = itemView.findViewById(R.id.txtDescRepo);

        }

        public void bindTo(Item item) {
            txtNomeRepo.setText(item.getName());
            txtDescRepo.setText(String.valueOf(item.getDescription()));
        }
    }
}
