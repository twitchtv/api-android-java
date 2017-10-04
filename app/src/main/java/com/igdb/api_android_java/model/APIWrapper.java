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
    }

    public enum Endpoint{
        CHARACTERS, COLLECTIONS, COMPANIES, CREDITS, FEEDS, FRANCHISES, GAME_ENGINES, GAME_MODES, GAMES,
        GENRES, KEYWORDS, PAGES, PEOPLE, PLATFORMS, PLAYER_PERSPECTIVES, PULSE_GROUPS,
        PULSE_SOURCES, PULSES, RELEASE_DATES, REVIEWS, THEMES, TITLES
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
     * This method returns a JSONArray with the specified data requested,
     * with this method you can request any kind of data from the IGDB API using custom urls.
     *
     * @param url               The url stands for the query, except for the standard 3Scale url,
     *                          where the requested data is specified.
     * @param customHeaders     Add custom headers
     * @param callback          The callback return the response from the server in the form of a JSONArray
     *
     *
     **/
    public void getJSONArray(String url, final Map<String, String> customHeaders, final onSuccessCallback callback){
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
                Map<String, String> headers = customHeaders;
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
     * Search the IGDB API for information
     *
     *
     * @param endpoint  Apply for which Endpoint to search in.
     * @param parameters      Args are the arguments, Ex: search query, fields, order etc.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * **/
    public void search(Endpoint endpoint, Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(endpoint), new onSuccessCallback() {
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
     * @param parameters    The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]")
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void games(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.GAMES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulses(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PULSES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void characters(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.CHARACTERS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void collections(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.COLLECTIONS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void companies(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.COMPANIES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void franchises(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.FRANCHISES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void feeds(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.FEEDS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pages(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PAGES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result.
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void gameEngines(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.GAME_ENGINES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void gameModes(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.GAME_MODES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void genres(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.GENRES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void keywords(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.KEYWORDS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void people(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PEOPLE), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void platforms(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PLATFORMS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void playerPerspectives(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PLAYER_PERSPECTIVES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void releaseDates(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.RELEASE_DATES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result, Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulseGroups(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PULSE_GROUPS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result,  Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void pulseSources(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.PULSE_SOURCES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result,  Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void themes(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.THEMES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result,  Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void reviews(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.REVIEWS), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result,  Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void titles(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.TITLES), new onSuccessCallback() {
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
     * @param parameters      The arguments added to specify the result,  Ex parameters.addFilter("[cover][exists]").
     * @param callback  Callback which gets activated as soon as the JSONArray is returned from the
     *                  API.
     * */
    public void credits(Parameters parameters, final onSuccessCallback callback){
        getJSONArray(parameters.buildQuery(Endpoint.CREDITS), new onSuccessCallback() {
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