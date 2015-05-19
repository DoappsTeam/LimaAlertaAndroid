package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Soul on 19/04/2015.
 */
public class alertatercero extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertatercero);
        Spinner spinner = (Spinner) findViewById(R.id.sp_distrito);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.distritos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void alertaSerenazgo(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Alerta Enviada a la Central de " + "";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent i = new Intent(this, alerta.class);
        startActivity(i);
    }

    public void alertaBomberos(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Alerta Enviada a la Central de Bomberos";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent i = new Intent(this, alerta.class);
        startActivity(i);
    }

    public void salir(View view) {
        System.exit(0);
    }
}
