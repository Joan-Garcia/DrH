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

public class adapVacunas extends RecyclerView.Adapter<adapVacunas.ViewHolder> {
    private List<lisEVacuna> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapVacunas(List<lisEVacuna> aData, Context context) {
        this.aData = aData;
        this.aInflater=LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public adapVacunas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= aInflater.inflate(R.layout.lisevacunas,null);
        return new adapVacunas.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull adapVacunas.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView idV, nombreMascota, nombreVacuna, fechaAp, proxFecha;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iconImageViewVacuna);
            idV=itemView.findViewById(R.id.idVac);
            nombreMascota=itemView.findViewById(R.id.nombreMascotaV);
            nombreVacuna=itemView.findViewById(R.id.nombreVacuna);
            fechaAp=itemView.findViewById(R.id.fechavac);
            proxFecha=itemView.findViewById(R.id.proxFVac);
        }
        void binData(final lisEVacuna item){
            iv.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            idV.setText(item.getIdVacuna());
            nombreMascota.setText(item.getNombremascota());
            nombreVacuna.setText(item.getVacuna());
            fechaAp.setText(item.getFechaApli().toString());
            proxFecha.setText(item.getProxFecV().toString());

        }
    }

}
