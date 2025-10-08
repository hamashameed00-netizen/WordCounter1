package com.example.wordcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Step 1: Declare the UI elements
    private EditText editTextInput;
    private Spinner spinnerMetric;
    private Button buttonCount;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Step 2: Connect this Java file with your XML layout
        setContentView(R.layout.activity_main);

        // Step 3: Link Java variables with XML elements (using their IDs)
        editTextInput = findViewById(R.id.editTextInput);
        spinnerMetric = findViewById(R.id.spinnerMetric);
        buttonCount = findViewById(R.id.buttonCount);
        textViewResult = findViewById(R.id.textViewResult);

        // Step 4: Populate the Spinner with options from strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.metrics_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetric.setAdapter(adapter);

        // Step 5: Set up what happens when the button is clicked
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Step 6: Get the text entered by the user
                String input = editTextInput.getText().toString();

                // Step 7: Validate that the input is not empty
                if (input.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.toast_empty_input, Toast.LENGTH_SHORT).show();
                    return; // stop here if no input
                }

                // Step 8: Determine which metric was selected in the Spinner
                int selectedPosition = spinnerMetric.getSelectedItemPosition();
                String metricName = getResources().getStringArray(R.array.metrics_array)[selectedPosition];

                // Step 9: Count according to the selected metric
                int countResult = 0;
                switch (selectedPosition) {
                    case 0: // Sentences
                        countResult = MetricsUtils.countSentencesNonRegex(input);
                        break;
                    case 1: // Words
                        countResult = MetricsUtils.countWordsWithRegex(input);
                        break;
                    case 2: // Characters
                        countResult = MetricsUtils.countCharactersNonRegex(input);
                        break;
                    case 3: // Numbers
                        countResult = MetricsUtils.countNumbersWithRegex(input);
                        break;
                }

                // Step 10: Show the result in the TextView
                String resultText = getString(R.string.result_format, metricName, countResult);
                textViewResult.setText(resultText);
            }
        });
    }
}
