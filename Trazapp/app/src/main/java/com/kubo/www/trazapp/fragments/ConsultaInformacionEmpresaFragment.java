package com.kubo.www.trazapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubo.www.trazapp.R;

public class ConsultaInformacionEmpresaFragment extends Fragment
{
    private TextView cifEmpresa, nombreEmpresa, direccionEmpresa, provinciaEmpresa, codigoPostalEmpresa;

    private OnFragmentInteractionListener mListener;

    public ConsultaInformacionEmpresaFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View vista = inflater.inflate(R.layout.fragment_consulta_informacion_empresa, container, false);

        cifEmpresa = (TextView) vista.findViewById(R.id.txtCifEmpresa);
        nombreEmpresa = (TextView) vista.findViewById(R.id.txtNombreEmpresa);
        direccionEmpresa = (TextView) vista.findViewById(R.id.txtDireccionEmpresa);
        provinciaEmpresa = (TextView) vista.findViewById(R.id.txtProvinciaEmpresa);
        codigoPostalEmpresa = (TextView) vista.findViewById(R.id.txtCodigoPostalEmpresa);

        cifEmpresa.setText(getArguments().getString("cif_empresa"));
        nombreEmpresa.setText(getArguments().getString("nombre_empresa"));
        direccionEmpresa.setText(getArguments().getString("direccion_empresa"));
        provinciaEmpresa.setText(getArguments().getString("provincia_empresa"));
        codigoPostalEmpresa.setText(getArguments().getString("cpostal_empresa"));

        return vista;
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null) { mListener.onFragmentInteraction(uri); }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener)
            mListener = (OnFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri); }
}
