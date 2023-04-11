package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements  SensorEventListener{

    TextView textview,textview2,textview3;
    ToggleButton toggle,toggle2,toggle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = findViewById(R.id.toggleButton1);
        toggle2 = findViewById(R.id.toggleButton2);
        toggle3 = findViewById(R.id.toggleButton3);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle.isChecked()){

                    if(sensorManager!=null){
                        Sensor proxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

                        if(proxi!=null){
                            sensorManager.registerListener( MainActivity.this,proxi,sensorManager.SENSOR_DELAY_NORMAL);
                        }
                    }
                }
                else {
                    Sensor proxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                    sensorManager.unregisterListener(MainActivity.this, proxi);
                    textview = findViewById(R.id.textview);
                    textview.setText("Off hai ye bhi");
                }
            }
        });
        toggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle2.isChecked()){

                    if(sensorManager!=null){
                        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

                        if(lightSensor!=null){
                            sensorManager.registerListener( MainActivity.this ,lightSensor,sensorManager.SENSOR_DELAY_NORMAL);
                        }
                    }
                }
                else {
                    Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    sensorManager.unregisterListener(MainActivity.this, lightSensor);
                    textview2 = findViewById(R.id.textview2);
                    textview2.setText("Off  hai abhi");
                }
            }
        });
        toggle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview3 = findViewById(R.id.textview7);
                if(toggle3.isChecked()){

                    if(sensorManager!=null){
                        Sensor geosensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);

                        if(geosensor!=null){
                            sensorManager.registerListener( MainActivity.this ,geosensor,sensorManager.SENSOR_DELAY_NORMAL);
                        }
                    }
                }
                else {
                    Sensor geosensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
                    sensorManager.unregisterListener(MainActivity.this, geosensor);
                    textview3.setText("off");
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            textview2 = findViewById(R.id.textview2);
            textview2.setText("Value : "+ sensorEvent.values[0]);
        } else if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY) {
            textview = findViewById(R.id.textview);
            textview.setText("Value : "+ sensorEvent.values[0]);
        } else if (sensorEvent.sensor.getType()==Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            float[] orientation = new float[3];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);
//            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            SensorManager.getOrientation(rotationMatrix, orientation);
            float azimuthInDegrees = (float) Math.toDegrees(orientation[0]);
            float pitchInDegrees = (float) Math.toDegrees(orientation[1]);
            float rollInDegrees = (float) Math.toDegrees(orientation[2]);

            String orientationString = String.format("Azimuth: %.2f°\nPitch: %.2f°\nRoll: %.2f°", azimuthInDegrees, pitchInDegrees, rollInDegrees);

            textview3 = findViewById(R.id.textview7);
//            textview3.setText("Value : "+ sensorEvent.values[0]);

            textview3.setText(orientationString);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}