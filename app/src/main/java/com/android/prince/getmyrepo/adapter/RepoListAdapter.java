package com.android.prince.getmyrepo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.prince.getmyrepo.R;
import com.android.prince.getmyrepo.fragment.RepoList;
import com.android.prince.getmyrepo.java.RepoListItem;

import java.util.ArrayList;

/**
 * Created by prince on 18/3/18.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.CustomeRepoListAdapter>{

    ArrayList<RepoListItem> myRepoList = new ArrayList<>();

    Context context;

    public RepoListAdapter(Context context, ArrayList<RepoListItem> myRepoList){

        this.context = context;
        this.myRepoList = myRepoList;
    }


    @Override
    public CustomeRepoListAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item,parent,false);

        return new CustomeRepoListAdapter(view);
    }

    @Override
    public void onBindViewHolder(final CustomeRepoListAdapter holder, final int position) {

        RepoListItem repoListItem = myRepoList.get(position);

        holder.nameView.setText(repoListItem.getName());
        holder.langView.setText(repoListItem.getLang());
        holder.ownerView.setText(repoListItem.getOwnerName());
        holder.viewView.setText(repoListItem.getView());
        holder.forkView.setText(repoListItem.getFork());
        holder.starView.setText(repoListItem.getStar());


    }

    @Override
    public int getItemCount() {
        return myRepoList.size();
    }

    public class CustomeRepoListAdapter extends RecyclerView.ViewHolder{

        private TextView nameView;
        private TextView langView;
        private TextView ownerView;
        private TextView viewView;
        private TextView forkView;
        private TextView starView;

        public CustomeRepoListAdapter(View itemView) {
            super(itemView);

            nameView = (TextView)itemView.findViewById(R.id.RepoItemName);
            langView = (TextView)itemView.findViewById(R.id.RepoItemLang);
            ownerView = (TextView)itemView.findViewById(R.id.RepoItemOwner);
            viewView = (TextView)itemView.findViewById(R.id.ViewRepoList);
            forkView = (TextView)itemView.findViewById(R.id.ForkRepoList);
            starView = (TextView)itemView.findViewById(R.id.StarRepoList);
        }
    }
}
