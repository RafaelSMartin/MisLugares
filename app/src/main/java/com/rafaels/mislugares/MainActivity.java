package com.rafaels.mislugares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void lanzarAbout(View view){
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.config){
            return true;
        }
        if(id == R.id.about){
            lanzarAbout(null);
            return true;
        }
        if(id == R.id.search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
