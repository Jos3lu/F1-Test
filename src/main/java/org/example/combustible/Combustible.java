package org.example.combustible;

import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativosException;

public class Combustible {
    private final String tipo;
    private final float litros;

    public Combustible(String tipo, float litros) throws LitrosNegativosException {
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

    public boolean esTipoValido() {
        return tipo.equalsIgnoreCase("Diésel") || tipo.equalsIgnoreCase("Gasolina");
    }

    public boolean hayCombustibleNecesario(float combustibleNecesario) {
        return combustibleNecesario <= litros;
    }

}
