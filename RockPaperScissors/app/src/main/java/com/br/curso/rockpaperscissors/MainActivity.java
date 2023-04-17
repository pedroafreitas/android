package com.br.curso.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        Integer systemChoice = new Random().nextInt(3);
        TextView resultText = findViewById(R.id.result_text);
        resultText.setText(R.string.loading);
        Handler handler = new Handler();
        GameResultEnum result = playerHasWon(view, systemChoice);
        handler.postDelayed(() -> showGameResult(resultText, result),1000);
    }

    private void showGameResult(TextView resultText, GameResultEnum result) {
        if(result.equals(GameResultEnum.WIN)) {
            resultText.setText(R.string.win_text);
            resultText.setVisibility(View.VISIBLE);
        }
        if(result.equals(GameResultEnum.LOST)) {
            resultText.setText(R.string.lose_text);
            resultText.setVisibility(View.VISIBLE);
        }
        if(result.equals(GameResultEnum.DRAW)) {
            resultText.setText(R.string.draw_text);
            resultText.setVisibility(View.VISIBLE);
        }
    }

    private GameResultEnum playerHasWon(View view, Integer systemChoice) {
        ImageView resultImg = findViewById(R.id.result_img);
        if(systemChoice.equals(GameChoicesEnum.ROCK.getId())) {
            resultImg.setImageResource(R.drawable.rock);
            if(Objects.equals(view.getId(), R.id.paper_img)) return GameResultEnum.WIN;
            if(checkDraw(R.id.rock_img, view)) return GameResultEnum.DRAW;
            return GameResultEnum.LOST;
        }
        if(systemChoice.equals(GameChoicesEnum.PAPER.getId())) {
            resultImg.setImageResource(R.drawable.paper);
            if(Objects.equals(view.getId(), R.id.scissors_img)) return GameResultEnum.WIN;
            if(checkDraw(R.id.paper_img, view)) return GameResultEnum.DRAW;
            return GameResultEnum.LOST;
        }
        if(systemChoice.equals(GameChoicesEnum.SCISSORS.getId())) {
            resultImg.setImageResource(R.drawable.scissors);
            if(Objects.equals(view.getId(), R.id.rock_img)) return GameResultEnum.WIN;
            if(checkDraw(R.id.scissors_img, view)) return GameResultEnum.DRAW;
            return GameResultEnum.LOST;
        }
        return GameResultEnum.DRAW;
    }

    private Boolean checkDraw(Integer op, View view) {
        return op == view.getId();
    }
}