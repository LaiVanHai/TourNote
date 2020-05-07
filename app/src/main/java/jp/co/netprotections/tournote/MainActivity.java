package jp.co.netprotections.tournote;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFirstFragmentListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FirstFragment firstFragment = new FirstFragment();
        if (findViewById(R.id.contentFrame) != null) {
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
                if (fragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragmentById).commit();
                }
            }
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, firstFragment).commit();
        } else {
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment firstFragmentById = getSupportFragmentManager().findFragmentById(R.id.firstFrame);
                if (firstFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(firstFragmentById).commit();
                }
                Fragment secondFragmentById = getSupportFragmentManager().findFragmentById(R.id.secondFrame);
                if (secondFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(secondFragmentById).commit();
                }
                getSupportFragmentManager().beginTransaction().add(R.id.firstFrame, firstFragment).commit();
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig){
     super.onConfigurationChanged(newConfig);
     drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search Button selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Intent intent = new Intent(this, ContactActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_ABOUT);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.help:
                intent = new Intent(this, ContactActivity.class);
                bundle = new Bundle();
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_HELP);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemPressed(String content) {
        SecondFragment secondFragment = SecondFragment.newInstance(content);
        if (findViewById(R.id.contentFrame) !=null ){
            // Found the ID of only one Fragment ==> Portrait mode
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentFrame, secondFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            // Landscape mode
            getSupportFragmentManager().beginTransaction().replace(R.id.secondFrame, secondFragment).commit();
        }


    }
}
