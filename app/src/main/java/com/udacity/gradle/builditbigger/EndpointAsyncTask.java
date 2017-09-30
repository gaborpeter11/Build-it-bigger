package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.pepovpc.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

// from there https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
class EndpointAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static MyApi myApiService = null;
    private MainActivityFragment mainActivityFragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                AndroidJsonFactory(), null)
                .setRootUrl("https://joketestingapp.appspot.com//_ah/api/");

        myApiService = builder.build();
        mainActivityFragment = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        mainActivityFragment.displayJokeActivity(result);
    }



}