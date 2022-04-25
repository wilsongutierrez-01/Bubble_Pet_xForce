package com.gutierrez.bubble_pet_xforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SingOut extends AppCompatActivity {
    Button btnGetOut;

    FirebaseAuth mAuth;

    DatabaseReference mRootReference;
    Button sendData;
    TextView tempV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_out);

        mAuth = FirebaseAuth.getInstance();
        mRootReference = FirebaseDatabase.getInstance().getReference();



        btnGetOut = findViewById(R.id.btnGetOutOk);
        btnGetOut.setOnClickListener(v ->{
           mAuth.signOut();

            Intent back = new Intent(getApplicationContext(), Login.class);
            startActivity(back);
        });

        sendData = findViewById(R.id.btnData);
        sendData.setOnClickListener(v -> {
            tempV = findViewById(R.id.txt1);
            String v1 = tempV.getText().toString();

            tempV = findViewById(R.id.text2);
            String v2 = tempV.getText().toString();

            tempV = findViewById(R.id.txt3);
            String v3 = tempV.getText().toString();

            String id = mAuth.getCurrentUser().getUid();

            Map<String, Object> data = new HashMap<>();
            data.put("v1", v1);
            data.put("v2", v2 );
            data.put("v3", v3);

            mRootReference.child("Info").child(id).push().setValue(data);
            
        });
        userInfo();
        dataUser();

    }

    private void userInfo (){
        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            for (UserInfo profile : user.getProviderData()){

                String correo = profile.getEmail();
                tempV = findViewById(R.id.txtDatos1);
                tempV.setText(correo);
                
            }
        }
    }

    private void msgToast (String e){
        Toast.makeText(getApplicationContext(),e, Toast.LENGTH_LONG).show();
    }

    private void dataUser(){
        String id = mAuth.getCurrentUser().getUid();
        mRootReference.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    try{
                        String nombre = snapshot.child("name").getValue().toString();
                        tempV = findViewById(R.id.txtDatos);
                        tempV.setText(nombre);
                    }catch(Exception e){
                        msgToast("" + e);
                    }


                }else{
                    msgToast("No entra a primer nodo");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}