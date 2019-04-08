package com.example.itesoclient;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityStore extends AppCompatActivity {


    EditText etidstore, etname, etphone, etcity, etthumb, etlat, etlong;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        etidstore = findViewById(R.id.etIdStore);
        etname = findViewById(R.id.etStore);
        etphone = findViewById(R.id.etphone);
        etcity = findViewById(R.id.etIdCity);
        etthumb = findViewById(R.id.etThumbnail);
        etlat = findViewById(R.id.etLatitud);
        etlong = findViewById(R.id.etLongitud);

        btnCrear = findViewById(R.id.btnCrear);

        String URL = "content://storeProvider/store";

        final Uri stores = Uri.parse(URL);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();

                values.put("idStore", etidstore.getText().toString());
                values.put("name", etname.getText().toString());
                values.put("phone", etphone.getText().toString());
                values.put("idCity", etcity.getText().toString());
                values.put("thumbnail", etthumb.getText().toString());
                values.put("latitude", etlat.getText().toString());
                values.put("longitude", etlong.getText().toString());



                ContentResolver cr = getContentResolver();

                cr.insert(stores, values);


                finish();

            }
        });

    }




}
