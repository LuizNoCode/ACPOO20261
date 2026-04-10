package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {

    public static ResultadoValidacao validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        String cpfLimpo = cpf.replaceAll("\\D", "");

        if (cpfLimpo.length() != 11 || isTodosDigitosIguais(cpfLimpo)) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (!isCpfValido(cpfLimpo)) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    public static ResultadoValidacao validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        String cnpjLimpo = cnpj.replaceAll("\\D", "");

        if (cnpjLimpo.length() != 14 || isTodosDigitosIguais(cnpjLimpo)) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (!isCnpjValido(cnpjLimpo)) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    private static boolean isTodosDigitosIguais(String str) {
        char first = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != first) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCpfValido(String cpf) {
        int d1 = calcularDigito(cpf, 9, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2});
        int d2 = calcularDigito(cpf, 10, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});
        return (cpf.charAt(9) - '0' == d1) && (cpf.charAt(10) - '0' == d2);
    }

    private static boolean isCnpjValido(String cnpj) {
        int d1 = calcularDigito(cnpj, 12, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int d2 = calcularDigito(cnpj, 13, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        return (cnpj.charAt(12) - '0' == d1) && (cnpj.charAt(13) - '0' == d2);
    }

    private static int calcularDigito(String str, int tamanho, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += (str.charAt(i) - '0') * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
}