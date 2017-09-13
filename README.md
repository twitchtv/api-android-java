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
```java
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
  Step 2. Add the dependency
``` java
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












