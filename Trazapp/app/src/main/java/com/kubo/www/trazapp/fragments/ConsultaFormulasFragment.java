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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.activities.LoginActivity;
import com.kubo.www.trazapp.adapters.FormulaAdapter;
import com.kubo.www.trazapp.entities.Formula;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultaFormulasFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener
{
    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerFormulas;
    ArrayList<Formula> listaFormulas;

    ProgressDialog barraProgreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonRequest;

    public ConsultaFormulasFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View vista = inflater.inflate(R.layout.fragment_consulta_formulas, container, false);

        listaFormulas = new ArrayList<>();

        recyclerFormulas = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerFormulas.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerFormulas.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(getContext());

        ConnectivityManager conexion = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conexion.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            cargarWebService();
        else
            onCreateDialog(0).show();

        final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }
        });

        recyclerFormulas.addOnItemTouchListener(new RecyclerView.OnItemTouchListener()
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
                        bundle.putInt("id_formula", listaFormulas.get(position).getIdFormula());
                        bundle.putString("nombre_formula", listaFormulas.get(position).getNombreFormula());
                        bundle.putString("fecha_formula", listaFormulas.get(position).getFechaFormula());
                        bundle.putString("fecha_modificacion", listaFormulas.get(position).getFechaModificacion());
                        bundle.putInt("tipo_formula", listaFormulas.get(position).getTipoFormula());
                        bundle.putInt("estado_formula", listaFormulas.get(position).getEstadoFormula());

                        Fragment detalleFormula = ConsultaFormulasDetalleFragment.newInstance();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        detalleFormula.setArguments(bundle);
                        transaction.replace(R.id.container, detalleFormula);
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

        String url = String.format("%s%s", getString(R.string.url), getString(R.string.ws_consulta_formulas));
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
        barraProgreso.hide();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        Formula formula = new Formula();
        JSONArray jsonArray = response.optJSONArray("formula");

        try
        {
            for (int i = 0; i < jsonArray.length(); i++)
            {
                formula = new Formula();
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);

                String[] fechaFormula = jsonObject.optString("fecha_formula").split(" "),
                        fechaModificacion = jsonObject.optString("fecha_modificacion").split(" ");

                formula.setIdFormula(jsonObject.optInt("id_formula"));
                formula.setNombreFormula(jsonObject.optString("nombre_formula"));
                formula.setFechaFormula(fechaFormula[0]);
                formula.setFechaModificacion(fechaModificacion[0]);
                formula.setTipoFormula(jsonObject.optInt("tipo_formula"));
                formula.setEstadoFormula(jsonObject.optInt("estado_formula"));
                listaFormulas.add(formula);
            }

            barraProgreso.hide();
            FormulaAdapter adapter = new FormulaAdapter(listaFormulas);
            recyclerFormulas.setAdapter(adapter);
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
