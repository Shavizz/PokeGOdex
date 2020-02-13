package com.example.PokeGOdex;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    TextView textView;

    SensorManager sensorManager;
    Sensor sensor;
    DatabaseAssist db;
    private MediaPlayer reproductor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView =(TextView) findViewById(R.id.textView2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        reproductor = MediaPlayer.create(this, R.raw.pikachu1);
        //reproductor.setLooping(true);



        db = new DatabaseAssist(this);
        mTextUsername = (EditText) findViewById(R.id.editText_username);
        mTextPassword = (EditText) findViewById(R.id.editText_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textView_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproductor.start();
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.validateUser(user, pwd);

                if (res == true) {
                    Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                    Intent loggedIn = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(loggedIn);
                } else {
                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reproductor.isPlaying()) {
            reproductor.stop();
            reproductor.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            textView.setText(""+event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
