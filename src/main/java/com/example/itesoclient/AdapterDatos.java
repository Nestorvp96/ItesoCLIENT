package com.example.itesoclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> list;


    public AdapterDatos(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.itemlist, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {

        viewHolderDatos.asignarDatos(list.get(i));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvproductos;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tvproductos = itemView.findViewById(R.id.tvproductos);
        }

        public void asignarDatos(String s) {


            tvproductos.setText(s);
        }
    }
}
