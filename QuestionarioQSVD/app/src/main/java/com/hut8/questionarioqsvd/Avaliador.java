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
    private String avaliacoes[];

    public Avaliador(String nome, String idade, String escolaridade, int sexo, int experiencia) {
        this.nome = nome;
        this.idade = idade;
        this.escolaridade = escolaridade;
        this.sexo = sexo;
        this.experiencia = experiencia;
        // TODO
        avaliacoes = new String[8]; // quantas serão??
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

    public String[] getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(String[] avaliacoes) {
        this.avaliacoes = avaliacoes;
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
        for (String avaliacao : avaliacoes){
            writer.value(avaliacao);
        }
        writer.endArray();


        writer.endObject();
        // TODO : Isso não deveria estar aqui.
        // Se for ser utilizado pra uma lista de avaliadores, um outro método que escreve json deve chamar este e criar um outro objeto(JSON) lá
        out.write("\n");
        writer.close();
    }
}
