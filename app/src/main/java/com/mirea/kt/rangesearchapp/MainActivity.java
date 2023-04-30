package com.mirea.kt.rangesearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etFrom;
    private EditText etTo;
    private EditText etDivider;
    private Button button;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);
        etDivider = findViewById(R.id.etDivider);
        button = findViewById(R.id.button);
        tvResult = findViewById(R.id.tvResult);

        button.setOnClickListener(x -> {
            Log.i("range_search_app", "Submit button clicked");

            String strFrom = etFrom.getText().toString();
            String strTo = etTo.getText().toString();
            String strDivider = etDivider.getText().toString();

            if (!strFrom.isEmpty() && !strTo.isEmpty() && !strDivider.isEmpty()) {
                try {
                    double from = Double.parseDouble(strFrom);
                    double to = Double.parseDouble(strTo);
                    double divider = Double.parseDouble(strDivider);

                    if (from > to) {
                        tvResult.setTextColor(Color.parseColor("#c90606"));
                        tvResult.setText("Ошибка! Начальная точка не может быть больше конечной!");
                    } else if (divider <= 0) {
                        tvResult.setTextColor(Color.parseColor("#c90606"));
                        tvResult.setText("Ошибка! Делитель должен быть больше нуля!");
                    } else {
                        StringBuilder builder = new StringBuilder();

                        for (double i=from; i<=to; i++) {
                            if (i % divider == 0) {
                                builder.append(i + ", ");
                            }
                            if (i == to && builder.length() != 0) {
                                builder.deleteCharAt(builder.length() - 1);
                                builder.deleteCharAt(builder.length() - 1);
                            }
                        }

                        if (builder.length() == 0) {
                            tvResult.setTextColor(Color.parseColor("#000000"));
                            tvResult.setText("В указанном промежутке нет таких делимых чисел");
                        } else {
                            tvResult.setTextColor(Color.parseColor("#000000"));
                            tvResult.setText(builder.toString());
                        }
                    }
                } catch (NumberFormatException e) {
                    tvResult.setTextColor(Color.parseColor("#c90606"));
                    tvResult.setText("Ошибка! Неверный формат данных!");
                }
            } else {
                tvResult.setTextColor(Color.parseColor("#c90606"));
                tvResult.setText("Ошибка! Все поля должны быть заполнены!");
            }
        });
    }
}