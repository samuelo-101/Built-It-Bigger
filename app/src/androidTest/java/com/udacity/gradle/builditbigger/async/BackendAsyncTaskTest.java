package com.udacity.gradle.builditbigger.async;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BackendAsyncTaskTest {

    BackendAsyncTask.ApiResponse apiResponse;

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
}