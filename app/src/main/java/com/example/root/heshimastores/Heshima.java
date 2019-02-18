package com.example.root.heshimastores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.heshimastores.R;

public class Heshima extends AppCompatActivity {
    private  static EditText username;
    private static EditText password;
    private static EditText attemps;
    private static Button login;
    int attemps_counter=5;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heshima);
        loginbuttonclick();}













    public void loginbuttonclick(){
        username=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        attemps=(EditText)findViewById(R.id.editText3);
        login=(Button)findViewById(R.id.button);
        attemps.setText(Integer.toString(attemps_counter));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("heshima") &&
                        password.getText().toString().equals("heshima")){
                    Toast.makeText(Heshima.this,"the username and password is correct",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(".updatessales");
                    startActivity(intent);
                }else{
                    Toast.makeText(Heshima.this,"the username andpassword is inccorrect",Toast.LENGTH_SHORT).show();
                    attemps_counter--;
                    attemps.setText(Integer.toString(attemps_counter));
                    if(attemps_counter==0){
                        login.setEnabled(false);
                    }
                }
            }
        });


    }
}



