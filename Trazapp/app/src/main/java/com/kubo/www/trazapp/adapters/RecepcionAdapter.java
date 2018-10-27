package com.kubo.www.trazapp.adapters;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.entities.Recepcion;

import java.util.List;

public class RecepcionAdapter extends RecyclerView.Adapter<RecepcionAdapter.RecepcionesHolder>
{
    List<Recepcion> listaRecepciones;

    public RecepcionAdapter(List<Recepcion> listaRecepciones){this.listaRecepciones = listaRecepciones;}

    @Override
    public RecepcionesHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recepciones, viewGroup, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new RecepcionesHolder(vista);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RecepcionesHolder holder, final int position)
    {
        DecimalFormat formatearCantidad = new DecimalFormat("###,###");

        holder.txtNombreComponente.setText(listaRecepciones.get(position).getMaterialRecepcion());
        holder.txtFechaRecepcion.setText(listaRecepciones.get(position).getFechaRecepcion());
        holder.txtCantidadRecepcion.setText(formatearCantidad.format(listaRecepciones.get(position).getCantidadRecepcion()));
    }

    @Override
    public int getItemCount()
    {
        return listaRecepciones.size();
    }


    public class RecepcionesHolder extends RecyclerView.ViewHolder
    {
        TextView txtNombreComponente, txtFechaRecepcion, txtCantidadRecepcion;

        public RecepcionesHolder(View itemView)
        {
            super(itemView);
            txtNombreComponente = (TextView) itemView.findViewById(R.id.txtNombreComponente);
            txtFechaRecepcion = (TextView) itemView.findViewById(R.id.txtFechaRecepcion);
            txtCantidadRecepcion = (TextView) itemView.findViewById(R.id.txtCantidadRecepcion);
        }
    }
}
