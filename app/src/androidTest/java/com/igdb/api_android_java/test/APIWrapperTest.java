package com.igdb.api_android_java.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.xml.sax.ContentHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by emilasberg on 2017-09-12.
 */
public class APIWrapperTest {

    private Context context;
    private APIWrapper wrapper;

    public void setUp(){
        context = InstrumentationRegistry.getContext();
        wrapper = new APIWrapper(context, "YOUR_API_KEY");
    }

    @Test
    public void search() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.SEARCH, "mass effect");
        args.put(APIWrapper.Operator.FIELDS,"*");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.search(APIWrapper.Endpoint.GAMES, args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject gameObject = result.getJSONObject(0);
                    String name = gameObject.getString("name");
                    String url = gameObject.getString("url");
                    assertThat(url, is("https://www.igdb.com/games/mass-effect"));
                    assertThat(name, is("Mass effect")); //should fail "Mass Effect"

                } catch (JSONException e) {
                    fail("JSONException! " + e.getMessage());
                }
            }

            @Override
            public void onError(VolleyError error) {
                fail("Received Volley error: " + error.getCause());
            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);


    }

    @Test
    public void games() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "12356");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.games(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    lock.countDown();
                    JSONObject jo = result.getJSONObject(0);
                    String s = jo.getString("slug");
                    assertThat(s, is("3d-quasars"));

                } catch (JSONException e) {
                    e.printStackTrace();
                            fail();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("Query", "Error in games: "+ error.getMessage());
                fail();
            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void pulse() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "12342");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.pulses(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("title");
                    assertThat(s, is("A First Look Inside the Sailor Moon Museum Exhibit"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);

    }

    @Test
    public void characters() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.characters(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Commander Shepard (female)"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
        lock.await(20000, TimeUnit.MILLISECONDS);

    }

    @Test
    public void collections() throws Exception {

    }

    @Test
    public void companies() throws Exception {

    }

    @Test
    public void franchises() throws Exception {

    }

    @Test
    public void feed() throws Exception {

    }

    @Test
    public void pages() throws Exception {

    }

    @Test
    public void gameEngines() throws Exception {

    }

    @Test
    public void gameModes() throws Exception {

    }

    @Test
    public void genres() throws Exception {

    }

    @Test
    public void keywords() throws Exception {

    }

    @Test
    public void people() throws Exception {

    }

    @Test
    public void platforms() throws Exception {

    }

    @Test
    public void playerPerspectives() throws Exception {

    }

    @Test
    public void releaseDates() throws Exception {

    }

    @Test
    public void pulseGroups() throws Exception {

    }

    @Test
    public void pulseSources() throws Exception {

    }

    @Test
    public void themes() throws Exception {

    }

    @Test
    public void reviews() throws Exception {

    }

    @Test
    public void titles() throws Exception {

    }

    @Test
    public void credits() throws Exception {

    }

}
