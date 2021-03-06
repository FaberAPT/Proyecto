package com.kubo.www.trazapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.adapters.RecepcionAdapter;
import com.kubo.www.trazapp.entities.Recepcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultaRecepcionesTipoComponenteFragment extends Fragment implements
             Response.Listener<JSONObject>, Response.ErrorListener
{
    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerRecepciones;
    ArrayList<Recepcion> listaRecepciones;

    ProgressDialog barraProgreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonRequest;

    private Spinner spTipoComponente;

    String[] arrayTipoComponentes = new String[] {"PRINCIPAL", "CORRECTOR", "LIQUIDO", "ALMACEN"};
    ArrayAdapter<String> spinnerAdapter;

    public ConsultaRecepcionesTipoComponenteFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View vista = inflater.inflate(R.layout.fragment_consulta_recepciones_tipo_componente, container, false);

        spTipoComponente = (Spinner) vista.findViewById(R.id.spTipoComponente);
        spinnerAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, arrayTipoComponentes);
        spTipoComponente.setAdapter(spinnerAdapter);

        listaRecepciones = new ArrayList<>();

        recyclerRecepciones = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerRecepciones.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerRecepciones.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(getContext());

        spTipoComponente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ConnectivityManager conexion = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = conexion.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected())
                    cargarWebService();
                else
                    onCreateDialog(0).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
        });

        recyclerRecepciones.addOnItemTouchListener(new RecyclerView.OnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent)
            {
                try
                {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if (child != null && mGestureDetector.onTouchEvent(motionEvent))
                    {
                        int position = recyclerView.getChildAdapterPosition(child);

                        Bundle bundle = new Bundle();
                        bundle.putInt("id_recepcion", listaRecepciones.get(position).getIdRecepcion());
                        bundle.putString("nombre_empresa", listaRecepciones.get(position).getNombreEmpresa());
                        bundle.putString("matricula_camion", listaRecepciones.get(position).getMatriculaCamion());
                        bundle.putString("conductor_camion", listaRecepciones.get(position).getConductorCamion());
                        bundle.putDouble("cantidad_recepcion", listaRecepciones.get(position).getCantidadRecepcion());
                        bundle.putString("material_recepcion", listaRecepciones.get(position).getMaterialRecepcion());
                        bundle.putInt("numero_silo", listaRecepciones.get(position).getNumeroSilo());
                        bundle.putString("fecha_recepcion", listaRecepciones.get(position).getFechaRecepcion());
                        bundle.putString("estado_recepcion", listaRecepciones.get(position).getEstadoRecepcion());
                        bundle.putString("inspeccion_visual", listaRecepciones.get(position).getInspeccionVisual());
                        bundle.putString("presentacion", listaRecepciones.get(position).getPresentacion());
                        bundle.putString("lote_proveedor", listaRecepciones.get(position).getLoteProveedor());
                        bundle.putDouble("densidad", listaRecepciones.get(position).getDensidad());
                        bundle.putDouble("peso_especifico", listaRecepciones.get(position).getPesoEspecifico());
                        bundle.putString("comentarios", listaRecepciones.get(position).getComentarios());

                        Fragment detalleRecepcion = ConsultaRecepcionesDetalleFragment.newInstance();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        detalleRecepcion.setArguments(bundle);
                        transaction.replace(R.id.container, detalleRecepcion);
                        transaction.addToBackStack(null);
                        transaction.commit();

                        return true;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent){}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b){}
        });

        return vista;
    }

    private void cargarWebService()
    {
        barraProgreso = new ProgressDialog(getContext());
        barraProgreso.setMessage(getString(R.string.barra_progreso_consulta));
        barraProgreso.show();

        int tipoComponente = spTipoComponente.getSelectedItem().toString() == "PRINCIPAL" ? 1 :
                             spTipoComponente.getSelectedItem().toString() == "CORRECTOR" ? 2 :
                             spTipoComponente.getSelectedItem().toString() == "LIQUIDO" ? 3 : 4;

        // Parametro_1 = 1 Hacer busqueda por tipo de componente
        String url = String.format("%s%s?%s%s&%s%s&%s%s", getString(R.string.url), getString(R.string.ws_consulta_recepciones),
                                                        getString(R.string.ws_parametro_1), 1, getString(R.string.ws_parametro_2),
                                                        tipoComponente, getString(R.string.ws_parametro_3), "");
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
            mListener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof ConsultaRecepcionesTipoComponenteFragment.OnFragmentInteractionListener)
            mListener = (ConsultaRecepcionesTipoComponenteFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(getContext(), R.string.error_conexion + error.toString(), Toast.LENGTH_LONG).show();
        barraProgreso.hide();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        Recepcion recepcion = new Recepcion();
        JSONArray jsonArray = response.optJSONArray("recepcion");

        listaRecepciones.clear();

        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                recepcion = new Recepcion();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);

                String[] fechaRecepcion = jsonObject.optString("fecha_recepcion").split(" ");

                recepcion.setIdRecepcion(jsonObject.optInt("id_recepcion"));
                recepcion.setNombreEmpresa(jsonObject.optString("nombre_empresa"));
                recepcion.setMatriculaCamion(jsonObject.optString("matricula_camion"));
                recepcion.setConductorCamion(jsonObject.optString("conductor_camion"));
                recepcion.setCantidadRecepcion(jsonObject.optDouble("cantidad_recepcion"));
                recepcion.setMaterialRecepcion(jsonObject.optString("material_recepcion"));
                recepcion.setNumeroSilo(jsonObject.optInt("numero_silo"));
                recepcion.setFechaRecepcion(fechaRecepcion[0]);
                recepcion.setEstadoRecepcion(jsonObject.optString("estado_recepcion"));
                recepcion.setInspeccionVisual(jsonObject.optString("inspeccion_visual"));
                recepcion.setPresentacion(jsonObject.optString("presentacion"));
                recepcion.setLoteProveedor(jsonObject.optString("lote_proveedor"));
                recepcion.setDensidad(jsonObject.optDouble("densidad"));
                recepcion.setPesoEspecifico(jsonObject.optDouble("peso_especifico"));
                recepcion.setComentarios(jsonObject.optString("comentarios"));

                listaRecepciones.add(recepcion);
            }

            barraProgreso.hide();
            RecepcionAdapter adapter = new RecepcionAdapter(listaRecepciones);
            recyclerRecepciones.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), R.string.error_conexion + response.toString(), Toast.LENGTH_LONG).show();
            barraProgreso.hide();
        }
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }

    public Dialog onCreateDialog(int id)
    {
        Dialog dialogo = null;
        AlertDialog.Builder dialogoError = new AlertDialog.Builder(getContext());
        dialogoError.setTitle(R.string.dialogo_sin_conexion_title);
        dialogoError.setMessage(R.string.dialogo_sin_conexion_mensage);
        dialogoError.setIcon(R.drawable.img_sin_conexion);
        dialogoError.setPositiveButton(R.string.dialogo_error_buttom, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });

        dialogo = dialogoError.create();
        return dialogo;
    }
}
