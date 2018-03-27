package com.android.prince.getmyrepo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.prince.getmyrepo.R;
import com.android.prince.getmyrepo.RecyclerItemClickListener;
import com.android.prince.getmyrepo.ShowDetail;
import com.android.prince.getmyrepo.adapter.RepoListAdapter;
import com.android.prince.getmyrepo.java.RepoListItem;
import com.android.prince.getmyrepo.network.LoadData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RepoList extends Fragment{

    Context context;

    RecyclerView recyclerView;

    private String search = "";
    private String sortBy = "";

    private ArrayList<RepoListItem> myRepoList = new ArrayList<>();

    public RepoList() {

    }

    public void getSearch(String search,String sortBy){
        this.search = search;
        this.sortBy = sortBy;
    }

    public void getContextMain(Context context){
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView)getView().findViewById(R.id.RepoListRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        makeGithubQuery(search);

        final Context cont = getActivity();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(cont,ShowDetail.class);
                        intent.putExtra("OWNERNAME",myRepoList.get(position).getOwnerName());
                        intent.putExtra("NAME",myRepoList.get(position).getName());
                        intent.putExtra("VIEW",myRepoList.get(position).getView());
                        intent.putExtra("STAR",myRepoList.get(position).getStar());
                        intent.putExtra("LANG",myRepoList.get(position).getLang());
                        intent.putExtra("FORK",myRepoList.get(position).getFork());
                        intent.putExtra("URL",myRepoList.get(position).getUrl());
                        intent.putExtra("IMAGE",myRepoList.get(position).getImageUrl());

                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }


    public void makeGithubQuery(String str){

        URL githubSearchUrl = LoadData.buildUrl(str,sortBy);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = urls[0];

            String githubSearchResults = null;

            try {
                githubSearchResults = LoadData.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject repoList = jsonArray.getJSONObject(i);
                    JSONObject ownerInfo = repoList.getJSONObject("owner");

                    String  nameStr = null;
                    String ownerStr = null;
                    String forkStr = null;
                    String viewStr = null;
                    String starStr = null;
                    String langStr = null;
                    String url = null;
                    String imageUrl = null;

                    try {

                        nameStr = repoList.getString("name");
                        ownerStr = ownerInfo.getString("login");
                        langStr = repoList.getString("language");
                        forkStr = repoList.getString("forks_count");
                        viewStr = repoList.getString("watchers_count");
                        starStr = repoList.getString("stargazers_count");
                        url = repoList.getString("html_url");
                        imageUrl = ownerInfo.getString("avatar_url");

                    } catch (Exception e) {

                    }

                    if (nameStr != null && ownerStr != null && forkStr != null && viewStr != null && starStr != null && langStr != null && url != null) {

                        RepoListItem repoListItem = new RepoListItem();
                        repoListItem.setFork(forkStr);
                        repoListItem.setLang(langStr);
                        repoListItem.setName(nameStr);
                        repoListItem.setStar(starStr);
                        repoListItem.setUrl(url);
                        repoListItem.setOwnerName(ownerStr);
                        repoListItem.setView(viewStr);
                        repoListItem.setImageUrl(imageUrl);

                        myRepoList.add(repoListItem);
                    }
                }

                RepoListAdapter repoListAdapter = new RepoListAdapter(context,myRepoList);
                recyclerView.setAdapter(repoListAdapter);

            }catch (Exception e){

            }
        }
    }
}
