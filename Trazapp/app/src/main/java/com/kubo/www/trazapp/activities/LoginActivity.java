package com.kubo.www.trazapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.entities.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener
{
    private static final int CODIGO_SOLICITUD_DATOS = 2;

    RequestQueue requestQueue;
    JsonRequest jsonRequest;
    EditText etUsuario, etClave;
    Button btnInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.txtusuario);
        etClave = (EditText) findViewById(R.id.txtclaveacesso);
        btnInicioSesion = (Button) findViewById(R.id.btniniciosesion);

        requestQueue = Volley.newRequestQueue(getBaseContext());
        btnInicioSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion()
    {
        String url = String.format("%s%s%s&%s%s", getString(R.string.url), getString(R.string.ws_inicio_sesion_user),
                etUsuario.getText().toString(), getString(R.string.ws_inicio_sesion_pwd),
                etClave.getText().toString());

        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        AlertDialog.Builder dialogoError = new AlertDialog.Builder(LoginActivity.this);

        dialogoError.setTitle(R.string.dialogo_error_title);
        dialogoError.setMessage(R.string.dialogo_error_mensage);
        dialogoError.setPositiveButton(R.string.dialogo_error_buttom, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });

        dialogoError.show();
    }

    @Override
    public void onResponse(JSONObject response)
    {
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try
        {
            Usuario usuario = new Usuario();
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setNombres(jsonObject.optString("nombres"));
            usuario.setTipo(jsonObject.optString("tipo"));

            Intent actividadPrincipal = new Intent(this, MainActivity.class);
            actividadPrincipal.putExtra("nombres", usuario.getNombres());
            startActivityForResult(actividadPrincipal, CODIGO_SOLICITUD_DATOS);;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int codigoSolicitud, int codigoResultado, Intent datos)
    {
        if (codigoResultado == RESULT_OK && codigoSolicitud == CODIGO_SOLICITUD_DATOS)
        {
            finish();
        }
    }
}
