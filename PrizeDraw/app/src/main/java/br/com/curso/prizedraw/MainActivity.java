package br.com.curso.prizedraw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void draw(View view) {
        Integer floor = getIntegerFromEditText(R.id.floor);
        Integer upper = getIntegerFromEditText(R.id.upper);
        Integer numberOfNumbers = getIntegerFromEditText(R.id.numberOfNumbers);
        TextView drawResultTextView = findViewById(R.id.result);

        LinkedList<Integer> drawnPool = new LinkedList<>();
        LinkedList<Integer> pool = createDrawPool(floor, upper);
        LinkedList<Integer> resultIntegers = drawNumber(numberOfNumbers, pool, drawnPool);

        formatResultView(drawResultTextView, resultIntegers);
    }

    private void formatResultView(TextView drawResultTextView, LinkedList<Integer> resultIntegers) {
        if(!resultIntegers.isEmpty()) {
            drawResultTextView.setText(formatToString(resultIntegers));
        }
        if(resultIntegers.isEmpty()) {
            drawResultTextView.setText(R.string.all_numbers_drawn);
        }
        if(drawResultTextView.getVisibility()==View.GONE)
            drawResultTextView.setVisibility(View.VISIBLE);
    }

    private LinkedList<Integer> createDrawPool(Integer floor, Integer upper) {
        LinkedList<Integer> drawPool = new LinkedList<>();
        for (int i =floor; i <= upper; i++) {
            drawPool.add(i);
        }
        return drawPool;
    }

    private String formatToString(Collection<Integer> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }


    private LinkedList<Integer> drawNumber(Integer numberOfNumbers,
                                           LinkedList<Integer> pool,
                                           LinkedList<Integer> drawnPool) {
        LinkedList<Integer> result = new LinkedList<>();
        if (!pool.isEmpty()) {
            for(int i = 0; i < numberOfNumbers; i++) {
                Random random = new Random();
                Integer randomIndex = random.nextInt(pool.size());
                drawnPool.add(pool.get(randomIndex));
                result.add(pool.get(randomIndex));
                pool.remove(randomIndex);
            }
        }
        return result;
    }

    @NonNull
    private Integer getIntegerFromEditText(Integer etId) {
        EditText et = findViewById(etId);
        String value= et.getText().toString();
        return Integer.parseInt(value);
    }
}