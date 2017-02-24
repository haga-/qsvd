package com.hut8.questionarioqsvd;


import android.util.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by erick on 13/10/16.
 */

public class Avaliador {
    public static final int SEXO_MASCULINO = 1;
    public static final int SEXO_FEMININO = 2;
    private String nome, idade, escolaridade;
    private int experiencia, respostaIshihara, sexo;
    private int avaliacoes[];

    public Avaliador(String nome, String idade, String escolaridade, int sexo, int experiencia) {
        this.nome = nome;
        this.idade = idade;
        this.escolaridade = escolaridade;
        this.sexo = sexo;
        this.experiencia = experiencia;
        // TODO
        avaliacoes = new int[30]; // quantas serão??
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public int getSexo() {
        return sexo;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getRespostaIshihara() {
        return respostaIshihara;
    }

    public void setRespostaIshihara(int respostaIshihara) {
        this.respostaIshihara = respostaIshihara;
    }

    public int[] getAvaliacoes() {
        return avaliacoes;
    }

    public void writeToJSON(OutputStreamWriter out) throws IOException {
        JsonWriter writer = new JsonWriter(out);
        writer.setIndent("  ");
        writer.beginObject();


        writer.name("nome").value(nome);
        writer.name("idade").value(idade);
        writer.name("escolaridade").value(escolaridade);
        writer.name("experiencia").value(experiencia);
        writer.name("respostaIshihara").value(respostaIshihara);
        writer.name("sexo").value(sexo == SEXO_FEMININO ? "F" : "M");

        writer.name("avaliacoes");
        writer.beginArray();
        for (int avaliacao : avaliacoes){
            writer.value(avaliacao);
        }
        writer.endArray();


        writer.endObject();
        writer.close();
    }

}
