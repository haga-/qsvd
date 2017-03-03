package com.hut8.questionarioqsvd;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    MaterialBetterSpinner materialDesignSpinner;
    String[] spinnerList;
    EditText editTextNome, editTextIdade;
    SeekBar seekBar;
    RadioGroup radioGroup;
    Avaliador avaliador;

    final static int PERMISSAO_GRAVAR_ARQUIVO = 12;
    final static int LER_RESPOSTA_FORMULARIO = 13;
    static final int LER_RESPOSTA_ISHIHARA = 14;
    final static String AVALIACAO_DO_VIDEO = "avaliacao_do_video";
    static final String RESPOSTA_ISHIHARA = "resposta_ishihara";

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

                Log.d("Main", "onClick: Nome: " + editTextNome.getText().length() + " idade: " + editTextIdade.getText().length() +
                        " spinner: " + materialDesignSpinner.getText().toString().length() +
                        " radio: " + radioGroup.getCheckedRadioButtonId() + " seekbar: " + seekBar.getProgress());

                if(editTextNome.getText().length() > 0 &&
                        editTextIdade.getText().length() > 0 &&
                        materialDesignSpinner.getText().toString().length() >= 0 &&
                        radioGroup.getCheckedRadioButtonId() >= 0 &&
                        seekBar.getProgress() >= 0){
                    escolaridade = materialDesignSpinner.getText().toString();
                    sexo = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonF ? Avaliador.SEXO_FEMININO : Avaliador.SEXO_MASCULINO;
                    avaliador = new Avaliador(editTextNome.getText().toString(), editTextIdade.getText().toString(),escolaridade, sexo, seekBar.getProgress());


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(getResources().getString(R.string.texto_avaliacao))
                            .setPositiveButton("Começar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(MainActivity.this, AvaliacaoActivity.class);
                                    startActivityForResult(intent, LER_RESPOSTA_FORMULARIO);
                                }
                            })
                            .setNegativeButton("Espere", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os dados.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setVisibility(View.GONE);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Salvar dados no sd?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (ContextCompat.checkSelfPermission(MainActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            PERMISSAO_GRAVAR_ARQUIVO);
                                }
                                else {
                                    writeToFile();
                                }


                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    public void writeToFile(){
        try {
            File myFile = new File(Environment.getExternalStorageDirectory(), "notice_me.txt");
            FileOutputStream fOut;
            if (!myFile.exists()) {
                myFile.createNewFile();
                fOut = new FileOutputStream(myFile);
            }
            else {
                Log.d("write_to_file", "writeToFile: ja existe file");
                fOut = new FileOutputStream(myFile, true); //openFileOutput(myFile.getName(), Context.MODE_APPEND);
            }

            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut, "UTF-8");

            avaliador.writeToJSON(myOutWriter);

            myOutWriter.close();
            fOut.close();
            Toast.makeText(MainActivity.this, "Escreveu no SD", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSAO_GRAVAR_ARQUIVO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    writeToFile();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Main", "onOptionsItemSelected: ");

        materialDesignSpinner.setUnderlineColor(getResources().getColor(R.color.colorAccent));

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LER_RESPOSTA_FORMULARIO){
            if(resultCode == RESULT_OK){
                String[] avaliacoes = data.getStringArrayExtra(AVALIACAO_DO_VIDEO);
                for(String a : avaliacoes) {
                    Log.d("Main.OnResult", "onActivityResult: " + a);
                }
                 avaliador.setAvaliacoes(Arrays.copyOf(avaliacoes, avaliacoes.length));
                Log.d("main", "voltou no onresult");
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSAO_GRAVAR_ARQUIVO);
                }
                else {
                    writeToFile();
                }
            }
            editTextNome.setText("");
            editTextIdade.setText("");
            radioGroup.clearCheck();
            materialDesignSpinner.setText("");
            materialDesignSpinner.dismissDropDown();
            seekBar.setProgress(-1);
        }
        else if(requestCode == LER_RESPOSTA_ISHIHARA){
            if(resultCode == RESULT_OK){
                String resultadoIshihara = data.getStringExtra(RESPOSTA_ISHIHARA);
            }

        }
    }
}
