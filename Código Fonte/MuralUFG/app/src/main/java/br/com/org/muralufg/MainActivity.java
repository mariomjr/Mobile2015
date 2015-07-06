package br.com.org.muralufg;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends ActionBarActivity {
    // Declaramos nossa variaveis locais relacionadas a Toolbar
    // e o DrawerLayout
    private android.support.v7.widget.Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawerLayout;
    private BaseAdapter adapter;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // Criei um método para startar nosso Menu
        startDrawer();

    }

    private void startDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);

        //Inicializamos nosso DrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed)
        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);

                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(drawerToggle);

        // O passo a seguir é o responsável por criar os itens do menu
        // DrawerLayout, neste caso buscamos na nossa lista de array que
        // criamos no arquivo de strings.xml que identificamos como: string_array_menu

        String[] values = getResources().getStringArray(R.array.string_array_menu);

        // Utilizei um adaptador basico para a criação do menu
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, //Contexto
                android.R.layout.simple_list_item_1,  // Utilizado para menu com acessibilidade
                android.R.id.text1, // Utilizado para menu com acessibilidade
                values);// Valore que serão exibidos
        // Setamos o adptador ao listView
        mDrawerList.setAdapter(adapter);
    }

    // Sobrescrevemos o método onPostCreate e executamos o método para
    // sincronizar nosso drawer com o método  onRestoreInstanceState
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}