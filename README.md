[![Build Status](https://travis-ci.org/igdb/api-android-java.svg?branch=master)](https://travis-ci.org/igdb/api-android-java)

# api-android-java
A Android wrapper for the IGDB.com Free Video Game Database API.

## About IGDB
One of the principles behind IGDB.com is accessibility of data. We wish to share the data with anyone who wants to build cool videogame oriented websites, apps and services. This means that the information you contribute to IGDB.com can be used by other projects as well.

Thus, you are not only contributing to the value of this site but to thousands of other projects as well. We are looking forward to see what exciting game related projects you come up with. Happy coding!

More info here:
* [About the API](https://www.igdb.com/api)
* [API Documentation](https://igdb.github.io/api/about/welcome/)

# Installation and setup

  Step 1. Add this in your root build.gradle at the end of the repositories:
```Gradle
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
  Step 2. Add the dependency
``` Gradle
dependencies {
	compile 'com.github.igdb:api-android-java:master-SNAPSHOT'
}
```
  Step 3. Add internet permissions in the manifest
  ``` xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Using your API key
* Create a new APIWrapper Object by passing you 3Scale key
``` java
APIWrapper wrapper = new APIWrapper(context, "YOUR_API_KEY");
```

## Usage
All API endpoints are available as methods in the APIWrapper object. Each method has the following signature:
### wrapper.endpoint(Map<Operator, String>)
__Arguments__
* Operator - An object specifying an operation, ex. expander, filter, ordering etc. These Operations can be found in the API documentation under References: (https://igdb.github.io/api/references/)
* String - The String is the accompaying data for the Operator, ex. game ids "1,2,3,4,5" OR fields "id,name,rating"

__Example__ 
* Requesting games from API
``` java
APIWrapper wrapper = new APIWrapper(context, "YOUR_API_KEY");
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.FIELDS,“*”);
args.put(APIWrapper.Operator.ORDER, "published_at:desc");

wrapper.games(args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// Do something with resulting JSONArray
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/games/?fields=*&order=published_at:desc */

```
The rest of the endpoints work similarly to the Games endpoint except for two cases presented bellow.

* Requesting search from the API
``` java
APIWrapper wrapper = new APIWrapper(context, "YOUR_API_KEY");
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.SEARCH, "SeachQuery");
args.put(APIWrapper.Operator.FIELDS,“*”);
args.put(APIWrapper.Operator.ORDER, "published_at:desc");

wrapper.search(APIWrapper.Endpoint.GAMES, args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// Do something with resulting JSONArray
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/games/?search=searchQuery&fields=*&order=published_at:desc */

```
The search endpoint need an extra parameter, Endpoint, as you can search any endpoint for information.

* Filtering a request result
``` java
APIWrapper wrapper = new APIWrapper(context, "YOUR_API_KEY");
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.FIELDS,“*”);
args.put(APIWrapper.Operator.FILTER, "[themes][not_in]=42");
args.put(APIWrapper.Operator.ORDER, "published_at:desc");

wrapper.games(args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// Do something with resulting JSONArray
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/games/?search=searchQuery&fields=*&filter[themes][not_in]=42&order=published_at:desc */

```
Filtering requires extra information in the arguments and needs to be written out like in the example above. 
Information about Filtering and the postfixes like 'not_in' can be found [here](https://igdb.github.io/api/references/filters/).

The rest of the available Endpoints are [Available Here](https://igdb.github.io/api/endpoints/).
The IGDB API documentation provides [details on search parameters](https://igdb.github.io/api/references/filters/).


## More examples
```Android

APIWrapper wrapper = new APIWrapper(context, "YOUR_API_KEY");

/*
Search for up to two Atari platforms and return their names
*/
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.SEARCH,“Atari”);
args.put(APIWrapper.Operator.FIELDS,“name”);
args.put(APIWrapper.Operator.LIMIT,“2”);

wrapper.search(APIWrapper.Endpoint.PLATFORMS, args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// JSONArray containing 2 Atari platforms
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/platforms/?search=Atari&fields=name&limit=2 */

/*
Search for up to five Zelda games with release dates between 1 Jan and
31 Dec 2011, sorted by release date in descending order.
*/
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.SEARCH,“Zelda”);
args.put(APIWrapper.Operator.FIELDS,“name,release_dates.date,rating,hypes,cover”);
args.put(APIWrapper.Operator.FILTER,“[release_dates.date][gt]=2010-12-31”);
args.put(APIWrapper.Operator.FILTER,“[release_dates.date][lt]=2012-01-01”);
args.put(APIWrapper.Operator.LIMIT,“2”);
args.put(APIWrapper.Operator.OFFSET,“0”);
args.put(APIWrapper.Operator.ORDER,“release_dates.date:desc”);

wrapper.search(APIWrapper.Endpoint.PLATFORMS, args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// JSONArray containing 5 Zelda games
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/games/?search=Zelda&fields=name,release_dates.date,rating,hypes,cover&filter[release_dates.date][gt]=2010-12-31&filter[release_dates.date][lt]=2012-01-01&limit=5&order=release_dates.date:desc */

/*
Search for two specific games by their IDs
*/
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.IDS,“18472,18228”);
args.put(APIWrapper.Operator.FIELDS,“name,cover”);

wrapper.games(args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// JSONArray containing 2 games
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/games/18472,18228?fields=name,cover */

/*
Search for companies with 'rockstar' in their name. Return up to five
results sorted by name in descending order
*/
Map<APIWrapper.Operator, String> args = new HashMap<>();
args.put(APIWrapper.Operator.SEARCH,“rockstar”);
args.put(APIWrapper.Operator.FIELDS,“name,logo”);
args.put(APIWrapper.Operator.FILTER,“[name][in]=rockstar”);
args.put(APIWrapper.Operator.LIMIT,“5”);
args.put(APIWrapper.Operator.OFFSET,“0”);
args.put(APIWrapper.Operator.ORDER,“name:desc”);

wrapper.search(APIWrapper.Endpoint.COMPANIES, args, new onSuccessCallback(){
	@Override
        public void onSuccess(JSONArray result) {
        	// JSONArray containing 5 Zelda games
        }

        @Override
        public void onError(VolleyError error) {
            // Do something on error
        }
});

/* The sent request will look like this:
https://api-2445582011268.apicast.io/companies/?search=rockstar&fields=name,logo&filter[name][in]=rockstar&limit=5&offset=0&order=name:desc */
