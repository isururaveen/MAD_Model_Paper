package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText userName, dob, password;
    Button add, updateProfile;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        userName = findViewById(R.id.etUserNamePM);
        dob = findViewById(R.id.etDobPM);
        password = findViewById(R.id.etPassPM);
        add = findViewById(R.id.addBtn);
        updateProfile = findViewById(R.id.updateBtnPM);
        male = findViewById(R.id.maleRadiobtnPM);
        female = findViewById(R.id.femaleRadioBtnPM);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (male.isChecked()){
                    gender = "Male";
                }
                else{
                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(userName.getText().toString(), dob.getText().toString(), password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User Added!, UserID:" +newID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);

                userName.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);

            }
        });

    }
}