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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by captainawesome on 2017-09-15.
 */

public class GamesTest {

    private Context context;
    private APIWrapper wrapper;


    public void setUp() throws InterruptedException {
        context = InstrumentationRegistry.getContext();
        String key = System.getProperty("API_KEY");
        wrapper = new APIWrapper(context, key);

    }

    @Test
    public void testSingleGames() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "1942");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);
                    int testId = object.getInt("id");
                    assertEquals(1942, testId);
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
    public void testMultipleGames() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "27193,23212,1942");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object1 = result.getJSONObject(0);
                    JSONObject object2 = result.getJSONObject(1);
                    JSONObject object3 = result.getJSONObject(2);

                    assertEquals(27193, object1.getInt("id"));
                    assertEquals(23212, object2.getInt("id"));
                    assertEquals(1942, object3.getInt("id"));

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
    public void testOrder() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.FIELDS, "name");
        args.put(APIWrapper.Operator.ORDER, "popularity:desc");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);

                    assertEquals(25657, object.getInt("id"));

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
    public void testSingleField() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "42");
        args.put(APIWrapper.Operator.FIELDS, "games");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);

                    assertTrue((object.length() == 2));

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
    public void testMultipleFields() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "42");
        args.put(APIWrapper.Operator.FIELDS, "games,name");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);

                    assertNotNull(object);
                    assertTrue((object.length() == 3));

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
    public void testGameSearchMultiAndSingle() throws InterruptedException {
        setUp();
        Map<APIWrapper.Operator,String> args = new HashMap<>();
        args.put(APIWrapper.Operator.SEARCH, "battlefield 1");
        args.put(APIWrapper.Operator.FIELDS, "name");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.search(APIWrapper.Endpoint.GAMES, args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject object = result.getJSONObject(0);

                    assertNotNull(object);
                    assertEquals(18320, object.getInt("id"));

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
