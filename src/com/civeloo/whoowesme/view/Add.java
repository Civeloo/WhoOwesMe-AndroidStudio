package com.civeloo.whoowesme.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.civeloo.whoowesme.R;
import com.civeloo.whoowesme.data.ClientDao;
import com.civeloo.whoowesme.data.DebtorDao;
import com.civeloo.whoowesme.logic.Client;
import com.civeloo.whoowesme.logic.Debtor;

/**
 * Created by DeG on 3/12/13.
 */
public class Add extends Activity {
    EditText eNikName;
    Client client = new Client();
    Debtor debtor = new Debtor();
    ClientDao cliService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        cliService = new ClientDao(this);

        ImageButton bSave = (ImageButton) findViewById(R.id.addBsave);
        eNikName = (EditText) findViewById(R.id.addETname);
        final EditText eValor = (EditText) findViewById(R.id.addETvalor);


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eNikName.getText().toString().equals("")) {
                client.setName(eNikName.getText().toString());
                cliService.insert(client);
                DebtorDao debService = new DebtorDao(Add.this);
                debtor.setClient(eNikName.getText().toString());
                debtor.setDebit( !eValor.getText().toString().equals("") ? Double.parseDouble(eValor.getText().toString()) : 0 );
                debtor.setCredit(0.00);
                debService.insert(debtor);
                exit();
                }
            }
        });

    }

    public void exit() {
        // En la Activity llamada utilizamos esto para devolver el parametro
        Intent resultIntent;
        resultIntent = new Intent();
        Intent result = resultIntent.putExtra("result", eNikName.getText().toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
