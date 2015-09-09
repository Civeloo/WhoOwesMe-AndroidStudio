package com.civeloo.whoowesme.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.civeloo.whoowesme.R;
import com.civeloo.whoowesme.data.ClientDao;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

/**
 * Created by DeG on 3/12/13.
 */
public final class MainActivity extends Activity {
    private AdView adView;
    ImageButton bAdd, bSearch;
    Spinner spinner;
    String spId = "";
    String[] asItems;
    ArrayAdapter<String> aaAdapter;
    Boolean vOnLoad = true;
    // Creamos el cursor
    ClientDao cliService;

    //EditText etName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cliService = new ClientDao(this);

        // Crear la adView
        adView = new AdView(this, AdSize.BANNER, "a152d94a5ce033f");
        // Buscar el LinearLayout suponiendo que se le haya asignado
        // el atributo android:id="@+id/mainLayout"
        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
        // Añadirle la adView
        layout.addView(adView);
        // Iniciar una solicitud genérica para cargarla con un anuncio
        adView.loadAd(new AdRequest());

        bAdd = (ImageButton) findViewById(R.id.mainIBadd);
        spinner = (Spinner) findViewById(R.id.mainSPname);
        getDataToSpinner();

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdd();
            }
        });

        // Seleccionar Localidad
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                if (vOnLoad) vOnLoad = false;
                else startQuery(asItems[position]);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                //startQuery("0");
            }
        });

    }

    public void startQuery(String pos) {
        Intent i = new Intent(this, Search.class);
        i.putExtra("name", pos);
        startActivityForResult(i, 1);
    }

    public void startAdd() {
        Intent i = new Intent(this, Add.class );
        startActivityForResult(i, 2);
    }

    public void getDataToSpinner() {
        Cursor c = cliService.getDataTo();
        if (c.getCount() > 0) {
            // Creamos la lista
            asItems = new String[c.getCount()+1];
            asItems[0] = this.getString(R.string.main_spinner);
            int i = 1;
            while (c.moveToNext()) {
                asItems[i] = new String(c.getString(1));
                i++;
            }
        } else {
            asItems = new String[1];
            asItems[0] = this.getString(R.string.main_spinner);
            startAdd();
        }
        c.close();
        vOnLoad = true;
        aaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, asItems);
        aaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aaAdapter);
    }

    /**
     * TRAER PARAMETROS CUANDO VUELVE DE OTRA ACTIVITY *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        Log.w("activity", String.valueOf(requestCode));
        if (resultCode == Activity.RESULT_OK)
            Log.w("name", String.valueOf(data.getStringArrayExtra("result")));
        else getDataToSpinner();
        */
        switch (requestCode) {
            case (1): {
                if (resultCode == Activity.RESULT_OK) startQuery(data.getStringExtra("result"));
                break;
            }
            case (2): {
                if (resultCode == Activity.RESULT_OK) startQuery(data.getStringExtra("result"));
                break;
            }
            default : {
                //getDataToSpinner();
                break;
            }
        }
       getDataToSpinner();
    }

    /*
    public void add(){
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        final View promptsView = li.inflate(R.layout.value, null);

        final EditText etName = (EditText) promptsView.findViewById(R.id.addETname);
        final EditText etValor = (EditText) promptsView.findViewById(R.id.addETvalor);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set dialog message
        alertDialogBuilder
                .setView(promptsView)
                .setCancelable(false)
                .setPositiveButton(this.getString(R.string.value_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                Client client = new Client();
                                client.setName(etName.getText().toString());
                                cliService.insert(client);
                                DebtorDao debService = new DebtorDao(MainActivity.this);
                                Debtor debtor = new Debtor();
                                debtor.setClient(etName.getText().toString());
                                debtor.setDebit(Double.parseDouble(etValor.getText().toString()));
                                debtor.setCredit(0.00);
                                debService.insert(debtor);
                            }
                        })
                .setNegativeButton(this.getString(R.string.value_cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
     */

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }
}
