package com.gutierrez.bubble_pet_xforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gutierrez.bubble_pet_xforce.Controllers.Servicios;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btnLogin, btnCuenta;
     FirebaseAuth mAuth;
    TextView tempVCorreo, tempVContra, tempV;
    String email, password;
    DatabaseReference mDatabase;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Firebase methods
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Get user data





        btnLogin = findViewById(R.id.btnnext);
       btnLogin.setOnClickListener(v ->{
           try {
               tempVCorreo = findViewById(R.id.edCorreo);
               email = tempVCorreo.getText().toString();
               tempVContra = findViewById(R.id.edContra);
               password = tempVContra.getText().toString();
               tempV = findViewById(R.id.edTest);

               firebaseAuthWithUser();
               tempV.setText(email + password );
           }catch(Exception e){
               msgToast("Error en el boton"+ e);
           }


        });

       btnCuenta = findViewById(R.id.btnSingIn);
       btnCuenta.setOnClickListener(view ->{
           Intent singIn = new Intent(getApplicationContext(), SingIn.class);
           startActivity(singIn);
       });
    }

    private void inicio (){
        Intent inicio = new Intent(getApplicationContext(), SingOut.class);
        startActivity(inicio);
    }

   @Override
   protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            inicio();
        }
    }

    private void firebaseAuthWithUser(){
        tempV = findViewById(R.id.edName);
        String name = tempV.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Map<String, Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("password", password);
                            map.put("name", name);

                            String id = mAuth.getCurrentUser().getUid();
                            mDatabase.child("Users").child(id).setValue(map)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task2) {
                                            if (task2.isSuccessful()){
                                                inicio();
                                                finish();
                                                msgToast("Tendria que pasar");
                                            }
                                             else{
                                                msgToast("Error al crear los datos");
                                            }
                                        }
                                    });
                            inicio();

                            //FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                           // Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",//        Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                            msgToast("no fue posible registrar");
                        }
                    }
                });
    }

    private void msgToast (String e){
        Toast.makeText(getApplicationContext(),e, Toast.LENGTH_LONG).show();
    }
}