package com.igdb.api_android_java.callback;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by captainawesome on 2017-09-12.
 */

public interface onSuccessCallback {
    void onSuccess(JSONArray result);
    void onError(VolleyError error);
}
