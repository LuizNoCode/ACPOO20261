package br.edu.cs.poo.ac.bolsa.negocio;

import br.edu.cs.poo.ac.bolsa.dao.DAOAtivo;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class AtivoMediator {
	private static AtivoMediator instancia = new AtivoMediator();
    private DAOAtivo dao = new DAOAtivo();
    
	private MensagensValidacao validar(Ativo ativo) {
		MensagensValidacao msgs = new MensagensValidacao();
		
		if (ativo == null) {
			msgs.adicionar("Ativo é nulo.");
			return msgs;
		}
		
		if(ativo.getCodigo() <= 0) {
			msgs.adicionar("Código deve ser maior que zero.");
		}
		
		if (ativo.getDescricao() == null || ativo.getDescricao().isBlank()) {
			msgs.adicionar("Descrição é obrigatória.");
		}
		
		if (ativo.getValorMinimoAplicacao() <= 0 || 
				ativo.getValorMaximoAplicacao() > ativo.getValorMaximoAplicacao()) {
			msgs.adicionar("Valor minimo de aplicacao menor ou igual a zero, ou maior que o valor maximo para aplicacao.");
		}
		
		if (ativo.getValorMaximoAplicacao() <= 0 || 
				ativo.getValorMaximoAplicacao() < ativo.getValorMinimoAplicacao()) {
			msgs.adicionar("Valor maximo de aplicacao menor ou igual a zero, ou menor que o valor minimo de aplicacao;");
		}
		
		if (ativo.getTaxaMensalMinima() < 0 || ativo.getTaxaMensalMinima() > ativo.getTaxaMensalMaxima()) {
			msgs.adicionar("Taxa mensal minima menor que 0, ou maior que tava mensal maxima");
		}
		
		if (ativo.getFaixaMinimaPermitida() == null) {
			msgs.adicionar("Faixa minima permitida é nula.");
		}
		
		if (ativo.getPrazoEmMeses() <= 0) {
			msgs.adicionar("Prazo em meses menor ou igual a zero.");
		}
		
		return msgs;
	}
	
	public MensagensValidacao incluir(Ativo ativo) {
		MensagensValidacao msgs = validar(ativo);
		
		if (msgs.estaVazio() == true) {
			DAOAtivo dao = new DAOAtivo();
			boolean sucesso = dao.incluir(ativo);
			if (sucesso == false) {
				msgs.adicionar("Ativo já existente.");
			}
		}
		
		return msgs;
	}
	
	public MensagensValidacao alterar(Ativo ativo) {
		MensagensValidacao msgs = validar(ativo);
		
		if (msgs.estaVazio() == true) {
			DAOAtivo dao = new DAOAtivo();
			boolean sucesso = dao.alterar(ativo);
			if (sucesso == false) {
				msgs.adicionar("Ativo não existente.");
			}
		}
		
		return msgs;
	}
	
	public MensagensValidacao excluir(long codigo) {
		MensagensValidacao msgs = new MensagensValidacao();
		
		if (codigo <= 0) {
			msgs.adicionar("Código deve ser maior que zero.");
		}
		
		if (msgs.estaVazio() == true) {
			DAOAtivo dao = new DAOAtivo();
			boolean sucesso = dao.excluir(codigo);
			if (sucesso == false) {
				msgs.adicionar("Ativo não existente.");
			}
		}
		
		return msgs;
	}
	
	public Ativo buscar(long codigo) {
		if (codigo < 0) {
			return null;
		} else {
			DAOAtivo dao = new DAOAtivo();
			return dao.buscar(codigo);
		}
	}
	
	public static AtivoMediator getInstancia() {
        return instancia;
    }
}
