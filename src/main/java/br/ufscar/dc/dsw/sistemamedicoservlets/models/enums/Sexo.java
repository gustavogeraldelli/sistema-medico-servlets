package br.ufscar.dc.dsw.sistemamedicoservlets.models.enums;

public enum Sexo {
    MASCULINO('M'),
    FEMININO('F');

    private final char letra;

    Sexo(char letra) {
        this.letra = letra;
    }

    public char getLetra() {
        return letra;
    }
}
