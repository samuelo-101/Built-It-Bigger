package com.udacity.gradle.builditbigger.async;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class BackendAsyncTaskTest extends AndroidTestCase {

    public void test() throws ExecutionException, InterruptedException {
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