package com.rationalstudio.hospitalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.rationalstudio.hospitalapp.Fragments.HomeFragment;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {
private SharedPreferences sharedPreferences;
private GetSharedPreferences getSharedPreferences;
private ImageView mainpageButton,callButton,exitButton;
private ChangeFragments changeFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        getSharedPreferences = new GetSharedPreferences(MainActivity.this);
        mainpageButton= findViewById(R.id.mainpageButton);
        callButton= findViewById(R.id.callButton);
        exitButton = findViewById(R.id.exitButton);
        sharedPreferences = getSharedPreferences.getSession();
        control();
        action();
    }
    public void action(){
    mainpageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeFragments.change(new HomeFragment());
        }
    });
    exitButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GetSharedPreferences getSharedPreferences= new GetSharedPreferences(MainActivity.this);
            getSharedPreferences.deleteToSession();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
    });
    callButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("tel:00000000000"));
            startActivity(intent);

        }
    });
    }

    private void getFragment() {
        changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    public void control(){
        if(sharedPreferences.getString("id",null)== null && sharedPreferences.getString("username",null)== null && sharedPreferences.getString("mailadres",null)== null   ){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
