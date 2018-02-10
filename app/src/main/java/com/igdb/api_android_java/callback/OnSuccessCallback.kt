package com.igdb.api_android_java.callback

import com.android.volley.VolleyError
import org.json.JSONArray

/**
 * Wrapper.OnSuccessCallback updated to Kotlin from java
 * Callback used to retrieve data from wrapper
 *
 * Created at: 2017-09-12
 * Updated at: 2018-02-10
 *
 * Created by Filip
 *
 */
interface OnSuccessCallback {
    fun onSuccess(result: JSONArray)
    fun onError(error: VolleyError)
}
