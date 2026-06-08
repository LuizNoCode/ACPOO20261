package br.edu.cs.poo.ac.bolsa.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.edu.cs.poo.ac.bolsa.util.Comparavel;

public class InvestidorPessoa extends Investidor implements Comparavel {
    private String cpf;
    private double renda;
    private FaixaRenda faixaRenda;
    
    public InvestidorPessoa() {
        
    }
    
    public InvestidorPessoa(String nome, Endereco endereco, LocalDate dataNascimento, BigDecimal bonus, Contatos contatos,
            String cpf, double renda, FaixaRenda faixaRenda) {
        super(nome, endereco, dataNascimento, bonus, contatos);
        this.cpf = cpf;
        this.renda = renda;
        this.faixaRenda = faixaRenda;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public void setFaixaRenda(FaixaRenda faixaRenda) {
        this.faixaRenda = faixaRenda;
    }

    public String getCpf() {
        return cpf;
    }

    public double getRenda() {
        return renda;
    }

    public FaixaRenda getFaixaRenda() {
        return faixaRenda;
    }
    
    public LocalDate getDataNascimento() {
        return super.getDataCriacao();
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        super.setDataCriacao(dataNascimento);
    }

    @Override
    public int comparar(Comparavel comp) {
        if (!(comp instanceof InvestidorPessoa)) {
            throw new RuntimeException("O argumento nao e do tipo InvestidorPessoa");
        }
        InvestidorPessoa outro = (InvestidorPessoa) comp;
        return this.getNome().compareTo(outro.getNome());
    }

    @Override
    public String getIdentificador() {
        return this.cpf;
    }

    @Override
    public BigDecimal getEntradaFinanceira() {
        return BigDecimal.valueOf(this.renda);
    }
}