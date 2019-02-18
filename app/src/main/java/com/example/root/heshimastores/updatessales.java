package com.example.root.heshimastores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class updatessales extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Button  btnupdates,btnsales,btntransfer,btntotals;
    public EditText goodtype,goodtype2,goodtype3;
    database mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatessales);
        mydb=new database(this);
        btnupdates=(Button)findViewById(R.id.button4);
        btntotals=(Button)findViewById(R.id.button10);
        btnsales=(Button)findViewById(R.id.button5);

        goodtype=(EditText)findViewById(R.id.editText11);
        goodtype2=(EditText)findViewById(R.id.editText12);
        goodtype3=(EditText)findViewById(R.id.editText13);

        click();
        clicksales();
        spinner();
        viewtotal();

    }
    public void viewtotal(){
        btntotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum=curso();
                goodtype2.setText(""+sum);

            }
        });
    }
    public int curso(){
        int sum=0;
        Cursor curso1=mydb.data(goodtype.getText().toString());
        if(curso1.moveToFirst())
            sum=curso1.getInt(0);

        int sum2=0;
        Cursor cursor2=mydb.datasales(goodtype.getText().toString());
        if(cursor2.moveToFirst())
            sum2=cursor2.getInt(0);
        int sum4=0;
        Cursor cursor3=mydb.datachukatransfer(goodtype.getText().toString());
        if(cursor3.moveToFirst())
            sum4=cursor3.getInt(0);
        int sum3=0;
        int sum5=0;
        sum3=sum4+sum2;
        sum5=sum-sum3;
        return sum5;}

    public void spinner(){
        Spinner myspiner=(Spinner)findViewById(R.id.spinner3);
        myspiner.setOnItemSelectedListener( this);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(
                updatessales.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gooods)
        );
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspiner.setAdapter(myAdapter);
    }
    public void click(){
        btnupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(".updates");
                startActivity(intent);
            }
        });
    }
    public void clicksales(){
        btnsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(".sales");
                startActivity(intent);

            }
        });}



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "selected is :" + item, Toast.LENGTH_LONG).show();
        goodtype.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

