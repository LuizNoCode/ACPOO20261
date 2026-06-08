package br.edu.cs.poo.ac.bolsa.negocio;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.edu.cs.poo.ac.bolsa.dao.DAO;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.entidade.Investidor;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.entidade.StatusTitulo;
import br.edu.cs.poo.ac.bolsa.entidade.Titulo;
import br.edu.cs.poo.ac.bolsa.util.ExcecaoNegocio;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class TituloMediator {
    
    private static TituloMediator instancia;
    private DAO<Titulo> daoTitulo;
    private AtivoMediator ativoMediator;
    private InvestidorMediator investidorMediator;

    private TituloMediator() {
        this.daoTitulo = new DAO<>(Titulo.class);
        this.ativoMediator = AtivoMediator.getInstancia();
        this.investidorMediator = new InvestidorMediator();
    }

    public static TituloMediator getInstancia() {
        if (instancia == null) {
            instancia = new TituloMediator();
        }
        return instancia;
    }

    public void incluir(DadosTitulo dados) throws ExcecaoNegocio {
        MensagensValidacao msgs = new MensagensValidacao();

        if (dados.getCpfOuCnpj() == null || dados.getCpfOuCnpj().trim().isEmpty()) {
            msgs.adicionar("CPF/CNPJ inválido");
        }
        if (dados.getCodigoAtivo() <= 0) {
            msgs.adicionar("Código do ativo inválido");
        }
        if (dados.getValorInvestido() == null) {
            msgs.adicionar("Valor investido não pode ser nulo");
        }
        if (dados.getTaxaDiaria() == null) {
            msgs.adicionar("Taxa diária não pode ser nula");
        }

        if (!msgs.estaVazio()) {
            throw new ExcecaoNegocio(msgs);
        }

        Ativo ativo = ativoMediator.buscar(dados.getCodigoAtivo());
        if (ativo == null) {
            msgs.adicionar("Ativo não encontrado");
            throw new ExcecaoNegocio(msgs);
        }

        Investidor investidor = investidorMediator.buscarInvestidor(dados.getCpfOuCnpj());
        if (investidor == null) {
            msgs.adicionar("Investidor não encontrado");
            throw new ExcecaoNegocio(msgs);
        }

        if (dados.getValorInvestido().compareTo(BigDecimal.valueOf(ativo.getValorMinimoAplicacao())) < 0 ||
            dados.getValorInvestido().compareTo(BigDecimal.valueOf(ativo.getValorMaximoAplicacao())) > 0) {
            msgs.adicionar("Valor investido fora da faixa permitida");
        }

        // Correção crucial: dividindo por 100 para transformar a taxa % em decimal antes do cálculo
        double taxaDiariaDecimal = dados.getTaxaDiaria().doubleValue() / 100.0;
        double taxaMensalDouble = 100 * (Math.pow(1 + taxaDiariaDecimal, 30) - 1);

        if (taxaMensalDouble < ativo.getTaxaMensalMinima() ||
            taxaMensalDouble > ativo.getTaxaMensalMaxima()) {
            msgs.adicionar("Taxa mensal fora da faixa permitida");
        }

        if (investidor instanceof InvestidorPessoa) {
            InvestidorPessoa ip = (InvestidorPessoa) investidor;
            if (ip.getFaixaRenda().ordinal() < ativo.getFaixaMinimaPermitida().ordinal()) {
                msgs.adicionar("Entrada financeira fora da faixa");
            }
        }

        if (!msgs.estaVazio()) {
            throw new ExcecaoNegocio(msgs);
        }

        Titulo titulo = new Titulo(
                investidor,
                ativo,
                dados.getValorInvestido(),
                dados.getValorInvestido(),
                dados.getTaxaDiaria(),
                LocalDate.now(),
                LocalDate.now().plusMonths(ativo.getPrazoEmMeses()),
                null,
                StatusTitulo.ATIVO
        );

        try {
            daoTitulo.incluir(titulo);
        } catch (Exception e) {
            msgs.adicionar("Erro interno: " + e.getMessage());
            throw new ExcecaoNegocio(msgs);
        }
    }

    public void processarRendimentos() {
        Titulo[] titulos = daoTitulo.buscarTodos();
        if (titulos == null) return;
        
        for (Titulo t : titulos) {
            if (t.render()) {
                BigDecimal dif = t.getValorAtual().subtract(t.getValorInvestido());
                BigDecimal bonus = dif.multiply(new BigDecimal("0.0001"));
                
                Investidor inv = investidorMediator.buscarInvestidor(t.getInvestidor().getIdentificador());
                if (inv != null) {
                    inv.creditarBonus(bonus);
                    investidorMediator.alterarInvestidor(inv);
                }
            }
            
            if (!t.getDataVencimento().isAfter(LocalDate.now())) {
                t.setStatus(StatusTitulo.VENCIDO);
            }
            
            try {
                daoTitulo.alterar(t);
            } catch (Exception e) {
            }
        }
    }

    public void cancelarTitulo(String numero) throws ExcecaoNegocio {
        MensagensValidacao msgs = new MensagensValidacao();
        Titulo t = daoTitulo.buscar(numero);

        if (t == null) {
            msgs.adicionar("Título não encontrado");
            throw new ExcecaoNegocio(msgs);
        }

        if (t.getStatus() == StatusTitulo.VENCIDO || t.getStatus() == StatusTitulo.CANCELADO) {
            msgs.adicionar("Título não pode ser cancelado");
            throw new ExcecaoNegocio(msgs);
        }

        t.setStatus(StatusTitulo.CANCELADO);
        try {
            daoTitulo.alterar(t);
        } catch (Exception e) {
            msgs.adicionar("Erro interno: " + e.getMessage());
            throw new ExcecaoNegocio(msgs);
        }

        Investidor inv = investidorMediator.buscarInvestidor(t.getInvestidor().getIdentificador());
        if (inv != null) {
            BigDecimal debito = inv.getBonus().multiply(new BigDecimal("0.70"));
            inv.debitarBonus(debito);
            investidorMediator.alterarInvestidor(inv);
        }
    }
}