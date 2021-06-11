package com.example.drh;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapContaco extends RecyclerView.Adapter<adapContaco.ViewHolder> {
    private List<contacto> aData;
    private LayoutInflater aInflater;
    private Context context;


    public adapContaco(List<contacto> aData,  Context context) {
        this.aData = aData;
        this.aInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public adapContaco.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= aInflater.inflate(R.layout.cardpresentacion,null);

        return new adapContaco.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapContaco.ViewHolder holder, int position) {
        holder.binData(aData.get(position));
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagenCon, imagenRed;
        TextView puesto, nombre, correo,  numero, redS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenCon=itemView.findViewById(R.id.imagenContaco);
            imagenRed=itemView.findViewById(R.id.redSocial);
            puesto=itemView.findViewById(R.id.tipoContacto);
            nombre=itemView.findViewById(R.id.nombreContacto);
            correo=itemView.findViewById(R.id.correoContacto);
            numero=itemView.findViewById(R.id.numeroContac);
            imagenRed.setOnClickListener(this);

        }
        void binData(final contacto item){
            imagenCon.setImageResource(item.getImagen1());
            imagenRed.setImageResource(item.getImagen2());
            puesto.setText(item.getPuesto());
            nombre.setText(item.getNombre());
            correo.setText(item.getCorreo());
            numero.setText(item.getNumero());

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position==0){
                Uri uri= Uri.parse("https://www.instagram.com/carreonchis_/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                   context.startActivity(instagram);
                }catch(ActivityNotFoundException a){
                    context.startActivity(new Intent (Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/carreonchis_/")));
                }
            }else if(position==1){
                Uri uri= Uri.parse("https://www.facebook.com/joangarcia2015");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    context.startActivity(instagram);
                }catch(ActivityNotFoundException a){
                    context.startActivity(new Intent (Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/joangarcia2015")));
                }
            }

        }
    }

    }
