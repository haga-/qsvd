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

public class AvaliacaoActivity extends AppCompatActivity {
    RadioGroup radioGroup;

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
                    //finalizarActivity() ?
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, selecione uma das opções.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    /*
    void finalizaActitivity(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.AVALIACAO_DO_VIDEO,avaliacoes);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
    */

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
