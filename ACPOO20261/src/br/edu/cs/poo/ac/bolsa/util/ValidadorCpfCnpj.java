package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {
    public static ResultadoValidacao validarCpf(String cpf) {

        if (cpf == null || cpf.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        cpf = cpf.replaceAll("\\D", ""); // remove tudo que não é número

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (!validarDvCpf(cpf)) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    private static boolean validarDvCpf(String cpf) {
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int dv1 = 11 - (soma % 11);
            if (dv1 >= 10) dv1 = 0;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int dv2 = 11 - (soma % 11);
            if (dv2 >= 10) dv2 = 0;

            return dv1 == (cpf.charAt(9) - '0') &&
                   dv2 == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }
    public static ResultadoValidacao validarCnpj(String cnpj) {

        if (cnpj == null || cnpj.trim().isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        if (!validarDvCnpj(cnpj)) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    private static boolean validarDvCnpj(String cnpj) {
        try {
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * peso1[i];
            }

            int dv1 = soma % 11;
            dv1 = (dv1 < 2) ? 0 : 11 - dv1;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * peso2[i];
            }

            int dv2 = soma % 11;
            dv2 = (dv2 < 2) ? 0 : 11 - dv2;

            return dv1 == (cnpj.charAt(12) - '0') &&
                   dv2 == (cnpj.charAt(13) - '0');

        } catch (Exception e) {
            return false;
        }
    }
}