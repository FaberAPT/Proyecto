package com.kubo.www.trazapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.entities.Formula;

import java.util.List;

public class FormulaAdapter extends RecyclerView.Adapter<FormulaAdapter.FormulasHolder>
{
    List<Formula> listaFormulas;

    public FormulaAdapter(List<Formula> listaFormulas)
    {
        this.listaFormulas = listaFormulas;
    }

    @Override
    public FormulasHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_formulas, viewGroup, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new FormulasHolder(vista);
    }

    @Override
    public void onBindViewHolder(FormulasHolder holder, final int position)
    {
        holder.txtNombreFormula.setText(listaFormulas.get(position).getNombreFormula());
        holder.txtEstadoFormula.setText(listaFormulas.get(position).getEstadoFormula());
    }

    @Override
    public int getItemCount()
    {
        return listaFormulas.size();
    }


    public class FormulasHolder extends RecyclerView.ViewHolder
    {
        TextView txtNombreFormula, txtEstadoFormula;

        public FormulasHolder(View itemView)
        {
            super(itemView);
            txtNombreFormula = (TextView) itemView.findViewById(R.id.txtNombreFormula);
            txtEstadoFormula = (TextView) itemView.findViewById(R.id.txtEstadoFormula);
        }
    }
}
