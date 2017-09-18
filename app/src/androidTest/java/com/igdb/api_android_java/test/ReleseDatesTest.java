package com.igdb.api_android_java.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;

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
 * Created by captainawesome on 2017-09-18.
 */

public class ReleseDatesTest {

    private Context context;
    private APIWrapper wrapper;


    public void setUp() throws InterruptedException {
        context = InstrumentationRegistry.getContext();
        String key = System.getProperty("API_KEY");
        wrapper = new APIWrapper(context, key);

    }

    @Test
    public void testSingleReleaseDate() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "86653");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
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
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "86660,86663,15683");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
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
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.FILTER, "[platform][eq]=48");
        args.put(APIWrapper.Operator.FIELDS, "game");
        args.put(APIWrapper.Operator.ORDER, "date:asc");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
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
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.FILTER, "[platform][eq]=48");
        args.put(APIWrapper.Operator.FILTER, "[date][gt]=1501586921000");
        args.put(APIWrapper.Operator.FIELDS, "game");
        args.put(APIWrapper.Operator.ORDER, "date:asc");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("id");
                    assertEquals(100998, testId);

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
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "86653");
        args.put(APIWrapper.Operator.EXPAND, "game");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    JSONObject game = object.getJSONObject("game");
                    int testId = game.getInt("id");
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
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "86653");
        args.put(APIWrapper.Operator.EXPAND, "game,platform");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
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
