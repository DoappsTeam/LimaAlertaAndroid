package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Soul on 19/04/2015.
 */
public class terminos extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminos);
        TextView acu = (TextView) findViewById(R.id.lblCondiciones);
        acu.setText(R.string.acuerdo);
    }

    public void regresar(View view) {
        Intent i = new Intent(this, activity_form.class);
        startActivity(i);
    }
}
