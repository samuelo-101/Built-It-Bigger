package javalibrary.four.gradle.udacity.com.androidjokelib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String JOKE_PARAM = "AndroidLib_JOKE_PARAM";

    private TextView mTextViewDisplayJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_activity_main);

        mTextViewDisplayJoke = findViewById(R.id.tv_display_joke);

        if(getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            if(extras.containsKey(JOKE_PARAM)) {
                mTextViewDisplayJoke.setText(extras.getString(JOKE_PARAM));
            }
        }
    }
}
