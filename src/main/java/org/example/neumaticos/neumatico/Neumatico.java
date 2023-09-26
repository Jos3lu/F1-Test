package org.example.neumaticos.neumatico;

import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNegativoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNegativoException;

public class Neumatico {
    private final Marca marca;
    private final float porcentajeDeVida;

    public Neumatico(Marca marca, float porcentajeDeVida) throws PorcentajeDeVidaNegativoException {
        if (porcentajeDeVida < 0f) throw new PorcentajeDeVidaNegativoException();

        this.marca = marca;
        this.porcentajeDeVida = porcentajeDeVida;
    }

    public float getPorcentajeDeVida() {
        return porcentajeDeVida;
    }

    public Marca getMarca() {
        return marca;
    }

    public static void esPorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNegativo(float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido)
            throws PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNegativoException {
        if (porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido < 0f)
            throw new PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNegativoException();
    }

    public static boolean esMarcaValida(Marca marca) {
        return marca.equals(Marca.PIRELLI) || marca.equals(Marca.BRIDGESTONE);
    }

    public static boolean noSeDesgasta(float neumaticosDesgaste, float porcentajeDeVida) {
        return neumaticosDesgaste <= porcentajeDeVida;
    }

}
