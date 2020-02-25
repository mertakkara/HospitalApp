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

import com.rationalstudio.hospitalapp.Models.RegisterPojo;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.Warnings;

public class SignUpActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText registerPassword, RegisterUserName, registerMailAdress;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerButton = findViewById(R.id.registerButton);
        registerPassword = findViewById(R.id.registerPassword);
        RegisterUserName = findViewById(R.id.registerUserName);
        registerMailAdress = findViewById(R.id.registerMailAdress);
        registerText = findViewById(R.id.registerText);
        registerToUser();
        changeActivity();

    }

    public void registerToUser() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = registerMailAdress.getText().toString();
                String userN = RegisterUserName.getText().toString();
                String pass = registerPassword.getText().toString();
                register(mail, userN, pass);
                delete();
            }
        });
    }

    public void changeActivity() {
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete() {
        registerPassword.setText("");
        RegisterUserName.setText("");
        registerMailAdress.setText("");

    }

    public void register(String userMailAddress, String userName, String userPass) {
        Call<RegisterPojo> req = ManagerAll.getInstance().kayitol(userMailAddress, userName, userPass);
        req.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();


                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
