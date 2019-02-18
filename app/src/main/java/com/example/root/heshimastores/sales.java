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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class sales extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    SimpleDateFormat simpledateformat;
    String date1;
    public EditText date,goodtype,amount,datesales,cost;
    public Button addsalesbtn,viewsalesbtn;
    database mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        mydb=new database(this);
        Spinner myspiner=(Spinner)findViewById(R.id.spinner);
        myspiner.setOnItemSelectedListener( this);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(
                sales.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gooods)
        );
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspiner.setAdapter(myAdapter);
        date=(EditText)findViewById(R.id.editText7);
        goodtype=(EditText)findViewById(R.id.editText10);
        amount=(EditText)findViewById(R.id.editText6);
        cost=(EditText)findViewById(R.id.editText8);
        addsalesbtn=(Button)findViewById(R.id.button8);
        viewsalesbtn=(Button)findViewById(R.id.button9);
        setdate();
        Viewall();
        adddata();

    }
    public String setdate(){
        Calendar calender=Calendar.getInstance();
        simpledateformat=new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
        date1=simpledateformat.format(calender.getTime());
        date.setText(date1);
        return date1;
    }
    public void adddata(){
        addsalesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted=mydb.insertdata2(goodtype.getText().toString(),Integer.parseInt(amount.getText().toString()),date.getText().toString(),Integer.parseInt(cost.getText().toString()));
                if(inserted=true)
                    Toast.makeText(sales.this,"data inserted",Toast.LENGTH_LONG).show();
                else{Toast.makeText(sales.this,"Data not inserted", Toast.LENGTH_LONG).show();}  }
        });
    }
    public void Viewall(){
        viewsalesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res2=mydb.getAllDatasales();
                if(res2.getCount()==0){showmessage("error","no data found");
                    return; }else{
                    StringBuffer buffer=new StringBuffer();
                    while(res2.moveToNext()){
                        buffer.append("ID    :"+res2.getString(0)+"\n");
                        buffer.append("goodsold    :"+res2.getString(1)+"\n");
                        buffer.append("amount :"+res2.getString(2)+"\n");
                        buffer.append("date   :"+res2.getString(3)+"\n");
                        buffer.append("cost_sh      :"+res2.getString(4)+"\n\n");

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
