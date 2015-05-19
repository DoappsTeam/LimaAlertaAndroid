package tesis.myapplicationproylimalert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class alerta extends Activity implements OnCompletionListener {
    private ImageView img;
    private LocationManager locManager;
    private String provider;
    String latitude = null;
    String longitud = null;
    String estado = "nada";
    String printedCode = "";
    private LocationListener locListener;
    Object response = "";
    String outputText = "";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    String ruta = "";
    ImageButton rec;
    ImageButton stop;
    ImageButton delete;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerta);

        rec = (ImageButton)findViewById(R.id.btnGrabar);
        stop = (ImageButton)findViewById(R.id.btnDetener);

        rec.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);
        //delete.setVisibility(View.INVISIBLE);

        LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> listaProviders = locManager.getAllProviders();
        LocationProvider provider = locManager.getProvider(listaProviders.get(0));

        Criteria req = new Criteria();
        req.setAccuracy(Criteria.ACCURACY_FINE);
        req.setAltitudeRequired(true);

        String mejorProviderCrit = locManager.getBestProvider(req, false);


        List<String> listaProvidersCrit = locManager.getProviders(req, false);
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                obtenerPosicion(location);
            }

            public void onProviderDisabled(String provider) {

            }

            public void onProviderEnabled(String provider) {

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };

        locManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 30000, 0, locListener);

        if (locManager.isProviderEnabled(mejorProviderCrit)) {
            Location location = locManager.getLastKnownLocation(mejorProviderCrit);
            obtenerPosicion(location);
        } else {
            estado = "error";
        }
    }

    public void capturarFoto(View view) {

        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        File imagesFolder = new File(
                Environment.getExternalStorageDirectory(), "LimAlert");
        imagesFolder.mkdirs();

        File image = new File(imagesFolder, "alerta.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        ruta = uriSavedImage.getPath();

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

        startActivityForResult(cameraIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

            } else if (resultCode == RESULT_CANCELED) {

            } else {

            }
        }
    }

    public void capturarAudio(View view) {
        rec.setVisibility(View.VISIBLE);
        stop.setVisibility(View.VISIBLE);
        rec.setEnabled(true);
        stop.setEnabled(false);
        //delete.setEnabled(false);
    }

    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory()
                .getPath());
        try {
            archivo = File.createTempFile("temporal", ".3gp", path);
        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();
        //tv1.setText("Grabando");
        rec.setEnabled(false);
        stop.setEnabled(true);
    }

    public void stop(View v) {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }
        rec.setEnabled(true);
        stop.setEnabled(false);
        //tv1.setText("Listo para reproducir");
    }
    public void onCompletion(MediaPlayer mp) {
        rec.setEnabled(true);
        stop.setEnabled(true);
        //tv1.setText("Listo");
    }

    public void alertaTercero(View view) {
        Intent i = new Intent(this, alertatercero.class);
        startActivity(i);
    }

    public void alertaSerenazgo(View view) {

        Intent i = new Intent(this, test.class);
        i.putExtra("latitude", latitude);
        i.putExtra("longitud", longitud);
        i.putExtra("estado", estado);
        i.putExtra("outputText", outputText);
        i.putExtra("ruta", ruta);
        startActivity(i);


    }

    private void obtenerPosicion(Location location) {
        if (location != null) {
            latitude = String.valueOf(location.getLatitude());
            longitud = String.valueOf(location.getLongitude());
            estado = "good";
            locateDistrito();
        } else {
            estado = "fail";
        }
    }

    public void locateDistrito() {
        String serverURL = "http://geocatmin.ingemmet.gob.pe/arcgis/rest/services/SERV_CARTOGRAFIA_DEMARCACION_WGS84/MapServer/2/query?f=json&returnGeometry=false&returnIdsOnly=false&returnCountOnly=false&returnZ=false&returnM=false" +
                "&returnDistinctValues=false&geometryType=esriGeometryPoint&geometry=" + longitud + "," + latitude + "";
        new QueryDistrito().execute(serverURL);
    }

    private class QueryDistrito extends AsyncTask<String, Void, Void> {


        protected Void doInBackground(String... urls) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet(urls[0]);
            ResponseHandler handler = new BasicResponseHandler();
            try {
                response = httpclient.execute(request, handler);
            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            httpclient.getConnectionManager().shutdown();
            return null;
        }

        protected void onPostExecute(Void unused) {
            try {
                if (!response.equals("")) {
                    Log.i("if1", "if1");
                    JSONObject jsonResponse = new JSONObject((String) response);
                    JSONObject attributes = jsonResponse.getJSONArray("features").getJSONObject(0).getJSONObject("attributes");
                    Log.d("attributes", attributes.toString());
                    printedCode = attributes.optString("NM_DIST");
                    if (printedCode.equals("null")) {
                        Log.i("if2", "if2");
                        return;
                    } else {
                        Log.i("if3", "if3");
                        outputText = printedCode;


                    }
                } else {
                    Log.i("if4", "if4");
                    Log.d("response", "empty response");
                }
            } catch (Exception ex) {
                Log.d("exception", ex.toString());
            }
        }
    }

    public void alertaBomberos(View view) {
        Intent i = new Intent(this, test.class);
        i.putExtra("latitude", latitude);
        i.putExtra("longitud", longitud);
        i.putExtra("estado", estado);
        i.putExtra("outputText", outputText);
        startActivity(i);
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


    private static File getOutputMediaFile(int type) {


        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void salir(View view) {
        System.exit(0);
    }

}
