package com.hut8.questionarioqsvd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AvaliacaoActivity extends AppCompatActivity {
    RadioGroup radioGroup;

    final static int N_AVALIACOES = 8;

    String avaliacoes[] = new String[N_AVALIACOES];
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupMOS);

        Button button = (Button) findViewById(R.id.buttonProximoIshihara);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();

                if(id != -1) {
                    View radioBttn = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioBttn);
                    RadioButton bttn = (RadioButton) radioGroup.getChildAt(radioId);
                    String avaliacao = (String) bttn.getText();

                    Log.i("Log", avaliacao);
                    //
                    avaliacoes[cont] = avaliacao;
                    ++cont;
                    Log.d("avaliacao", "onClick: "+ cont );

                    if( cont == N_AVALIACOES ){
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(MainActivity.AVALIACAO_DO_VIDEO, avaliacoes);
                        setResult(Activity.RESULT_OK, returnIntent);
                        Log.d("finaliza", "finalizaActivity: setou result");
                        finish();
                    }

                    //
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, selecione uma das opções.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
