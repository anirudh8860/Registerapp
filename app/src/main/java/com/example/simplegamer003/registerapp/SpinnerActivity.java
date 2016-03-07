package com.example.simplegamer003.registerapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner spinner1;
    EditText editText;
    Button buttonSave;
    private static final String[] sports = {
            "Hockey","Cricket","Football","Basketball","Badminton","Tennis"
    };
    SharedPreferences prefs;
    final String KEY_SavedText = "Saved Text";
    final String SavedSel = "Saved Selection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        editText = (EditText)findViewById(R.id.edittext);
        buttonSave = (Button)findViewById(R.id.save);
        spinner1 = (Spinner)findViewById(R.id.drop_down);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,sports);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);


        prefs= getPreferences(MODE_PRIVATE);
        String prefsString = prefs.getString(KEY_SavedText, null);
        if(prefsString != null){
            editText.setText(prefsString);
        }
        int prefsInt1 = prefs.getInt(SavedSel, -1);
        if(prefsInt1 != -1){
            spinner1.setSelection(prefsInt1);
        }

        buttonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString(KEY_SavedText, editText.getText().toString());
                editor.commit();
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putInt(SavedSel, position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
