package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class test extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        TextView l = (TextView) findViewById(R.id.textView);
        TextView lo = (TextView) findViewById(R.id.textView2);
        TextView e = (TextView) findViewById(R.id.textView3);
        TextView p = (TextView) findViewById(R.id.textView4);
        ImageView u = (ImageView) findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();
        l.setText(bundle.getString("latitude"));
        lo.setText(bundle.getString("longitud"));
        e.setText(bundle.getString("estado"));
        p.setText(bundle.getString("outputText"));

    }

    public void regresar(View view) {
        Intent i = new Intent(this, activity_form.class);
        startActivity(i);
    }
}