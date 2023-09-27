package org.example;

import org.example.combustible.Combustible;
import org.example.combustible.exceptions.CombustibleConsumidoPorKmRecorridoNegativoException;
import org.example.combustible.exceptions.LitrosNegativosException;
import org.example.estrategia.Estrategia;
import org.example.estrategia.exceptions.KilometrosARecorrerNegativoException;
import org.example.neumaticos.Neumaticos;
import org.example.neumaticos.neumatico.Neumatico;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException;
import org.example.neumaticos.neumatico.exceptions.PorcentajeDeVidaNoValidoException;

import java.util.Arrays;

public class F1TestApplication {

    private static final float LITROS = 50f;
    private static final String TIPO = "Diésel";

    public static void main(String[] args) throws LitrosNegativosException,
            PorcentajeDeVidaNoValidoException, PorcentajeDeVidaDeNeumaticosConsumidoPorRecorridoNoValidoException,
            KilometrosARecorrerNegativoException, CombustibleConsumidoPorKmRecorridoNegativoException {
        Combustible combustible = new Combustible(TIPO, LITROS);
        float combustibleConsumidoPorKmRecorrido = 2f;

        Neumaticos neumaticos = new Neumaticos(Arrays.asList(
                new Neumatico("Bridgeston", 70f),
                new Neumatico("Bridgeston", 70f),
                new Neumatico("Bridgeston", 70f),
                new Neumatico("Bridgeston", 70f)
        ));

        float porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido = 2f;

        float kilometrosARecorrer = 20;

        Estrategia estrategia = new Estrategia(
                combustible,
                combustibleConsumidoPorKmRecorrido,
                neumaticos,
                porcentajeDeVidaDeNeumaticosConsumidoPorRecorrido,
                kilometrosARecorrer
        );

        boolean laEstrategiaEsViable = estrategia.esViable();
        System.out.println(laEstrategiaEsViable);
    }

}
