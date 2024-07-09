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

    public static Sexo fromLetra(char letra) {
        for (Sexo s : Sexo.values())
            if (s.getLetra() == letra)
                return s;
        return null;
    }
}
