package com.igdb.api_android_java.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.igdb.api_android_java.callback.onSuccessCallback;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by captainawesome on 2017-09-12.
 */

public class APIWrapper {

    private static RequestQueue requestQueue;
    private static final String API_URL = "https://api-2445582011268.apicast.io/";
    private static final String API_Header = "user-key";
    private static String API_KEY = "";
    private static String YOUTUBE_KEY = "";

    public APIWrapper(Context context, String API_KEY){
        this.requestQueue = Volley.newRequestQueue(context);
        this.API_KEY = API_KEY;
        Log.d("KEY", "key: " + API_KEY);
    }

    public enum Endpoint{
        CHARACTERS, COLLECTIONS, COMPANIES, CREDITS, FEEDS, FRANCHISES, GAME_ENGINES, GAME_MODES, GAMES,
        GENRES, KEYWORDS, PAGES, PEOPLE, PLATFORMS, PLAYER_PERSPECTIVES, PULSE_GROUPS,
        PULSE_SOURCES, PULSES, RELEASE_DATES, REVIEWS, THEMES, TITLES
    }
    public enum Operator{
        IDS, FIELDS, EXPAND, LIMIT, OFFSET, ORDER, SEARCH, SCROLL, FILTER
    }

    /**
     * This method returns a JSONArray with the specified data requested,
     * with this method you can request any kind of data from the IGDB API using custom urls.
     *
     * @param url       The url stands for the query, except for the standard 3Scale url,
     *                  where the requested data is specified.
     * @param callback  The callback return the response from the server in the form of a JSONArray
     *
     *
     **/
    public void getJSONArray(String url, final onSuccessCallback callback){
        String uri = Uri.parse(API_URL + url).buildUpon().build().toString();

        JsonArrayRequest request = new JsonArrayRequest(uri, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(API_Header, API_KEY);
                headers.put("Accept", "text/plain");
                return headers;
            }
        };
        requestQueue.add(request);



        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    /**
     *  querybuilder is an inner private class that builds the query for the request based on the
     *  Map parameter args
     *
     *  @param args     Map containing the operator and the string ex: /games/
     *
     *  @param endpoint The endpoint for which the arguments should apply for
     *                  ex: /games/
     *
     **/
    private String queryBuilder(Endpoint endpoint, Map<Operator, String> args){

        String ids, fields, expand, limit, offset, order, search, scroll, filter, query;
        ids = fields = expand = limit = offset = order = search = scroll = filter = query = "";


        Set<Operator> keySet = args.keySet();
        for (Operator operator : keySet) {
            switch (operator){
                case IDS: ids = args.get(Operator.IDS).replaceAll(" ", "");
                    break;
                case FIELDS:  fields = "fields=" + args.get(Operator.FIELDS).replaceAll(" ", "");
                    break;
                case EXPAND: expand = "&expand=" + args.get(Operator.EXPAND).replaceAll(" ", "");
                    break;
                case LIMIT: limit = "&limit=" + args.get(Operator.LIMIT).replaceAll(" ", "");
                    break;
                case OFFSET: offset = "&offset=" + args.get(Operator.OFFSET).replaceAll(" ", "");
                    break;
                case ORDER: order = "&order=" + args.get(Operator.ORDER).replaceAll(" ", "");
                    break;
                case SEARCH: search = "?search=" + args.get(Operator.SEARCH).replaceAll(" ", "%20");
                    break;
                case SCROLL: scroll = "&scroll=" + args.get(Operator.SCROLL).replaceAll(" ", "");
                    break;
                case FILTER: filter = args.get(Operator.FILTER).replaceAll(" ", "");
                    break;
            }
        }

        query = endpoint.toString().toLowerCase();

        query += "/" + ids + search;

        if (!fields.isEmpty() && !search.isEmpty()){
            query += "&" + fields;
        }else if (!fields.isEmpty()){
            query += "?" + fields;
        }

        query += filter + expand + order + limit + offset + scroll;

        Log.d("Query", query);

        return query;
    }

    /**
     * Search the IGDB API for information
     *
     *
     * @param endpoint  Apply for which Endpoint to search in.
     * @param args      Args are the arguments, Ex: search query, fields, order etc.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * **/
    public void search(Endpoint endpoint, Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(endpoint, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {

                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Games method returns a JSONArray containing the game information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void games(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.GAMES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Pulse method returns a JSONArray containing the pulse information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulses(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PULSES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Characters method returns a JSONArray containing the character information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void characters(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.CHARACTERS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Collections method returns a JSONArray containing the collection information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void collections(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.COLLECTIONS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Companies method returns a JSONArray containing the company information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void companies(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.COMPANIES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Franchises method returns a JSONArray containing the franchise information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void franchises(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.FRANCHISES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Feed method returns a JSONArray containing the feed information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void feeds(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.FEEDS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Pages method returns a JSONArray containing the page information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pages(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PAGES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * GameEngine method returns a JSONArray containing the game engine information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void gameEngines(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.GAME_ENGINES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * GameModes method returns a JSONArray containing the game mode information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void gameModes(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.GAME_MODES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Genres method returns a JSONArray containing the genre information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void genres(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.GENRES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Keywords method returns a JSONArray containing the keyword information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void keywords(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.KEYWORDS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * People method returns a JSONArray containing the people information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void people(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PEOPLE, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Platforms method returns a JSONArray containing the platform information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void platforms(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PLATFORMS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * PlayerPerspectives method returns a JSONArray containing the player perspective information
     * requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void playerPerspectives(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PLAYER_PERSPECTIVES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * ReleaseDates method returns a JSONArray containing the release date information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void releaseDates(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.RELEASE_DATES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * PulseGroups method returns a JSONArray containing the pulse group information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulseGroups(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PULSE_GROUPS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * PulseSources method returns a JSONArray containing the pulse source information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulseSources(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.PULSE_SOURCES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Themes method returns a JSONArray containing the theme information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void themes(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.THEMES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Reviews method returns a JSONArray containing the review information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void reviews(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.REVIEWS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Titles method returns a JSONArray containing the title information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void titles(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.TITLES, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Credits method returns a JSONArray containing the credit information requested.
     *
     * @param args      The arguments added to specify the result, Ex: map<FIELDS, "id,name">
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void credits(Map<Operator, String> args, final onSuccessCallback callback){
        getJSONArray(queryBuilder(Endpoint.CREDITS, args), new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(VolleyError error) {
                callback.onError(error);
            }
        });
    }


}