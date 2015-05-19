package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends Activity {

    String dni;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void registro(View view) {
        Intent i = new Intent(this, activity_form.class);
        startActivity(i);
    }

    public void inicio(View view) {
        Intent i = new Intent(this, alerta.class);
        startActivity(i);
    }

    public void salir(View view) {
        System.exit(0);
    }
}

