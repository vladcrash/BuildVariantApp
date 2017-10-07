package ru.tinkoff.school.activitylecture;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static ru.tinkoff.school.activitylecture.R.id.famous_btn;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 0;

    private TextInputEditText mIntroduction;
    private Button mFindOutYourName;
    private Button mBecomeFamous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntroduction = (TextInputEditText) findViewById(R.id.introduction);
        mFindOutYourName = (Button) findViewById(R.id.find_out_btn);
        mBecomeFamous = (Button) findViewById(famous_btn);

        mFindOutYourName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExplicitActivity.startForResult(MainActivity.this, REQ_CODE,
                        mIntroduction.getText().toString());
            }
        });

        mBecomeFamous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String sendText = getResources().getString(R.string.where_is_my_fame,
                        mIntroduction.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, sendText);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(mIntroduction, R.string.cute, Snackbar.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Snackbar.make(mIntroduction, R.string.rude, Snackbar.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
