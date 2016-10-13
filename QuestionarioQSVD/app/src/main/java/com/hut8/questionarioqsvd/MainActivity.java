package com.hut8.questionarioqsvd;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.github.channguyen.rsv.RangeSliderView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MainActivity extends AppCompatActivity {

    MaterialBetterSpinner materialDesignSpinner;
    String[] SPINNERLIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SPINNERLIST = getResources().getStringArray(R.array.escolaridade);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        materialDesignSpinner = (MaterialBetterSpinner) findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);

        materialDesignSpinner.setUnderlineColor(getResources().getColor(R.color.colorAccent));
        materialDesignSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Main", "onClick: underline " + materialDesignSpinner.getUnderlineColor());
                materialDesignSpinner.setUnderlineColor(getResources().getColor(R.color.colorAccent));
                Log.i("Main", "onClick: underline " + materialDesignSpinner.getUnderlineColor());
            }
        });

        Button buttonProximo = (Button) findViewById(R.id.buttonProximo);
        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IshiharaActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Main", "onOptionsItemSelected: ");

        materialDesignSpinner.setUnderlineColor(getResources().getColor(R.color.colorAccent));

        return super.onOptionsItemSelected(item);
    }
}
