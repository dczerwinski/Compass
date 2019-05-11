package com.example.kompas;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

     private Sensor magnetic_sensor;
    private SensorManager sensorManager;
    private ImageView  wskazowka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        magnetic_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        wskazowka = findViewById(R.id.imageView3);
        if (magnetic_sensor != null) {
            sensorManager.registerListener(MainActivity.this, magnetic_sensor,SensorManager.SENSOR_DELAY_NORMAL);
            Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");
        } else {
            Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
            Toast.makeText(this, "ORIENTATION Sensor not found",
                    Toast.LENGTH_LONG).show();
            finish();
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        wskazowka.setRotation(-event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
