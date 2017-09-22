package com.igdb.api_android_java.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by emilasberg on 2017-09-21.
 */

public class Parameters {
    private ArrayList<String> filters = new ArrayList<>();
    private String offset = "";
    private String ids = "";
    private String fields = "";
    private String expand = "";
    private String limit = "";
    private String order = "";
    private String search = "";
    private String scroll = "";
    private String query = "";

    public Parameters addFilter(String filter) {
        filters.add( ("&filter" + filter).replaceAll(" ",""));
        return this;
    }

    public Parameters addIds(String ids){
        this.ids = ids.replaceAll(" ", "");
        return this;
    }

    public Parameters addOffset(String offset){
        this.offset = "&offset=" + offset.replaceAll(" ", "");
        return this;
    }

    public Parameters addFields(String fields){
        this.fields = fields.replaceAll(" ", "");
        return this;
    }

    public Parameters addExpand(String expand) {
        this.expand ="&expand=" + expand.replaceAll(" ", "");
        return this;
    }

    public Parameters addLimit(String limit) {
        this.limit = "&limit=" + limit.replaceAll(" ", "");
        return this;
    }

    public Parameters addOrder(String order){
        this.order = "&order=" + order.replaceAll(" ", "");
        return this;
    }

    public Parameters addSearch(String search){
        this.search= "?search=" + search.replaceAll(" ", "%20");
        return this;
    }

    public Parameters addScroll(String scroll){
        this.scroll = "&scroll=" + scroll.replaceAll(" ", "");
        return this;
    }

    public String buildQuery(APIWrapper.Endpoint endpoint) {
        String filter = "";
        for (String s: filters) {
            filter += s;
        }

        query = endpoint.toString().toLowerCase();
        query += "/" + ids + search;

        if (fields != "" && search != ""){
            query += "&fields=" + fields;
        }else if (!fields.isEmpty()){
            query += "?fields=" + fields;
        }else{
            query += "?fields=*";
        }

        query += filter + expand + order + limit + offset + scroll;
        Log.d("Query", query);
        return query;
    }

}
