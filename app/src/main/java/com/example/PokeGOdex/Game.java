package com.example.PokeGOdex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    Button juego,poke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        juego = (Button) findViewById(R.id.game);
        poke = (Button) findViewById(R.id.poke);

        juego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loggedIn = new Intent(Game.this, Jugar.class);
                startActivity(loggedIn);
            }
        });

        poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loggedIn = new Intent(Game.this, MainActivity.class);
                startActivity(loggedIn);
            }
        });


    }

}
