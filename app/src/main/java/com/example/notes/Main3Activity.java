package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    ResminamaDB mydb;
    Context context=this;
    ListView listView;
    List<String> arrayList=new ArrayList<>();
    List<String> arrayList1=new ArrayList<>();
    List<String> arrayList2=new ArrayList<>();
    Button addbtn;
    Button delete_resminama;
    Button edit_resminama;
    Button delete_all;
    EditText gozle_resminama;
    int i=1;
    int j=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView=(ListView) findViewById(R.id.resminama_lv);
        mydb = new ResminamaDB(context);
        addbtn=(Button) findViewById(R.id.resminama_add);
        delete_resminama=(Button) findViewById(R.id.delete_resminama);
        edit_resminama=(Button) findViewById(R.id.edit_resminama);
        delete_all=(Button) findViewById(R.id.delete_all);
        gozle_resminama=(EditText) findViewById(R.id.gozle_resminama);
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Üns Beriň!!!");
                builder.setMessage("Ähli girizilen resminamalar pozular\nSiz Razymy?");
                builder.setNegativeButton("Ýok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Howwa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.all();
                    }
                });
                builder.show();

            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this,Add_Resminama.class));
                finish();
            }
        });
        delete_resminama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, com.example.notes.delete_resminama.class));
                finish();
            }
        });
        edit_resminama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main3Activity.this, edit_resminama.class));
                finish();
            }
        });

        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found");

        }
        while (res.moveToNext()) {
            arrayList.add(i+". "+res.getString(1));
            arrayList2.add(res.getString(1));
            i++;
        }


        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        listView.setAdapter(arrayAdapter);

        gozle_resminama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s!=""){
                        arrayList1.clear();
                        j=1;
                        for(int i=0;i<arrayList.size();i++){
                            if(arrayList.get(i).toUpperCase().contains(s.toString().toUpperCase())){
                                arrayList1.add(j+". "+arrayList2.get(i));
                                j++;

                            }
                        }
                        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList1);
                        listView.setAdapter(arrayAdapter1);
                    }
                    else {
                        listView.setAdapter(arrayAdapter);

                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Main3Activity.this,Main2Activity.class));
    }
}
