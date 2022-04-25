package com.gutierrez.bubble_pet_xforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingIn extends AppCompatActivity {

    String email, password;
    Button btnSingInOk;
    TextView tempV;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        mAuth = FirebaseAuth.getInstance();



        btnSingInOk = findViewById(R.id.btnSingInOk);
        btnSingInOk.setOnClickListener(v -> {
            tempV = findViewById(R.id.txtEmail);
            email = tempV.getText().toString();

            tempV = findViewById(R.id.txtPassword);
            password = tempV.getText().toString();
            try{
                loginUser();

            }catch (Exception e){
                msgToast("si aca : " + e );
            }

        });


    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Intent Home = new Intent (getApplicationContext(), SingOut.class);
                    startActivity(Home);

                }else{
                    msgToast("Imposible iniciar sesion");
                }

            }
        });
    }
    private void msgToast (String e){
        Toast.makeText(getApplicationContext(),e, Toast.LENGTH_LONG).show();
    }
}