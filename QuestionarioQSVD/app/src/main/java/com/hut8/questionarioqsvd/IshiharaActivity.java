package com.hut8.questionarioqsvd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IshiharaActivity extends AppCompatActivity {

    final static int MARCOU_QUE_NAO_ENXERGOU = -1;
    final static int[] respostas_certas = new int[] {42, 12, 8, 5, 74, 2, 97, 5, 73};
    final static String stringTeste = "Teste número ";

    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishihara);

        final TextView labelTesteIshihara = (TextView) findViewById(R.id.labelTesteIshihara);
        final Button buttonProximo = (Button) findViewById(R.id.buttonProximoIshihara);
        final AppCompatEditText editText = (AppCompatEditText) findViewById(R.id.editTextIshihara);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxIshihara);
        final ImageView imageView = (ImageView) findViewById(R.id.imageIshihara);

        final int[] respostas_dadas = new int[MainActivity.N_AVALIACOES_ISHIHARA];

        final String[] imagens = new String[] { "ishihara0.png", "ishihara1.jpg", "ishihara2.jpg", "ishihara3.jpg",
                                                "ishihara4.jpg", "ishihara5.jpg", "ishihara6.jpg", "ishihara7.jpg", "ishihara8.jpg" };
        final int[] imagesId = new int[] { R.mipmap.ishihara0, R.mipmap.ishihara1, R.mipmap.ishihara2, R.mipmap.ishihara3, R.mipmap.ishihara4,
                                        R.mipmap.ishihara5, R.mipmap.ishihara6, R.mipmap.ishihara7, R.mipmap.ishihara8 };


        labelTesteIshihara.setText(stringTeste + String.valueOf(cont+1));
        imageView.setImageResource(imagesId[cont]);

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editText.getText().toString();
                if (texto.length() > 0 || checkBox.isChecked()) {
                    if (!checkBox.isChecked()) {
                        respostas_dadas[cont] = Integer.parseInt(texto);
                    }
                    else {
                        respostas_dadas[cont] = MARCOU_QUE_NAO_ENXERGOU;
                    }

                    ++cont;

                    if (cont == MainActivity.N_AVALIACOES_ISHIHARA){
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(MainActivity.RESPOSTAS_ISHIHARA, respostas_dadas);
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                    else{
                        labelTesteIshihara.setText(stringTeste + String.valueOf(cont+1));
                        imageView.setImageResource(imagesId[cont]);
                        editText.setText("");
                        if(checkBox.isChecked()){
                            checkBox.setChecked(false);
                            editText.setEnabled(true);
                        }
                        editText.requestFocus();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.validacaoIshihara), Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    editText.setEnabled(false);
                }
                else {
                    editText.setEnabled(true);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
