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

public class adapMascota extends RecyclerView.Adapter<adapMascota.ViewHolder> {
    private List<lisEMascota> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapMascota(List<lisEMascota> aData, Context context) {
        this.aData = aData;
        this.aInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public adapMascota.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= aInflater.inflate(R.layout.lisemascota,null);
        return new adapMascota.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapMascota.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView idM, idU, nombre, fechaNac, especie, raza, color, sexo, tatuajes, microchip, nombrePro;

        ViewHolder( View itemView) {
            super(itemView);
            iv= itemView.findViewById(R.id.iconImageViewMas);
            idM= itemView.findViewById(R.id.idMasc);
            nombre=itemView.findViewById(R.id.nombreMascota);
            fechaNac= itemView.findViewById(R.id.fechaNacimiento);
            especie= itemView.findViewById(R.id.especie);
            raza= itemView.findViewById(R.id.raza);
            color= itemView.findViewById(R.id.color);
            sexo= itemView.findViewById(R.id.sexo);
            tatuajes= itemView.findViewById(R.id.tatuaje);
            microchip= itemView.findViewById(R.id.microchip);
            nombrePro=itemView.findViewById(R.id.nombreProp);
        }

        void binData(final lisEMascota item){
            iv.setColorFilter(Color.parseColor(item.getColorImg()), PorterDuff.Mode.SRC_IN);
            idM.setText(item.getIdMascota());
            nombre.setText(item.getNombre());
            fechaNac.setText(item.getFechaNac().toString());
            raza.setText(item.getRaza());
            especie.setText(item.getEspecie());
            color.setText(item.getColor());
            sexo.setText(item.getSexo());
            tatuajes.setText(item.getTatuaje());
            microchip.setText(item.getMicrochip());
            nombrePro.setText(item.getNombrepro());
        }
    }


}

