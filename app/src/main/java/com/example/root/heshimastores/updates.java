package com.example.root.heshimastores;

import android.app.AlertDialog;
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

import com.example.root.heshimastores.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class updates extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    database mydb;
    EditText goodtype,cordinator,quantity,date,vehicleid;
    Button btnAdd;
    Button  viewall;
    Calendar calender;
    SimpleDateFormat simpledateformat;
    String date1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        mydb=new database(this);
        goodtype=(EditText)findViewById(R.id.editText9);
        cordinator=(EditText)findViewById(R.id.editText2);
        quantity=(EditText)findViewById(R.id.editText3);
        date=(EditText)findViewById(R.id.editText4);


        vehicleid=(EditText)findViewById(R.id.editText5);
        btnAdd=(Button)findViewById(R.id.button);
        viewall=(Button)findViewById(R.id.button2);
        spinner();
        setdate();
        adddata();
        Viewall();


    }
    public void spinner(){
        Spinner myspiner=(Spinner)findViewById(R.id.spinner2);
        myspiner.setOnItemSelectedListener( this);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(
                updates.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gooods)
        );
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspiner.setAdapter(myAdapter);
    }
    public String setdate(){
        calender=Calendar.getInstance();
        simpledateformat=new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
        date1=simpledateformat.format(calender.getTime());
        date.setText(date1);
        return date1;
    }
    public void adddata(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted=mydb.insertData(goodtype.getText().toString(),cordinator.getText().toString(),Integer.parseInt(quantity.getText().toString()),date.getText().toString(),vehicleid.getText().toString());
                if(inserted=true)
                    Toast.makeText(updates.this,"data inserted",Toast.LENGTH_LONG).show();
                else{Toast.makeText(updates.this,"Data not inserted", Toast.LENGTH_LONG).show();}  }
        });
    }
    public void Viewall(){
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getAllData();
                if(res.getCount()==0){showmessage("error","no data found");
                    return; }else{
                    StringBuffer buffer=new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("ID    :"+res.getString(0)+"\n");
                        buffer.append("goodid     :"+res.getString(1)+"\n");
                        buffer.append("price :"+res.getString(2)+"\n");
                        buffer.append("quantity  :"+res.getString(3)+"\n");
                        buffer.append("date       :"+res.getString(4)+"\n\n");
                        buffer.append("vehicleid  :"+res.getString(5)+"\n\n");
                    }
                    showmessage("data",buffer.toString());

                }
            }
        });

    }
    public void showmessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"selected is :"+item,Toast.LENGTH_LONG).show();
        goodtype.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
