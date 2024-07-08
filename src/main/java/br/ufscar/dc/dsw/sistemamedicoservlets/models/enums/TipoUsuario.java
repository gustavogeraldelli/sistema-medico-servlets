package br.ufscar.dc.dsw.sistemamedicoservlets.models.enums;

public enum TipoUsuario {
    ADMIN(1),
    MEDICO(2),
    PACIENTE(3);

    private final int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
