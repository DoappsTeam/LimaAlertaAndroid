package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends Activity {

    String dni;
    private TextView lblTituloForm;
    private Button btnIngresar;
    private Button btnNewUser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        lblTituloForm = (TextView) findViewById(R.id.lblLogin);
        lblTituloForm.setTypeface(FontUtil.setNeuropol(this));

        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setTypeface(FontUtil.setJoystick(Login.this));

        btnNewUser = (Button) findViewById(R.id.btnNewUser);
        btnNewUser.setTypeface(FontUtil.setJoystick(Login.this));
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

