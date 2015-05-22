package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Soul on 19/04/2015.
 */
public class activity_form extends Activity {
    private TextView lblTituloForm;
    private Button btnEnviarRegistro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Spinner spinner = (Spinner) findViewById(R.id.sp_distrito);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.distritos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        lblTituloForm = (TextView) findViewById(R.id.lblTituloForm);
        lblTituloForm.setTypeface(FontUtil.setNeuropol(this));

        btnEnviarRegistro = (Button) findViewById(R.id.btnEnviarRegistro);
        btnEnviarRegistro.setTypeface(FontUtil.setJoystick(activity_form.this));

    }

    public void acuerdo(View view) {
        Intent i = new Intent(this, terminos.class);
        startActivity(i);
    }

    public void enviarRegistro(View view) {
        Intent i = new Intent(this, alerta.class);
        startActivity(i);
    }

    public void atras(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}
