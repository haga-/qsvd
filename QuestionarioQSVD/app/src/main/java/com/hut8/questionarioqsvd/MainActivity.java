package com.hut8.questionarioqsvd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class MainActivity extends AppCompatActivity {

    MaterialBetterSpinner materialDesignSpinner;
    String[] spinnerList;
    EditText editTextNome, editTextIdade;
    SeekBar seekBar;
    RadioGroup radioGroup;
    Avaliador avaliador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerList = getResources().getStringArray(R.array.escolaridade);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinnerList);
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

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexo);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        Button buttonProximo = (Button) findViewById(R.id.buttonProximo);
        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, idade, escolaridade;
                int sexo, experiencia;

                if(editTextNome.getText().length() > 0 &&
                        editTextIdade.getText().length() > 0 &&
                        materialDesignSpinner.getListSelection() >= 0 &&
                        radioGroup.getCheckedRadioButtonId() >= 0 &&
                        seekBar.getProgress() > 0){
                    escolaridade = spinnerList[materialDesignSpinner.getListSelection()];
                    sexo = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonF ? Avaliador.SEXO_FEMININO : Avaliador.SEXO_MASCULINO;
                    avaliador = new Avaliador(editTextNome.getText().toString(), editTextIdade.getText().toString(),escolaridade, sexo, seekBar.getProgress());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os dados.", Toast.LENGTH_SHORT).show();
                }
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
