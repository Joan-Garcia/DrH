package com.example.drh;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapFech extends RecyclerView.Adapter<adapFech.ViewHolder > {
    private List<lisMasFech> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapFech(List<lisMasFech> aData, Context context){
        this.aData=aData;
        this.context=context;
        aInflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public adapFech.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = aInflater.inflate(R.layout.cardfechas,null);
        return new adapFech.ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull adapFech.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView idA, nombreMascota, nombreDes, fechaAp, proxFecha, tipoA;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iconImageViewFechas);
            idA=itemView.findViewById(R.id.idFecUser);
            nombreMascota=itemView.findViewById(R.id.nombreMascotaFec);
            nombreDes=itemView.findViewById(R.id.nombreAppF);
            fechaAp=itemView.findViewById(R.id.fechavacFec);
            proxFecha=itemView.findViewById(R.id.proxFFEchas);
            tipoA=itemView.findViewById(R.id.tipoApp);
        }
        void binData(final lisMasFech item){
            iv.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            idA.setText(item.getIdAplicacion());
            nombreMascota.setText(item.getNombremascota());
            nombreDes.setText(item.getVacuna());
            fechaAp.setText(item.getFechaApli().toString());
            proxFecha.setText(item.getProxFecV().toString());
            tipoA.setText(item.getDescripcion());
        }
    }

}
