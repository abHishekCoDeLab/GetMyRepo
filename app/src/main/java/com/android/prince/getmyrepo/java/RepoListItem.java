package com.android.prince.getmyrepo.java;

/**
 * Created by prince on 19/3/18.
 */

public class RepoListItem {
    private String name;
    private String ownerName;
    private String star;
    private String fork;
    private String view;
    private String lang;
    private String url;
    private String imageUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public void setFork(String fork) {
        this.fork = fork;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStar() {
        return star;
    }

    public String getFork() {
        return fork;
    }

    public String getView() {
        return view;
    }

    public String getLang() {
        return lang;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }


}
