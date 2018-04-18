package leandrorodrig.com.br.desafioandroid.repository;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import leandrorodrig.com.br.desafioandroid.R;
import leandrorodrig.com.br.desafioandroid.model.Item;

public class ReposAdapter extends PagedListAdapter<Item, RecyclerView.ViewHolder> {

    private static final String TAG = "ReposAdapter";
//    private NetworkState networkState;
    private ListItemClickListener itemClickListener;

    public ReposAdapter(ListItemClickListener itemClickListener) {
        super(Item.DiffCallback);
        this.itemClickListener = itemClickListener;
       }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == R.layout.item_repo_act) {
            view = layoutInflater.inflate(R.layout.item_repo_act, parent, false);
            return new ReposItemViewHolder(view);
//        } else if (viewType == R.layout.network_state_item) {
//            view = layoutInflater.inflate(R.layout.network_state_item, parent, false);
//            return new NetworkStateItemViewHolder(view, itemClickListener);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_repo_act:
                ((ReposItemViewHolder) holder).bindTo(getItem(position));
                break;
//            case R.layout.network_state_item:
//                ((NetworkStateItemViewHolder) holder).bindView(networkState);
//                break;
        }
    }

//    private boolean hasExtraRow() {
//        if (networkState != null && networkState != NetworkState.LOADED) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public int getItemViewType(int position) {
//        if (hasExtraRow() && position == getItemCount() - 1) {
//            return R.layout.network_state_item;
//        } else {
//            return R.layout.item_repo_act;
//        }

          return R.layout.item_repo_act;
          }

//    public void setNetworkState(NetworkState newNetworkState) {
//        NetworkState previousState = this.networkState;
//        boolean previousExtraRow = hasExtraRow();
//        this.networkState = newNetworkState;
//        boolean newExtraRow = hasExtraRow();
//        if (previousExtraRow != newExtraRow) {
//            if (previousExtraRow) {
//                notifyItemRemoved(getItemCount());
//            } else {
//                notifyItemInserted(getItemCount());
//            }
//        } else if (newExtraRow && previousState != newNetworkState) {
//            notifyItemChanged(getItemCount() - 1);
//        }
//    }

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

//    static class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {
//
////        private final ProgressBar progressBar;
////        private final TextView errorMsg;
////        private Button button;
//
//        @BindView(R.id.progress_bar) ProgressBar progressBar;
//        @BindView(R.id.error_msg) TextView errorMsg;
//        @BindView(R.id.retry_button) Button button;
//
//        public NetworkStateItemViewHolder(View itemView, ListItemClickListener listItemClickListener) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
////            progressBar = itemView.findViewById(R.id.progress_bar);
////            errorMsg = itemView.findViewById(R.id.error_msg);
////            button = itemView.findViewById(R.id.retry_button);
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listItemClickListener.onRetryClick(view, getAdapterPosition());
//                }
//            });
//        }
//
//
//        public void bindView(NetworkState networkState) {
//            if (networkState != null && networkState.getStatus() == Status.RUNNING) {
//                progressBar.setVisibility(View.VISIBLE);
//            } else {
//                progressBar.setVisibility(View.GONE);
//            }
//
//            if (networkState != null && networkState.getStatus() == Status.FAILED) {
//                errorMsg.setVisibility(View.VISIBLE);
//                errorMsg.setText(networkState.getMsg());
//            } else {
//                errorMsg.setVisibility(View.GONE);
//            }
//        }
//    }
}
