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
                    assertThat(name, is("Mass Effect"));

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
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.collections(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("The Elder Scrolls"));
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
    public void companies() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.companies(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Lionhead Studios"));
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
    public void franchises() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.franchises(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Warhammer 40,000"));
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
    public void feeds() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "4011");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.feeds(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("pulse");
                    assertThat(s, is("24221"));
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
    public void pages() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.pages(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("theRadBrad"));
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
    public void gameEngines() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "6");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.gameEngines(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Unreal Engine"));
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
    public void gameModes() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.gameModes(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Massively Multiplayer Online (MMO)"));
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
    public void genres() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.genres(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Shooter"));
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
    public void keywords() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.keywords(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("zombies"));
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
    public void people() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.people(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("David Falkner"));
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
    public void platforms() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.platforms(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Wii"));
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
    public void playerPerspectives() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "5");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.playerPerspectives(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Text"));
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
    public void releaseDates() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "4");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.releaseDates(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("human");
                    assertThat(s, is("2008-Oct-20"));
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
    public void pulseGroups() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "1254");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.pulseGroups(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Marvel vs. Capcom: Infinite"));
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
    public void pulseSources() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "1");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.pulseSources(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Kotaku"));
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
    public void themes() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "42");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.themes(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Erotic"));
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
    public void reviews() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "42");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.reviews(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("title");
                    assertThat(s, is("This is a game"));
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
    public void titles() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "42");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.titles(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("name");
                    assertThat(s, is("Assistant External Resources Producer"));
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
    public void credits() throws Exception {
        setUp();
        Map<APIWrapper.Operator, String> args = new HashMap<>();
        args.put(APIWrapper.Operator.IDS, "1073987150");

        final CountDownLatch lock = new CountDownLatch(1);
        wrapper.credits(args, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                JSONObject jo;
                try {
                    lock.countDown();
                    jo = result.getJSONObject(0);
                    String s = jo.getString("game");
                    assertThat(s, is("493"));
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

}
