package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Soul on 17/05/2015.
 */
public class portada extends Activity {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 5000; // 3 segundos
    private TextView lblTituloForm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.portada);

        lblTituloForm = (TextView) findViewById(R.id.textTilte);
        lblTituloForm.setTypeface(FontUtil.setNeuropol(this));

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(portada.this, Login.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}