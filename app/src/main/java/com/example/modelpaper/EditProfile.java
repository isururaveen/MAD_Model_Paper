package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText userName, dob, password;
    Button edit, delete, search;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userName = findViewById(R.id.etUserNameEP);
        dob = findViewById(R.id.dobEP);
        password = findViewById(R.id.passwordEP);
        edit = findViewById(R.id.btnEditEp);
        delete = findViewById(R.id.delBtnEP);
        search = findViewById(R.id.btnSearch);
        male = findViewById(R.id.radioBtnMaleEP);
        female = findViewById(R.id.radioBtnFemaleEP);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfor(userName.getText().toString());

                if (user.isEmpty()){
                    Toast.makeText(EditProfile.this, "No User", Toast.LENGTH_SHORT).show();
                    userName.setText(null);
                }
                else{
                    Toast.makeText(EditProfile.this, "User Found! User:"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    userName.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());

                    if (user.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else{
                        female.setChecked(true);
                    }
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else{
                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                Boolean status = dbHandler.updateInfor(userName.getText().toString(), dob.getText().toString(), password.getText().toString(),gender);
                if (status){
                    Toast.makeText(EditProfile.this, "User Updated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Update failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(userName.getText().toString());

                Toast.makeText(EditProfile.this, "User Deleted!", Toast.LENGTH_SHORT).show();
                userName.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}