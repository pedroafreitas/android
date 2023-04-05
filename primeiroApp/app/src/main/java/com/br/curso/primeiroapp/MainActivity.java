package com.br.curso.primeiroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void randomNumber(View view) {
        TextView mainTitle = findViewById(R.id.number);
        mainTitle.setText(generateRandomNumber(1,10).toString());
    }

    private Integer generateRandomNumber(Integer start, Integer end) {
        Random generator = new Random();
        return generator.nextInt(end-start+1)+start;
    }
}