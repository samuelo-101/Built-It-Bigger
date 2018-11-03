package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.async.BackendAsyncTask;

public class MainActivityPaid extends AppCompatActivity implements BackendAsyncTask.ApiResponse {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paid);

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        new BackendAsyncTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponseReceived(String response) {
        mProgressBar.setVisibility(View.GONE);
        if(response != null) {
            Intent intent = new Intent(getApplicationContext(), javalibrary.four.gradle.udacity.com.androidjokelib.MainActivity.class);
            intent.putExtra(javalibrary.four.gradle.udacity.com.androidjokelib.MainActivity.JOKE_PARAM, response);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.generic_api_error_message), Toast.LENGTH_LONG).show();
        }
    }
}
