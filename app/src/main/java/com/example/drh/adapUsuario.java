package com.example.drh;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapUsuario extends RecyclerView.Adapter<adapUsuario.ViewHolder> {
    private List<lisUsuario> aData;
    private LayoutInflater aInflater;
    private Context context;

    public adapUsuario(List<lisUsuario> itemlist, Context context){
        this.aInflater=LayoutInflater.from(context);
        this.context=context;
        this.aData=itemlist;



    }
    @Override
    public int getItemCount(){ return aData.size(); }

    @Override
    public adapUsuario.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= aInflater.inflate(R.layout.listeusuario,null);
        return new adapUsuario.ViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final adapUsuario.ViewHolder holder, int pos){
        holder.bindData(aData.get(pos));
    }
    public void setItems(List <lisUsuario> items){ aData=items; }//Método para volver a llenar las vistas

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nombre,domicilio, correo, id, tipo_u, tel,celu,colonia,cp, ciudad, estado, pais;

        ViewHolder(View v){
            super(v);
            iconImage=v.findViewById(R.id.iconImageView);
            nombre=v.findViewById(R.id.nombreCompleto);
            domicilio=v.findViewById(R.id.direccion);
            correo=v.findViewById(R.id.email);
            id=v.findViewById(R.id.idUsuarioC);
            tipo_u=v.findViewById(R.id.tipU);
            tel=v.findViewById(R.id.tel);
            celu=v.findViewById(R.id.cel);

            colonia=v.findViewById(R.id.colonia);
            cp=v.findViewById(R.id.cp);
            ciudad=v.findViewById(R.id.ciudad);
            estado=v.findViewById(R.id.estado);
            pais=v.findViewById(R.id.pais);






        }

        void bindData(final lisUsuario item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
            domicilio.setText(item.getDomicilio());
            correo.setText(item.getCorreo());
            id.setText(item.getId());
            tipo_u.setText(item.getTipoU());
            tel.setText(item.getTel());
            celu.setText(item.getCelu());

            colonia.setText(item.getColonia());
            cp.setText(item.getCp());
            ciudad.setText(item.getCiudad());
            estado.setText(item.getEstado());
            pais.setText(item.getPais());
        }
    }
}
