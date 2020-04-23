package jp.co.netprotections.tournote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    public static final String KEY_SHOW_WHAT = "show_what";
    public static final String VALUE_SHOW_ABOUT = "show_about";
    public static final String VALUE_SHOW_HELP = "show_help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // 前の画面に戻るボタンを作ります。
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String valueString = bundle.getString(KEY_SHOW_WHAT, "");
            Toast.makeText(
        this, "Show value " + valueString, Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: //システムが自動的に設定
                finish(); //Activity完了します。
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
