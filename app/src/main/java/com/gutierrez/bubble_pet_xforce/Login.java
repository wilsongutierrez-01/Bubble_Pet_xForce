package com.gutierrez.bubble_pet_xforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.gutierrez.bubble_pet_xforce.Controllers.Servicios;

public class Login extends AppCompatActivity {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btnLogin = findViewById(R.id.btnnext);
       btnLogin.setOnClickListener(v ->{
            inicio();
        });
    }

    private void inicio (){
        Intent inicio = new Intent(getApplicationContext(), Services.class);
        startActivity(inicio);
    }
}