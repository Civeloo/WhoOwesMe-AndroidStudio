package com.civeloo.whoowesme.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.civeloo.whoowesme.R;
import com.civeloo.whoowesme.data.DebtorDao;
import com.civeloo.whoowesme.logic.Debtor;

/**
 * Created by DeG on 3/12/13.
 */
public class SplashScreen extends Activity {
    private final int SPLASH_TIME = 5000;
    /** AL INICIO **/
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
		/*
		 * Handler que quitará el splash screen después del SPLASH_TIME y creará
		 * un intent de la actividad principal.
		 */
        new Handler().postDelayed(new Runnable() {
            // @Override
            public void run() {
				/*
				 * Creamos un Intent que lanzará nuestra Actividad principal (en
				 * nuestro caso Main.java)
				 */
                Intent miIntent = new Intent(SplashScreen.this,
                        MainActivity.class);
                SplashScreen.this.startActivity(miIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_TIME);
        /*
        Debtor debtor = new Debtor();
        DebtorDao debService;
        debService = new DebtorDao(this);
        debtor = debService.find();
        debtor.exist();
        */
    }
}
