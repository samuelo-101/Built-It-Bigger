package com.udacity.gradle.builditbigger.async;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class BackendAsyncTaskTest {

    @Test
    public void should_return_non_empty_result() throws ExecutionException, InterruptedException {
        BackendAsyncTask backendAsyncTask = new BackendAsyncTask(new BackendAsyncTask.ApiResponse() {
            @Override
            public void onResponseReceived(String response) {

            }
        });
        backendAsyncTask.execute();
        String actual = backendAsyncTask.get();
        String unexpected = "";

        Assert.assertNotNull(actual);
        Assert.assertNotEquals(unexpected, actual.trim());
    }

    @Test
    public void should_invoke_callback_when_response_received() throws ExecutionException, InterruptedException {
        BackendAsyncTask.ApiResponse apiResponse = Mockito.mock(BackendAsyncTask.ApiResponse.class);
        BackendAsyncTask backendAsyncTask = new BackendAsyncTask(apiResponse);
        // Mimic AsycTask call sequence
        String actual = backendAsyncTask.doInBackground(null);
        backendAsyncTask.onPostExecute(actual);

        Mockito.verify(apiResponse, Mockito.times(1)).onResponseReceived(actual);
    }
}