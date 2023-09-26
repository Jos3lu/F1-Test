package org.example.neumaticos.neumatico;

import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;

public class Neumatico {
    private final Marca marca;
    private final float porcentajeDeVida;

    public Neumatico(Marca marca, float porcentajeDeVida) throws PorcentajeDeVidaNoValidoException {
        if (porcentajeDeVida < 0f || porcentajeDeVida > 100f) throw new PorcentajeDeVidaNoValidoException();

        this.marca = marca;
        this.porcentajeDeVida = porcentajeDeVida;
    }

    public float getPorcentajeDeVida() {
        return porcentajeDeVida;
    }

    public Marca getMarca() {
        return marca;
    }

    public static void esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValido(float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido)
            throws PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException {
        if (porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido < 0f ||
                porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido > 100f)
            throw new PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException();
    }

    public static boolean esMarcaValida(Marca marca) {
        return marca.equals(Marca.PIRELLI) || marca.equals(Marca.BRIDGESTONE);
    }

    public static boolean noSeDesgasta(float neumaticosDesgaste, float porcentajeDeVida) {
        return neumaticosDesgaste <= porcentajeDeVida;
    }

}
