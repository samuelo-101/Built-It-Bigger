package com.udacity.gradle.builditbigger.async;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;

import java.io.IOException;

public class BackendAsyncTask extends AsyncTask<Void, Void, String> {
    private JokesApi jokesApiService;
    private ApiResponse mApiResponse;

    public BackendAsyncTask(ApiResponse apiResponse) {
        this.mApiResponse = apiResponse;
        initializeApiClient();
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return jokesApiService.fetchJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeApiClient() {
        if (jokesApiService == null) {
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(BuildConfig.BACKEND_BASE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokesApiService = builder.build();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        this.mApiResponse.onResponseReceived(joke);
    }

    public interface ApiResponse {
        void onResponseReceived(String response);
    }
}
