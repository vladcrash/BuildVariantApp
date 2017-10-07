package ru.tinkoff.school.activitylecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ExplicitActivity extends AppCompatActivity {
    private static final String NAME = "NAME";

    private TextView mWelcome;
    private Button mOK;
    private Button mCancel;
    
    public static void startForResult(AppCompatActivity activity, int requestCode, String name) {
        Intent starter = new Intent(activity, ExplicitActivity.class);
        starter.putExtra(NAME, name);
        activity.startActivityForResult(starter, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        mWelcome = (TextView) findViewById(R.id.welcome_tv);
        mOK = (Button) findViewById(R.id.ok);
        mCancel = (Button) findViewById(R.id.cancel);

        String name = getIntent().getStringExtra(NAME);
        mWelcome.setText(getResources().getString(R.string.welcome, name));

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
