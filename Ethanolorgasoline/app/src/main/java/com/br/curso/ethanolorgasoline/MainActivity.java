package com.br.curso.ethanolorgasoline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText ethanolEdit, gasolineEdit;
    private TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ethanolEdit = findViewById(R.id.ethanol_edit);
        gasolineEdit = findViewById(R.id.gasoline_edit);
        resultText = findViewById(R.id.result);
    }

    public void compute(View view) {
        String ethanolPrice = Objects.requireNonNull(ethanolEdit.getText()).toString();
        String gasolinePrice = Objects.requireNonNull(gasolineEdit.getText()).toString();
        if(!validateFields(ethanolPrice, gasolinePrice)) resultText.setText(R.string.field_error);
        if(validateFields(ethanolPrice, gasolinePrice)) {
            if((Double.parseDouble(ethanolPrice)/Double.parseDouble(gasolinePrice)) >= 0.7) {
                resultText.setText(R.string.result_gasoline);
            } else {
                resultText.setText(R.string.result_ethanol);
            }
        }

    }

    public Boolean validateFields(String ethanol, String gasoline) {
        return !Objects.isNull(ethanol) && !ethanol.equals("")
                && !Objects.isNull(gasoline) && !gasoline.equals("");
    }
}