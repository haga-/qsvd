package com.hut8.questionarioqsvd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class IshiharaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishihara);

        final Button buttonProximo = (Button) findViewById(R.id.buttonProximoIshihara);
        final AppCompatEditText editText = (AppCompatEditText) findViewById(R.id.editTextIshihara);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxIshihara);

        final int numeroNaImagem = 42;

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = editText.getText().toString();
                if (texto.length() > 0 || checkBox.isChecked()) {
                    if (!checkBox.isChecked()) {
                        boolean acertou = Integer.parseInt(texto) == numeroNaImagem;
                    }

                    Intent intent = new Intent(IshiharaActivity.this, AvaliacaoActivity.class);
                    startActivity(intent);
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
