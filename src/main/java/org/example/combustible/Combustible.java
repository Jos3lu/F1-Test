package org.example.combustible;

import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativosException;

public class Combustible {
    private final Tipo tipo;
    private final int litros;

    public Combustible(Tipo tipo, int litros) throws LitrosNegativosException {
        if (litros < 0) throw new LitrosNegativosException();

        this.tipo = tipo;
        this.litros = litros;
    }

    public static void esCombustibleConsumidoPorKmRecorridoNegativo(float combustibleConsumidoPorKmRecorrido)
            throws CombustibleConsumidoPorKmRecorridoNegativoException {
        if (combustibleConsumidoPorKmRecorrido < 0f)
            throw new CombustibleConsumidoPorKmRecorridoNegativoException();
    }

    public boolean esValido(float combustibleNecesario) {
        return esTipoValido() && hayCombustibleNecesario(combustibleNecesario);
    }

    private boolean esTipoValido() {
        return tipo.equals(Tipo.DIESEL) || tipo.equals(Tipo.GASOLINA);
    }

    private boolean hayCombustibleNecesario(float combustibleNecesario) {
        return combustibleNecesario <= litros;
    }

}
