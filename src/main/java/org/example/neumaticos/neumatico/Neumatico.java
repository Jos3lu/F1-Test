package org.example.neumaticos.neumatico;

import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;

public class Neumatico {
    private final String marca;
    private final float porcentajeDeVida;

    public Neumatico(String marca, float porcentajeDeVida) throws PorcentajeDeVidaNoValidoException {
        if (porcentajeDeVida < 0f || porcentajeDeVida > 100f) throw new PorcentajeDeVidaNoValidoException();

        this.marca = marca;
        this.porcentajeDeVida = porcentajeDeVida;
    }

    public float getPorcentajeDeVida() {
        return porcentajeDeVida;
    }

    public String getMarca() {
        return marca;
    }

    public static void esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido)
            throws PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException {
        if (porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido < 0f ||
                porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido > 100f)
            throw new PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException();
    }

    public boolean esMarcaValida() {
        return marca.equalsIgnoreCase("Pirelli") || marca.equalsIgnoreCase("Bridgeston");
    }

    public boolean noSeDesgasta(float neumaticoDesgaste) {
        return neumaticoDesgaste <= porcentajeDeVida;
    }

}
