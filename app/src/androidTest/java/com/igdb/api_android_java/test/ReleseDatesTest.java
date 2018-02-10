package com.igdb.api_android_java.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.OnSuccessCallback;
import com.igdb.api_android_java.wrapper.IGDBWrapper;
import com.igdb.api_android_java.wrapper.Parameters;
import com.igdb.api_android_java.wrapper.Version;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by captainawesome on 2017-09-18.
 */

public class ReleseDatesTest {

    private Context context;
    private IGDBWrapper wrapper;


    public void setUp() throws InterruptedException {
        context = InstrumentationRegistry.getContext();
        String key = System.getProperty("API_KEY");
        wrapper = new IGDBWrapper(context, key, Version.PRO, true);

    }

    @Test
    public void testSingleReleaseDate() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("86653");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("id");
                    assertEquals(86653, testId);
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
    public void testMultipleReleaseDates() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("86660,86663,15683");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object1 = result.getJSONObject(0);
                    JSONObject object2 = result.getJSONObject(1);
                    JSONObject object3 = result.getJSONObject(2);

                    assertEquals(86660, object1.getInt("id"));
                    assertEquals(86663, object2.getInt("id"));
                    assertEquals(15683, object3.getInt("id"));

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
    public void testSingleFiltersReleaseDates() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addFilter("[platform][eq]=48")
                .addFields("game")
                .addOrder("date:asc");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("id");
                    assertEquals(44949, testId);

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
    public void testMultipleFiltersReleaseDates() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addFilter("[platform][eq]=48")
                .addFilter("[date][gt]=1501586921000")
                .addFields("game")
                .addOrder("date:asc");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("id");
                    assertEquals(99334, testId);

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
    public void testSingleExpander() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("86653")
                .addFields("game");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("game");
                    assertEquals(38722, testId);

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
    public void testMultipleExpander() throws InterruptedException {
        setUp();
        Parameters parameters = new Parameters()
                .addIds("86653")
                .addExpand("game,platform");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(parameters, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    JSONObject game = object.getJSONObject("game");
                    JSONObject platform = object.getJSONObject("platform");
                    int testId = game.getInt("id");
                    int testId2 = platform.getInt("id");
                    assertEquals(38722, testId);
                    assertEquals(34, testId2);

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
