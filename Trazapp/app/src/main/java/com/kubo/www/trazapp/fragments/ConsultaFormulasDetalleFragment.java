package com.kubo.www.trazapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.adapters.ComponenteAdapter;
import com.kubo.www.trazapp.entities.Componente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultaFormulasDetalleFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener
{
    private TextView nombreFormula, fechaFormula, tipoFormula, estadoFormula;
    private int idFormula;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerComponentes;
    ArrayList<Componente> listaComponentes;

    RequestQueue requestQueue;
    JsonObjectRequest jsonRequest;

    public ConsultaFormulasDetalleFragment(){}

    public static ConsultaFormulasDetalleFragment newInstance()
    {
        ConsultaFormulasDetalleFragment fragment = new ConsultaFormulasDetalleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View vista = inflater.inflate(R.layout.fragment_consulta_formulas_detalle, container, false);

        listaComponentes = new ArrayList<>();

        recyclerComponentes = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerComponentes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerComponentes.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(getContext());

        String FechaModificacion;

        idFormula = getArguments().getInt("id_formula");
        FechaModificacion = getArguments().getString("fecha_modificacion");

        nombreFormula = (TextView) vista.findViewById(R.id.txtNombreFormula);
        fechaFormula = (TextView) vista.findViewById(R.id.txtFechaFormula);
        tipoFormula = (TextView) vista.findViewById(R.id.txtTipoFormula);
        estadoFormula = (TextView) vista.findViewById(R.id.txtEstadoFormula);

        nombreFormula.setText(getArguments().getString("nombre_formula"));
        fechaFormula.setText(getArguments().getString("fecha_formula"));
        tipoFormula.setText(getArguments().getString("tipo_formula"));
        estadoFormula.setText(getArguments().getString("estado_formula"));

        cargarWebService();

        return vista;
    }

    private void cargarWebService()
    {
        String url = String.format("%s%s?%s%s", getString(R.string.url), getString(R.string.ws_consulta_componentes_formula),
                getString(R.string.ws_parametro_1), idFormula);
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null) mListener.onFragmentInteraction(uri);
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

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(getContext(), R.string.error_conexion + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        Componente componente = new Componente();
        JSONArray jsonArray = response.optJSONArray("componente");

        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                componente = new Componente();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);

                componente.setIdFormulaComponente(jsonObject.optInt("id_formula_componente"));
                componente.setIdFormula(jsonObject.optInt("id_formula"));
                componente.setNombreComponente(jsonObject.optString("componente"));
                componente.setPorcentajeComponente((float) jsonObject.optDouble("porcentaje"));
                componente.setOrdenComponente(jsonObject.optInt("orden"));
                componente.setDosificacionComponente(jsonObject.optString("dosificacion"));
                componente.setTipoComponente(jsonObject.optInt("tipo"));
                componente.setEstadoCompoenente(jsonObject.optInt("estado"));
                listaComponentes.add(componente);
            }

            ComponenteAdapter adapter = new ComponenteAdapter(listaComponentes);
            recyclerComponentes.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), R.string.error_conexion + response.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }
}
