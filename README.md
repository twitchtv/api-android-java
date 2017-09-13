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

```Step 1. Add this in your root build.gradle at the end of the repositories:
allprojects {
	repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
```Step 2. Add the dependency 
dependencies {
	compile 'com.github.igdb:api-android-java:master-SNAPSHOT'
}
```

## Using the wrapper
* Create a new APIWrapper Object by passing you 3Scale key
