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

public class adapDespa extends RecyclerView.Adapter<adapDespa.ViewHolder > {
    private List<lisEDespa> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapDespa(List<lisEDespa> aData, Context context) {
        this.aData = aData;
        this.context = context;
        this.aInflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public adapDespa.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= aInflater.inflate(R.layout.lisedespa,null);
        return new adapDespa.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapDespa.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView idD, nombreMascota, nombreDes, fechaAp, proxFecha;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iconImageViewDespa);
            idD=itemView.findViewById(R.id.idDes);
            nombreMascota=itemView.findViewById(R.id.nombreMascotaD);
            nombreDes=itemView.findViewById(R.id.nombreDespa);
            fechaAp=itemView.findViewById(R.id.fechaDes);
            proxFecha=itemView.findViewById(R.id.proxFDes);
        }
        void binData(final lisEDespa item){
            iv.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            idD.setText(item.getIdDesp());
            nombreMascota.setText(item.getNombremascota());
            nombreDes.setText(item.getDespa());
            fechaAp.setText(item.getFechaApli().toString());
            proxFecha.setText(item.getProxFecD().toString());

        }
    }

}
