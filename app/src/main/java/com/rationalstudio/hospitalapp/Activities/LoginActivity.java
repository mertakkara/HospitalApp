package com.rationalstudio.hospitalapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rationalstudio.hospitalapp.Models.LoginModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;
import com.rationalstudio.hospitalapp.Utils.Warnings;

public class LoginActivity extends AppCompatActivity {
    private EditText loginMailAdress,loginPassword;
    private TextView loginText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMailAdress = findViewById(R.id.loginMailAdress);
        loginPassword = findViewById(R.id.loginPassword);
        loginText = findViewById(R.id.loginText);
        loginButton=findViewById(R.id.loginButton);
        click();
    }
    public void click(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = loginMailAdress.getText().toString();
                String pass = loginPassword.getText().toString();
                login(mail,pass);
                delete();

            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void login(String mail,String pass){
       Call<LoginModel> req= ManagerAll.getInstance().girisyap(mail,pass);
       req.enqueue(new Callback<LoginModel>() {
           @Override
           public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
               if (response.body().isTf()) {
                   Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   GetSharedPreferences getSharedPreferences = new GetSharedPreferences(LoginActivity.this);
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUsername(),response.body().getMailadres());
                   startActivity(intent);
                   finish();
               }else{
                   Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<LoginModel> call, Throwable t) {
               Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_SHORT).show();
           }
       });
    }
    public void delete() {
        loginMailAdress.setText("");
        loginPassword.setText("");

    }
}
