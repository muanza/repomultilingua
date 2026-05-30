package com.muanza.repomultilingua.facturacao.core.domain;

public class Empresa {
    private Long id;
    private String nome;
    private String nif;
    private String idiomaPadrao;
    private String zonaHoraria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getIdiomaPadrao() {
        return idiomaPadrao;
    }

    public void setIdiomaPadrao(String idiomaPadrao) {
        this.idiomaPadrao = idiomaPadrao;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }
}

