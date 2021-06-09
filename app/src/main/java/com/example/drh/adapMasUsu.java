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

public class adapMasUsu extends RecyclerView.Adapter<adapMasUsu.ViewHolder> {
    private List<lisMascotaU> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapMasUsu(List<lisMascotaU> aData, Context context) {
        this.aData = aData;
        this.aInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public adapMasUsu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= aInflater.inflate(R.layout.lismasusuario,null);
        return new adapMasUsu.ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return aData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull adapMasUsu.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView idM, nombre, fechaNac, especie, raza, color, sexo, tatuajes, microchip;

        ViewHolder( View itemView) {
            super(itemView);
            iv= itemView.findViewById(R.id.iconImageViewMasU);
            idM= itemView.findViewById(R.id.idMascU);
            nombre=itemView.findViewById(R.id.nombreMascotaU);
            fechaNac= itemView.findViewById(R.id.fechaNacimientoU);
            especie= itemView.findViewById(R.id.especieU);
            raza= itemView.findViewById(R.id.razaU);
            color= itemView.findViewById(R.id.colorU);
            sexo= itemView.findViewById(R.id.sexoU);
            tatuajes= itemView.findViewById(R.id.tatuajeU);
            microchip= itemView.findViewById(R.id.microchipU);

        }

        void binData(final lisMascotaU item){
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

        }
    }


}

