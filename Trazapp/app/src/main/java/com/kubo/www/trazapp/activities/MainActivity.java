package com.kubo.www.trazapp.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;

import com.kubo.www.trazapp.R;
import com.kubo.www.trazapp.adapters.ExpandableListAdapter;
import com.kubo.www.trazapp.fragments.ConsultaFormulasDetalleFragment;
import com.kubo.www.trazapp.fragments.ConsultaFormulasFragment;
import com.kubo.www.trazapp.fragments.ConsultaRecepcionesComponenteFragment;
import com.kubo.www.trazapp.fragments.ConsultaRecepcionesDetalleFragment;
import com.kubo.www.trazapp.fragments.ConsultaRecepcionesTipoComponenteFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        ConsultaFormulasFragment.OnFragmentInteractionListener, ConsultaFormulasDetalleFragment.OnFragmentInteractionListener,
        ConsultaRecepcionesTipoComponenteFragment.OnFragmentInteractionListener,
        ConsultaRecepcionesComponenteFragment.OnFragmentInteractionListener,
        ConsultaRecepcionesDetalleFragment.OnFragmentInteractionListener
{
    private static final int CODIGO_SOLICITUD_AYUDA = 2;
    private static final int CODIGO_SOLICITUD_ACERCA_DE = 3;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private View view_Group;

    public static int[] iconos = {R.drawable.icon_recepciones, R.drawable.icon_formulas,
                                  R.drawable.icon_fabricaciones, R.drawable.icon_fabricaciones}; //R.drawable.icon_expediciones};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableListView = (ExpandableListView) findViewById(R.id.list_slidermenu);

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        expandableListView.addHeaderView(listHeaderView);
        mTitle = mDrawerTitle = getTitle();

//        if (savedInstanceState == null)
//            selectFirstItemDefault();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrincipalSilos)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setUpDrawer();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.icon_drawer, R.string.app_name)
        {
            @Override
            public void onDrawerClosed(View view)
            {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        makeActionOverflowMenuShown();
    }

    private void makeActionOverflowMenuShown()
    {
        try
        {
            final ViewConfiguration config = ViewConfiguration.get(this);
            final Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

            if (menuKeyField != null)
            {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }
        catch (final Exception e)
        {
            Log.e("", e.getLocalizedMessage());
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemDefault()
    {
/*        if (navigationManager != null)
        {
            String firstItem = listTitle.get(0);
            navigationManager.showFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId())
        {
            case R.id.ayuda:
            {
/*                Intent ayuda = new Intent(this, AyudaActivity.class);
                startActivityForResult(ayuda, CODIGO_SOLICITUD_AYUDA);*/
                return true;
            }
            case R.id.acercaDe:
            {
                Intent acercaDe = new Intent(this, AcercaDeActivity.class);
                startActivityForResult(acercaDe, CODIGO_SOLICITUD_ACERCA_DE);
                return true;
            }
            case R.id.salir:
            {
                Intent actividadDatos = new Intent();
                setResult(RESULT_OK, actividadDatos);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpDrawer()
    {
        expandableListView = (ExpandableListView) findViewById(R.id.list_slidermenu);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View vista, int groupPosition, int childPosition, long id)
            {
                String selectedItem = listDataHeader.get(groupPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                vista.setSelected(true);

                if (view_Group != null) view_Group.setBackgroundResource(R.color.white);

                view_Group = vista;
                view_Group.setBackgroundResource(R.color.green_050);

                Fragment miFragment = null;
                boolean fragmentSeleccionado = false;

                switch (groupPosition) {
                    // Recepciones
                    case 0:
                        switch (childPosition)
                        {
                            case 0:
                                miFragment = new ConsultaRecepcionesTipoComponenteFragment();
                                fragmentSeleccionado = true;
                                break;
                            case 1:
                                miFragment = new ConsultaRecepcionesComponenteFragment();
                                fragmentSeleccionado = true;
                                break;

                            case 2:
                                break;
                        }
                        break;

                    // Formulas
                    case 1:
                        switch (childPosition)
                        {
                            case 0:
                                miFragment = new ConsultaFormulasFragment();
                                fragmentSeleccionado = true;
                                break;
                        }
                        break;

                    // Fabricaciones
                    case 2:
                        switch (childPosition)
                        {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                        break;

                    // Expediciones
                    case 3:
                        switch (childPosition)
                        {

                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                        }
                        break;
                }

                if (fragmentSeleccionado)
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, miFragment).commit();

                expandableListView.setItemChecked(childPosition, true);
                mDrawerLayout.closeDrawer(expandableListView);
                return false;
            }
        });
    }

    private void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        String[] array = getResources().getStringArray(R.array.nav_drawer_items);
        listDataHeader = Arrays.asList(array);

        // Recepciones
        List<String> recepciones = new ArrayList<String>();
        String[] dash = getResources().getStringArray(R.array.recepciones);
        recepciones = Arrays.asList(dash);

        // Formulas
        List<String> formulas = new ArrayList<String>();
        String[] before = getResources().getStringArray(R.array.formulas);
        formulas = Arrays.asList(before);

        // Fabricaciones
        List<String> fabricaciones = new ArrayList<String>();
        String[] myproe = getResources().getStringArray(R.array.fabricaciones);
        fabricaciones = Arrays.asList(myproe);

        // expediciones
        List<String> expediciones = new ArrayList<String>();
        String[] inco = getResources().getStringArray(R.array.expediciones);
        expediciones = Arrays.asList(inco);

        // assigning values to menu and submenu
        listDataChild.put(listDataHeader.get(0), recepciones); // Header, Child
        // data
        listDataChild.put(listDataHeader.get(1), formulas);
        listDataChild.put(listDataHeader.get(2), fabricaciones);
        listDataChild.put(listDataHeader.get(3), expediciones);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) { return false; }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
