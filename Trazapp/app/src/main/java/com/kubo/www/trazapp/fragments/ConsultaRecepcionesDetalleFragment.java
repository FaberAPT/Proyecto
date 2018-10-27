package com.kubo.www.trazapp.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubo.www.trazapp.R;

public class ConsultaRecepcionesDetalleFragment extends Fragment
{
    private TextView nombreEmpresa, matriculaVehiculo, nombreConductor, cantidadRecepcion, materialRecepcion,
                     numeroSilo, fechaRecepcion, estadoRecepcion, inspeccionVisual, presentacion, loteProveedor,
                     densidad, pesoEspecifico, comentarios;

    private OnFragmentInteractionListener mListener;

    public ConsultaRecepcionesDetalleFragment() {}

    public static ConsultaRecepcionesDetalleFragment newInstance()
    {
        ConsultaRecepcionesDetalleFragment fragment = new ConsultaRecepcionesDetalleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View vista = inflater.inflate(R.layout.fragment_consulta_recepciones_detalle, container, false);

        DecimalFormat formatearCantidad = new DecimalFormat("###,###.##");

        nombreEmpresa = (TextView) vista.findViewById(R.id.txtNombreEmpresa);
        matriculaVehiculo = (TextView) vista.findViewById(R.id.txtMatriculaVehiculo);
        nombreConductor = (TextView) vista.findViewById(R.id.txtNombreConductor);
        cantidadRecepcion = (TextView) vista.findViewById(R.id.txtCantidadRecepcion);
        materialRecepcion = (TextView) vista.findViewById(R.id.txtNombreComponente);
        numeroSilo = (TextView) vista.findViewById(R.id.txtNumeroSilo);
        inspeccionVisual = (TextView) vista.findViewById(R.id.txtInspeccionVisual);
        presentacion = (TextView) vista.findViewById(R.id.txtPresentacion);
        loteProveedor = (TextView) vista.findViewById(R.id.txtLoteProveedor);
        densidad = (TextView) vista.findViewById(R.id.txtDensidad);
        pesoEspecifico = (TextView) vista.findViewById(R.id.txtPesoEspecifico);
        fechaRecepcion = (TextView) vista.findViewById(R.id.txtFechaRecepcion);
        estadoRecepcion = (TextView) vista.findViewById(R.id.txtEstadoRecepcion);
        comentarios = (TextView) vista.findViewById(R.id.txtComentarios);

        nombreEmpresa.setText(getArguments().getString("nombre_empresa"));
        matriculaVehiculo.setText(getArguments().getString("matricula_camion"));
        nombreConductor.setText(getArguments().getString("conductor_camion"));
        materialRecepcion.setText(getArguments().getString("material_recepcion"));
        cantidadRecepcion.setText(formatearCantidad.format(getArguments().getDouble("cantidad_recepcion")) + " Kg");
        numeroSilo.setText(Integer.toString(getArguments().getInt("numero_silo")));
        inspeccionVisual.setText(getArguments().getString("inspeccion_visual"));
        presentacion.setText(getArguments().getString("presentacion"));
        loteProveedor.setText(getArguments().getString("lote_proveedor"));
        densidad.setText(formatearCantidad.format(getArguments().getDouble("densidad")) + " Kg");
        pesoEspecifico.setText(formatearCantidad.format(getArguments().getDouble("peso_especifico")) + " Kg");
        fechaRecepcion.setText(getArguments().getString("fecha_recepcion"));
        estadoRecepcion.setText(getArguments().getString("estado_recepcion"));
        comentarios.setText(getArguments().getString("comentarios"));

        return vista;
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {void onFragmentInteraction(Uri uri); }
}
