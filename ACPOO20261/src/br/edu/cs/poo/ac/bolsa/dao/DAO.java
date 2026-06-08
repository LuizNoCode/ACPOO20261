package br.edu.cs.poo.ac.bolsa.dao;

import br.edu.cs.poo.ac.bolsa.util.ExcecaoObjetoJaExistente;
import br.edu.cs.poo.ac.bolsa.util.ExcecaoOobjetoNaoExistente;
import br.edu.cs.poo.ac.bolsa.util.Registro;
import java.lang.reflect.Array;
import java.io.Serializable;

public class DAO<T extends Registro> {
    
    private DAOGenerico base;
    private Class<T> tipo;

    public DAO(Class<T> tipo) {
        this.tipo = tipo;
        this.base = new DAOGenerico() {};
        this.base.inicializarCadastro(tipo);
    }

    public void incluir(T obj) throws ExcecaoObjetoJaExistente {
        if (buscar(obj.getIdentificador()) != null) {
            throw new ExcecaoObjetoJaExistente("Objeto já existente");
        }
        this.base.cadastro.incluir(obj, obj.getIdentificador());
    }

    public void alterar(T obj) throws ExcecaoOobjetoNaoExistente {
        if (buscar(obj.getIdentificador()) == null) {
            throw new ExcecaoOobjetoNaoExistente("Objeto não existente");
        }
        this.base.cadastro.alterar(obj, obj.getIdentificador());
    }

    public void excluir(String id) throws ExcecaoOobjetoNaoExistente {
        if (buscar(id) == null) {
            throw new ExcecaoOobjetoNaoExistente("Objeto não existente");
        }
        this.base.cadastro.excluir(id);
    }

    @SuppressWarnings("unchecked")
    public T buscar(String id) {
        return (T) this.base.cadastro.buscar(id);
    }

    @SuppressWarnings("unchecked")
    public T[] buscarTodos() {
        Serializable[] dados = this.base.cadastro.buscarTodos();
        if (dados == null) {
            return (T[]) Array.newInstance(tipo, 0);
        }
        T[] resultado = (T[]) Array.newInstance(tipo, dados.length);
        for (int i = 0; i < dados.length; i++) {
            resultado[i] = (T) dados[i];
        }
        return resultado;
    }
}