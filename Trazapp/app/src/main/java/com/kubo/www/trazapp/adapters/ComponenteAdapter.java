package com.kubo.www.trazapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.entities.Componente;

import java.util.List;

public class ComponenteAdapter extends RecyclerView.Adapter<ComponenteAdapter.ComponentesHolder>
{
    List<Componente> listaComponentes;

    public ComponenteAdapter(List<Componente> listaComponentes)
    {
        this.listaComponentes = listaComponentes;
    }

    @Override
    public ComponenteAdapter.ComponentesHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.componente_list, viewGroup, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new ComponenteAdapter.ComponentesHolder(vista);
    }

    @Override
    public void onBindViewHolder(ComponenteAdapter.ComponentesHolder holder, int position)
    {
        holder.txtNombreComponente.setText(listaComponentes.get(position).getNombreComponente());
        holder.txtPorcentajeComponente.setText(Float.toString(listaComponentes.get(position).getPorcentajeComponente()));
        holder.txtOrdenComponente.setText(Integer.toString(listaComponentes.get(position).getOrdenComponente()));
    }

    @Override
    public int getItemCount()
    {
        return listaComponentes.size();
    }


    public class ComponentesHolder extends RecyclerView.ViewHolder
    {
        TextView txtNombreComponente, txtPorcentajeComponente, txtOrdenComponente;

        public ComponentesHolder(View itemView)
        {
            super(itemView);
            txtNombreComponente = (TextView) itemView.findViewById(R.id.txtNombreComponente);
            txtPorcentajeComponente = (TextView) itemView.findViewById(R.id.txtPorcentajeComponente);
            txtOrdenComponente = (TextView) itemView.findViewById(R.id.txtOrdenComponente);
        }
    }
}
