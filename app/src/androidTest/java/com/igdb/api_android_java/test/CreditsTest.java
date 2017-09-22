package com.igdb.api_android_java.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by captainawesome on 2017-09-15.
 */

public class CreditsTest {

    private Context context;
    private APIWrapper wrapper;


    public void setUp() throws InterruptedException {
        context = InstrumentationRegistry.getContext();
        String key = System.getProperty("API_KEY");
        wrapper = new APIWrapper(context, key);

    }

    @Test
    public void testSingleCredits() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("1073917668");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.credits(parameters, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject credits = result.getJSONObject(0);
                    int testId = credits.getInt("id");
                    assertEquals(1073917668, testId);
                } catch (JSONException e) {
                    e.printStackTrace();
                    fail("JSONException!!");
                }
            }

            @Override
            public void onError(VolleyError error) {
                fail("Volley Error!!");
            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);

    }

    @Test
    public void testMultipleCredits() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("1073917668,1073917629,1073917617");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.credits(parameters, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject credit1 = result.getJSONObject(0);
                    JSONObject credit2 = result.getJSONObject(1);
                    JSONObject credit3 = result.getJSONObject(2);

                    assertEquals(1073917668, credit1.getInt("id"));
                    assertEquals(1073917629, credit2.getInt("id"));
                    assertEquals(1073917617, credit3.getInt("id"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    fail("JSONException!!");
                }
            }

            @Override
            public void onError(VolleyError error) {
                fail("Volley Error!!");
            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);

    }
}
