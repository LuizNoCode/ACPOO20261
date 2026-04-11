package br.edu.cs.poo.ac.bolsa.negocio; // Agora com o nome correto!

// Imports necessários para "olhar" os outros pacotes
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.dao.DAOAtivo; 
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class AtivoMediator {

    // Se o seu arquivo na pasta DAO se chamar DAOAtivo, mude aqui para: private DAOAtivo dao = new DAOAtivo();
    private DAOAtivo dao = new DAOAtivo(); 

    private MensagensValidacao validar(Ativo ativo) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (ativo == null) {
            msgs.adicionar("O ativo não pode ser nulo.");
            return msgs;
        }

        if (ativo.getCodigo() <= 0) {
            msgs.adicionar("O código deve ser maior que zero.");
        }

        if (ativo.getDescricao() == null || ativo.getDescricao().trim().isEmpty()) {
            msgs.adicionar("A descrição é obrigatória e não pode estar em branco.");
        }

        if (ativo.getValorMinimoAplicacao() <= 0 || ativo.getValorMinimoAplicacao() > ativo.getValorMaximoAplicacao()) {
            msgs.adicionar("O valor mínimo deve ser maior que zero e menor ou igual ao valor máximo.");
        }

        if (ativo.getValorMaximoAplicacao() <= 0 || ativo.getValorMaximoAplicacao() < ativo.getValorMinimoAplicacao()) {
            msgs.adicionar("O valor máximo deve ser maior que zero e maior ou igual ao valor mínimo.");
        }

        if (ativo.getTaxaMensalMinima() < 0 || ativo.getTaxaMensalMinima() > ativo.getTaxaMensalMaxima()) {
            msgs.adicionar("A taxa mensal mínima deve ser maior/igual a zero e menor/igual à taxa máxima.");
        }

        if (ativo.getTaxaMensalMaxima() < 0 || ativo.getTaxaMensalMaxima() < ativo.getTaxaMensalMinima()) {
            msgs.adicionar("A taxa mensal máxima deve ser maior/igual a zero e maior/igual à taxa mínima.");
        }

        if (ativo.getFaixaMinimaPermitida() == null) {
            msgs.adicionar("A faixa mínima permitida é obrigatória.");
        }

        if (ativo.getPrazoEmMeses() <= 0) {
            msgs.adicionar("O prazo em meses deve ser maior que zero.");
        }

        return msgs;
    }

    public MensagensValidacao incluir(Ativo ativo) {
        MensagensValidacao msgs = validar(ativo);
        if (msgs.estaVazio()) {
            if (!dao.incluir(ativo)) {
                msgs.adicionar("Ativo já existente");
            }
        }
        return msgs;
    }

    public MensagensValidacao alterar(Ativo ativo) {
        MensagensValidacao msgs = validar(ativo);
        if (msgs.estaVazio()) {
            if (!dao.alterar(ativo)) {
                msgs.adicionar("Ativo não existente");
            }
        }
        return msgs;
    }

    public MensagensValidacao excluir(long codigo) {
        MensagensValidacao msgs = new MensagensValidacao();
        if (codigo <= 0) {
            msgs.adicionar("O código informado para exclusão deve ser maior que zero.");
        }
        if (msgs.estaVazio()) {
            if (!dao.excluir(codigo)) {
                msgs.adicionar("Ativo não existente");
            }
        }
        return msgs;
    }

    public Ativo buscar(long codigo) {
        if (codigo <= 0) return null;
        return dao.buscar(codigo);
    }
}
