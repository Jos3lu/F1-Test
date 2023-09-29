package org.example.combustible;

import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativoException;

public class Combustible {
    private final String tipo;
    private final float litros;

    public Combustible(String tipo, float litros) throws LitrosNegativoException {
        esLitrosNegativo(litros);

        this.tipo = tipo;
        this.litros = litros;
    }

    private void esLitrosNegativo(float litros) throws LitrosNegativoException {
        if (litros < 0) throw new LitrosNegativoException();
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
        return tipo.equalsIgnoreCase("Diésel") || tipo.equalsIgnoreCase("Gasolina");
    }

    private boolean hayCombustibleNecesario(float combustibleNecesario) {
        return combustibleNecesario <= litros;
    }

}
