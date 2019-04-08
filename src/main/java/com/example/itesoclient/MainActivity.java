package com.example.itesoclient;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> list;
    RecyclerView recyclerView;
    TextView tvproductos;

    int categoria=1;

    String[] projection = new String[] {
    "idProduct",
    "name",
    "descriptionTEXT",
    "image",
    "idCategory",
    "idStore"
    };

    //private static final String uri = "content://net.product.android.contentproviders/product";
    //public static final Uri CONTENT_URI = Uri.parse(uri);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.itmtecno:

                list.clear();

                SolicitarDatos(1);

                AdapterDatos adapterDatos=new AdapterDatos(list);

                recyclerView.setAdapter(adapterDatos);

                categoria = 1;

                break;

            case R.id.itmhome:

                list.clear();

                SolicitarDatos(2);

                AdapterDatos adapterDatos1=new AdapterDatos(list);

                recyclerView.setAdapter(adapterDatos1);

                categoria = 2;

                break;


            case R.id.itmelect:

                list.clear();

                SolicitarDatos(3);

                AdapterDatos adapterDatos2=new AdapterDatos(list);

                recyclerView.setAdapter(adapterDatos2);

                categoria = 3;

                break;

            case R.id.itmcreatestore:

                Intent intent=new Intent(getBaseContext(), ActivityStore.class);
                startActivity(intent);



                break;


            case R.id.itmRefresh:

                Toast.makeText(getBaseContext(), "Id categoria:"+ categoria, Toast.LENGTH_SHORT).show();

                list.clear();

                SolicitarDatos(categoria);

                AdapterDatos adapterDatos3=new AdapterDatos(list);

                recyclerView.setAdapter(adapterDatos3);


                break;


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvproductos = findViewById(R.id.tvproductos);
        recyclerView = findViewById(R.id.recyclerid);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        list = new ArrayList<>();


    }//ONCREATE FIN



    public void SolicitarDatos(int Categoria){

        //Uri productsUri = CONTENT_URI;
        ContentResolver cr = getContentResolver();

        String URL = "content://productProvider/product/"+Categoria;

        Uri products = Uri.parse(URL);


        try{
            Cursor cur = cr.query(products,
                    projection, //Columnas a devolver
                    null,       //Condición de la query
                    null,       //Argumentos variables de la query
                    null); //Orden de los resultados

            if (cur.moveToFirst())
            {
                String nombre;
                String descripcion;
                int image;
                int idcategory;
                int idstore;


                int colNombre = cur.getColumnIndex("name");
                int colDescripcion = cur.getColumnIndex("descriptionTEXT");
                int colImage = cur.getColumnIndex("image");
                int colIDCategory = cur.getColumnIndex("idCategory");
                int colIDStore = cur.getColumnIndex("idStore");

                //tvproductos.setText("");

                do
                {
                    nombre = cur.getString(colNombre);
                    descripcion = cur.getString(colDescripcion);
                    image = cur.getInt(colImage);
                    idcategory = cur.getInt(colIDCategory);
                    idstore = cur.getInt(colIDStore);

                    list.add(nombre);

                } while (cur.moveToNext());
            }

            AdapterDatos adapterDatos1=new AdapterDatos(list);

        }catch (Exception e){

            throw (e);
        }

    }




}
