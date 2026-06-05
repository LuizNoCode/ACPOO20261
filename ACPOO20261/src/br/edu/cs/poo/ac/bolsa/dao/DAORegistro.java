package br.edu.cs.poo.ac.bolsa.dao;

import br.edu.cs.poo.ac.bolsa.util.Registro;

public class DAORegistro extends DAOGenerico {

    public DAORegistro(Class<?> classe) {
        inicializarCadastro(classe);
    }

    public Registro buscar(String codigo) {
        try {
            Object obj = cadastro.buscar(codigo);
            return (Registro) obj;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean incluir(Registro registro) {
        if (registro == null) {
            return false;
        }
        if (buscar(registro.getIdentificador()) != null) {
            return false;
        }
        try {
            cadastro.incluir(registro, registro.getIdentificador());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean alterar(Registro registro) {
        if (registro == null) {
            return false;
        }
        if (buscar(registro.getIdentificador()) == null) {
            return false;
        }
        try {
            cadastro.alterar(registro, registro.getIdentificador());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean excluir(String codigo) {
        if (buscar(codigo) == null) {
            return false;
        }
        try {
            cadastro.excluir(codigo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
