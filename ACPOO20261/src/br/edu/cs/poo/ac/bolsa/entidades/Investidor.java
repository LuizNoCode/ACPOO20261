package br.edu.cs.poo.ac.bolsa.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investidor {
    private String nome;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private BigDecimal bonus;
    private Contatos contatos;

    public Investidor(String nome, Endereco endereco, LocalDate dataCriacao, BigDecimal bonus, Contatos contatos) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = dataCriacao;
        this.bonus = bonus;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setContatos(Contatos contatos) {
        this.contatos = contatos;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    protected LocalDate getDataCriacao() {
        return dataCriacao;
    }

    protected void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }   

}
