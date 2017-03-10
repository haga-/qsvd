package com.hut8.questionarioqsvd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class AvaliacaoActivity extends AppCompatActivity {
    RadioGroup radioGroup;


    final static String stringAvalicao = "Avaliação número ";

    String avaliacoes[] = new String[MainActivity.N_AVALIACOES_VIDEO];
    final int[] ids_imagens = new int[] { R.mipmap.avaliacao0, R.mipmap.avaliacao1, R.mipmap.avaliacao2, R.mipmap.avaliacao3,
                                        R.mipmap.avaliacao4, R.mipmap.avaliacao5, R.mipmap.avaliacao6 };
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView imageView = (ImageView) findViewById(R.id.imageQuadroVideo);
        imageView.setImageResource(ids_imagens[cont]);

        final TextView labelAvaliacao = (TextView) findViewById(R.id.labelAvaliacao);
        labelAvaliacao.setText(stringAvalicao+String.valueOf(cont+1));

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
                    avaliacoes[cont] = avaliacao;
                    ++cont;
                    Log.d("avaliacao", "onClick: "+ cont );

                    if( cont == MainActivity.N_AVALIACOES_VIDEO ){
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(MainActivity.AVALIACAO_DO_VIDEO, avaliacoes);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                    else {
                        radioGroup.clearCheck();
                        labelAvaliacao.setText(stringAvalicao + String.valueOf(cont + 1));
                        if (cont % 2 == 0){ // se cont é par, ainda é a mesma imagem, mesmo vídeo, mas outra avaliação
                            imageView.setImageResource(ids_imagens[cont]);
                        }
                    }
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
